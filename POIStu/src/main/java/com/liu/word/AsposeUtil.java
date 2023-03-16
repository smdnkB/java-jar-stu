package com.liu.word;

import com.aspose.words.Document;
import com.aspose.words.License;
import com.aspose.words.SaveFormat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author : LCheng
 * @date : 2020-12-25 13:47
 * description : Aspose工具类
 */
public class AsposeUtil {

    /**
     * 加载license 用于破解 不生成水印
     *
     * @author LCheng
     * @date 2020/12/25 13:51
     */
    private static void getLicense() throws Exception{
        InputStream is = AsposeUtil.class.getClassLoader().getResourceAsStream("License.xml");
        License license = new License();
        license.setLicense(is);

    }

    /**
     * word转pdf
     *
     * @param wordPath word文件保存的路径
     * @param pdfPath  转换后pdf文件保存的路径
     * @author LCheng
     * @date 2020/12/25 13:51
     */
    public static void wordToPdf (String wordPath, String pdfPath) throws Exception {
        getLicense();
        File file = new File(pdfPath);
        try (FileOutputStream os = new FileOutputStream(file)) {
            Document doc = new Document(wordPath);
            doc.save(os, SaveFormat.PDF);
        }
    }
}

