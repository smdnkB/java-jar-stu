package com.liu;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * POI操作excel 读取表数据
 * 读取操作可以先获取列数 和  行数
 * 读取值的时候先判断 数据类型 不同的数据类型使用不同的获取方法 . POI提供了类型的枚举
 */
public class PoiStuRead {
    public static void main(String[] args) throws Exception {
//        testRead03();
        testRead07();
    }
    /**
     * 读取03版excel
     */
    public static void testRead03() throws IOException {
        FileInputStream inputStream = new FileInputStream("E:\\liu\\java\\web\\TestJarUtils\\POIStu\\src\\main\\resources\\03版.xls");
        // 读取到文件并创建excel对象
        Workbook workbook = new HSSFWorkbook(inputStream);

//        Sheet sheet = workbook.getSheet("sheet1"); // 通过工作簿名
        Sheet sheet = workbook.getSheetAt(0);      // 通过工作簿索引

        // 获取到行
        Row row = sheet.getRow(0);
        // 获取到行的单元格
        Cell cell = row.getCell(0);
        // 获取单元格的数据  注意数据有类型映射要求  不同数据类型要用不同的方法获取
        String value = cell.getStringCellValue();

        System.out.println(value);
    }

    /**
     * 读取07版excel
     */
    public static void testRead07() throws IOException {
        FileInputStream inputStream = new FileInputStream("E:\\liu\\java\\web\\TestJarUtils\\POIStu\\src\\main\\resources\\论文数据分析.xlsx");
        // 读取到文件并创建excel对象
        Workbook workbook = new XSSFWorkbook(inputStream);

        Sheet sheet = workbook.getSheet("疫情后数据"); // 通过工作簿名
//        Sheet sheet = workbook.getSheetAt(0);      // 通过工作簿索引


        // 获取到行 -- 表头
        Row rowHead = sheet.getRow(0);
        int titleCount = rowHead.getPhysicalNumberOfCells(); // 获取这一行的单元格个数
        for (int cellNum = 0; cellNum< titleCount ;cellNum++){
            Cell cell = rowHead.getCell(cellNum);
            if (cell != null){
                String value = cellType(cell);
                System.out.printf(value+"| ");
            }
        }
        System.out.println();

        // 获取到行 -- 表体
        int rowCount = sheet.getPhysicalNumberOfRows();
        for (int rowNum = 1; rowNum< rowCount; rowNum++){
            Row row = sheet.getRow(rowNum);
            int cellCount = row.getPhysicalNumberOfCells();
            for (int cellNum = 0;cellNum < cellCount; cellNum++){
                Cell cell = row.getCell(cellNum);
                if (cell != null){
                    String value = cellType(cell); // 通过单元格类型取数据
                    System.out.printf(value+"| ");
                }

            }
            System.out.println();
        }

    }

    public static String cellType(Cell cell) {
        String value = "";
        int cellType = cell.getCellType();
        switch (cellType) {
            case HSSFCell.CELL_TYPE_BLANK: { // 空数据
                value = "";
                break;
            }
            case HSSFCell.CELL_TYPE_BOOLEAN: { // 布尔
                boolean cellValue = cell.getBooleanCellValue();
                value = String.valueOf(cellValue);
                break;
            }
            case HSSFCell.CELL_TYPE_ERROR: { // 错误
                value = "error";
                break;
            }
            case HSSFCell.CELL_TYPE_STRING: { // 字符串
                value = cell.getStringCellValue();
                break;
            }
            case HSSFCell.CELL_TYPE_NUMERIC: { // 数字类型 又包含日期和数字
                if (HSSFDateUtil.isCellDateFormatted(cell)) { // 这个单元格是日期类型
                    Date date = cell.getDateCellValue();
                    value = new SimpleDateFormat("yyyy:MM:dd").format(date);
                } else { // 这个单元格是数字
                    cell.setCellType(HSSFCell.CELL_TYPE_STRING); // 防止数字过长无法显示 转换为字符串
                    value = cell.toString();
                }
                break;
            }
        }
        return value;
    }
}
