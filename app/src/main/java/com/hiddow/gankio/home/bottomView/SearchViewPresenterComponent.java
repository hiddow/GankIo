package com.hiddow.gankio.home.bottomView;

import com.hiddow.gankio.ApplicationModule;
import com.hiddow.gankio.network.ApiBaseComponent;
import com.hiddow.gankio.util.FragmentScoped;

import dagger.Component;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */
@FragmentScoped
@Component(modules = {SearchViewPresenterModule.class, ApplicationModule.class}, dependencies = {ApiBaseComponent.class})
public interface SearchViewPresenterComponent {
    void inject(BottomSheetView bottomSheetView);
}
