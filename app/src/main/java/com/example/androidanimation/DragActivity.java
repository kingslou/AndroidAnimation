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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import static java.io.FileDescriptor.in;
import static java.io.FileDescriptor.out;

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

        dragInfoList.add(new DragInfo("0","w","0"));
        dragInfoList.add(new DragInfo("1","o","0"));
        dragInfoList.add(new DragInfo("2","r","0"));
        dragInfoList.add(new DragInfo("3","l","0"));
        dragInfoList.add(new DragInfo("4","d","0"));
        dragInfoList.add(new DragInfo("6","o","0"));

        dragAdapter = new DragAdapter(dragInfoList);
        recyclerView.setAdapter(dragAdapter);
    }

    private void initRecycleBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        dragResultList.add(new DragInfo("0","","0"));
        dragResultList.add(new DragInfo("1","","0"));
        dragResultList.add(new DragInfo("2","","0"));
        dragResultList.add(new DragInfo("3","","0"));
        dragResultList.add(new DragInfo("4","","0"));
        dragResultAdapter = new DragResultAdapter(dragResultList);
        recyclerView.setAdapter(dragResultAdapter);

        dragResultAdapter.setDragListener(new DragResultAdapter.DragListener() {
            @Override
            public void drag(DragInfo dragInfo) {
                dragAdapter.removeItem(dragInfo);
            }
        });
    }

}
