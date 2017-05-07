package com.android.viewbinding;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.viewBinding.BindView;
import com.viewBinding.OnClick;

public class ListActvity extends AppCompatActivity {
    @BindView(viewId = R.id.rv_list)
    RecyclerView rvList;
    @BindView(viewId = R.id.toolbar)
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_actvity);
        ViewBinding.bind(this);
        setSupportActionBar(toolbar);
        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(new ListAdapter());
    }




    class ListAdapter extends RecyclerView.Adapter{
        private String[]  strs= new String[]{"a", "b", "c", "d", "e"};
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ListActvity.this).inflate(R.layout.item_list, parent, false);
            return new ListHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if(holder instanceof ListHolder){
                ((ListHolder)holder).bindTo(position);
            }
        }

        @Override
        public int getItemCount() {
            return strs.length;
        }

        class ListHolder extends RecyclerView.ViewHolder{
            @BindView(viewId = R.id.tv_item)
            TextView tvItem;
            public ListHolder(View itemView) {
                super(itemView);
                ViewBinding.bind(this, itemView);
            }

            public void bindTo(int posi){
                tvItem.setText(strs[posi]);
            }

            @OnClick(viewId = R.id.tv_item)
            void onTvClick(){
                String result = strs[getAdapterPosition()];
                Toast.makeText(ListActvity.this, result, Toast.LENGTH_SHORT).show();
            }

        }
    }

}
