package com.example.androidanimation;

import android.os.Bundle;
import android.view.View;

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

        dragInfoList.add(new DragInfo("0", "w"));
        dragInfoList.add(new DragInfo("1", "o"));
        dragInfoList.add(new DragInfo("2", "r"));
        dragInfoList.add(new DragInfo("3", "l"));
        dragInfoList.add(new DragInfo("4", "d"));

        dragAdapter = new DragAdapter(dragInfoList);
        recyclerView.setAdapter(dragAdapter);
    }

    private void initRecycleBottom(RecyclerView recyclerView) {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(layoutManager);
        dragResultList.add(new DragInfo("0", ""));
        dragResultList.add(new DragInfo("1", ""));
        dragResultList.add(new DragInfo("2", ""));
        dragResultList.add(new DragInfo("3", ""));
        dragResultList.add(new DragInfo("4", ""));
        dragResultAdapter = new DragResultAdapter(dragResultList);
        recyclerView.setAdapter(dragResultAdapter);

        dragResultAdapter.setDragListener(new DragResultAdapter.DragListener() {
            @Override
            public void drag(DragInfo dragInfo) {
                dragAdapter.removeItem(dragInfo);
            }

            @Override
            public void dragAdd(DragInfo dragInfo) {

                dragAdapter.addItem(dragInfo);
            }

            @Override
            public void error() {
                mBinding.textError.setVisibility(View.VISIBLE);
                mBinding.textSuccess.setVisibility(View.GONE);
            }

            @Override
            public void success() {
                mBinding.textSuccess.setVisibility(View.VISIBLE);
                mBinding.textError.setVisibility(View.GONE);
            }
        });
    }

}
