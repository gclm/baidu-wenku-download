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
public enum  RestMsg {

    downloadSuccess(1,"下载成功"),
    downloadError(2,"下载失败");
    

    @Getter @Setter
    private int  codeNumber;
    @Getter @Setter
    private String  message;


    public String  getRestMsg(int codeNumber) {
        for (RestMsg restMsg : RestMsg.values()){
            if (restMsg.getCodeNumber() == codeNumber){
                return restMsg.getMessage();
            }
        }
        return  null;
    }
}
