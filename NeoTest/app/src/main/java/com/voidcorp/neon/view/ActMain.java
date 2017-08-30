package com.voidcorp.neon.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;

import com.voidcorp.neon.R;
import com.voidcorp.neon.databinding.ActMainBinding;

import javax.inject.Inject;

public class ActMain extends BaseActivity {

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnMenuItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull final MenuItem item) {
            final int id = item.getItemId();
            switch (id) {
                case R.id.action_history:
                    navigate(mFragHistory);
                    break;
                case R.id.action_send:
                    navigate(mFragSendTo);
                    break;
                default:
                    navigate(mFragProfile);
                    break;
            }
            return true;
        }
    };

    @Inject
    FragProfile mFragProfile;

    @Inject
    FragSendTo mFragSendTo;

    @Inject
    FragHistory mFragHistory;

    private ActMainBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_main);
        getActivityComponent().inject(this);
        setSupportActionBar(mBinding.toolbar);
        mBinding.menu.setOnNavigationItemSelectedListener(mOnMenuItemSelectedListener);
        mBinding.menu.setSelectedItemId(R.id.action_profile);
    }

    private void navigate(final ChildViewContract childView) {
        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, (Fragment) childView);
        transaction.commitAllowingStateLoss();
        getSupportActionBar().setTitle(childView.getTitle());
    }

    interface ChildViewContract {

        int getTitle();

    }

}
