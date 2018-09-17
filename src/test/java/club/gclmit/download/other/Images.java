package club.gclmit.download.other;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;


/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download
 * @author: gclm
 * @date: 2018/9/11 下午7:31
 * @description:
 */
public class Images {


    public static void main(String[] args) {
        ArrayList<String> imagesListNameList = new ArrayList<String>();
        //JpgToPDF.JpgToPDF("D:/jdp", "D:/pdf");单独转换一张图片
        Images.getAllFileName("D:/jpg", imagesListNameList);
        String errorPath = null;
        //批量转换图片
        for (String name : imagesListNameList) {
            try {
                //所用的包是itextpdf5.4.3.jar

                //图片文件的路径：D:/jpg/
                //存放pdf文件的路径：D:/pdf/
                Images.imageToPDF("D:/jpg/" + name, "D:/pdf/" + name.substring(0, name.lastIndexOf(".jpg")) + "pdf");
            } catch (Exception e) {
                errorPath = "D:/jpg" + name;
            }
            if (errorPath == null) {
                System.out.println("文件转换成功");
            } else {
                System.out.println(errorPath + "路径下的文件转换失败");
            }
        }
        System.out.println("文件全部转换成功");
    }

    //获取文件里的所有图片名称
    public static List getAllFileName(String path, ArrayList<String> fileName) {
        File file = new File(path);
        File[] files = file.listFiles();
        String[] names = file.list();
        if (names != null)
            fileName.addAll(Arrays.asList(names));
        List imageListNameList = new ArrayList();
        for (File a : files) {
            if (a.isDirectory()) {
                imageListNameList.addAll(fileName);
            }
        }
        return imageListNameList;
    }

    //jpg图片转换成pdf
    public static void imageToPDF(String imagePath, String pdfPath) throws Exception {
        BufferedImage img = ImageIO.read(new File(imagePath));
        FileOutputStream fos = new FileOutputStream(pdfPath);
        Document doc = new Document(null, 0, 0, 0, 0);
        doc.setPageSize(new Rectangle(img.getWidth(), img.getHeight()));
        Image image = Image.getInstance(imagePath);
        PdfWriter.getInstance(doc, fos);
        doc.open();
        doc.add(image);
        doc.close();
    }
}
