package com.flyaswind.piedemo.custom;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.flyaswind.piedemo.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunfei on 2017/7/31.
 */
public class PieView extends View {
    private List<PieData> mDatas;
    private int[] mColors = {0xFFCCFF00, 0xFF6405ED, 0xFFE32636, 0xFFFF8C69, 0xFF7CFC00};
    private Paint mPaint;
    private int startPos;
    private RectF ova;

    public PieView(Context context) {
        this(context, null);
    }

    public PieView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PieView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        initTools();
        initData();
    }

    /**
     * 初始化假数据
     */
    private void initData() {
        mDatas = new ArrayList<>();
        PieData data;
        for (int i = 0; i < 5; i++) {
            data = new PieData();
            data.setPercentNum(72);
//            if (i%2==0){
            data.setColor(mColors[i]);
//            }else {
//                data.setColor(R.color.mygreen);
//            }
            mDatas.add(data);
        }
    }

    private void initTools() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        ova = new RectF(100, 100, 500, 500);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mDatas == null)
            return;
        for (int i = 0; i < mDatas.size(); i++) {
            mPaint.setColor(mDatas.get(i).getColor());
            // canvas.drawArc(ova,startPos,mDatas.get(i).getPercentNum(),false,mPaint);
            startPos += mDatas.get(i).getPercentNum();
        }

        //画一个矩形，圆角的
        mPaint.setColor(getResources().getColor(R.color.myred));
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(1);
        canvas.drawRoundRect(new RectF(100, 600, 120, 1000), 15, 15, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(100, 985, 120, 1000), mPaint);
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(50, 985, 600, 985, mPaint);
        canvas.drawLine(50, 385, 50, 985, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawRoundRect(new RectF(140, 800, 160, 1000), 15, 15, mPaint);
        mPaint.setColor(Color.WHITE);
        canvas.drawRect(new RectF(140, 985, 160, 1000), mPaint);
        mPaint.setColor(Color.RED);
        float[] pts = {43, 385, 57, 385,
                43, 485, 57, 485,
                43, 575, 57, 575,
                43, 685, 57, 685,
                43, 785, 57, 785,
                43, 885, 57, 885,

        };
        canvas.drawLines(pts, mPaint);
        Rect bounds = new Rect();
        String name = "张佳佳";
        mPaint.setTextSize(30);
        mPaint.getTextBounds(name, 0, name.length(), bounds);
        Log.i("height--", bounds.height() + "===");
        canvas.drawText(name, 100 - (bounds.width() / 2 - 30), 1000 + bounds.height(), mPaint);
        mPaint.setColor(Color.BLUE);


        Rect numberBound;
        mPaint.setTextSize(30);
        for (int i = 0; i < 6; i++) {
            String number = 6 - i + "";
            numberBound = new Rect();
            mPaint.getTextBounds(number, 0, number.length(), numberBound);
            canvas.drawText(number, (43 - numberBound.width()) / 2, 385 + (100 * i) + numberBound.height() / 2, mPaint);

        }
//        String number="6";
//        Rect numberBound = new Rect();
//        mPaint.setTextSize(30);
//        mPaint.getTextBounds(number, 0, number.length(), numberBound);
//        canvas.drawPosText("654321", new float[]{
//                (43 - numberBound.width()) / 2, 385 + numberBound.height() / 2,
//                (43 - numberBound.width()) / 2, 485 + numberBound.height() / 2,
//                (43 - numberBound.width()) / 2, 585 + numberBound.height() / 2,
//                (43 - numberBound.width()) / 2, 685 + numberBound.height() / 2,
//                (43 - numberBound.width()) / 2, 785 + numberBound.height() / 2,
//                (43 - numberBound.width()) / 2, 885 + numberBound.height() / 2
//        }, mPaint);


    }

}
