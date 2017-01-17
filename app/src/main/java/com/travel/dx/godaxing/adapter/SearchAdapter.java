package com.travel.dx.godaxing.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.travel.dx.godaxing.R;
import com.travel.dx.godaxing.activity.HotSearchActivity;
import com.travel.dx.godaxing.bean.SearchInfo;

import java.util.List;

/**
 * Created by Administrator on 2016/11/16 0016.
 */
public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> {
    private final Context context;
    private List<SearchInfo> list;

    public SearchAdapter(Context context, List<SearchInfo> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SearchViewHolder holder = new SearchViewHolder(LayoutInflater.from(context).inflate(R.layout.layout_search_hot,parent,false));
        return holder;
    }

    @Override
    public void onBindViewHolder(final SearchViewHolder holder, int position) {
        holder.tv.setText(list.get(position).getName());
        SearchInfo searchId = list.get(position);
        holder.id = searchId.getId();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv;
        private int id;

        public SearchViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.hot_search);
            tv.setOnClickListener(this);
            tv.setTag(this);
        }

        @Override
        public void onClick(View v) {
            SearchViewHolder holder = (SearchViewHolder) v.getTag();
            Intent intent = new Intent(v.getContext(),HotSearchActivity.class);
            intent.putExtra("id",holder.id);
            v.getContext().startActivity(intent);
        }
    }
}
