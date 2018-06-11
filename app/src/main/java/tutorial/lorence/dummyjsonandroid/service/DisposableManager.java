package tutorial.lorence.dummyjsonandroid.service;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Item;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeModelImpl;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class DisposableManager {

    private IDisposableListener listener;
    private Disposable disposable;

    @Inject
    public DisposableManager() {
    }

    public Disposable callDisposable(Observable<List<Item>> observable) {
        disposable = observable.subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<List<Item>>() {
                    @Override
                    public void onComplete() {
                        listener.onComplete();
                    }

                    @Override
                    public void onNext(List<Item> items) {
                        listener.onHandleData(items);
                    }

                    @Override
                    public void onError(Throwable e) {
                        listener.onApiFailure(e);
                    }
                });
        return disposable;
    }

    public void setDisposableInterface(HomeModelImpl disposableInterface) {
        this.listener = disposableInterface;
    }
}