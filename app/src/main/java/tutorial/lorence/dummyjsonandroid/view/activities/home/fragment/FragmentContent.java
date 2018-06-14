package tutorial.lorence.dummyjsonandroid.view.activities.home.fragment;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import javax.inject.Inject;

import butterknife.BindView;
import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.app.Application;
import tutorial.lorence.dummyjsonandroid.di.module.FragmentModule;
import tutorial.lorence.dummyjsonandroid.di.module.HomeModule;
import tutorial.lorence.dummyjsonandroid.service.asyntask.DownloadImage;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeActivity;
import tutorial.lorence.dummyjsonandroid.view.fragments.BaseFragment;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class FragmentContent extends BaseFragment implements DownloadImage.DownloadImageInterface {

    @Inject
    HomeActivity mHomeActivity;

    @Inject
    DownloadImage mDownloadImage;

    @BindView(R.id.imgDemo)
    ImageView imgDemo;

    public void distributedDaggerComponents(HomeActivity homeActivity) {
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule(homeActivity))
                .plus(new FragmentModule())
                .inject(this);
    }


    @SuppressLint("ValidFragment")
    public FragmentContent() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        bindView(view);
        downloadImageFromInternet(getArguments().getString("sURL"));
        return view;
    }

    private void downloadImageFromInternet(String sURL) {
        mDownloadImage = new DownloadImage();
        mDownloadImage.attachInterface(this);
        mDownloadImage.execute(sURL);
    }

    @Override
    public void getBitmap(Bitmap bitmap) {
        imgDemo.setImageBitmap(bitmap);
    }
}