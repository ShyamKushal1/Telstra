package com.e.telstra.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.e.telstra.R;

public class NewsListAdapter extends BaseAdapter {

    LayoutInflater inflater=null;
    ConstraintLayout constraintLayout;
    TextView tvTitle,tvDisc;
    ImageView ivImage;


    public NewsListAdapter() {
        
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view=inflater.inflate(R.layout.list_items,viewGroup,false);
        constraintLayout=view.findViewById(R.id.cl_Layout);
        hookViews(constraintLayout);

        return view;
    }

    private void hookViews(ViewGroup vg) {
        for (int i=0;i<vg.getChildCount();i++){
            View view=vg.getChildAt(i);
            if (view instanceof TextView){
                switch (view.getId()){
                    case R.id.tv_Title:
                        tvTitle=(TextView) view;
                        break;
                    case R.id.tv_Disc:
                        tvDisc= (TextView) view;
                        break;
                }
            }
            if (view instanceof ImageView){
                switch (view.getId()){
                    case R.id.iv_Image:
                        ivImage= (ImageView) view;
                        break;
                }
            }
        }

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }


}
