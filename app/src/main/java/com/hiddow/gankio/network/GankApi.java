package com.hiddow.gankio.network;

import com.hiddow.gankio.model.AndroidData;
import com.hiddow.gankio.model.IOSData;
import com.hiddow.gankio.model.SearchResultData;
import com.hiddow.gankio.model.WelfareData;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by yangxiaoguang on 2016/11/4.
 */

public interface GankApi {
    @GET("data/福利/{pageSize}/{pageNum}")
    Observable<WelfareData> getPicData(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);

    @GET("data/Android/{pageSize}/{pageNum}")
    Observable<AndroidData> getAndroidData(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);

    @GET("data/iOS/{pageSize}/{pageNum}")
    Observable<IOSData> getIOSData(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);

    @GET("data/休息视频/{pageSize}/{pageNum}")
    Observable<WelfareData> getVideoData(@Path("pageSize") int pageSize, @Path("pageNum") int pageNum);

    @GET("search/query/{keyword}/category/{category}/count/{pageSize}/page/{pageNum}")
    Observable<SearchResultData> getSearchData(@Path("keyword") String keyword, @Path("category") String category,
                                               @Path("pageSize") int pageSize, @Path("pageNum") int pageNum);
}
