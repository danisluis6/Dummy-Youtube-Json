package tutorial.lorence.dummyjsonandroid.view.activities.home.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import tutorial.lorence.dummyjsonandroid.R;
import tutorial.lorence.dummyjsonandroid.app.Application;
import tutorial.lorence.dummyjsonandroid.data.storage.database.entities.Item;
import tutorial.lorence.dummyjsonandroid.di.module.FragmentModule;
import tutorial.lorence.dummyjsonandroid.di.module.HomeModule;
import tutorial.lorence.dummyjsonandroid.service.asyntask.DownloadImage;
import tutorial.lorence.dummyjsonandroid.view.activities.home.HomeActivity;
import tutorial.lorence.dummyjsonandroid.view.activities.home.adapter.UserAdapter;

/**
 * Created by vuongluis on 4/14/2018.
 *
 * @author vuongluis
 * @version 0.0.1
 */

@SuppressLint("ValidFragment")
public class FragmentContent extends Fragment implements DownloadImage.DownloadImageInterface {

    private List<Item> mGrouItems;
    private UserAdapter mUserAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Context mContext;

    @Inject
    HomeActivity mHomeActivity;

    @Inject
    FragmentTransaction mFragmentTransaction;

    @Inject
    DownloadImage mDownloadImage;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Application.getInstance()
                .getAppComponent()
                .plus(new HomeModule((HomeActivity) getActivity()))
                .plus(new FragmentModule())
                .inject(this);
    }

    @SuppressLint("ValidFragment")
    public FragmentContent(Context context, HomeActivity homeActivity, UserAdapter userAdapter) {
        this.mContext = context;
        this.mHomeActivity = homeActivity;
        this.mUserAdapter = userAdapter;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        mGrouItems = mHomeActivity.getGroupItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_recycler, container, false);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(mContext);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mUserAdapter.updateData(mGrouItems);
        mRecyclerView.setAdapter(mUserAdapter);

//        mDownloadImage.attachInterface(this);
//        mDownloadImage.execute("");
        return rootView;
    }

    @Override
    public void getBitmap(Bitmap bitmap) {

    }
}