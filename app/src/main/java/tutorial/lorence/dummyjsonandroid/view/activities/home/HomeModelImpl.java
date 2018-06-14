package tutorial.lorence.dummyjsonandroid.view.activities.home;

import android.content.Context;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import io.reactivex.Observable;
import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Item;
import tutorial.lorence.dummyjsonandroid.other.Constants;
import tutorial.lorence.dummyjsonandroid.other.GenerateWebsite;
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
    private HomePresenter mHomePresenter;
    private JsonData mJsonData;
    private DisposableManager mDisposableManager;
    private GenerateWebsite mGenerateWebsite;
    private HomeActivity mHomeActivity;

    public HomeModelImpl(Context context, GenerateWebsite generateWebsite) {
        mContext = context;
        mGenerateWebsite = generateWebsite;
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
    public void attachActivity(HomeActivity homeActivity) {
        mHomeActivity = homeActivity;
    }

    @Override
    public void attachDisposable(DisposableManager disposableManager) {
        mDisposableManager = disposableManager;
        mDisposableManager.setDisposableInterface(this);
    }

    @Override
    public void getItems(Constants.MVP mvp) {
        if (mvp == Constants.MVP._JSON) {
            if (Utils.isInternetOn(mContext)) {
                mHomePresenter.setDisposable(mDisposableManager.callDisposable(Observable.just(mJsonData.getItemsFromJson())));
            } else {
                mHomePresenter.onGetItemsFailure(mContext.getString(R.string.no_internet_connection));
            }
        } else if (mvp == Constants.MVP._JSOUP){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    final StringBuilder builder = new StringBuilder();
                    try {
                        Document doc = Jsoup.connect(mGenerateWebsite.jsoup_URL()).get();
                        String title = doc.title();
                        Log.i("TAG", "title: "+doc.select("title"));
                        Elements trs = doc.getElementsByClass("ltd-tr2");

                        builder.append(title).append("\n");

                        for(Element tr: trs) {
                            builder.append("\n").append("Info : ").append(tr.text());
                        }
                    } catch (IOException e) {
                        builder.append("Error : ").append(e.getMessage()).append("\n");
                    }
                    mHomeActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.i("TAG", "Builder: "+builder);
                        }
                    });
                }

            }).start();
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
