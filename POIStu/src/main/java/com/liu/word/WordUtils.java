package com.liu.word;

import com.liu.chart.ChartConf;
import com.liu.chart.ChartUtils;
import com.liu.ppt.PPTUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.util.Units;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xwpf.usermodel.XWPFChart;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class WordUtils {

    public static String RESOURCE= Objects.requireNonNull(PPTUtils.class.getClassLoader().getResource("")).getPath()+ File.separator;

    /**
     * 折线图
     * @throws IOException
     * @throws InvalidFormatException
     */
    public void createLineChart() throws IOException, InvalidFormatException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();
        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);
        // 3、图表相关设置
        chart.setTitleText("使用POI创建的折线图"); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置
        XDDFCategoryAxis xAxis = chart.createCategoryAxis(AxisPosition.BOTTOM); // 创建X轴,并且指定位置
        xAxis.setTitle("日期（年月）"); // x轴标题
        String[] xAxisData = new String[] {
                "2021-01","2021-02","2021-03","2021-04","2021-05","2021-06",
                "2021-07","2021-08","2021-09","2021-10","2021-11","2021-12",
        };
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromArray(xAxisData); // 设置X轴数据

        // 6、Y轴(值轴)相关设置
        XDDFValueAxis yAxis = chart.createValueAxis(AxisPosition.LEFT); // 创建Y轴,指定位置
        yAxis.setTitle("粉丝数（个）"); // Y轴标题
        Integer[] yAxisData = new Integer[]{
                10, 35, 21, 46, 79, 88,
                39, 102, 71, 28, 99, 57
        };
        XDDFNumericalDataSource<Integer> yAxisSource = XDDFDataSourcesFactory.fromArray(yAxisData); // 设置Y轴数据

        // 7、创建折线图对象
        XDDFLineChartData lineChart = (XDDFLineChartData) chart.createData(ChartTypes.LINE, xAxis, yAxis);

        // 8、加载折线图数据集
        XDDFLineChartData.Series lineSeries = (XDDFLineChartData.Series) lineChart.addSeries(xAxisSource, yAxisSource);
//        lineSeries.setTitle("粉丝数", null); // 图例标题
//        lineSeries.setSmooth(true); // 线条样式:true平滑曲线,false折线
//        lineSeries.setMarkerSize((short) 6); // 标记点大小
//        lineSeries.setMarkerStyle(MarkerStyle.CIRCLE); // 标记点样式

        // 9、绘制折线图
        chart.plot(lineChart);

        // 10、输出到word文档
        FileOutputStream fos =new FileOutputStream(RESOURCE+"test.docx");
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }

    public void createBarChart() throws IOException, InvalidFormatException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();
        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);

        // 3、图表相关设置
        chart.setTitleText("使用POI创建的柱状图"); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置
        XDDFCategoryAxis xAxis = chart.createCategoryAxis(AxisPosition.BOTTOM); // 创建X轴,并且指定位置
        xAxis.setTitle("日期（年月）"); // x轴标题
        String[] xAxisData = new String[] {
                "2021-01","2021-02","2021-03","2021-04","2021-05","2021-06",
                "2021-07","2021-08","2021-09","2021-10","2021-11","2021-12",
        };
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromArray(xAxisData); // 设置X轴数据

        // 6、Y轴(值轴)相关设置
        XDDFValueAxis yAxis = chart.createValueAxis(AxisPosition.LEFT); // 创建Y轴,指定位置
        yAxis.setTitle("粉丝数（个）"); // Y轴标题
        yAxis.setCrossBetween(AxisCrossBetween.BETWEEN); // 设置图柱的位置:BETWEEN居中
        Integer[] yAxisData = new Integer[]{
                10, 35, 21, 46, 79, 88,
                39, 102, 71, 28, 99, 57
        };
        XDDFNumericalDataSource<Integer> yAxisSource = XDDFDataSourcesFactory.fromArray(yAxisData); // 设置Y轴数据

        // 7、创建柱状图对象
        XDDFBarChartData barChart = (XDDFBarChartData) chart.createData(ChartTypes.BAR, xAxis, yAxis);
        barChart.setBarDirection(BarDirection.COL); // 设置柱状图的方向:BAR横向,COL竖向,默认是BAR

        // 8、加载柱状图数据集
        XDDFBarChartData.Series barSeries = (XDDFBarChartData.Series) barChart.addSeries(xAxisSource, yAxisSource);
        barSeries.setTitle("粉丝数", null); // 图例标题

        // 9、绘制柱状图
        chart.plot(barChart);

        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream(RESOURCE+"test.docx");
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }

    public void createPieChart() throws IOException, InvalidFormatException, XmlException {
        // 1、创建word文档对象
        XWPFDocument document = new XWPFDocument();


        // 2、创建chart图表对象,抛出异常
        XWPFChart chart = document.createChart(15 * Units.EMU_PER_CENTIMETER, 10 * Units.EMU_PER_CENTIMETER);

        Map<String,Integer[]> series = new LinkedHashMap<>();
        series.put("SMS",new Integer[]{900,800,600,700,400});
        series.put("LUD",new Integer[]{1000,200,400,300,800});
        ChartConf chartConf = new ChartConf(new String[]{"12/1", "12/2", "12/3", "12/4", "12/5"}, series);
        ChartUtils.createBarChart(chart,chartConf);

        // 10、输出到word文档
        FileOutputStream fos = new FileOutputStream(RESOURCE+"test.docx");
        document.write(fos); // 导出word

        // 11、关闭流
        fos.close();
        document.close();
    }
}
