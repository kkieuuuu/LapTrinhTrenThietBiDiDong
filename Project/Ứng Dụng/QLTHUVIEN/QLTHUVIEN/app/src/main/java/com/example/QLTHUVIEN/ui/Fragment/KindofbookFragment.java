package com.example.QLTHUVIEN.ui.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.QLTHUVIEN.dao.DaoKindofbook;
import com.example.QLTHUVIEN.model.Kindofbook;
import com.example.QLTHUVIEN.ui.Adapter.AdapterKindofbook;
import com.example.QLTHUVIEN.R;

import java.util.List;


public class KindofbookFragment extends Fragment implements AdapterKindofbook.Listener {
    private TextView titleThemLoai;
    private EditText etLoaiSach;
    private Button btnThemLT;
    private TextView xoaTextLT;
    private TextView tvtongsokindofbook;
    private RecyclerView recyclerview;
    private Button btnAddKindofbook;
    private DaoKindofbook mDaoKindofbook;
    private AdapterKindofbook mAdapterKindofbook;
    private List<Kindofbook> mKindofBookList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kindofbook, container, false);

        tvtongsokindofbook = (TextView) view.findViewById(R.id.tvtongsokindofbook);
        recyclerview = (RecyclerView) view.findViewById(R.id.recyclerview);
        btnAddKindofbook = (Button) view.findViewById(R.id.btnAddKindofbook);


        this.mAdapterKindofbook = new AdapterKindofbook(getActivity());
        this.mAdapterKindofbook.setListener(this);
        this.mDaoKindofbook = new DaoKindofbook(getActivity());
        recyclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        showData();
        btnAddKindofbook.setOnClickListener(view1 -> {
            final Dialog dialog = new Dialog(getContext());
            dialog.setCancelable(false);
            dialog.setContentView(R.layout.dialogaddkindofbook);
            Window window = dialog.getWindow();
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (dialog != null && dialog.getWindow() != null) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }


            titleThemLoai = (TextView) dialog.findViewById(R.id.titleThemLoai);
            etLoaiSach = (EditText) dialog.findViewById(R.id.etLoaiSach);
            btnThemLT = (Button) dialog.findViewById(R.id.btnThemLT);
            xoaTextLT = (TextView) dialog.findViewById(R.id.xoaTextLT);

            btnThemLT.setOnClickListener(view2 -> {
                String themText = etLoaiSach.getText().toString();


                if (themText.isEmpty() || themText.toString() == null) {
                    Toast.makeText(getActivity(), "VUI LÒNG NHẬP LOẠI SÁCH", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Kindofbook tc = new Kindofbook(0, themText);
                    if (mDaoKindofbook.themKind(tc) == true) {
                        showData();
                        Toast.makeText(getActivity(), "LOẠI SÁCH ĐƯỢC THÊM THÀNH CÔNG!", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    } else {
                        Toast.makeText(getActivity(), "THÊM LOẠI SÁCH KHÔNG THÀNH CÔNG!", Toast.LENGTH_SHORT).show();
                    }
                }


            });

            xoaTextLT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            dialog.show();
        });


        return view;
    }

    public void showData() {
        tvtongsokindofbook.setText("HIỆN CÓ" + this.mDaoKindofbook.getListOfKindofBooks().size() + " LOẠI SÁCH !");
        this.mKindofBookList = this.mDaoKindofbook.getListOfKindofBooks();
        this.mAdapterKindofbook.setData(mKindofBookList);
        recyclerview.setAdapter(mAdapterKindofbook);
    }

    @Override
    public void onClickEdit(View view, int position) {
        Kindofbook kind = mKindofBookList.get(position);
        final Dialog dialog = new Dialog(getContext());
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialogaddkindofbook);
        Window window = dialog.getWindow();
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (dialog != null && dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        titleThemLoai = (TextView) dialog.findViewById(R.id.titleThemLoai);
        etLoaiSach = (EditText) dialog.findViewById(R.id.etLoaiSach);
        btnThemLT = (Button) dialog.findViewById(R.id.btnThemLT);
        xoaTextLT = (TextView) dialog.findViewById(R.id.xoaTextLT);
        titleThemLoai.setText("SỬA LOẠI SÁCH");
        btnThemLT.setText("SỬA");
        etLoaiSach.setText(kind.getTenloaiSach());
        btnThemLT.setOnClickListener(view2 -> {
            String themText = etLoaiSach.getText().toString();
            Kindofbook tc = new Kindofbook(kind.getIdLoaiSach(), themText);
            if (mDaoKindofbook.editKind(tc) == true) {
                showData();
                Toast.makeText(getActivity(), "THÀNH CÔNG!", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            } else {
                Toast.makeText(getActivity(), "KHÔNG THÀNH CÔNG!", Toast.LENGTH_SHORT).show();
            }
        });

        xoaTextLT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public void onClickDelete(View view, int position) {
        Kindofbook kind = mKindofBookList.get(position);
        AlertDialog dialog = new AlertDialog.Builder(getActivity()).setTitle("THÔNG BÁO").setMessage("\n" +
                "BẠN CÓ MUỐN XÓA LOẠI SÁCH NÀY KHÔNG").setPositiveButton("Ok", null).setNegativeButton("Cancel", null).show();
        Button positiveButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE);
        positiveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDaoKindofbook.deleteTitle(kind.getIdLoaiSach());
                Toast.makeText(getActivity(), "XÓA THÀNH CÔNG" + kind.getTenloaiSach(), Toast.LENGTH_SHORT).show();
                showData();
                dialog.cancel();
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();

    }
}