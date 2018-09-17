package club.gclmit.download.other;


import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download
 * @author: gclm
 * @date: 2018/9/10 下午7:58
 * @description:
 */
public class ImageTest {

    public  static void main(String[] args)throws FileNotFoundException, DocumentException, IOException {
        Document document=new Document(PageSize.A4,50,50,50,50);
        PdfWriter pdfWriter=PdfWriter.getInstance(document,new FileOutputStream("E:/work/test.pdf"));
        document.open();
        //document.setPageSize(PageSize.A4);        //设置页面大小
//        document.add(new Paragraph("Hello World"));
        document.addTitle("this is a title D");
        document.addAuthor("mld");
        document.addSubject("this is a subject D");
        document.addKeywords("Keywords D");
        document.addCreationDate();
        Image image=null;
//        image=Image.getInstance("E:/picSource/20170905100453_3245.jpg");
        File file=new File("E:/picSource");
        File[] fileList=file.listFiles();
        for(int i=0;i<fileList.length;i++){
            image=Image.getInstance("E:/picSource/"+fileList[i].getName());
            Map<String,Float> param=new HashMap<String,Float>();
            param=getHeighWidth(image);
            image.scaleAbsolute(param.get("imageWidth"),param.get("imageHeight"));
            image.setAlignment(Element.ALIGN_CENTER);                      //设置元素居中
            //image.setAlignment(1);                      //设置元素居中
            document.add(image);
        }
        document.close();
    }
    public static Map<String,Float> getHeighWidth(Image image){
        Float imageHeight=image.getScaledHeight();
        Float imageWidth=image.getScaledWidth();
        Map<String,Float> resultParam=new HashMap<String, Float>();
        int i=0;
        while(imageHeight>500||imageWidth>500){
            image.scalePercent(100-i);
            i++;
            imageHeight=image.getScaledHeight();
            imageWidth=image.getScaledWidth();
        }
        resultParam.put("imageWidth",imageWidth);
        resultParam.put("imageHeight",imageHeight);
        return resultParam;
    }
}
