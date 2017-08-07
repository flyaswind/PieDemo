package com.flyaswind.customchartview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sunfei on 2017/8/5.
 */
public class ChartView extends View {

    private int xyLineColor;
    private int xNameColor;
    private int yNameColor;
    private int aColor;
    private int bColor;
    private int yDividerColor;
    private float columnarWidth, columnSpace;
    private float xNameSize, yNameSize;
    private float xLeftSpace, yBottomSpace, yTopSpace;
    private float beanUnitSpace;
    private float columnRadius;
    private float yDividerSize;
    private float buttomTextSpace;
    private Paint mPaint;
    private List<ColmnarBean> colmnarBeans;
    private final float FULL_AMOUNT = 100;

    public ChartView(Context context) {
        this(context, null);
    }

    public ChartView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ChartView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getXmlAttrs(context, attrs);
        initTools();
    }

    private void getXmlAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ChartView);
        if (typedArray != null) {
            xyLineColor = typedArray.getColor(R.styleable.ChartView_xy_line_color, Color.BLACK);
            xNameColor = typedArray.getColor(R.styleable.ChartView_x_name_color, Color.RED);
            yNameColor = typedArray.getColor(R.styleable.ChartView_y_name_color, Color.BLUE);
            aColor = typedArray.getColor(R.styleable.ChartView_a_color, Color.RED);
            bColor = typedArray.getColor(R.styleable.ChartView_b_color, Color.BLUE);
            yDividerColor = typedArray.getColor(R.styleable.ChartView_y_divider_color, Color.GREEN);
            columnarWidth = typedArray.getDimension(R.styleable.ChartView_columnar_width, 15);
            columnSpace = typedArray.getDimension(R.styleable.ChartView_columnar_space, 20);
            xNameSize = typedArray.getDimension(R.styleable.ChartView_x_name_size, 30);
            yNameSize = typedArray.getDimension(R.styleable.ChartView_y_name_size, 30);
            xLeftSpace = typedArray.getDimension(R.styleable.ChartView_x_left_space, 50);
            yTopSpace = typedArray.getDimension(R.styleable.ChartView_y_top_space, 30);
            yBottomSpace = typedArray.getDimension(R.styleable.ChartView_y_bottom_space, 100);
            beanUnitSpace = typedArray.getDimension(R.styleable.ChartView_bean_unit_space, 50);
            columnRadius = typedArray.getDimension(R.styleable.ChartView_columar_radius, 15);
            yDividerSize = typedArray.getDimension(R.styleable.ChartView_y_divider_size, 14);
            buttomTextSpace = typedArray.getDimension(R.styleable.ChartView_x_name_space_to_x, 10);
            typedArray.recycle();
        }
    }


    private void initTools() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先画好xy轴
        mPaint.setColor(xyLineColor);
        float xLineTop = getHeight() - yBottomSpace;//x轴离上面的距离
        float yLineTop = yTopSpace;//y轴离上面的距离
        if (getWidth() - xLeftSpace > 0)
            canvas.drawLine(xLeftSpace, xLineTop, getWidth() - xLeftSpace, xLineTop, mPaint);
        canvas.drawLine(xLeftSpace, yLineTop, xLeftSpace, xLineTop, mPaint);
        //y轴分割线
        mPaint.setColor(yDividerColor);
        float spaceVertical = (getHeight() - yTopSpace - yBottomSpace) / 6;
        float[] pts = {xLeftSpace - yDividerSize / 2, yLineTop, xLeftSpace + yDividerSize / 2, yLineTop,
                xLeftSpace - yDividerSize / 2, yLineTop + spaceVertical, xLeftSpace + yDividerSize / 2, yLineTop + spaceVertical,
                xLeftSpace - yDividerSize / 2, yLineTop + spaceVertical * 2, xLeftSpace + yDividerSize / 2, yLineTop + spaceVertical * 2,
                xLeftSpace - yDividerSize / 2, yLineTop + spaceVertical * 3, xLeftSpace + yDividerSize / 2, yLineTop + spaceVertical * 3,
                xLeftSpace - yDividerSize / 2, yLineTop + spaceVertical * 4, xLeftSpace + yDividerSize / 2, yLineTop + spaceVertical * 4,
                xLeftSpace - yDividerSize / 2, yLineTop + spaceVertical * 5, xLeftSpace + yDividerSize / 2, yLineTop + spaceVertical * 5

        };
        canvas.drawLines(pts, mPaint);
        //y轴内容
        mPaint.setColor(yNameColor);
        Rect numberBound;
        mPaint.setTextSize(yNameSize);
        for (int i = 0; i < 6; i++) {
            String number = 6 - i + "";
            numberBound = new Rect();
            mPaint.getTextBounds(number, 0, number.length(), numberBound);
            canvas.drawText(number, (xLeftSpace - 7 - numberBound.width()) / 2, yLineTop + (spaceVertical * i) + numberBound.height() / 2, mPaint);

        }
        if (colmnarBeans == null)
            return;
        //遍历内容，画柱状图
        float fullHeight = getHeight() - yTopSpace - yBottomSpace;

        for (int i = 0; i < colmnarBeans.size(); i++) {
            float left = xLeftSpace + (i + 1) * beanUnitSpace + (columnarWidth * 2 + columnSpace) * i;
            //画柱状图 圆角矩形
            mPaint.setColor(aColor);
            ColmnarBean colmnarBean = colmnarBeans.get(i);
            float aHeight = xLineTop - (fullHeight * colmnarBean.getColumaAmoutA() / FULL_AMOUNT);
            float bHeight = xLineTop - (fullHeight * colmnarBean.getColumaAmoutB() / FULL_AMOUNT);
            canvas.drawRoundRect(new RectF(left, aHeight, left + columnarWidth, xLineTop + columnRadius), columnRadius, columnRadius, mPaint);
            mPaint.setColor(bColor);
            canvas.drawRoundRect(new RectF(left + columnarWidth + columnSpace, bHeight, left + columnarWidth * 2 + columnSpace, xLineTop + columnRadius), columnRadius, columnRadius, mPaint);
            //下面取消圆角
            Drawable background = getBackground();
            if (background!=null) {
                try {
                    ColorDrawable colorDrawable = (ColorDrawable) background;
                    mPaint.setColor(colorDrawable.getColor());
                }catch (Exception e){
                    mPaint.setColor(Color.WHITE);
                }

            }else{
                mPaint.setColor(Color.WHITE);
            }
            canvas.drawRect(new RectF(left, xLineTop, left + columnarWidth, xLineTop + columnRadius), mPaint);
            canvas.drawRect(new RectF(left + columnarWidth + columnSpace, xLineTop, left + columnarWidth * 2 + columnSpace, xLineTop + columnRadius), mPaint);
            mPaint.setColor(xyLineColor);
            canvas.drawLines(new float[]{left, xLineTop, left + columnarWidth, xLineTop,
                    left + columnarWidth + columnSpace, xLineTop, left + columnarWidth * 2 + columnSpace, xLineTop

            }, mPaint);
            //画出名字
            mPaint.setColor(xNameColor);
            Rect bounds = new Rect();
            String name = colmnarBeans.get(i).getName();
            mPaint.setTextSize(xNameSize);
            mPaint.getTextBounds(name, 0, name.length(), bounds);
            canvas.drawText(name, left - (bounds.width() / 2 - (columnarWidth * 2 + columnSpace) / 2), xLineTop + bounds.height() + buttomTextSpace, mPaint);
        }

    }

    public void setColumaData(List<ColmnarBean> data) {
        colmnarBeans = new ArrayList<>();
        colmnarBeans.addAll(data);
        WindowManager wm = (WindowManager) getContext()
                .getSystemService(Context.WINDOW_SERVICE);

        int width = wm.getDefaultDisplay().getWidth();
        float fullCount = (width - xLeftSpace - beanUnitSpace) / (beanUnitSpace + columnarWidth * 2 + columnSpace);
        if (data.size() > fullCount) {
            setMinimumWidth((int) (xLeftSpace * 2 + beanUnitSpace + data.size() * (beanUnitSpace + columnarWidth * 2 + columnSpace)));
        } else {
            setMinimumWidth(width);
        }
        invalidate();
    }


}