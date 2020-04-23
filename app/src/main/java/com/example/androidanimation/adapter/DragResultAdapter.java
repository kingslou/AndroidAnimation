package com.example.androidanimation.adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipDescription;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidanimation.DragUtils;
import com.example.androidanimation.R;
import com.example.androidanimation.bean.DragInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DragResultAdapter extends RecyclerView.Adapter<DragResultAdapter.ViewHolder> {

    private List<DragInfo> dragInfoList = new ArrayList<>();

    private TextView currentTouchView;
    private int currentTouchPosition;
    private Context mContext;
    private DragListener dragListener;

    public interface DragListener {

        void drag(DragInfo dragInfo);

        void error();

        void success();

        void dragAdd(DragInfo dragInfo);
    }

    public void setDragListener(DragListener dragListener) {
        this.dragListener = dragListener;
    }

    public DragResultAdapter(List<DragInfo> dragInfoList) {
        this.dragInfoList = dragInfoList;
    }

    public boolean isAllFill() {
        for (DragInfo dragInfo : dragInfoList) {
            Log.e("当前选中的值", dragInfo.getDragText() + "    -----");
            if (TextUtils.isEmpty(dragInfo.getDragText())) {
                return false;
            }
        }
        //todo 判断结果是否正确
        StringBuilder stringBuilder = new StringBuilder();
        for(DragInfo dragInfo :dragInfoList){
            stringBuilder.append(dragInfo.getDragText());
            if(stringBuilder.toString().equals("world")){
                dragListener.success();
            }else{
                dragListener.error();
            }
        }
        return true;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag_result, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        final TextView textWords = holder.itemView.findViewById(R.id.textResultWords);
        final DragInfo dragInfo = dragInfoList.get(position);
        textWords.setText(dragInfo.getDragText());
        DragUtils.bindDragInZone(holder.itemView, new DragUtils.DragStatus() {
            @Override
            public void complete(Object obj) {
                if (obj instanceof DragInfo) {
                    DragInfo info = (DragInfo) obj;
                    //todo 判断是否是同一个数据源左右拖动
                    if (currentTouchView != null) {
                        //todo 同一行 数据交换
                        Collections.swap(dragInfoList,position,currentTouchPosition);
                        notifyDataSetChanged();
                    } else {
                        //如果已经有单词占位
                        if(!TextUtils.isEmpty(dragInfo.getDragText())){
                            return;
                        }
                        dragInfo.setDragText(info.getDragText());
                        textWords.setText(info.getDragText());
                        if (dragListener != null) {
                            dragListener.drag(info);
                        }
                    }
                    isAllFill();
                }
            }
        });

        textWords.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                String text = textWords.getText().toString();
                if (TextUtils.isEmpty(text)) {
                    return false;
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    currentTouchView = textWords;
                    currentTouchPosition = position;
                    /*首先,构造ClipData对象,该对象是用来存储拖拽行为时的产生的数据*/
                    ClipData.Item item = new ClipData.Item(text);
                    String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};
                    ClipData dragData = new ClipData(text, mimeTypes, item);
                    View.DragShadowBuilder shadow = new View.DragShadowBuilder(textWords);
                    if (Build.VERSION.SDK_INT >= 24) {
                        textWords.startDragAndDrop(dragData, shadow, dragInfo, View.DRAG_FLAG_GLOBAL);
                    } else {
                        textWords.startDrag(dragData, shadow, dragInfo, View.DRAG_FLAG_GLOBAL);
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
