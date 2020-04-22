package com.example.androidanimation.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidanimation.R;
import com.example.androidanimation.bean.DragInfo;

import java.util.ArrayList;
import java.util.List;

public class DragAdapter extends RecyclerView.Adapter<DragAdapter.ViewHolder> {

    private List<DragInfo> dragInfoList = new ArrayList<>();
    public DragAdapter(List<DragInfo> dragInfoList) {
        this.dragInfoList = dragInfoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Button button = holder.itemView.findViewById(R.id.btnDrag);
        button.setText(dragInfoList.get(position).getDragText());

        button.setOnTouchListener(new View.OnTouchListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    String text = dragInfoList.get(position).getDragText();
                    /*首先,构造ClipData对象,该对象是用来存储拖拽行为时的产生的数据*/
                    if (Build.VERSION.SDK_INT >= 24) {
                        ClipData.Item item = new ClipData.Item(text);
                        String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                        ClipData dragData = new ClipData(text, mimeTypes, item);
                        View.DragShadowBuilder shadow = new View.DragShadowBuilder(button);
                        button.startDragAndDrop(dragData, shadow, null, View.DRAG_FLAG_GLOBAL);
                    }
                    return true;
                } else {
                    return false;
                }
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
