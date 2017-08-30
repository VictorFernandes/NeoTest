package com.voidcorp.neon.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.voidcorp.neon.R;
import com.voidcorp.neon.databinding.FragProfileBinding;
import com.voidcorp.neon.viewmodel.ProfileViewModel;

import javax.inject.Inject;

public class FragProfile extends BaseFragment implements ActMain.ChildViewContract {

    @Inject
    ProfileViewModel mViewModel;

    private FragProfileBinding mBinding;

    @Inject
    public FragProfile() {

    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        getFragmentComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        mBinding = FragProfileBinding.inflate(inflater, container, false);
        mBinding.setModel(mViewModel);
        return mBinding.getRoot();
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        mViewModel.load();
    }

    @Override
    public int getTitle() {
        return R.string.profile;
    }
}
