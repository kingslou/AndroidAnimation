package com.example.androidanimation;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.androidanimation.adapter.DragAdapter;
import com.example.androidanimation.adapter.DragResultAdapter;
import com.example.androidanimation.bean.DragInfo;
import com.example.androidanimation.databinding.ActivityDragBinding;

import java.util.ArrayList;
import java.util.List;

public class DragActivity extends AppCompatActivity {

    private ActivityDragBinding mBinding;
    private DragAdapter dragAdapter;
    private DragResultAdapter dragResultAdapter;
    private List<DragInfo> dragResultList = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = ActivityDragBinding.inflate(getLayoutInflater());
        setContentView(mBinding.getRoot());

        initRecycleTop(mBinding.recycleDrag);
        initRecycleBottom(mBinding.recycleResult);
    }

    private void initRecycleTop(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        List<DragInfo> dragInfoList = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            DragInfo dragInfo = new DragInfo();
            dragInfo.setId(i + "");
            dragInfo.setDragPosition(i + "");
            dragInfo.setDragText("A" + i);
            dragInfoList.add(dragInfo);
        }

        dragResultList.addAll(dragInfoList);
        dragAdapter = new DragAdapter(dragInfoList);
        recyclerView.setAdapter(dragAdapter);
    }

    private void initRecycleBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        dragResultAdapter = new DragResultAdapter(dragResultList);
        recyclerView.setAdapter(dragResultAdapter);
    }
}
