package com.yue.yueapp.module.search.result;


import com.yue.yueapp.bean.news.MultiNewsArticleDataBean;
import com.yue.yueapp.module.base.IBaseListView;
import com.yue.yueapp.module.base.IBasePresenter;

import java.util.List;

/**
 * Created by Meiji on 2017/2/7.
 */

interface ISearchResult {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... parameter);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);

        void doShowNoMore();
    }
}
