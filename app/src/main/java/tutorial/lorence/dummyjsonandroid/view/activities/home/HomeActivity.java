package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.app.Application;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Item;
import tutorial.lorence.dummyjsonandroid.di.module.HomeModule;
import tutorial.lorence.dummyjsonandroid.service.JsonData;
import tutorial.lorence.dummyjsonandroid.service.asyntask.DownloadImage;
import tutorial.lorence.dummyjsonandroid.view.activities.BaseActivity;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.FragmentContent;
import tutorial.lorence.dummyjsonandroid.view.activities.home.loading.FragmentLoading;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomeActivity extends BaseActivity implements HomeView {

    private List<Item> mGroupItems = new ArrayList<>();

    @Inject
    FragmentLoading mFragmentLoading;

    @Inject
    FragmentTransaction mFragmentTransaction;

    @Inject
    JsonData mJsonData;

    @Inject
    FragmentContent mFragmentContent;

    @Inject
    HomePresenter mHomePresenter;

    @Inject
    DownloadImage mDownloadImage;

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
        if (savedInstanceState == null) {
            mFragmentTransaction.add(R.id.fragment_container, mFragmentLoading);
            mFragmentTransaction.commit();
            mHomePresenter.getItems();
        }
    }

    public List<Item> getGroupItems() {
        return mGroupItems;
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onGetItemsSuccess(List<Item> items) {
        String url = items.get(0).getData().getThumbnail().getHqDefault();
        Intent sendResult = new Intent("tutorial.lorence.dummyjsonandroid");
        if (url != null) {
            sendResult.putExtra("sURL", url);
        }
        mContext.sendBroadcast(sendResult);
        showDataOnUI();
    }

    @Override
    public void onGetItemsFailure(String message) {

    }

    private void showDataOnUI() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mFragmentTransaction = getSupportFragmentManager().beginTransaction();
                mFragmentTransaction.replace(R.id.fragment_container, mFragmentContent);
                mFragmentTransaction.disallowAddToBackStack();
                mFragmentTransaction.commit();
            }
        }, 1000);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
