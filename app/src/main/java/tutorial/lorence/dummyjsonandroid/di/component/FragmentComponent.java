package tutorial.lorence.dummyjsonandroid.di.component;

import dagger.Subcomponent;
import tutorial.lorence.dummyjsonandroid.di.module.ScheduleModule;
import tutorial.lorence.dummyjsonandroid.di.scope.FragmentScope;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.schedule.FragmentSchedule;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@FragmentScope
@Subcomponent(

        modules = {
                ScheduleModule.class
        }
)
public interface FragmentComponent {
    FragmentSchedule inject(FragmentSchedule fragmentSchedule);
}


