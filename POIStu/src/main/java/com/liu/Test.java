package com.liu;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.PresetColor;
import org.apache.poi.xddf.usermodel.XDDFColor;
import org.apache.poi.xddf.usermodel.XDDFSolidFillProperties;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.chart.*;

import java.io.FileOutputStream;
import java.io.IOException;

public class Test {

    public static void main(String[] args) throws IOException {
        XSSFWorkbook wb = new XSSFWorkbook();
        String sheetName = "Sheet1";
        FileOutputStream fileOut = null;
        try {
            XSSFSheet sheet = wb.createSheet(sheetName);
            //第一行，国家名称
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("俄罗斯");
            cell = row.createCell(1);
            cell.setCellValue("加拿大");
            cell = row.createCell(2);
            cell.setCellValue("美国");
            cell = row.createCell(3);
            cell.setCellValue("中国");
            cell = row.createCell(4);
            cell.setCellValue("巴西");
            cell = row.createCell(5);
            cell.setCellValue("澳大利亚");
            cell = row.createCell(6);
            cell.setCellValue("印度");
            // 第二行，乡村地区
            row = sheet.createRow(1);
            cell = row.createCell(0);
            cell.setCellValue(17098242);
            cell = row.createCell(1);
            cell.setCellValue(9984670);
            cell = row.createCell(2);
            cell.setCellValue(9826675);
            cell = row.createCell(3);
            cell.setCellValue(9596961);
            cell = row.createCell(4);
            cell.setCellValue(8514877);
            cell = row.createCell(5);
            cell.setCellValue(7741220);
            cell = row.createCell(6);
            cell.setCellValue(3287263);
            // 第三行，农村人口
            row = sheet.createRow(2);
            cell = row.createCell(0);
            cell.setCellValue(14590041);
            cell = row.createCell(1);
            cell.setCellValue(35151728);
            cell = row.createCell(2);
            cell.setCellValue(32993302);
            cell = row.createCell(3);
            cell.setCellValue(14362887);
            cell = row.createCell(4);
            cell.setCellValue(21172141);
            cell = row.createCell(5);
            cell.setCellValue(25335727);
            cell = row.createCell(6);
            cell.setCellValue(13724923);
            // 第四行，面积平局
            row = sheet.createRow(3);
            cell = row.createCell(0);
            cell.setCellValue(9435701.143);
            cell = row.createCell(1);
            cell.setCellValue(9435701.143);
            cell = row.createCell(2);
            cell.setCellValue(9435701.143);
            cell = row.createCell(3);
            cell.setCellValue(9435701.143);
            cell = row.createCell(4);
            cell.setCellValue(9435701.143);
            cell = row.createCell(5);
            cell.setCellValue(9435701.143);
            cell = row.createCell(6);
            cell.setCellValue(9435701.143);
            // 第四行，人口平局
            row = sheet.createRow(4);
            cell = row.createCell(0);
            cell.setCellValue(22475821.29);
            cell = row.createCell(1);
            cell.setCellValue(22475821.29);
            cell = row.createCell(2);
            cell.setCellValue(22475821.29);
            cell = row.createCell(3);
            cell.setCellValue(22475821.29);
            cell = row.createCell(4);
            cell.setCellValue(22475821.29);
            cell = row.createCell(5);
            cell.setCellValue(22475821.29);
            cell = row.createCell(6);
            cell.setCellValue(22475821.29);

            //创建一个画布
            XSSFDrawing drawing = sheet.createDrawingPatriarch();
            //前四个默认0，[0,5]：从0列5行开始;[7,26]:到7列26行结束
            //默认宽度(14-8)*12
            XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 5, 7, 26);
            //创建一个chart对象
            XSSFChart chart = drawing.createChart(anchor);
            //标题
            chart.setTitleText("地区排名前七的国家");
            //标题覆盖
            chart.setTitleOverlay(false);

            //图例位置
            XDDFChartLegend legend = chart.getOrAddLegend();
            legend.setPosition(LegendPosition.TOP);

            //分类轴标(X轴),标题位置
            XDDFCategoryAxis bottomAxis = chart.createCategoryAxis(AxisPosition.BOTTOM);
            bottomAxis.setTitle("国家");
            //值(Y轴)轴,标题位置
            XDDFValueAxis leftAxis = chart.createValueAxis(AxisPosition.LEFT);
            leftAxis.setTitle("面积和人口");

            //CellRangeAddress(起始行号，终止行号， 起始列号，终止列号）
            //分类轴标(X轴)数据，单元格范围位置[0, 0]到[0, 6]
            XDDFDataSource<String> countries = XDDFDataSourcesFactory.fromStringCellRange(sheet, new CellRangeAddress(0, 0, 0, 6));
//			XDDFCategoryDataSource countries = XDDFDataSourcesFactory.fromArray(new String[] {"俄罗斯","加拿大","美国","中国","巴西","澳大利亚","印度"});
            //数据1，单元格范围位置[1, 0]到[1, 6]
            XDDFNumericalDataSource<Double> area = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(1, 1, 0, 6));
//			XDDFNumericalDataSource<Integer> area = XDDFDataSourcesFactory.fromArray(new Integer[] {17098242,9984670,9826675,9596961,8514877,7741220,3287263});

