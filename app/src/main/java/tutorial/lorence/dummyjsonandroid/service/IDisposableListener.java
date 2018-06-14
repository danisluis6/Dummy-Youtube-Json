package tutorial.lorence.dummyjsonandroid.service;

import java.util.List;

import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.recycler.Item;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public interface IDisposableListener<T> {
    void onComplete();

    void onHandleData(List<Item> items);

    void onApiFailure(Throwable error);
}
