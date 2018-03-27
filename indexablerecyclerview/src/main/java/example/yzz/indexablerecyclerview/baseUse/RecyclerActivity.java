package example.yzz.indexablerecyclerview.baseUse;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import example.yzz.indexablerecyclerview.R;

public class RecyclerActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    public String[] myDatas = {"第一代", "第二代", "第三代", "第四代", "第五代", "第六代", "第七代", "第八代",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        mRecyclerView = findViewById(R.id.recycler_view);
        //创建默认的线性LayoutManager
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        //创建并设置Adapter
        mRecyclerView.setAdapter(new MyAdapter(myDatas));
    }

    public class MyAdapter extends RecyclerView.Adapter {
        public String[] datas = null;

        public MyAdapter(String[] datas) {
            this.datas = datas;
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
            ViewHolder vh = new ViewHolder(view);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof ViewHolder) {
                ((ViewHolder) holder).mTextView.setText(datas[position]);
            }
        }

        @Override
        public int getItemCount() {
            return datas.length;
        }


        //自定义的ViewHolder，持有每个Item的的所有界面元素
        class ViewHolder extends RecyclerView.ViewHolder {
            public TextView mTextView;

            public ViewHolder(View view) {
                super(view);
                mTextView = view.findViewById(R.id.tv_recycler);
            }
        }

    }


}
