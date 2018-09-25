package club.gclmit.download.enums;

import lombok.*;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.enums
 * @author: gclm
 * @date: 2018/9/17 上午9:02
 * @description: 返回消息枚举类
 */
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public enum ResultEnum {


    UnknownError(-1,"未知错误"),
    Query(1,"查询成功,存在该文件正在生成下载链接"),
    UnQuery(-1,"查询失败，不存在该文件，开始解析文件"),
    Resolve(1,"解析成功"),
    UnResolve(-1,"解析失败"),
    Download(1,"下载成功"),
    NnDownload(-1,"下载失败");



    @Getter @Setter
    private int  codeNumber;
    @Getter @Setter
    private String  message;


    public String  getResultMsg(int codeNumber) {
        for (ResultEnum restMsg : ResultEnum.values()){
            if (restMsg.getCodeNumber() == codeNumber){
                return restMsg.getMessage();
            }
        }
        return  null;
    }
}
