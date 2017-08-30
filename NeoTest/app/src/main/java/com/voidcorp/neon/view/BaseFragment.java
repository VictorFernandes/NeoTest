package com.voidcorp.neon.view;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.voidcorp.neon.NeoTestApp;
import com.voidcorp.neon.inject.component.DaggerConfigPersistentComponent;
import com.voidcorp.neon.inject.component.FragmentComponent;
import com.voidcorp.neon.inject.module.DataModule;
import com.voidcorp.neon.inject.module.FragmentModule;

public class BaseFragment extends Fragment {

    private FragmentComponent mFragmentComponent;

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        mFragmentComponent = DaggerConfigPersistentComponent.builder()
                .applicationComponent(getNeoTest().getComponent())
                .dataModule(new DataModule(getNeoTest()))
                .build()
                .fragmentComponent(new FragmentModule(this));
    }

    protected final FragmentComponent getFragmentComponent() {
        return mFragmentComponent;
    }

    public NeoTestApp getNeoTest() {
        if(getActivity() != null) {
            return (NeoTestApp) getActivity().getApplication();
        } else if(getContext() != null) {
            return (NeoTestApp) getContext().getApplicationContext();
        } else {
            return null;
        }
    }

}
