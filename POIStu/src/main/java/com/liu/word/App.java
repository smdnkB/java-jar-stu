package com.liu.word;

import com.liu.ppt.PPTUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class App {
    public static String RESOURCE= Objects.requireNonNull(App.class.getClassLoader().getResource("")).getPath();
    public static void main(String[] args) throws Exception {
//
        WordUtils wordUtils = new WordUtils();
        wordUtils.createBarChart();

        String path = RESOURCE + "test.docx";
        path = path.substring(1,path.length());
        AsposeUtil.wordToPdf(path,RESOURCE+"test.pdf");
    }
}

