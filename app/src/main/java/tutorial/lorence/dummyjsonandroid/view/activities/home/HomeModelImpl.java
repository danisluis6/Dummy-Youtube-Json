package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Context;

import java.util.List;

import io.reactivex.Observable;
import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.data.storage.database.DbAccess.DAItem;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Item;
import tutorial.lorence.dummyjsonandroid.other.Utils;
import tutorial.lorence.dummyjsonandroid.service.DisposableManager;
import tutorial.lorence.dummyjsonandroid.service.IDisposableListener;
import tutorial.lorence.dummyjsonandroid.service.JsonData;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomeModelImpl implements HomeModel, IDisposableListener<Item> {

    private Context mContext;
    private DAItem mDaItem;
    private HomePresenter mHomePresenter;
    private JsonData mJsonData;
    private DisposableManager mDisposableManager;

    public HomeModelImpl(Context context, DAItem daItem) {
        mContext = context;
        mDaItem = daItem;
    }

    @Override
    public void attachPresenter(HomePresenterImpl homePresenter) {
        mHomePresenter = homePresenter;
    }

    @Override
    public void attachJsonData(JsonData jsonData) {
        mJsonData = jsonData;
    }

    @Override
    public void attachDisposable(DisposableManager disposableManager) {
        mDisposableManager = disposableManager;
        mDisposableManager.setDisposableInterface(this);
    }

    @Override
    public void getItems() {
        if (Utils.isInternetOn(mContext)) {
            mHomePresenter.setDisposable(mDisposableManager.callDisposable(Observable.just(mJsonData.getItemsFromJson())));
        } else {
            mHomePresenter.onGetItemsFailure(mContext.getString(R.string.no_internet_connection));
        }
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onHandleData(List<Item> items) {
        mHomePresenter.onGetItemsSuccess(items);
    }

    @Override
    public void onApiFailure(Throwable error) {
        mHomePresenter.onGetItemsFailure(mContext.getString(R.string.error_time_out));
    }
}
