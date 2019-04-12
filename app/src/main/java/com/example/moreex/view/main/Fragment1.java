package com.example.moreex.view.main;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapUtils;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.example.moreex.R;
import com.example.moreex.presenter.Fragment1Presenter;
import com.example.moreex.view.BaseActivity;

import java.lang.reflect.Field;

import at.markushi.ui.CircleButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment implements IFragment1, AMapLocationListener {

    private static final String TAG = "Fragment1";
    public Fragment1Presenter presenter = new Fragment1Presenter(this);

    //地图
    private TextureMapView textureMapView;
    private AMap aMap;
    protected static CameraPosition cameraPosition;

    //工具
    private TextView textViewMiles;
    private TextView textViewTime;
    private CircleButton buttonPlay;

    //定位
    private AMapLocationClient mLocationClient;
    private AMapLocationClientOption mLocationOption;

    //轨迹
    private PolylineOptions mPolyoptions;
    private Polyline mpolyline;

    //位置时间
    LatLng myLastLocation = null;
    double betweenDistance = 0;
    long mStartTime = System.currentTimeMillis();
    long mCurrentTime = System.currentTimeMillis();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment1,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        textureMapView = getView().findViewById(R.id.map);
        if(textureMapView != null){
            textureMapView.onCreate(savedInstanceState);
            aMap = textureMapView.getMap();
            if (getCameraPosition() == null) {
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition(getTarget(), 18, 0, 0)));
            }else {
                aMap.moveCamera(CameraUpdateFactory.newCameraPosition(getCameraPosition()));
            }
        }

        customMap();

        textViewMiles = getView().findViewById(R.id.textView_miles);
        textViewTime = getView().findViewById(R.id.textView_time);
        buttonPlay = getView().findViewById(R.id.button_play);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!BUTTON_STATE_PLAY){
                    presenter.requestStartSport();
                }
                else{
                    presenter.requestEndSport();
                }
            }
        });
    }

    //定制地图
    private void customMap(){
        initBluePoint();
        customUiSettings();
        initPolyLine();
    }

    //小蓝点
    private void initBluePoint(){
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(1000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        myLocationStyle.strokeColor(Color.argb(0, 0, 0, 0));// 设置圆形的边框颜色
        myLocationStyle.radiusFillColor(Color.argb(0, 0, 0, 0));// 设置圆形的填充颜色

        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
    }

    //轨迹初始化
    private void initPolyLine(){
        mPolyoptions = new PolylineOptions();
        mPolyoptions.width(10f);
        mPolyoptions.color(Color.GRAY);
    }

    //定制控件交互
    private void customUiSettings(){
        UiSettings uiSettings = aMap.getUiSettings();
        uiSettings.setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        uiSettings.setZoomControlsEnabled(false);
        uiSettings.setCompassEnabled(true);
        uiSettings.setScaleControlsEnabled(true);
    }

    public void stopLocation(){
        mLocationClient.stopLocation();
    }

    public void startLocation(){
        mLocationClient = new AMapLocationClient(getContext());
        mLocationOption = new AMapLocationClientOption();
        //设置定位监听
        mLocationClient.setLocationListener(this);
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(1000);

        mLocationClient.setLocationOption(mLocationOption);
        mLocationClient.startLocation();


    }

    @Override
    public void onLocationChanged(AMapLocation aMapLocation) {
        LatLng myLocation = new LatLng(aMapLocation.getLatitude(),aMapLocation.getLongitude());
        submitPerFive(myLocation);
        Log.d(TAG, myLocation.toString());

        if(myLastLocation != null){
            betweenDistance += AMapUtils.calculateLineDistance(myLastLocation,myLocation);
            mCurrentTime = System.currentTimeMillis();

            textViewMiles.setText("路程/m\n"+String.format("%.2f", betweenDistance));
            textViewTime.setText("时间/s\n"+(mCurrentTime-mStartTime)/1000);
        }
        myLastLocation = myLocation;

        //轨迹
        mPolyoptions.add(myLocation);
        reDrawLine();
    }

    //实时轨迹画线
    private void reDrawLine(){
        if(mPolyoptions.getPoints().size()>1){
            if(mpolyline != null){
                mpolyline.setPoints(mPolyoptions.getPoints());
            }else{
                mpolyline = aMap.addPolyline(mPolyoptions);
            }
        }
    }


    public int RECORD_TIMES = 0;
    public void submitPerFive(LatLng myLocation){
        RECORD_TIMES++;
        if(RECORD_TIMES==5){
            presenter.requestSubmitTracePoint(myLocation);
            RECORD_TIMES = 0;
        }
    }



    public Fragment1() {
        // Required empty public constructor
    }

    public LatLng getTarget(){
        return new LatLng(32.04,118.78	);
    }

    public CameraPosition getCameraPosition(){
        return cameraPosition;
    }

    public void setCameraPosition(CameraPosition cameraPosition){
        Fragment1.cameraPosition = cameraPosition;
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onResume() {
        super.onResume();
        textureMapView.onResume();
        resumeButtonColor();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onPause() {
        super.onPause();
        textureMapView.onPause();
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        textureMapView.onSaveInstanceState(outState);
    }

    /**
     * 方法必须重写
     */
    @Override
    public void onDestroy() {
        setCameraPosition(aMap.getCameraPosition());
        super.onDestroy();
        textureMapView.onDestroy();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    //按钮更换颜色
    private boolean BUTTON_STATE_PLAY = false;
    public void buttonChangeColor(){
        if(!BUTTON_STATE_PLAY){
            buttonPlay.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
            BUTTON_STATE_PLAY = true;
        }
        else{
            buttonPlay.setColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
            BUTTON_STATE_PLAY = false;
        }
    }
    //回复按钮颜色
    public void resumeButtonColor(){
        if(BUTTON_STATE_PLAY){
            buttonPlay.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
        }
    }


    @Override
    public void showLoading() {
        getSelfActivity().showLoading();
    }

    @Override
    public void hideLoading() {
        getSelfActivity().hideLoading();
    }

    @Override
    public void showToast(String msg) {
        getSelfActivity().showToast(msg);
    }

    @Override
    public BaseActivity getSelfActivity() {
        return (BaseActivity) getActivity();
    }

    @Override
    public void onSuccessStartSport() {
        buttonChangeColor();
        startLocation();

        //启动通知栏
        mLocationClient.enableBackgroundLocation(2333,getSelfActivity().buildMapNotification());

        //位置时间初始化
        textViewMiles.setText("路程/m\n"+0);
        textViewTime.setText("时间/s\n"+0/1000);
        myLastLocation = null;
        betweenDistance = 0;
        mStartTime = System.currentTimeMillis();

        //轨迹
        if(mpolyline!=null)
            mpolyline.remove();

    }

    @Override
    public void onSuccessEndSport() {
        buttonChangeColor();
        stopLocation();

        //停止通知栏
        mLocationClient.disableBackgroundLocation(true);

    }

    //用于修复按钮颜色bug
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser)
            resumeButtonColor();
    }
}
