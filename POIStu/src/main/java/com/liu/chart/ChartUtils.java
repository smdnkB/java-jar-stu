package com.liu.chart;

import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xddf.usermodel.XDDFShapeProperties;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.SchemaType;
import org.apache.xmlbeans.XmlException;
import org.apache.xmlbeans.impl.schema.SchemaTypeImpl;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;
import org.openxmlformats.schemas.drawingml.x2006.chart.impl.CTSerTxImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.impl.CTSolidColorFillPropertiesImpl;
import org.openxmlformats.schemas.drawingml.x2006.main.impl.CTTextBodyPropertiesImpl;

import java.util.*;

public class ChartUtils {

    /**
     * 绘制折线图
     * 外部传入要绘制的chart对象
     * 并传入数据
     * 方法内部负责帮你绘制完成
     * @param chart 要绘制的chart对象
     */
    public static void createLineChart(XDDFChart chart,ChartConf conf){

        XSSFWorkbook workbook = conf.getWorkbook();
        XSSFSheet sheet = workbook.getSheetAt(0);
        ArrayList<String> seriesName = conf.getSeriesName();

        // 3、图表相关设置
        chart.setWorkbook(workbook);
        chart.setTitleText("使用POI创建的折线图"); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置
        XDDFCategoryAxis xAxis = chart.createCategoryAxis(AxisPosition.BOTTOM); // 创建X轴,并且指定位置
        xAxis.setTitle(conf.getxTitle()); // x轴标题
        org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(0,0,1,5));

        // 6、Y轴(值轴)相关设置
        XDDFValueAxis yAxis = chart.createValueAxis(AxisPosition.LEFT); // 创建Y轴,指定位置
        yAxis.setTitle(conf.getyTitle()); // Y轴标题

        ArrayList<XDDFNumericalDataSource<Double>> yAxisSourceList = new ArrayList<>();
        for (int i = 0; i < seriesName.size(); i++) {
            XDDFNumericalDataSource<Double> yAxisSource = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1+i, 1+i, 1, 5));
            yAxisSourceList.add(yAxisSource);
        }

        // 7、创建折线图对象
        XDDFLineChartData lineChart = (XDDFLineChartData) chart.createData(ChartTypes.LINE, xAxis, yAxis);

        // 8、加载折线图数据集
        for (int i = 0; i < yAxisSourceList.size(); i++) {
            XDDFLineChartData.Series lineSeries = (XDDFLineChartData.Series) lineChart.addSeries(xAxisSource, yAxisSourceList.get(i));
            lineSeries.setTitle(seriesName.get(i), new CellReference(sheet.getRow(i+1).getCell(0)));
            //        lineSeries.setSmooth(true); // 线条样式:true平滑曲线,false折线
            //        lineSeries.setMarkerSize((short) 6); // 标记点大小
            //        lineSeries.setMarkerStyle(MarkerStyle.CIRCLE); // 标记点样式
        }
        // 9、绘制折线图
        chart.plot(lineChart);
    }

    /**
     * 绘制条形图 (柱状图)
     * @param chart 要绘制的chart对象
     */
    public static void createBarChart(XDDFChart chart, ChartConf conf) throws XmlException {
        XSSFWorkbook workbook = conf.getWorkbook();
        XSSFSheet sheet = workbook.getSheetAt(0);
        ArrayList<String> seriesName = conf.getSeriesName();

        // 3、图表相关设置
        chart.setWorkbook(workbook);
        chart.setTitleText(conf.getxTitle()); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

        // 5、X轴(分类轴)相关设置
        XDDFCategoryAxis xAxis = chart.createCategoryAxis(AxisPosition.BOTTOM); // 创建X轴,并且指定位置
        xAxis.setTitle(conf.getxTitle()); // x轴标题
        // x轴数据
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(0,0,1,conf.getCategoryNum()));

        // 6、Y轴(值轴)相关设置
        XDDFValueAxis yAxis = chart.createValueAxis(AxisPosition.LEFT); // 创建Y轴,指定位置
        yAxis.setTitle(conf.getyTitle()); // Y轴标题

        ArrayList<XDDFNumericalDataSource<Double>> yAxisSourceList = new ArrayList<>();
        for (int i = 0; i < seriesName.size(); i++) {
            // y轴数据
            XDDFNumericalDataSource<Double> yAxisSource = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1+i, 1+i, 1, conf.getCategoryNum()));
            yAxisSourceList.add(yAxisSource);
        }
        // 7、创建柱状图对象
        XDDFBarChartData barChart = (XDDFBarChartData) chart.createData(ChartTypes.BAR, xAxis, yAxis);

        for (int i = 0; i < yAxisSourceList.size(); i++) {
            XDDFBarChartData.Series lineSeries = (XDDFBarChartData.Series) barChart.addSeries(xAxisSource, yAxisSourceList.get(i));
            lineSeries.setTitle(seriesName.get(i), new CellReference(sheet.getRow(i+1).getCell(0)));
//            lineSeries.setTitle(seriesName.get(i), null); // 图例标题

            lineSeries.setTitle(seriesName.get(i), new CellReference(sheet.getRow(i+1).getCell(0)));
            CTDLbls dLbls = chart.getCTChart().getPlotArea().getBarChartArray(0).getSerArray(i).addNewDLbls();
//            lineSeries.setShapeProperties(new XDDFShapeProperties());
            setChart(dLbls,conf);
        }

        // 这一句是y轴的一个操作，也没懂什么意思，但是没有这个，画的图表会超出画布范围
        yAxis.setCrossBetween(AxisCrossBetween.BETWEEN);
        // 柱状图的类型，不是有什么堆积。。。的
        barChart.setBarGrouping(BarGrouping.STACKED); // STANDARD 正常图 // STACKED 堆积图
        // 柱状图的方向
        barChart.setBarDirection(BarDirection.BAR); // COL 柱状图 BAR 横向条形图
        // 是否设置不同的颜色 false就行
        barChart.setVaryColors(false);

        CTOverlap ctOverlap = CTOverlap.Factory.parse("<xml-fragment val='100'/>");
        chart.getCTChart().getPlotArea().getBarChartArray(0).setOverlap(ctOverlap);

        // 9、绘制柱状图
        chart.plot(barChart);


    }


    public static void setChart(CTDLbls dLbls, ChartConf conf){
        dLbls.addNewShowVal().setVal(true); //图上面 显示数据
        dLbls.addNewShowLegendKey().setVal(false);
        dLbls.addNewShowCatName().setVal(false);//图上面  类别名称
        dLbls.addNewShowSerName().setVal(false);
        dLbls.addNewShowPercent().setVal(false);//图上面  显示百分比
        dLbls.addNewShowLeaderLines().setVal(false);// 引导线
        dLbls.setSeparator("\n");// 分隔符为分行符
    }

    /**
     * 饼图
     * @param chart
     * @param conf
     */
    public static void createPieChart(XDDFChart chart, ChartConf conf){
        XSSFWorkbook workbook = conf.getWorkbook();
        XSSFSheet sheet = workbook.getSheetAt(0);
        ArrayList<String> seriesName = conf.getSeriesName();

        // 3、图表相关设置
        chart.setWorkbook(workbook);
        chart.setTitleText(conf.getxTitle()); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

/*        // 5、X轴(分类轴)相关设置
        XDDFCategoryAxis xAxis = chart.createCategoryAxis(AxisPosition.BOTTOM); // 创建X轴,并且指定位置
        xAxis.setTitle(conf.getxTitle()); // x轴标题*/
        // x轴数据
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(0,0,1,conf.getCategoryNum()));

