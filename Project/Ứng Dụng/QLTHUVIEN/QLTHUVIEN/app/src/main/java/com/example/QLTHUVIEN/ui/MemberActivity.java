package com.example.QLTHUVIEN.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.QLTHUVIEN.dao.DaoController;
import com.example.QLTHUVIEN.dao.DaoMember;
import com.example.QLTHUVIEN.model.Member;
import com.example.QLTHUVIEN.ui.Adapter.AdapterMember;
import com.example.QLTHUVIEN.ui.Adapter.ViewPagerAdapter;
import com.example.QLTHUVIEN.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MemberActivity extends AppCompatActivity {
    private MaterialToolbar toolBar;
    private TabLayout tabLayout;
    private ViewPager viewpager;
    private DaoMember mDaoMember;
    private AdapterMember mAdapterMember;
    private List<Member> mmemberList;
    private DaoController mDaoThuTHu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);
        toolBar = (MaterialToolbar) findViewById(R.id.tool_bar);
        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        viewpager = (ViewPager) findViewById(R.id.viewpager);
        initToolBar();
        ViewPagerAdapter mViewPager = new ViewPagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewpager.setAdapter(mViewPager);
        tabLayout.setupWithViewPager(viewpager);

        this.mDaoMember = new DaoMember(this);
        this.mAdapterMember = new AdapterMember(this);
        this.mDaoThuTHu = new DaoController(this);
        this.mmemberList = new ArrayList<>();

    }

    public void showData() {
        this.mmemberList = this.mDaoMember.getListMember();
        tabLayout.getTabAt(0).getOrCreateBadge().setNumber(this.mmemberList.size());
        tabLayout.getTabAt(1).getOrCreateBadge().setNumber(this.mDaoThuTHu.getListThuTHu().size());
    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("Quản Lý Thành Viên");

    }

    @Override
    public boolean onSupportNavigateUp() {
        this.onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    protected void onResume() {
        super.onResume();
        showData();
    }
}