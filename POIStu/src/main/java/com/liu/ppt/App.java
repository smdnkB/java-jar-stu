package com.liu.ppt;

import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFChart;
import org.apache.poi.xslf.usermodel.XSLFSlide;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class App {
    public static String RESOURCE= Objects.requireNonNull(PPTUtils.class.getClassLoader().getResource("")).getPath()+ File.separator;
    public static void main(String[] args) throws IOException, XmlException {
//
//        PPTUtils pptCreate = new PPTUtils();
////        pptCreate.createTextBox(slide, new Rectangle2D.Double(80,50, 1000, 70), TextAlign.LEFT, false, true, false, 40D, Color.decode("#1a335c"), "微软雅黑", " Frequent Track "+date1+"-"+date2);
//        pptCreate.createChart();
//

        FileInputStream fileInputStream = new FileInputStream(RESOURCE+"test.pptx");
        XMLSlideShow pptx = new XMLSlideShow(fileInputStream);
        int height = pptx.getPageSize().height;
        int width = pptx.getPageSize().width;

        System.out.println(height+":"+width);

    }
}
