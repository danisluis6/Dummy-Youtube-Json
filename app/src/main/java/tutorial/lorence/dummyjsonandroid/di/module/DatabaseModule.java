package tutorial.lorence.dummyjsonandroid.di.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import tutorial.lorence.dummyjsonandroid.app.Application;
import tutorial.lorence.dummyjsonandroid.other.GenerateWebsite;
import tutorial.lorence.dummyjsonandroid.service.DisposableManager;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeModel;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeModelImpl;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class DatabaseModule {

    private Application mApplication;
    private Context mContext;

    public DatabaseModule(Application application, Context context) {
        mApplication = application;
        mContext = context;
    }

    @Singleton
    @Provides
    HomeModel provideHomeModel(GenerateWebsite generateWebsite) {
        return new HomeModelImpl(mContext, generateWebsite);
    }

    @Singleton
    @Provides
    DisposableManager provideDisposableManager() {
        return new DisposableManager();
    }
}
