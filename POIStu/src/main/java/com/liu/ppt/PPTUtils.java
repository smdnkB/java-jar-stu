package com.liu.ppt;
import com.liu.chart.ChartConf;
import com.liu.chart.ChartUtils;
import org.apache.commons.compress.utils.IOUtils;
import org.apache.poi.sl.usermodel.PictureData;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xddf.usermodel.chart.AxisCrossBetween;
import org.apache.poi.xddf.usermodel.chart.AxisPosition;
import org.apache.poi.xddf.usermodel.chart.BarDirection;
import org.apache.poi.xddf.usermodel.chart.BarGrouping;
import org.apache.poi.xddf.usermodel.chart.ChartTypes;
import org.apache.poi.xddf.usermodel.chart.LegendPosition;
import org.apache.poi.xddf.usermodel.chart.XDDFBarChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryAxis;
import org.apache.poi.xddf.usermodel.chart.XDDFCategoryDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFChartData;
import org.apache.poi.xddf.usermodel.chart.XDDFChartLegend;
import org.apache.poi.xddf.usermodel.chart.XDDFDataSourcesFactory;
import org.apache.poi.xddf.usermodel.chart.XDDFNumericalDataSource;
import org.apache.poi.xddf.usermodel.chart.XDDFValueAxis;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFChart;
import org.apache.poi.xslf.usermodel.XSLFPictureData;
import org.apache.poi.xslf.usermodel.XSLFSheet;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.poi.xslf.usermodel.XSLFTextBox;
import org.apache.poi.xslf.usermodel.XSLFTextParagraph;
import org.apache.poi.xslf.usermodel.XSLFTextRun;
import org.apache.poi.xslf.usermodel.XSLFTextShape;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.XmlException;
import org.openxmlformats.schemas.drawingml.x2006.chart.CTDLbls;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class PPTUtils {

    public static String RESOURCE= Objects.requireNonNull(PPTUtils.class.getClassLoader().getResource("")).getPath()+ File.separator;
    public static String PPT_SOURCE= Objects.requireNonNull(PPTUtils.class.getClassLoader().getResource("")).getPath()+ File.separator+"source.pptx";


    /**
     * 在指定位置 创建具有样式的文本
     * @param slide 某一页ppt
     * @param rectangle2D 文本的坐标
     * 使用模板:  createTextBox(slide, new Rectangle2D.Double(80,50, 1000, 70), TextAlign.LEFT, false, true, false, 40D, Color.decode("#1a335c"), "微软雅黑", " Text");
     */
    public static void createTextBox(XSLFSheet slide, Rectangle2D rectangle2D, TextParagraph.TextAlign textAlign, Boolean italicFlag, Boolean boldFlag,
                                     Boolean underLineFlag, Double fontSize, Color fontColor, String fontFamily, String text) {
        XSLFTextBox textBox = slide.createTextBox();
        textBox.setAnchor(rectangle2D);
        setTextStyle(textBox, textAlign, italicFlag, boldFlag,
                underLineFlag, fontSize, fontColor, fontFamily, text);
    }

    public static void setTextStyle(XSLFTextShape obj, TextParagraph.TextAlign textAlign, Boolean italicFlag, Boolean boldFlag,
                                    Boolean underLineFlag, Double fontSize, Color fontColor, String fontFamily, String text) {
        XSLFTextParagraph textParagraph = obj.addNewTextParagraph();
        if(textAlign != null) {
            textParagraph.setTextAlign(textAlign);
        }
        XSLFTextRun textRun = textParagraph.addNewTextRun();
        if(italicFlag != null) {
            textRun.setItalic(italicFlag);
        }
        if(boldFlag != null) {
            textRun.setBold(boldFlag);
        }
        if(underLineFlag != null) {
            textRun.setUnderlined(underLineFlag);
        }

        if(fontSize != null) {
            textRun.setFontSize(fontSize);
        }
        if(fontColor != null) {
            textRun.setFontColor(fontColor);
        }
        if(fontFamily != null) {
            textRun.setFontFamily(fontFamily);
        }
        textRun.setText(text);
    }
    public void createPPT(){
        XMLSlideShow pptx = new XMLSlideShow();
        XSLFSlide slide1 = pptx.createSlide();


        XSLFChart chart = pptx.createChart();

        slide1.addChart(chart);
    }

    public void readPPTX( FileInputStream fileInputStream) throws IOException {
        XMLSlideShow pptx = new XMLSlideShow(fileInputStream);
        XSLFSlide slide1 = pptx.createSlide();

        XSLFChart chart = pptx.createChart();

        slide1.addChart(chart);
    }

    public void addPicture() throws IOException {
        XMLSlideShow ppt = new XMLSlideShow();
        XSLFSlide slide = ppt.createSlide();

        byte[] pictureData = IOUtils.toByteArray(new FileInputStream(RESOURCE+"image.png"));
        XSLFPictureData pd = ppt.addPicture(pictureData, PictureData.PictureType.PNG);
        slide.createPicture(pd);
        ppt.write(new FileOutputStream(RESOURCE+"test.pptx"));
    }

    public void addText() throws IOException {
        XMLSlideShow ppt = new XMLSlideShow();
        XSLFSlide slide = ppt.createSlide();

        XSLFTextBox textBox = slide.createTextBox();
        // 这是很重要的一步, 不然文本不会显示
        textBox.setAnchor(new Rectangle2D.Double(80,50, 1000, 70));

        XSLFTextRun textRun = textBox.addNewTextParagraph().addNewTextRun();
        textRun.setText("www");

//        createTextBox(slide, new Rectangle2D.Double(80,50, 1000, 70), TextParagraph.TextAlign.LEFT, false, true, false, 40D, Color.decode("#1a335c"), "微软雅黑", " Frequent Track ");
        ppt.write(new FileOutputStream(RESOURCE+"test.pptx"));
    }


    /**
     * excel为数据源创建条形图
     * @throws IOException
     */
    public void createChartTest() throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        XSSFRow row0 = sheet.createRow(0);
        row0.createCell(1).setCellValue("男");
        row0.createCell(2).setCellValue("女");
        for (int i=0;i<4;i++){
            XSSFRow row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(i+"班");
            row.createCell(1).setCellValue(2*i);
            row.createCell(2).setCellValue(3*i);
        }

        // 创建一个ppt
        XMLSlideShow ppt = new XMLSlideShow();
        // 创建了一个幻灯片 这就是个空白的幻灯片，没有背景啥的，需要的可以自行研究
        XSLFSlide slide = ppt.createSlide();
        // 创建一个图表
        XSLFChart chart = ppt.createChart();
        // 把工作簿放到图表里，这样可以方便文件更新
        chart.setWorkbook(workbook);
        // 图表头
        chart.setTitleText("测试文本title");
        //这个是生成图表底部的示例的
        XDDFChartLegend legend = chart.getOrAddLegend();
        legend.setPosition(LegendPosition.BOTTOM);
        // x坐标轴 底部
        XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
        // y轴  左侧
        XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
        // 创建图表数据，第一个指定是什么图表 柱状图或者饼图，折线图都ok，
        XDDFChartData data = chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);
        // 底部类别的数据源，可以从数组读，也可以从指定一个excel范围
        XDDFCategoryDataSource xddfCategoryDataSource = XDDFDataSourcesFactory.fromStringCellRange(sheet,new CellRangeAddress(1,4,0,0));
        // 这是第一个柱状图的数据源
        XDDFNumericalDataSource<Double> doubleXDDFNumericalDataSource = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 4, 1, 1));
        // 这是第二个柱状图的数据源
        XDDFNumericalDataSource<Double> doubleXDDFNumericalDataSource2 = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 4, 2, 2));
        // 把第一组柱状图添加到图表数据里 返回一个系列数据
        XDDFChartData.Series series = data.addSeries(xddfCategoryDataSource, doubleXDDFNumericalDataSource);
        // 第二组
        XDDFChartData.Series series1 = data.addSeries(xddfCategoryDataSource, doubleXDDFNumericalDataSource2);
        // 设置第一个系列的名称 上面那个生成图表底部的示例的就是这里 ,第一个指定名称，第二个可以给一个单元格。两个参数可以有一个为null
        series.setTitle("男", new CellReference(sheet.getRow(0).getCell(1)));
        series1.setTitle("女", new CellReference(sheet.getRow(0).getCell(2)));
        // 数据源转为barchart
        XDDFBarChartData bar = (XDDFBarChartData) data;
        // 这一句是y轴的一个操作，也没懂什么意思，但是没有这个，画的图表会超出画布范围
        leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);
        // 是否设置不同的颜色 false就行
        bar.setVaryColors(false);
        // 柱状图的方向
        bar.setBarDirection(BarDirection.BAR); // COL 柱状图 BAR 横向条形图
        // 柱状图的类型，不是有什么堆积。。。的
        bar.setBarGrouping(BarGrouping.CLUSTERED); // STANDARD 正常图 // STACKED 堆积图
        // 可以设置间隙宽度
        // bar.setGapWidth(200);
        // 开始画图
        chart.plot(data);
        // ***一开始我生成的图总是在左上角一点点，我看了addChart源码 x:10，y:10,w:500000,h:500000
        // 我就只能一点点加到了合适的差不多的参数，你们可以自己试试
        Rectangle2D.Double rect = new Rectangle2D.Double(700000, 500000, 7000000, 5000000);
        // 把柱状图加到幻灯片里，指定画布
        slide.addChart(chart,rect);
        ppt.write(new FileOutputStream(RESOURCE+"test.pptx"));
    }

    /**
     *
     * @throws IOException
     */
    public void createChart() throws IOException, XmlException {

        XMLSlideShow pptx = new XMLSlideShow();
        XSLFChart barChart = pptx.createChart();
        XSLFChart pieChart = pptx.createChart();
        XSLFChart lineChart = pptx.createChart();
        XSLFChart doughnutChart = pptx.createChart();



        Map<String,Integer[]> series = new LinkedHashMap<>();
        series.put("SMS",new Integer[]{900,800,600,700,400});
        series.put("LUD",new Integer[]{1000,200,400,300,800});
        ChartConf chartConf = new ChartConf(new String[]{"12/1", "12/2", "12/3", "12/4", "12/5"}, series);
        ChartUtils.createBarChart(barChart,chartConf);
        ChartUtils.createLineChart(lineChart,chartConf);
        ChartUtils.createPieChart(pieChart,chartConf);
        ChartUtils.createDoughnutChart(doughnutChart,chartConf);
        // 图表位置
        Rectangle2D.Double aDouble = new Rectangle2D.Double(700000, 500000, 7000000, 5000000);
        // 图表放入幻灯片
        XSLFSlide slide = pptx.createSlide();
        XSLFSlide slide1 = pptx.createSlide();
        XSLFSlide slide2 = pptx.createSlide();
        XSLFSlide slide3 = pptx.createSlide();
        slide.addChart(barChart,aDouble);
        slide1.addChart(lineChart,aDouble);
        slide2.addChart(pieChart,aDouble);
        slide3.addChart(doughnutChart,aDouble);

        pptx.write(new FileOutputStream(RESOURCE+"test.pptx"));
    }


    public void createChartOld() throws IOException {
        XSSFWorkbook sheets = new XSSFWorkbook();
        XSSFSheet sheet1 = sheets.createSheet();
        XSSFRow row0 = sheet1.createRow(0);
        row0.createCell(1).setCellValue("选项");
        row0.createCell(2).setCellValue("个数");
        for (int i=0;i<4;i++){
            XSSFRow row = sheet1.createRow(i + 1);
            row.createCell(1).setCellValue(i+1+"选项");
            row.createCell(2).setCellValue(i*5);
        }

        XMLSlideShow pptx = new XMLSlideShow();
        XSLFSlide slide = pptx.createSlide();
        XSLFChart chartOption = pptx.createChart();

        chartOption.setWorkbook(sheets);
        chartOption.setTitleText("测试图");

        // 设置类目坐标轴 (x轴  底部)
        XDDFCategoryAxis categoryAxis = chartOption.createCategoryAxis(AxisPosition.BOTTOM);
        // 设置值坐标轴 (y轴  左侧)
        XDDFValueAxis valueAxis = chartOption.createValueAxis(AxisPosition.LEFT);

        // 创建图表
        XDDFChartData chart = chartOption.createData(ChartTypes.DOUGHNUT, categoryAxis, valueAxis);
        chart.setVaryColors(true);

        XSSFSheet sheet = sheets.getSheetAt(0);
        XDDFCategoryDataSource categoryDataSource = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(1, 4, 1, 1));
        XDDFNumericalDataSource<Double> valueDataSource = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 4, 2, 2));

        chart.addSeries(categoryDataSource,valueDataSource);
        // 画图
        chartOption.plot(chart);

//        CTDLbls dLbls = chartOption.getCTChart().getPlotArea().getDoughnutChartArray(0).getSerArray(0).addNewDLbls();
//        dLbls.addNewShowVal().setVal(true);//图上面 显示数据
//        dLbls.addNewShowLegendKey().setVal(false);
//        dLbls.addNewShowCatName().setVal(true);//图上面  类别名称
//        dLbls.addNewShowSerName().setVal(false);
//        dLbls.addNewShowPercent().setVal(true);//图上面  显示百分比
//        dLbls.addNewShowLeaderLines().setVal(false);// 引导线
//        dLbls.setSeparator("\n");// 分隔符为分行符
//        dLbls.addNewDLblPos().setVal(STDLblPos.Enum.forString("inEnd"));// 数据标签内


//         设置圆环的大小
//        CTHoleSize ctHoleSize = chartOption.getCTChart().getPlotArea().getDoughnutChartArray(0).addNewHoleSize();


        // 图表位置
        Rectangle2D.Double aDouble = new Rectangle2D.Double(700000, 500000, 7000000, 5000000);
        // 图表放入幻灯片
        slide.addChart(chartOption,aDouble);

        pptx.write(new FileOutputStream(RESOURCE+"test.pptx"));
    }
}
