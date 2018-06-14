package tutorial.lorence.dummyjsonandroid.view.activities.home;

import tutorial.lorence.dummyjsonandroid.other.Constants;
import tutorial.lorence.dummyjsonandroid.service.DisposableManager;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface HomeModel {
    void getItems(Constants.MVP mvp);
    void attachPresenter(HomePresenterImpl homePresenter);
    void attachDisposable(DisposableManager disposableManager);
    void attachActivity(HomeActivity mHomeActivity);
}
