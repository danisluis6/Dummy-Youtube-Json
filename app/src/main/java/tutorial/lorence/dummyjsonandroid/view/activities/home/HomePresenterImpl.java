package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Context;

import java.util.List;

import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Item;
import tutorial.lorence.dummyjsonandroid.other.Constants;
import tutorial.lorence.dummyjsonandroid.service.DisposableManager;
import tutorial.lorence.dummyjsonandroid.service.JsonData;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

public class HomePresenterImpl implements HomePresenter {

    private Context mContext;
    private HomeActivity mHomeActivity;
    private HomeView mHomeView;
    private HomeModel mHomeModel;
    private JsonData mJsonData;
    private DisposableManager mDisposableManager;

    public HomePresenterImpl(Context context, HomeActivity homeActivity, HomeView homeView, HomeModel homeModel, JsonData jsonData, DisposableManager disposableManager) {
        mContext = context;
        mHomeView = homeView;
        mHomeModel = homeModel;
        mHomeActivity = homeActivity;
        mDisposableManager = disposableManager;
        mJsonData = jsonData;
        mHomeModel.attachActivity(mHomeActivity);
        mHomeModel.attachPresenter(this);
        mHomeModel.attachJsonData(mJsonData);
        mHomeModel.attachDisposable(disposableManager);
    }

    @Override
    public void getItems() {
        mHomeModel.getItems(Constants.MVP._JSOUP);
    }

    @Override
    public void setDisposable(Disposable disposable) {
        mHomeView.setDisposable(disposable);
    }

    @Override
    public void onGetItemsSuccess(List<Item> items) {
        mHomeView.onGetItemsSuccess(items);
    }

    @Override
    public void onGetItemsFailure(String message) {
        mHomeView.onGetItemsFailure(message);
    }
}
