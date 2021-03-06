package com.hiddow.gankio.home.ios;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.common.collect.Lists;
import com.hiddow.gankio.ApplicationModule;
import com.hiddow.gankio.GankIoApplication;
import com.hiddow.gankio.R;
import com.hiddow.gankio.base.BaseFragment;
import com.hiddow.gankio.model.object.IOSInfo;
import com.hiddow.gankio.network.ApiBaseComponent;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by yangxiaoguang on 2016/11/10.
 */

public class IOSFragment extends BaseFragment implements IOSContact.View {
    @Inject
    IOSPresenter mPresenter;

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private IOSInfoAdapter mAdapter;
    private List<IOSInfo> mData = Lists.newArrayList();


    public static IOSFragment newInstance() {

        Bundle args = new Bundle();

        IOSFragment fragment = new IOSFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ApiBaseComponent component = ((GankIoApplication) getActivity().getApplication()).getComponent();
        DaggerIOSPresenterComponent.builder()
                .apiBaseComponent(component)
                .iOSPresenterModule(new IOSPresenterModule(this))
                .applicationModule(new ApplicationModule(getContext()))
                .build()
                .inject(this);
    }

    @Override
    public int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView(@Nullable Bundle savedInstanceState) {
        mAdapter = new IOSInfoAdapter(R.layout.item_info, mData);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(mActivity, layoutManager.getOrientation()));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPresenter.fetchData();
            }
        });
        mAdapter.openLoadMore(1, true);
        mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                mPresenter.loadMore();
            }
        });
        mAdapter.setOnRecyclerViewItemClickListener(new BaseQuickAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, int i) {
                mPresenter.porformItemClick(mData.get(i));
            }
        });
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.fetchData();
    }


    @Override
    public void setPresenter(IOSContact.Presenter presenter) {

    }

    @Override
    public void showData(List<IOSInfo> data) {
        mData.clear();
        mData.addAll(data);
        mAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void addData(List<IOSInfo> results) {
        mAdapter.notifyDataChangedAfterLoadMore(results, true);
    }
}
