package com.example.ex;

import android.app.AlertDialog;
import android.content.Context;
import android.util.TypedValue;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    // 어댑터 상속
    private ArrayList<Dictionary> mList; //dictionary 클래스의 데이터 타입을 가진 arrayList 객체 생성
    private Context mContext;

    // 1. 컨텍스트 메뉴를 사용하라면 RecyclerView.ViewHolder를 상속받은 클래스에서
    // OnCreateContextMenuListener 리스너를 구현해야 합니다.
    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener {
        // 1. 리스너 추가
        protected TextView mId;
        protected TextView mEnglish;
        protected TextView mKorean;

        public CustomViewHolder(View view) {
            super(view);
            this.mId = (TextView) view.findViewById(R.id.id_listitem);
            this.mEnglish = (TextView) view.findViewById(R.id.english_listitem);
            this.mKorean = (TextView) view.findViewById(R.id.korean_listitem);
//            this.id = (TextView) view.findViewById(R.id.id_listitem);
//            this.english = (TextView) view.findViewById(R.id.english_listitem);
//            this.korean = (TextView) view.findViewById(R.id.korean_listitem);
            view.setOnCreateContextMenuListener(this); //2. OnCreateContextMenuListener 리스너를 현재 클래스에서 구현한다고 설정해둡니다.
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            // 3. 컨텍스트 메뉴를 생성하고 메뉴 항목 선택시 호출되는 리스너를 등록해줍니다.
            // ID 1001, 1002로 어떤 메뉴를 선택했는지 리스너에서 구분하게 됩니다.
            MenuItem Edit = menu.add(Menu.NONE, 1001, 1, "편집");
            MenuItem Delete = menu.add(Menu.NONE, 1002, 2, "삭제");
            Edit.setOnMenuItemClickListener(onEditMenu);
            Delete.setOnMenuItemClickListener(onEditMenu);
        }

        // 4. 컨텍스트 메뉴에서 항목 클릭시 동작을 설정합니다.
        private final MenuItem.OnMenuItemClickListener onEditMenu = new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case 1001:  // 5. 편집 항목을 선택시
                        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                        // 다이얼로그를 보여주기 위해 edit_box.xml 파일을 사용합니다.
                        View view = LayoutInflater.from(mContext)
                                .inflate(R.layout.edit_box, null, false);
                        builder.setView(view);
                        final Button ButtonSubmit = (Button) view.findViewById(R.id.button_dialog_submit);
                        final EditText editTextID = (EditText) view.findViewById(R.id.edittext_dialog_id);
                        final EditText editTextEnglish = (EditText) view.findViewById(R.id.edittext_dialog_endlish);
                        final EditText editTextKorean = (EditText) view.findViewById(R.id.edittext_dialog_korean);

                        // 6. 해당 줄에 입력되어 있던 데이터를 불러와서 다이얼로그에 보여줍니다.
                        editTextID.setText(mList.get(getAdapterPosition()).getId());
                        editTextEnglish.setText(mList.get(getAdapterPosition()).getEnglish());
                        editTextKorean.setText(mList.get(getAdapterPosition()).getKorean());

                        final AlertDialog dialog = builder.create();
                        ButtonSubmit.setOnClickListener(new View.OnClickListener() {
                            // 7. 수정 버튼을 클릭하면 현재 UI에 입력되어 있는 내용으로
                            public void onClick(View v) {
                                String strID = editTextID.getText().toString();
                                String strEnglish = editTextEnglish.getText().toString();
                                String strKorean = editTextKorean.getText().toString();

                                Dictionary dict = new Dictionary(strID, strEnglish, strKorean);

                                // 8. ListArray에 있는 데이터를 변경하고
                                mList.set(getAdapterPosition(), dict);
                                // 9. 어댑터에서 RecyclerView에 반영하도록 합니다.
                                notifyItemChanged(getAdapterPosition());
                                dialog.dismiss();
                            }
                        });
                        dialog.show();
                        break;
                    case 1002:  // 5. 삭제 항목을 선택시
                        // 6. ArratList에서 해당 데이터를 삭제하고
                        mList.remove(getAdapterPosition());
                        // 7. 어댑터에서 RecyclerView에 반영하도록 합니다.
                        notifyItemRemoved(getAdapterPosition());
                        notifyItemRangeChanged(getAdapterPosition(), mList.size());
                        break;
                }
                return true;
            }
        };
    }

//    public CustomAdapter(ArrayList<Dictionary> list) {
//        // 생성자에서 데이터 리스트 객체를 전달받음.
//        this.mList = list;
//    }

        public CustomAdapter(Context context, ArrayList<Dictionary> list) {
            mList = list;
            mContext = context;
        }

//    public class CustomViewHolder extends RecyclerView.ViewHolder {
//        // 아이템 뷰를 저장하는 뷰 홀더 클래스
//        protected TextView id;
//        protected TextView english;
//        protected TextView korean;
//
//        public CustomViewHolder(View view) {
//            super(view);
//            // item_list2.xml id에 값 대입
//            this.id = (TextView) view.findViewById(R.id.id_listitem);
//            this.english = (TextView) view.findViewById(R.id.english_listitem);
//            this.korean = (TextView) view.findViewById(R.id.korean_listitem);
//        }
//    }



    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // 아이템 뷰를 위한 뷰 홀더 객체 생성
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list2, viewGroup, false);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {
        // position에 해당하는 데이터를 뷰 홀더의 아이템뷰에 표시

        // 글자 크기 지정
        viewholder.mId.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mEnglish.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        viewholder.mKorean.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
        // 위치 지정
        viewholder.mId.setGravity(Gravity.CENTER);
        viewholder.mEnglish.setGravity(Gravity.CENTER);
        viewholder.mKorean.setGravity(Gravity.CENTER);
        // 텍스트 입력
        viewholder.mId.setText(mList.get(position).getId());
        viewholder.mEnglish.setText(mList.get(position).getEnglish());
        viewholder.mKorean.setText(mList.get(position).getKorean());
    }

    @Override
    public int getItemCount() {
        // 전체 데이터 갯수 리턴
        return (null != mList ? mList.size() : 0);
    }

}