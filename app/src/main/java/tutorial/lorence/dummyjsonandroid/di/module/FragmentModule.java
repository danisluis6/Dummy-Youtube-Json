package tutorial.lorence.dummyjsonandroid.di.module;

import android.content.Context;

import dagger.Module;
import tutorial.lorence.dummyjsonandroid.app.Application;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@Module
public class FragmentModule {

    private Application mApplication;
    private Context mContext;

    public FragmentModule() {
    }
}
