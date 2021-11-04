package com.example.lab6;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lab6.databinding.FragmentItemBinding;

import java.util.List;

public class MyApplicationRecyclerViewAdapter extends RecyclerView.Adapter<MyApplicationRecyclerViewAdapter.ViewHolder> {

    private final List<Application> mValues;

    public MyApplicationRecyclerViewAdapter(List<Application> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(FragmentItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.devName.setText(holder.mItem.getDevName());
        holder.appName.setText(holder.mItem.getAppName());
    }



    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView devName, appName, number, email, pubName, version, type, rate;
        public Application mItem;

        public ViewHolder(FragmentItemBinding binding) {
            super(binding.getRoot());
            devName = binding.devName;
            appName = binding.appName;
        }
    }
}