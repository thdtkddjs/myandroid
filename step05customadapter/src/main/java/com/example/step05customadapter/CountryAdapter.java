package com.example.step05customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CountryAdapter extends BaseAdapter{
    Context context;
    int layoutRes;
    List<CountryDto> list;

    public CountryAdapter(Context context, int layoutRes, List<CountryDto> list){
        //생성자의 인자로 전달된 값을 필드에 저장
        this.context=context;
        this.layoutRes=layoutRes;
        this.list=list;
    }
    /*
    아래 4개의 메소드는 ListView가 필요시 호출하는 메소드이다.
    따라서 적절한 값을 리턴하도록 우리가 프로그래밍 해야한다.
     */
    @Override
    public int getCount() {
        //모델의 크기
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        //i번쨰 인덱스에 해당하는 모델을 리턴
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        //i번째 인덱스에 해당하는 모델의 id(primary key값) db에서 불러온게 아니므로 그냥 인덱스를 아이디 값으로 사용
        return i;
    }
    //인자로 전달된 position에 해당하는 cell view를 만들어서 리턴하거나
    //이미 만들어진 cell view의 내용만 만들어서 리턴해준다.
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //1.res/layout/listview_cell.xml 문서를 전개해서 View객체를 만든다.
        if(view==null){
            //레이아웃전개자(레이아웃 xml 문서를 이용해서 view를 만드는 객체) 객체의 참조값을 얻어오기
            LayoutInflater inflater=LayoutInflater.from(context);
            //listview_cell.xml을 이용해서 view 생성
            view=inflater.inflate(layoutRes, viewGroup, false);
        }
        //2. i에 해당하는 CountryDto 객체의 참조값을 얻어온다.
        CountryDto dto=list.get(i);
        //3. 만든 View 객체 안에 있는 ImageView, TextView의 참조값을 얻어온다.
        ImageView imageView=view.findViewById(R.id.imageView);
        TextView textView=view.findViewById(R.id.textView2);
        //4. ImageView, TextView에 정보를 출력한다.
        imageView.setImageResource(dto.getResId());
        textView.setText(dto.getName());
        //i번쨰 인덱스에 해당하는 View를 리턴해준다.
        return view;
    }
}
