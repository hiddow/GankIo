package com.hiddow.gankio.home.android;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.android.databinding.library.baseAdapters.BR;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hiddow.gankio.model.object.AndroidInfo;

import java.util.List;

/**
 * Created by yangxiaoguang on 2016/11/10.
 */

public class AndroidInfoAdapter extends BaseQuickAdapter<AndroidInfo> {

    public AndroidInfoAdapter(int layoutResId, List<AndroidInfo> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, AndroidInfo androidInfo) {
        ViewDataBinding dataBinding = DataBindingUtil.bind(baseViewHolder.itemView);
        dataBinding.setVariable(BR.baseResult, androidInfo);
        dataBinding.executePendingBindings();

    }
}
