package com.liu.chart;

import com.liu.ppt.PPTUtils;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

public class ChartConf {

    private String title; // 图表标题
    private String xTitle; // x轴标题
    private String yTitle; // y 轴标题
    private final Integer categoryNum; // 类目轴长度
    private final ArrayList<String> seriesName; // 系列名称
    private final XSSFWorkbook workbook; // 图表依赖的excel表格数据
    // todo 属性里面是否要加入更具体的样式配置

    /**
     * 构造出图表使用的数据原
     * x 轴为类别
     * y 轴为值
     * 有多组值时每组值称为一个系列
     * @param x 类目轴
     * @param series 值 (用map可以传入多个系列的值, 健为系类名称)
     */
    public ChartConf(String[] x, Map<String,Integer[]> series) throws IOException {
        // todo 当给的参数中 x轴和y轴长度不同时, 抛异常还是自动补零

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        int rowCount = 0;
        XSSFRow row0 = sheet.createRow(rowCount);
        for (int i = 0; i < x.length; i++) {
            row0.createCell(1+i).setCellValue(x[i]);
        }

        Set<String> keySet = series.keySet();
        Iterator<String> iterator = keySet.iterator();

        rowCount++;
        ArrayList<String> seriesName = new ArrayList<>();
        while (iterator.hasNext()){
            String key = iterator.next();
            seriesName.add(key);
            Integer[] nums = series.get(key);
            XSSFRow row = sheet.createRow(rowCount);
            row.createCell(0).setCellValue(key);
            for (int i = 0; i < nums.length; i++) {
                row.createCell(1 + i).setCellValue(nums[i]);
            }
            rowCount++;
        }
        this.workbook = workbook;
        this.seriesName = seriesName;
        this.categoryNum = x.length;
        workbook.write(new FileOutputStream(Objects.requireNonNull(PPTUtils.class.getClassLoader().getResource("")).getPath()+ File.separator+"source.xlsx"));
    }

    public Integer getCategoryNum() {
        return categoryNum;
    }

    public ArrayList<String> getSeriesName() {
        return seriesName;
    }

    public XSSFWorkbook getWorkbook() {
        return workbook;
    }

    public String getxTitle() {
        return xTitle;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setxTitle(String xTitle) {
        this.xTitle = xTitle;
    }

    public String getyTitle() {
        return yTitle;
    }

    public void setyTitle(String yTitle) {
        this.yTitle = yTitle;
    }
}
