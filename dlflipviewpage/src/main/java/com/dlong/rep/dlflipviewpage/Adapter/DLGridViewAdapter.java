package com.dlong.rep.dlflipviewpage.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.dlong.rep.dlflipviewpage.R;
import com.dlong.rep.dlflipviewpage.bean.DLGridViewBean;

import java.util.List;

public class DLGridViewAdapter extends BaseAdapter{
    private Context mContext;
    private List<DLGridViewBean> mList;

    public DLGridViewAdapter(Context context, List<DLGridViewBean> list){
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder mHolder;
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_main_mode, parent, false);
            mHolder.iv_img = (ImageView) convertView.findViewById(R.id.iv_img);
            mHolder.tv_text = (TextView) convertView.findViewById(R.id.tv_text);
            mHolder.rl_item = (RelativeLayout) convertView.findViewById(R.id.rl_item);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }
        DLGridViewBean bean = mList.get(position);
        if (bean != null) {
            mHolder.iv_img.setImageResource(bean.getImg());
            mHolder.tv_text.setText(bean.getText());
        }
        return convertView;
    }

    private class ViewHolder{
        private ImageView iv_img;
        private TextView tv_text;
        private RelativeLayout rl_item;
    }
}