/*        // 6、Y轴(值轴)相关设置
        XDDFValueAxis yAxis = chart.createValueAxis(AxisPosition.LEFT); // 创建Y轴,指定位置
        yAxis.setTitle(conf.getyTitle()); // Y轴标题*/

        ArrayList<XDDFNumericalDataSource<Double>> yAxisSourceList = new ArrayList<>();
        for (int i = 0; i < seriesName.size(); i++) {
            // y轴数据
            XDDFNumericalDataSource<Double> yAxisSource = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1+i, 1+i, 1, conf.getCategoryNum()));
            yAxisSourceList.add(yAxisSource);
        }
        // 7、创建饼图对象,饼状图不需要X,Y轴,只需要数据集即可
        XDDFPieChartData pieChart = (XDDFPieChartData) chart.createData(ChartTypes.PIE, null, null);

        // 8、加载饼图数据集
        for (int i = 0; i < yAxisSourceList.size(); i++) {
            XDDFPieChartData.Series lineSeries = (XDDFPieChartData.Series) pieChart.addSeries(xAxisSource, yAxisSourceList.get(i));
            lineSeries.setTitle(seriesName.get(i), new CellReference(sheet.getRow(i+1).getCell(0)));

        }
        // 9、绘制饼图
        chart.plot(pieChart);
    }

    /**
     * 环形图
     * @param chart
     * @param conf
     */
    public static void createDoughnutChart(XDDFChart chart, ChartConf conf){
        XSSFWorkbook workbook = conf.getWorkbook();
        XSSFSheet sheet = workbook.getSheetAt(0);
        ArrayList<String> seriesName = conf.getSeriesName();

        // 3、图表相关设置
        chart.setWorkbook(workbook);
        chart.setTitleText(conf.getxTitle()); // 图表标题
        chart.setTitleOverlay(false); // 图例是否覆盖标题

        // 4、图例设置
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.TOP); // 图例位置:上下左右

/*        // 5、X轴(分类轴)相关设置
        XDDFCategoryAxis xAxis = chart.createCategoryAxis(AxisPosition.BOTTOM); // 创建X轴,并且指定位置
        xAxis.setTitle(conf.getxTitle()); // x轴标题*/
        // x轴数据
        XDDFCategoryDataSource xAxisSource = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(0,0,1,conf.getCategoryNum()));

/*        // 6、Y轴(值轴)相关设置
        XDDFValueAxis yAxis = chart.createValueAxis(AxisPosition.LEFT); // 创建Y轴,指定位置
        yAxis.setTitle(conf.getyTitle()); // Y轴标题*/

        ArrayList<XDDFNumericalDataSource<Double>> yAxisSourceList = new ArrayList<>();
        for (int i = 0; i < seriesName.size(); i++) {
            // y轴数据
            XDDFNumericalDataSource<Double> yAxisSource = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1+i, 1+i, 1, conf.getCategoryNum()));
            yAxisSourceList.add(yAxisSource);
        }
        // 7、创建饼图对象,饼状图不需要X,Y轴,只需要数据集即可
        XDDFDoughnutChartData doughnutChart = (XDDFDoughnutChartData) chart.createData(ChartTypes.DOUGHNUT, null, null);
//        doughnutChart.setHoleSize(50);

        // 8、加载饼图数据集
        for (int i = 0; i < yAxisSourceList.size(); i++) {
            XDDFDoughnutChartData.Series lineSeries = (XDDFDoughnutChartData.Series) doughnutChart.addSeries(xAxisSource, yAxisSourceList.get(i));
            lineSeries.setTitle(seriesName.get(i), new CellReference(sheet.getRow(i+1).getCell(0)));
        }

        // 9、绘制饼图
        chart.plot(doughnutChart);
    }


}
