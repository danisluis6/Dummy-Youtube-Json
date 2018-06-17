package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Context;

import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Schedule;
import tutorial.lorence.dummyjsonandroid.other.Constants;
import tutorial.lorence.dummyjsonandroid.service.DisposableManager;

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

    public HomePresenterImpl(Context context, HomeActivity homeActivity, HomeView homeView, HomeModel homeModel, DisposableManager disposableManager) {
        mContext = context;
        mHomeView = homeView;
        mHomeModel = homeModel;
        mHomeActivity = homeActivity;
        mHomeModel.attachActivity(mHomeActivity);
        mHomeModel.attachPresenter(this);
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
    public void onGetItemsSuccess(ArrayList<Schedule> items) {
        mHomeView.onGetItemsSuccess(items);
    }

    @Override
    public void onGetItemsFailure(String message) {
        mHomeView.onGetItemsFailure(message);
    }
}
