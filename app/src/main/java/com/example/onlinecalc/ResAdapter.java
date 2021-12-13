package com.example.onlinecalc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResAdapter extends RecyclerView.Adapter<ResAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<ResModel> resModels;

    ResAdapter(Context context, List<ResModel> ITOGI)
    {
        this.resModels = ITOGI;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public ResAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {

        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ResAdapter.ViewHolder holder, int position)
    {
        ResModel itogi_r = resModels.get(position);
        holder.indexView.setText(itogi_r.getINDEXING());
    }

    @Override
    public int getItemCount()
    {
        return resModels.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        final TextView indexView;
        ViewHolder(View view)
        {
            super(view);
            indexView = view.findViewById(R.id.INDEXING);
        }
    }
}
