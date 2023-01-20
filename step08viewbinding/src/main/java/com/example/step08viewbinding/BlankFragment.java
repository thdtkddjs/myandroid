package com.example.step08viewbinding;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.step08viewbinding.databinding.FragmentBlankBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BlankFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlankFragment extends Fragment {
    FragmentBlankBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BlankFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BlankFragment newInstance(String param1, String param2) {
        BlankFragment fragment = new BlankFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //view binding을 이용하려면 아래와 같이 코딩해야 한다.
        binding=FragmentBlankBinding.inflate(inflater,container,false);
        View view=binding.getRoot();
        //만일 fragment_blank.xml 문서에 myViewText라는 객체의 참조값이 필요하다면
        //TextView a=binding.myTextView;

        return view;
    }
    //프래그먼트가 레이웃으로 가지고 있는 뷰가 파괴될때 호출되는 메소드
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //메모리의 효율성을 위해 참조를 강제로 해제해준다.
        //프래그먼트보다 뷰가 오래 살아남는 경우가 있기 때문
        binding = null;
    }
}