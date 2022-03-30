package com.example.androidip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidip.databinding.RecyclerRowBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    List<String> diseaseName = new ArrayList<>();

    //method to update disease names
    public void updateDiseaseName(List<String> diseaseName){
        this.diseaseName = diseaseName;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        RecyclerRowBinding recyclerRowBinding = RecyclerRowBinding.inflate(layoutInflater, parent, false);
        return new ViewHolder(recyclerRowBinding);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(diseaseName.get(position));
    }

    @Override
    public int getItemCount() {
        return diseaseName.size();//size of disease name
    }

    //View holder
    class ViewHolder extends RecyclerView.ViewHolder {

        //Constructor for the ViewHolder
        private RecyclerRowBinding recyclerRowBinding;

        public ViewHolder(@NonNull RecyclerRowBinding recyclerRowBinding){
            super(recyclerRowBinding.getRoot());
            this.recyclerRowBinding = recyclerRowBinding;
        }
        //Bind object to recycler view
        public void bindView(String diseaseName){
            recyclerRowBinding.diseaseTextView.setText(diseaseName);
        }

    }
}
