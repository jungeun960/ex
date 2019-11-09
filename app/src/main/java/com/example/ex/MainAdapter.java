package com.example.ex;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.CustomViewHolder> {

    private ArrayList<MainData> arrayList;

    public MainAdapter(ArrayList<MainData> arrayList){
        // 생성자에서 데이터 리스트 객체를 전달받음.
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public MainAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // viewType 형태의 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent, false);
        CustomViewHolder holder = new CustomViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MainAdapter.CustomViewHolder holder, int position) {
        // position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.

        holder.iv_profile.setImageResource(arrayList.get(position).getIv_profile()); // 이미지 가져오기
        holder.tv_name.setText(arrayList.get(position).getTv_name()); // 이름 가져오기
        holder.tv_content.setText(arrayList.get(position).getTv_cotent()); // 내용 가져오기
        holder.tv_title.setText(arrayList.get(position).getTv_cotent());

        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            // 아이템 클릭시
            @Override
            public void onClick(View v) {
                String curName = holder.tv_name.getText().toString();
                Toast.makeText(v.getContext(),curName,Toast.LENGTH_SHORT).show(); // 토스트 메세지 보내기
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            //아이템 길게 클릭시
            @Override
            public boolean onLongClick(View v) {
                remove(holder.getAdapterPosition()); // 제거
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        // 전체 아이템 갯수 리턴.
        return (null != arrayList ? arrayList.size() : 0);
    }

    public void remove(int position){
        // 삭제
        try{
            arrayList.remove(position); // arrayList에서 제거
            notifyItemRemoved(position);// 새로고침해 지워줌
        }catch (IndexOutOfBoundsException ex){
            ex.printStackTrace();
        }
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        // 아이템 뷰를 저장하는 뷰홀더 클래스.

        protected ImageView iv_profile;
        protected TextView tv_name;
        protected TextView tv_content;
        protected TextView tv_title;

        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            // 뷰 객체에 대한 참조. (hold strong reference)
            this.iv_profile=(ImageView)itemView.findViewById(R.id.iv_profile);
            this.tv_name=(TextView)itemView.findViewById(R.id.tv_name);
            this.tv_content=(TextView)itemView.findViewById(R.id.tv_content);
            this.tv_title=(TextView)itemView.findViewById(R.id.tv_title);
        }
    }
}
