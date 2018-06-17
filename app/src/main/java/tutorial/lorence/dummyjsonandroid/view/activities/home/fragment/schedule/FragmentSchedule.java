package tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.app.Application;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Schedule;
import tutorial.lorence.dummyjsonandroid.di.module.HomeModule;
import tutorial.lorence.dummyjsonandroid.di.module.ScheduleModule;
import tutorial.lorence.dummyjsonandroid.service.asyntask.DownloadImage;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeActivity;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.adapter.ScheduleAdapter;
import tutorial.lorence.dummyjsonandroid.view.activities.home.loading.FragmentLoading;
import tutorial.lorence.dummyjsonandroid.view.fragments.BaseFragment;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class FragmentSchedule extends BaseFragment implements ScheduleView {

    @Inject
    Context mContext;

    @Inject
    HomeActivity mHomeActivity;

    @Inject
    DownloadImage mDownloadImage;

    @Inject
    ScheduleAdapter mScheduleAdapter;

    @Inject
    FragmentLoading mFragmentLoading;

    @Inject
    SchedulePresenter mSchedulePresenter;

    @BindView(R.id.rcvSchedules)
    RecyclerView rcvSchedules;

    private FragmentTransaction mFragmentMFragmentTransaction;
    private Disposable mDisposable;

    public void distributedDaggerComponents(HomeActivity homeActivity) {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule(homeActivity))
                .plus(new ScheduleModule(this, this))
                .inject(this);
    }

    @SuppressLint("ValidFragment")
    public FragmentSchedule() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @SuppressLint("CommitTransaction")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        bindView(view);
        initComponents();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        mFragmentMFragmentTransaction = getChildFragmentManager().beginTransaction();
        mFragmentMFragmentTransaction.add(R.id.fragment_container, mFragmentLoading);
        mFragmentMFragmentTransaction.commit();
        mSchedulePresenter.getItems();
    }

    @Override
    public void initComponents() {
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(mContext);
        rcvSchedules.setLayoutManager(mLayoutManager);
        rcvSchedules.setItemAnimator(new DefaultItemAnimator());
        rcvSchedules.setAdapter(mScheduleAdapter);
    }

    @Override
    public void onGetItemsSuccess(ArrayList<Schedule> items) {
        mScheduleAdapter.updateSchedules(items);
        mFragmentMFragmentTransaction = getChildFragmentManager().beginTransaction();
        mFragmentMFragmentTransaction.remove(mFragmentLoading);
        mFragmentMFragmentTransaction.commit();
    }

    @Override
    public void onGetItemsFailure(String message) {

    }

    @Override
    public void setDisposable(Disposable disposable) {
        mDisposable = disposable;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mDisposable != null) {
            mDisposable.dispose();
        }
    }
}