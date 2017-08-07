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
        ColmnarBean columaBean=new ColmnarBean();
        columaBean.setName("张三");
        columaBean.setColumaAmoutA(80);
        columaBean.setColumaAmoutB(60);
        datas.add(columaBean);
        ColmnarBean columaBean1=new ColmnarBean();
        columaBean1.setName("张晓明");
        columaBean1.setColumaAmoutA(50);
        columaBean1.setColumaAmoutB(75);
        datas.add(columaBean1);
        chartView.setColumaData(datas);
    }
}
