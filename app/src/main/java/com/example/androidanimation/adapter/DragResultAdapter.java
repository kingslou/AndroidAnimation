package com.example.androidanimation.adapter;

import android.graphics.Color;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidanimation.DragUtils;
import com.example.androidanimation.R;
import com.example.androidanimation.bean.DragInfo;

import java.util.ArrayList;
import java.util.List;

public class DragResultAdapter extends RecyclerView.Adapter<DragResultAdapter.ViewHolder> {

    private List<DragInfo> dragInfoList = new ArrayList<>();

    public DragResultAdapter(List<DragInfo> dragInfoList) {
        this.dragInfoList = dragInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Button button = holder.itemView.findViewById(R.id.btnDrag);
        button.setBackgroundResource(R.color.colorAccent);

        DragUtils.bindDragInZone(holder.itemView, new DragUtils.DragStatus() {
            @Override
            public void complete(String obj) {
                Log.e("ddd",obj);
                button.setText(obj);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dragInfoList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
