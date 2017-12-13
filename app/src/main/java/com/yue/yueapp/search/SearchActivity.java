package com.yue.yueapp.search;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxLayout;
import com.jakewharton.rxbinding2.view.RxView;
import com.yue.yueapp.Constant;
import com.yue.yueapp.ErrorAction;
import com.yue.yueapp.R;
import com.yue.yueapp.adapter.search.SearchHistoryAdapter;
import com.yue.yueapp.adapter.search.SearchSuggestionAdapter;
import com.yue.yueapp.api.IMobileSearchApi;
import com.yue.yueapp.base.BaseActivity;
import com.yue.yueapp.bean.search.SearchRecommentBean;
import com.yue.yueapp.retrofitHttp.RetrofitFactory;
import com.yue.yueapp.utils.SettingUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


public class SearchActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "SearchActivity";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private String[] titles = new String[]{"综合", "视频", "图集", "用户(beta)", "问答"};
    private SearchView searchView;
    private LinearLayout resultLayout;
    private ListView suggestionList;
    private ListView historyList;
    private SearchHistoryAdapter historyAdapter;
    private SearchSuggestionAdapter suggestionAdapter;
    //    private SearchHistoryDao dao = new SearchHistoryDao();
    private FlexboxLayout flexboxLayout;
    private LinearLayout hotWordLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
    }


    private void initView() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar, true, "");
        // 热门搜索
        hotWordLayout = (LinearLayout) findViewById(R.id.hotword_layout);
        flexboxLayout = (FlexboxLayout) findViewById(R.id.flexbox_layout);
        flexboxLayout.setFlexDirection(FlexDirection.ROW);
        flexboxLayout.setFlexWrap(FlexWrap.WRAP);
        TextView tv_clear = (TextView) findViewById(R.id.tv_clear);
        tv_clear.setOnClickListener(this);
        TextView tv_refresh = (TextView) findViewById(R.id.tv_refresh);
        RxView.clicks(tv_refresh)
                // 防抖
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(@NonNull Object o) throws Exception {
                        flexboxLayout.removeAllViews();
                        getSearchHotWord();
                    }
                }, ErrorAction.error());
        // 搜索结果
        resultLayout = (LinearLayout) findViewById(R.id.result_layout);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabLayout.setBackgroundColor(SettingUtil.getInstance().getColor());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        // 搜索建议
        suggestionList = (ListView) findViewById(R.id.suggestion_list);
        suggestionAdapter = new SearchSuggestionAdapter(this, -1);
        suggestionList.setAdapter(suggestionAdapter);
        suggestionList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyWord = suggestionAdapter.getItem(position).getKeyword();
                searchView.clearFocus();
                searchView.setQuery(keyWord, true);
            }
        });
        // 搜索历史
        historyList = (ListView) findViewById(R.id.history_list);
        historyAdapter = new SearchHistoryAdapter(this, -1);
        historyList.setAdapter(historyAdapter);
        historyList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String keyWord = historyAdapter.getItem(position).getKeyWord();
                searchView.clearFocus();
                searchView.setQuery(keyWord, true);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onClick(View v) {

    }


    private void getSearchHotWord() {

        RetrofitFactory.getRetrofit().create(IMobileSearchApi.class).getSearchRecomment()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .map(new Function<SearchRecommentBean, List<String>>() {
                    @Override
                    public List<String> apply(@NonNull SearchRecommentBean searchRecommentBean) throws Exception {
                        List<SearchRecommentBean.DataBean.SuggestWordListBean> suggest_word_list = searchRecommentBean.getData().getSuggest_word_list();
                        List<String> hotList = new ArrayList<>();
                        for (int i = 0; i < suggest_word_list.size(); i++) {
                            if (suggest_word_list.get(i).getType().equals("recom")) {
                                hotList.add(suggest_word_list.get(i).getWord());
                            }
                        }
                        return hotList;
                    }
                })

                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull final List<String> list) throws Exception {
                        for (int i = 0; i < list.size(); i++) {
                            final TextView tv = (TextView) LayoutInflater.from(SearchActivity.this).inflate(R.layout.item_search_sug_text, flexboxLayout, false);
                            final String keyWord = list.get(i);
                            int color = Constant.TAG_COLORS[i % Constant.TAG_COLORS.length];
                            tv.setText(keyWord);
                            tv.setBackgroundColor(color);
                            tv.setTextColor(Color.WHITE);
                            tv.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    searchView.clearFocus();
                                    searchView.setQuery(keyWord, true);
                                }
                            });
                            flexboxLayout.addView(tv);
                            if (i == 7) {
                                return;
                            }
                        }
                    }
                }, ErrorAction.error());
    }
}
