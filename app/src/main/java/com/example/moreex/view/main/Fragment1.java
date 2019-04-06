package com.example.moreex.view.main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.MyLocationStyle;
import com.example.moreex.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment {


    public Fragment1() {
        // Required empty public constructor
    }

    MapView mMapView = null;
    AMap aMap = null;
    MyLocationStyle myLocationStyle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);

        //地图初始化
        initView(savedInstanceState,view);
        //蓝点初始化
        initBluePoint();

        //todo can't find bluepoint
        return view;
    }

    //初始化地图
    private void initView( Bundle savedInstanceState,View view){
        //获取地图控件引用
        mMapView = view.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);

        //初始化地图控制器
        if(aMap == null){
            aMap = mMapView.getMap();
        }
    }

    //初始化蓝点
    private void initBluePoint(){
        myLocationStyle = new MyLocationStyle();    //初始化蓝点样式类

        //连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。
        //如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_SHOW);
        myLocationStyle.interval(1000);

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点style

        aMap.getUiSettings().setMyLocationButtonEnabled(true);  //设置默认定位按钮
        aMap.setMyLocationEnabled(true);    //true 启动显示定位蓝点,false 表示隐藏定位蓝点并不进行定位
    }







    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mMapView.onSaveInstanceState(outState);
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }
}
