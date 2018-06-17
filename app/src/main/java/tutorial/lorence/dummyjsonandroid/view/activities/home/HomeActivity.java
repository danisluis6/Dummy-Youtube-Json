package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;
import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.app.Application;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Schedule;
import tutorial.lorence.dummyjsonandroid.di.module.HomeModule;
import tutorial.lorence.dummyjsonandroid.service.JsonData;
import tutorial.lorence.dummyjsonandroid.service.asyntask.DownloadImage;
import tutorial.lorence.dummyjsonandroid.view.activities.BaseActivity;
import tutorial.lorence.dummyjsonandroid.view.activities.home.adapter.ViewPaperAdapter;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomeActivity extends BaseActivity implements HomeView, MaterialTabListener {

    @Inject
    Context mContext;

    @Inject
    FragmentTransaction mFragmentTransaction;

    @Inject
    JsonData mJsonData;

    @Inject
    HomePresenter mHomePresenter;

    @Inject
    DownloadImage mDownloadImage;

    @Inject
    ViewPaperAdapter mPaperAdapter;


    @BindView(R.id.pager)
    ViewPager mViewPager;

    @BindView(R.id.tabHost)
    MaterialTabHost mTabHost;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    private Disposable mDisposable;

    @Override
    public void distributedDaggerComponents() {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule(this, this))
                .inject(this);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAttributes(Bundle savedInstanceState) {
        super.initAttributes(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            this.getWindow().setStatusBarColor(ContextCompat.getColor(HomeActivity.this, R.color.home_statusbar_color));
        }
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        mViewPager.setAdapter(mPaperAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabHost.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        for (int index = 0; index < mPaperAdapter.getCount(); index++) {
            mTabHost.addTab(mTabHost.newTab()
                    .setIcon(this.getResources()
                            .getDrawable(getResId(index)))
                    .setTabListener(this));
        }
    }

    private int getResId(int index) {
        if (index == 0) {
            return R.drawable.ic_calendar;
        } else if (index == 1) {
            return R.drawable.ic_result;
        } else if (index == 2) {
            return R.drawable.ic_team;
        }
        return 0;
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onGetItemsSuccess(ArrayList<Schedule> items) {
    }

    @Override
    public void onGetItemsFailure(String message) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }

    @Override
    public void onTabSelected(MaterialTab tab) {
        // TODO
    }

    @Override
    public void onTabReselected(MaterialTab tab) {
        // TODO
    }

    @Override
    public void onTabUnselected(MaterialTab tab) {
        // TODO
    }
}
