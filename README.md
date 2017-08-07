# PieDemo
小试自定义柱状图表

Step 1. Add the JitPack repository to your build file

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.flyaswind:PieDemo:1.2'
	}
  
  Step 3 Demo
      in xml
      <RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        tools:context=".MainActivity">

        <com.flyaswind.customchartview.ChartView
            android:id="@+id/chart_view"
            android:layout_width="match_parent"
            android:layout_height="200dp" />

      </RelativeLayout>
      
      也可以嵌套进scrollview滑动
      
      in activity
      
      ChartView mChartView;
      @Override
      protected void onCreate(Bundle savedInstanceState) {
          super.onCreate(savedInstanceState);
          setContentView(R.layout.activity_main);
          mChartView= (ChartView) findViewById(R.id.chart_view);
          List<ColmnarBean> datas=new ArrayList<>();
          ColmnarBean data;
          for (int i = 0; i < 3; i++) {
              data=new ColmnarBean();
              data.setName("小明"+i);
              data.setColumaAmoutA(30);
              data.setColumaAmoutB(50);
              datas.add(data);
          }
          mChartView.setColumaData(datas);
      }
    
    看到的属性都可以设置在xml中
      <declare-styleable name="ChartView">
          <attr name="xy_line_color" format="color" /><!--xy轴线条颜色-->
          <attr name="x_name_color" format="color" /><!--x轴文字颜色-->
          <attr name="y_name_color" format="color" /><!--y轴文字颜色-->
          <attr name="a_color" format="color" /><!--第一条柱状图颜色-->
          <attr name="b_color" format="color" /><!--第二条柱状图颜色-->
          <attr name="y_divider_color" format="color" /><!--y轴分割的颜色-->
          <attr name="y_divider_size" format="dimension" /><!--y轴分割线长度-->
          <attr name="columnar_width" format="dimension" /><!--条柱状宽度-->
          <attr name="columnar_space" format="dimension" /><!--条柱状间宽度-->
          <attr name="data_space" format="dimension" /><!--数据间宽度-->
          <attr name="x_name_size" format="dimension" /><!--x轴字体大小-->
          <attr name="y_name_size" format="dimension" /><!--数据间宽度-->
          <attr name="x_left_space" format="dimension" /><!--x轴距离左边的距离-->
          <attr name="y_bottom_space" format="dimension" /><!--y轴距离下边的距离-->
          <attr name="y_top_space" format="dimension" /><!--y轴距离上边的距离-->
          <attr name="bean_unit_space" format="dimension" /><!--不同组别间距离-->
          <attr name="columar_radius" format="dimension" /><!--柱状图圆角-->
          <attr name="x_name_space_to_x" format="dimension" /><!--x轴内容与x轴之间的距离-->
      </declare-styleable>
