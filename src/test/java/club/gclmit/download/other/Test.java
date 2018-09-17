package club.gclmit.download.other;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;


/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download
 * @author: gclm
 * @date: 2018/9/9 下午7:58
 * @description:
 */
public class Test {

    public static void main(String[] args) throws IOException {

//        获取 doc_id
//        String url = "https://wenku.baidu.com/view/123d438fb84ae45c3a358c0e.html?from=search";
//
//        int index = url.indexOf("view/");
//
//        int last = url.indexOf(".html");
//
//        System.out.println(url.substring(index+5,last));

//        String imagePath = "https://static.oschina.net/uploads/space/2017/0723/062024_aljX_3621115.png";

//        String imagePath = "https://wkretype.bdimg.com/retype/zoom/c1d3939cd4d8d15abe234ee8?pn=3&o=jpg_6&md5sum=045a1f8d70b56b635421f81ac71de73c&sign=8e4ec55a14&png=43196-64793&jpg=158842-309995";
//
//        List<String> list = new ArrayList<>();
//
//        list.add("https://wkretype.bdimg.com/retype/zoom/24b84244804d2b160b4ec0a9?pn=1&o=jpg_6&md5sum=d8a53520a3894717412042059122e8cc&sign=5d61f204f0&png=0-59494&jpg=0-62871");
//        list.add("https://wkretype.bdimg.com/retype/zoom/24b84244804d2b160b4ec0a9?pn=2&o=jpg_6&md5sum=d8a53520a3894717412042059122e8cc&sign=5d61f204f0&png=59495-88543&jpg=62872-221176");
//        list.add("https://wkretype.bdimg.com/retype/zoom/24b84244804d2b160b4ec0a9?pn=3&o=jpg_6&md5sum=d8a53520a3894717412042059122e8cc&sign=5d61f204f0&png=88544-129160&jpg=221177-393701");
//
//        try {
//
//            URL url = new URL(list.get(0));
//
//            URLConnection uri = url.openConnection();
//            //获取数据流
//            InputStream inputStream = uri.getInputStream();
//
//            BufferedImage img = ImageIO.read(inputStream);
//
//            System.out.println(img.getHeight()+" "+img.getWidth());
//
//            Document document=new Document(new Rectangle(img.getWidth(), img.getHeight()), 0, 0, 0, 0);
//
//            FileOutputStream fos = new FileOutputStream("/Volumes/Workspace/test.pdf");
//
//            PdfWriter.getInstance(document, fos);
//
//            document.open();
////          document.add(new Paragraph("Hello World"));
//            document.addTitle("this is a title D");
//
//            document.addCreationDate();
//            Image image=null;
//
//            for(int i=0;i<list.size();i++){
////
//                image = Image.getInstance(list.get(i));
//
//                image.scaleAbsolute(img.getWidth(),img.getHeight());
//
//                document.add(image);
//            }
//            document.close();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        String docType ="1";

        int i = Integer.parseInt(docType);

        System.out.println(i);

        File file = new File("/Volumes/Workspace/WenKu/docx/如何选择软考科目.docx");

        if(!file.exists()){
            file.mkdirs();
            file.createNewFile();
        }
    }
}
