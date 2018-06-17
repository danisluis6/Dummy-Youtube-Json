package tutorial.lorence.dummyjsonandroid.di.module;

import android.content.Context;
import android.support.v4.app.FragmentManager;

import java.util.ArrayList;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Schedule;
import tutorial.lorence.dummyjsonandroid.di.scope.FragmentScope;
import tutorial.lorence.dummyjsonandroid.service.DisposableManager;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeActivity;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeModel;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomePresenter;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomePresenterImpl;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.schedule.ScheduleModel;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.schedule.SchedulePresenter;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.schedule.SchedulePresenterImpl;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.schedule.ScheduleView;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.schedule.FragmentSchedule;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.adapter.ScheduleAdapter;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class ScheduleModule {

    private FragmentSchedule mFragment;
    private ScheduleView mView;

    public ScheduleModule(FragmentSchedule fragmentSchedule, ScheduleView view) {
        mFragment = fragmentSchedule;
        mView = view;
    }

    @Provides
    @FragmentScope
    ScheduleAdapter provideScheduleAdapter(Context context) {
        return new ScheduleAdapter(context, mFragment, new ArrayList<Schedule>());
    }

    /**
     * Show up recyclerView adapter
     * @return FragmentTransaction
     */
    @Provides
    @FragmentScope
    FragmentManager provideFragmentTransaction() {
        return mFragment.getChildFragmentManager();
    }

    @Provides
    @FragmentScope
    SchedulePresenter provideSchedulePresenter(Context context, HomeActivity homeActivity, FragmentSchedule fragment, ScheduleModel scheduleModel, DisposableManager disposableManager) {
        return new SchedulePresenterImpl(context, homeActivity, fragment, mView, scheduleModel, disposableManager);
    }
}