package com.liu.ppt;

import java.io.IOException;

public class App {

    public static void main(String[] args) throws IOException {

        PPTUtils pptCreate = new PPTUtils();
//        pptCreate.createTextBox(slide, new Rectangle2D.Double(80,50, 1000, 70), TextAlign.LEFT, false, true, false, 40D, Color.decode("#1a335c"), "微软雅黑", " Frequent Track "+date1+"-"+date2);
        pptCreate.createChart();
    }
}
