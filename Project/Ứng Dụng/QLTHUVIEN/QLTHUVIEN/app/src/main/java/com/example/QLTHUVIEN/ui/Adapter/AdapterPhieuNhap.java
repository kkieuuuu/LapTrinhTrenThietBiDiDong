package com.example.QLTHUVIEN.ui.Adapter;

import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.QLTHUVIEN.dao.DaoNhapSach;
import com.example.QLTHUVIEN.model.NhapSach;
import com.example.QLTHUVIEN.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class AdapterPhieuNhap extends RecyclerView.Adapter<AdapterPhieuNhap.ViewHolder>{
    private DaoNhapSach mDaoNhapSach;
    private List<NhapSach> list;
    private Context mContext;
    private TextView txtEdit;
    private TextView txtAddPhieumuon;
    private TextView txtXoa;
    public AdapterPhieuNhap(Context context){
        this.mContext = context;
        this.mDaoNhapSach = new DaoNhapSach(context);

    }
    public void setData(List<NhapSach> list){
        this.list = list;
        notifyDataSetChanged();
    }
//    public void setListener(Listener listener) {
//        this.listener = listener;
//    }

//    public interface Listener {
//        void onClickEdit(View view, int position);
//        void onClickDelete(View view, int position);
//        void onAddLoanClip(View view, int position);
//    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phieunhap , parent , false);
                return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  ViewHolder holder, int position) {
        NhapSach kind = list.get(position);
        if(kind !=null){
            holder.tv_NXB.setText("NXB: "+kind.getMaNXB());
            holder.tv_MaSach.setText("Mã Sách: "+kind.getMaSach());
            holder.tv_SoLuong.setText("Số lượng: "+kind.getSoLuong()+"");
            holder.tv_NgayNhap.setText("Ngày Nhập: "+kind.getNgayNhap());
            holder.itemView.setOnClickListener(view ->{

            });

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_NXB;
        private TextView tv_MaSach;
        private TextView tv_SoLuong;
        private TextView tv_NgayNhap;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_NXB = (TextView)itemView. findViewById(R.id.tv_NXB);
            tv_MaSach = (TextView)itemView. findViewById(R.id.tv_MaSach);
            tv_SoLuong = (TextView) itemView.findViewById(R.id.tv_SoLuong);
            tv_NgayNhap = (TextView)itemView. findViewById(R.id.tv_NgayNhap);

        }
    }
}
