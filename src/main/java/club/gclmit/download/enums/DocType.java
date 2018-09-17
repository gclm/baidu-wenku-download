package club.gclmit.download.enums;

import lombok.Getter;
import lombok.Setter;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.entity
 * @author: gclm
 * @date: 2018/9/12 下午8:03
 * @description:  百度文库文档类型枚举
 *    '0': '',
 * 	'1': 'doc',
 * 	'2': 'xls',
 * 	'3': 'ppt',
 * 	'4': 'docx',
 * 	'5': 'xlsx',
 * 	'6': 'pptx',
 * 	'7': 'pdf',
 * 	'8': 'txt',
 * 	'9': 'wps',
 * 	'10': 'et',
 * 	'11': 'dps',
 * 	'12': 'vsd',
 * 	'13': 'rtf',
 * 	'14': 'pot',
 * 	'15': 'pps',
 * 	'16': 'epub'
 */
public enum  DocType {

    notDoc(0,""),doc(1,"doc"),xls(2,"xls"),ppt(3,"ppt"),docx(4,"docx"),xlsx(5,"xlsx"),pptx(6,"pptx"),pdf(7,"pdf"),
    txt(8,"txt"),wps(9,"wps"),et(10,"et"),dps(11,"dps"),vsd(12,"vsd"),rtf(13,"rtf"),pot(14,"pot"),pps(15,"pps")
    ,epub(16,"epub");

    /**
     * docType code 1-16
     */
    @Getter @Setter
    private  int docTypeCode;
    /**
     * docType name
     */
    @Getter @Setter
    private  String docTypename;


    /**
     * docType 构造方法
     * @param docTypeCode
     * @param docTypename
     */
    private DocType(int docTypeCode, String docTypename) {
        this.docTypeCode = docTypeCode;
        this.docTypename = docTypename;
    }


    public  static  String getDocType(int Code){
        for (DocType docType : DocType.values()){
            if(docType.getDocTypeCode() == Code){
                return  docType.getDocTypename();
            }
        }
        return  null;
    }
}
