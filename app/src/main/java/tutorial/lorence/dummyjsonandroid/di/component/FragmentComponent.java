package tutorial.lorence.dummyjsonandroid.di.component;

import dagger.Subcomponent;
import tutorial.lorence.dummyjsonandroid.di.module.FragmentModule;
import tutorial.lorence.dummyjsonandroid.di.scope.FragmentScope;
import tutorial.lorence.dummyjsonandroid.view.activities.home.fragment.FragmentContent;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@FragmentScope
@Subcomponent(

        modules = {
                FragmentModule.class
        }
)
public interface FragmentComponent {
    FragmentContent inject(FragmentContent fragmentContent);
}


