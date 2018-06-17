package tutorial.lorence.dummyjsonandroid.view.activities.home;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Schedule;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface HomeView {
    void onGetItemsSuccess(ArrayList<Schedule> items);
    void onGetItemsFailure(String message);
    void setDisposable(Disposable disposable);
}
