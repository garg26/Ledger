package com.example.kartikeya.ledger.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;


import com.example.kartikeya.ledger.holder.BaseHolder;

import java.util.List;


/**
 * Created by kartikeya on 4/8/17.
 */

public class BaseRecycleAdapter<T> extends RecyclerView.Adapter<BaseHolder>{

    private List<T> list;
    private Context context;
    private RecyclerAdapterInterface adapterInterface;

    public BaseRecycleAdapter(Context context, List<T> list, RecyclerAdapterInterface adapterInterface){
        this.context=context;
        this.list=list;
        this.adapterInterface=adapterInterface;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return adapterInterface.onCreateViewHolder(parent,viewType);
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {
        adapterInterface.onBindViewHolder(holder,position);
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    public interface RecyclerAdapterInterface{
         BaseHolder onCreateViewHolder(ViewGroup parent, int viewType);
         void onBindViewHolder(BaseHolder holder, int position);
    }
}
