package com.example.QLTHUVIEN.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.QLTHUVIEN.dao.DaoBook;
import com.example.QLTHUVIEN.model.TopMost;
import com.example.QLTHUVIEN.ui.Adapter.AdapterTopMost;
import com.example.QLTHUVIEN.R;
import com.google.android.material.appbar.MaterialToolbar;

import java.util.ArrayList;
import java.util.List;

public class TopMostActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private RecyclerView recyclerview;
    private DaoBook mDaoBook;
    private AdapterTopMost mAdapterTopMost;
    private List<TopMost> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_most);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        initToolBar();
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        this.list = new ArrayList<>();
        this.mDaoBook = new DaoBook(this);
        this.mAdapterTopMost = new AdapterTopMost(this);
        this.recyclerview.setLayoutManager(new LinearLayoutManager(this));
        showData();
    }
    public void showData(){
        this.list =this.mDaoBook.getListTopMost();
        this.mAdapterTopMost.setData(this.list);
        recyclerview.setAdapter(mAdapterTopMost);
    }
    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setTitle("Top Sách Đọc");
        }
    }
    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }
}