package com.app.toast_test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showToast(View view){
        Toast.makeText(this,"自定义Toast,需要权限",Toast.LENGTH_SHORT).show();
    }
}
