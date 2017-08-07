package com.flyaswind.piedemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.flyaswind.customchartview.ChartView;
import com.flyaswind.customchartview.ColmnarBean;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ChartView chartView= (ChartView) findViewById(R.id.pieview);
        ArrayList datas=new ArrayList();
        ColmnarBean columaBean;
        for (int i = 0; i < 16; i++) {
            columaBean=new ColmnarBean();
            columaBean.setName("张三"+i);
            columaBean.setColumaAmoutA(80);
            columaBean.setColumaAmoutB(60);
            datas.add(columaBean);
        }

        chartView.setColumaData(datas);

    }
}
