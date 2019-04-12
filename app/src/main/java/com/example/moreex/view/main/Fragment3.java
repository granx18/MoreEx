package com.example.moreex.view.main;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.moreex.R;
import com.example.moreex.presenter.Fragment3Presenter;
import com.example.moreex.view.BaseActivity;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment implements IFragment3{


    ListView listView;
    public Fragment3Presenter fragment3Presenter = new Fragment3Presenter(this);

    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment3, container, false);
        listView = view.findViewById(R.id.listview_options);

        String[] adapterData = new String[]{"我","更改个人封面","更改密码","关于","感谢"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getSelfActivity()
                 ,android.R.layout.simple_list_item_1,adapterData);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new MyOnItemClickListener());
        // Inflate the layout for this fragment
        return view;
    }

    //定制的listView click listener
    public final int RESULT_LOAD_IMAGE = 1;
    private class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            switch (position) {
                case 0: {
                    Intent i = new Intent(getContext(),Me.class);
                    startActivity(i);
                    break;
                }
                case 1: {
                    //调用系统相册
                    Intent i = new Intent(
                            Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(i,RESULT_LOAD_IMAGE);
                    break;
                }
                case 2:{
                    final EditText et = new EditText(getContext());
                    new AlertDialog.Builder(getContext()).setTitle("更改密码")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setView(et)
                            .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    String input = et.getText().toString();
                                    if (input.equals("")) {
                                        showToast("密码不能为空！");
                                    }
                                    else {
                                        fragment3Presenter.requestChangePwd(et.getText().toString());
                                    }
                                }
                            })
                            .setNegativeButton("取消", null)
                            .show();
                    break;
                }
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            //选择图片
            case RESULT_LOAD_IMAGE :{
                if(resultCode == RESULT_OK && null != data){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getSelfActivity().getContentResolver().query(selectedImage,
                            filePathColumn, null, null, null);
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String picturePath = cursor.getString(columnIndex);
                    //todo 上传图片
                    Bitmap bm = BitmapFactory.decodeFile(picturePath);
                    fragment3Presenter.requestChangePicture(bm);
                    cursor.close();
                }
                break;
            }
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
        return (BaseActivity)this.getActivity();
    }

    @Override
    public void onSuccessChangePwd() {
        getSelfActivity().finish();
    }
}
