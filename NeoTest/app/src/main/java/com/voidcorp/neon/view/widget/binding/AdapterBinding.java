package com.voidcorp.neon.view.widget.binding;

import android.databinding.BindingAdapter;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.data.BarData;

import java.util.List;

public class AdapterBinding {

    @BindingAdapter("items")
    public static void notifyDataSetChanged(final RecyclerView view, final List<?> list) {
        if (view != null) {
            final RecyclerView.Adapter adapter = view.getAdapter();
            if (adapter != null) {
                adapter.notifyDataSetChanged();
            }
        }
    }

    @BindingAdapter("item")
    public static void notifyItemInserted(final RecyclerView view, final ArrayMap<?, ?> list) {
        if (view != null) {
            final RecyclerView.Adapter adapter = view.getAdapter();
            if (adapter != null) {
                adapter.notifyItemInserted(list.size());
            }
        }
    }

    @BindingAdapter("item")
    public static void notifyItemInserted(final RecyclerView view, final List<?> list) {
        if (view != null) {
            final RecyclerView.Adapter adapter = view.getAdapter();
            if (adapter != null) {
                adapter.notifyItemInserted(list.size());
            }
        }
    }

    @BindingAdapter("barData")
    public static void generateGraph(final HorizontalBarChart view, final BarData data) {
        if(view != null && data != null) {
            view.setData(data);
            view.invalidate();
            view.animateY(500);
        }
    }

}
