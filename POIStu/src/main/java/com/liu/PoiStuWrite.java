package com.liu;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * PIO ecxel操作工具包 案例
 * excel分03  和 07 两个版本  03限制行数65536 07版不限制行数
 * POI 有三个主要对象
 *      HSSFWorkBook  操作03版excel  速度快(写满65536 2s不到) 但023版限制行数65536
 *      XSSFWorkBook  操作07版excel  速度慢(写65536 近8s) 但023版限制行数65536
 *      SXSSFWorkBook 操作07版excel加强版  速度快(写满65536 2s不到)  但会产生临时文件 要手动清理  workbook.dispose()清理临时文件
 */
public class PoiStuWrite {
    public static void main(String[] args) throws Exception{
//        test03Xls();
        test07Xlsx();
    }

    /**
     * POI操作 03版excel
     */
    public static void test03Xls(){
        // 创建一个工作表对象
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 在工作表对象上创建一个工作簿对象  指定名字
        HSSFSheet sheet = workbook.createSheet("sheet1");

        // 在这个页对象上创建一个行对象 数字代表第几行
        HSSFRow row = sheet.createRow(0);

        // 在这个行对象上创建一个单元格 数字代表第几个单元格
        HSSFCell cell = row.createCell(0);
        // 在这个单元格上写入数据
        cell.setCellValue("姓名: ");

        // 把对象写入文件
        try {
            workbook.write(new FileOutputStream("E:\\liu\\java\\web\\TestJarUtils\\POIStu\\src\\main\\resources\\03版.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void test07Xlsx(){
        // 创建一个工作表对象  07版
        XSSFWorkbook workbook = new XSSFWorkbook();

        // 在工作表对象上创建一个工作簿对象  指定名字
        Sheet sheet = workbook.createSheet("sheet1");

        // 在这个页对象上创建一个行对象 数字代表第几行
        Row row = sheet.createRow(0);

        // 在这个行对象上创建一个单元格 数字代表第几个单元格
        Cell cell = row.createCell(0);
        // 在这个单元格上写入数据
        cell.setCellValue("姓名: ");

        // 把对象写入文件
        try {
            workbook.write(new FileOutputStream("E:\\liu\\java\\web\\TestJarUtils\\POIStu\\src\\main\\resources\\07版.xlsx"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
