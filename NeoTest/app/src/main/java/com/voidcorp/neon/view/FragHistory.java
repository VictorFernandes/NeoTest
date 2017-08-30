package com.voidcorp.neon.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.voidcorp.neon.R;
import com.voidcorp.neon.databinding.FragHistoryBinding;
import com.voidcorp.neon.view.adapter.AdpContact;
import com.voidcorp.neon.view.util.RecyclerViewItemClickSupport;
import com.voidcorp.neon.viewmodel.HistoryViewModel;

import javax.inject.Inject;

public class FragHistory extends BaseFragment implements ActMain.ChildViewContract {

    private final OnChartValueSelectedListener mOnChartValueSelectedListener = new OnChartValueSelectedListener() {
        @Override
        public void onValueSelected(final Entry e, final Highlight h) {
            final int position = mViewModel.findPositionFor(e.getX());
            if (position > -1) {
                mBinding.contacts.scrollToPosition(position);
            }
        }

        @Override
        public void onNothingSelected() {

        }
    };

    private final RecyclerViewItemClickSupport.OnItemClickListener mOnContactClickListener = new RecyclerViewItemClickSupport.OnItemClickListener() {
        @Override
        public void onItemClicked(final RecyclerView recyclerView, final int position, final View v) {
            mBinding.historyGraph.highlightValue(-1, 0);
            if(mViewModel.isValid(position)) {
                mBinding.historyGraph.highlightValue(position, 0);
                mBinding.historyScroller.scrollTo(0, 0);
            }
        }
    };


    @Inject
    HistoryViewModel mViewModel;

    @Inject
    AdpContact mAdapter;

    private FragHistoryBinding mBinding;

    @Inject
    public FragHistory() {

    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        getFragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        mBinding = FragHistoryBinding.inflate(inflater, container, false);
        mBinding.setModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        mAdapter.setViewModel(mViewModel);
        mBinding.contacts.setAdapter(mAdapter);
        mBinding.contacts.setNestedScrollingEnabled(false);
        RecyclerViewItemClickSupport.addTo(mBinding.contacts).setOnItemClickListener(mOnContactClickListener);
        initializeGraph();
        mViewModel.load();
    }

    private void initializeGraph() {
        mBinding.historyGraph.setAutoScaleMinMaxEnabled(true);
        mBinding.historyGraph.getLegend().setEnabled(false);
        mBinding.historyGraph.setScaleEnabled(false);
        mBinding.historyGraph.setHighlightFullBarEnabled(false);
        mBinding.historyGraph.setDoubleTapToZoomEnabled(false);
        mBinding.historyGraph.setOnChartValueSelectedListener(mOnChartValueSelectedListener);
        mBinding.historyGraph.setTouchEnabled(false);

        mBinding.historyGraph.getDescription().setEnabled(false);

        final YAxis axisRight = mBinding.historyGraph.getAxisRight();
        axisRight.setEnabled(false);

        final YAxis axisLeft = mBinding.historyGraph.getAxisLeft();
        axisLeft.setEnabled(false);

        final XAxis xAxis = mBinding.historyGraph.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.boulder));

        mViewModel.setDataSetColor(ContextCompat.getColor(getContext(), R.color.dodger_blue));
    }

    @Override
    public int getTitle() {
        return R.string.history;
    }

}
