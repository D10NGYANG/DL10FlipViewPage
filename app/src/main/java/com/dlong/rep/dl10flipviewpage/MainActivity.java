package com.dlong.rep.dl10flipviewpage;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dlong.rep.dlflipviewpage.bean.DLGridViewBean;
import com.dlong.rep.dlflipviewpage.indicator.CirclePageIndicator;
import com.dlong.rep.dlflipviewpage.utils.DLVPSetting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Context mContext = this;
    private ViewPager view_pager;
    private CirclePageIndicator indicator;

    private List<DLGridViewBean> dataList;
    private ArrayList<Map<String, Object>> dataList2;

    private DLVPSetting setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
        initDatas2();
    }

    private void initview() {
        view_pager = (ViewPager) findViewById(R.id.viewPager);
        indicator = (CirclePageIndicator) findViewById(R.id.indicator);
    }

    private void initDatas() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 22; i++) {
            DLGridViewBean bean = new DLGridViewBean();
            bean.setText("靓仔"+i);
            bean.setImg(R.mipmap.ima);
            dataList.add(bean);
        }
        setting = new DLVPSetting(mContext, 4, 4, new DLVPSetting.OnClickItemListener() {
            @Override
            public void OnClickItem(int position, DLGridViewBean bean) {
                Toast.makeText(mContext, bean.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void OnClickItem(int position, Map<String, Object> map) {

            }
        });
        view_pager.setAdapter(setting.getAdapter(dataList));
        indicator.setViewPager(view_pager);
    }

    private void initDatas2() {
        String[] from = new String[]{"txt", "img"};
        int[] to = new int[]{R.id.tv_text, R.id.iv_img};
        dataList2 = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            Map<String, Object> bean = new HashMap<>();
            bean.put("txt", "家园"+i);
            bean.put("img", R.drawable.home);
            dataList2.add(bean);
        }
        setting = new DLVPSetting(mContext, 3, 3, new DLVPSetting.OnClickItemListener() {
            @Override
            public void OnClickItem(int position, DLGridViewBean bean) {

            }

            @Override
            public void OnClickItem(int position, Map<String, Object> map) {
                Toast.makeText(mContext, (String) map.get("txt"), Toast.LENGTH_SHORT).show();
            }
        });
        view_pager.setAdapter(setting.getAdapter(dataList2, R.layout.item_main_mode, from, to));
        indicator.setViewPager(view_pager);
    }
}