            //数据2，单元格范围位置[2, 0]到[2, 6]
            XDDFNumericalDataSource<Double> population = XDDFDataSourcesFactory.fromNumericCellRange(sheet, new CellRangeAddress(2, 2, 0, 6));

            //bar：条形图，
            XDDFBarChartData bar = (XDDFBarChartData) chart.createData(ChartTypes.BAR, bottomAxis, leftAxis);

            leftAxis.setCrosses(AxisCrosses.AUTO_ZERO);
            leftAxis.setCrossBetween(AxisCrossBetween.BETWEEN);
            //设置为可变颜色
            bar.setVaryColors(true);
            //条形图方向，纵向/横向：纵向
            bar.setBarDirection(BarDirection.COL);

            //图表加载数据，条形图1
            XDDFBarChartData.Series series1 = (XDDFBarChartData.Series) bar.addSeries(countries, area);
            //条形图例标题
            series1.setTitle("面积", null);
            XDDFSolidFillProperties fill = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.RED));
            //条形图，填充颜色
            series1.setFillProperties(fill);


            //图表加载数据，条形图2
            XDDFBarChartData.Series series2 = (XDDFBarChartData.Series) bar.addSeries(countries, population);
            //条形图例标题
            series2.setTitle("人口", null);
            XDDFSolidFillProperties fill2 = new XDDFSolidFillProperties(XDDFColor.from(PresetColor.BLUE));
            //条形图，填充颜色
            series2.setFillProperties(fill2);

            CTPlotArea plotArea = chart.getCTChart().getPlotArea();
            //绘制
            chart.plot(bar);


            // 堆积条形图，将2个条形图重叠起来
            bar.setBarGrouping(BarGrouping.STACKED);


            //柱状图1上显示数值
            plotArea.getBarChartArray(0).getSerArray(0).addNewDLbls();
            plotArea.getBarChartArray(0).getSerArray(0).getDLbls().addNewShowVal().setVal(true);
            plotArea.getBarChartArray(0).getSerArray(0).getDLbls().addNewShowLegendKey().setVal(false);
            plotArea.getBarChartArray(0).getSerArray(0).getDLbls().addNewShowCatName().setVal(false);
            plotArea.getBarChartArray(0).getSerArray(0).getDLbls().addNewShowSerName().setVal(false);

            //柱状图2上显示数值
            plotArea.getBarChartArray(0).getSerArray(1).addNewDLbls();
            plotArea.getBarChartArray(0).getSerArray(1).getDLbls().addNewShowVal().setVal(true);
            plotArea.getBarChartArray(0).getSerArray(1).getDLbls().addNewShowLegendKey().setVal(false);
            plotArea.getBarChartArray(0).getSerArray(1).getDLbls().addNewShowCatName().setVal(false);
            plotArea.getBarChartArray(0).getSerArray(1).getDLbls().addNewShowSerName().setVal(false);


            //给每个条形图设置颜色
            CTBarSer ser = plotArea.getBarChartArray(0).getSerArray(0);
            //黑色
            CTDPt dpt = ser.addNewDPt();
            dpt.addNewIdx().setVal(0);
            dpt.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { (byte) 0, (byte) 0, (byte) 0 });
            //红色
            dpt = ser.addNewDPt();
            dpt.addNewIdx().setVal(1);
            dpt.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { (byte) 255, (byte) 0, (byte) 0 });
            //蓝色
            dpt = ser.addNewDPt();
            dpt.addNewIdx().setVal(2);
            dpt.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { (byte) 0, (byte) 0, (byte) 255 });
            //绿色
            dpt = ser.addNewDPt();
            dpt.addNewIdx().setVal(3);
            dpt.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { (byte) 0, (byte) 128, (byte) 0 });
            //黄色
            dpt = ser.addNewDPt();
            dpt.addNewIdx().setVal(4);
            dpt.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { (byte) 255, (byte) 255, (byte) 0 });
            //紫色
            dpt = ser.addNewDPt();
            dpt.addNewIdx().setVal(5);
            dpt.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { (byte) 80, (byte) 0, (byte) 80 });
            //棕色
            dpt = ser.addNewDPt();
            dpt.addNewIdx().setVal(6);
            dpt.addNewSpPr().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { (byte) 165, (byte) 42, (byte) 42 });


            //修正重叠，使钢筋真正堆叠而不是并排
            CTOverlap ctOverlap = CTOverlap.Factory.parse("<xml-fragment val='100'/>");
            chart.getCTChart().getPlotArea().getBarChartArray(0).setOverlap(ctOverlap);

            //边框
            //ser.getSpPr().addNewLn().addNewSolidFill().addNewSrgbClr().setVal(new byte[] { (byte) 0, (byte) 0, (byte) 0 });

            // 打印图表的xml
            //System.out.println(chart.getCTChart());

            // 将输出写入excel文件
            String filename = "排行榜前七的国家.xlsx";
            fileOut = new FileOutputStream(filename);
            wb.write(fileOut);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            wb.close();
            if (fileOut != null) {
                fileOut.close();
            }
        }

    }

}
