package tutorial.lorence.dummyjsonandroid.view.activities.home;

import java.util.List;

import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Schedule;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.recycler.Item;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface HomePresenter {
    void getItems();

    void setDisposable(Disposable disposable);

    void onGetItemsSuccess(List<Schedule> items);

    void onGetItemsFailure(String message);
}
