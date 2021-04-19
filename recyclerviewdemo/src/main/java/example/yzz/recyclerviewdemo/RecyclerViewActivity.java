package example.yzz.recyclerviewdemo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewActivity extends AppCompatActivity {
    private RecyclerView recyclerView, recyclerView2;
    private List<String> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dates = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            dates.add("这是数据+" + i);
        }
        recyclerView = findViewById(R.id.rcv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new MyAdapter());
        recyclerView.addItemDecoration(new TextItemDecoration());


        /**
         * 既然是动画，就会有时间，我们把动画执行时间变大一点来看一看效果
         */
        DefaultItemAnimator defaultItemAnimator = new DefaultItemAnimator();
        defaultItemAnimator.setAddDuration(1000);
        defaultItemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(defaultItemAnimator);



        recyclerView2 = findViewById(R.id.rcv_main2);
        recyclerView2.setLayoutManager(new LinearLayoutManager(this));
        recyclerView2.setAdapter(new MyAdapter());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyHolder> {


        @Override
        public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_activity_main, parent, false);
            MyHolder myHolder = new MyHolder(view);
            return myHolder;
        }

        @Override
        public void onBindViewHolder(MyHolder holder, int position) {
            holder.tvText.setText(dates.get(position));
        }


        @Override
        public int getItemCount() {
            return dates.size();
        }


        class MyHolder extends RecyclerView.ViewHolder {
            private TextView tvText;

            public MyHolder(View itemView) {
                super(itemView);
                tvText = itemView.findViewById(R.id.tv_text);
            }
        }
    }


    class TextItemDecoration extends RecyclerView.ItemDecoration {
        private Paint mPaint;
        private Paint textPaint;

        public TextItemDecoration() {
            this.mPaint = new Paint();
            // 画笔颜色设置为黄色
            mPaint.setColor(Color.YELLOW);
            this.textPaint = new Paint();
            textPaint.setColor(Color.BLACK);
            textPaint.setTextSize(30);
        }

        //设置ItemView的内嵌偏移长度（inset）
        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = parent.getChildAdapterPosition(view);
            if (position % 5 == 0) {
                outRect.set(0, 50, 0, 0);
            }
        }

        // 在子视图上设置绘制范围，并绘制内容
        // 绘制图层在ItemView以下，所以如果绘制区域与ItemView区域相重叠，会被遮挡
        @Override
        public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDraw(c, parent, state);

        }


        //同样是绘制内容，但与onDraw（）的区别是：绘制在图层的最上层
        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            // 获取RecyclerView的Child view的个数
            int childCount = parent.getChildCount();
            // 遍历每个Item，分别获取它们的位置信息，然后再绘制对应的分割线
            for (int i = 0; i < childCount; i++) {
                View child = parent.getChildAt(i);
                int index = parent.getChildAdapterPosition(child);
                if (index % 5 == 0) {
                    int item = (index) / 5;
                    if (i < 5) {
                        if (i == 1 && child.getTop() < 100) {
                            int left = 0;
                            int top = child.getTop() - 100;
                            int right = child.getRight();
                            int bottom = child.getTop() - 50;
                            c.drawRect(left, top, right, bottom, mPaint);
                            c.drawText("这是条目" + item + "视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, left, top + 50, textPaint);
                        } else {
                            int left = 0;
                            int top = 0;
                            int right = child.getRight();
                            int bottom = 50;
                            c.drawRect(left, top, right, bottom, mPaint);
                            if (i == 0) {
                                c.drawText("这是条目" + (item + 1) + "视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, left, top + 50, textPaint);
                            } else {
                                c.drawText("这是条目" + (item) + "视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, left, top + 50, textPaint);
                            }
                        }

                    }
                    if (i != 0) {
                        int left = 0;
                        int top = child.getTop() - 50;
                        int right = child.getRight();
                        int bottom = child.getTop();
                        c.drawRect(left, top, right, bottom, mPaint);
                        c.drawText("这是条目" + item + "视图i是+" + i + "顶部的高低" + child.getTop() + "index++" + index, left, top + 50, textPaint);
                    }
                }
            }
        }
    }
}
