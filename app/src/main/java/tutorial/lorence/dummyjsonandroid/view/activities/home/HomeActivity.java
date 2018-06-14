package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentTransaction;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
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

    @Inject
    FragmentLoading mFragmentLoading;

    @Inject
    Context mContext;

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
        mFragmentContent.distributedDaggerComponents(this);
        if (savedInstanceState == null) {
            mFragmentTransaction.add(R.id.fragment_container, mFragmentLoading);
            mFragmentTransaction.commit();
            mHomePresenter.getItems();
        }
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onGetItemsSuccess(List<Item> items) {
        String url = items.get(0).getData().getThumbnail().getHqDefault();
        Bundle bundle = new Bundle();
        bundle.putString("sURL", url);
        mFragmentContent.setArguments(bundle);
        loadFragmentContent();
    }

    @Override
    public void onGetItemsFailure(String message) {

    }

    private void loadFragmentContent() {
        mFragmentTransaction = getSupportFragmentManager().beginTransaction();
        mFragmentTransaction.replace(R.id.fragment_container, mFragmentContent);
        mFragmentTransaction.disallowAddToBackStack();
        mFragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}
