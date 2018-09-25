package club.gclmit.download.util;

import club.gclmit.download.enums.ResultEnum;
import lombok.*;

/**
 * Copyright (C), 2016-2018, 孤城落寞的博客
 *
 * @program: club.gclmit.download.util
 * @author: gclm
 * @date: 2018/9/17 下午8:44
 * @description:
 */
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@ToString
public class ResultMsg {

    /**
     * 状态码
     */
    @Getter @Setter
    private int code;

    /**
     * 消息
     */
    @Getter @Setter
    private String msg;

    /**
     * 返回数据
     */
    @Getter @Setter
    private Object data;

    public ResultMsg(ResultEnum resultEnum) {
        this.code = resultEnum.getCodeNumber();
        this.msg = resultEnum.getMessage();
    }

    public ResultMsg(ResultEnum resultEnum, Object data) {
        this(resultEnum);
        this.data = data;
    }

    /**
     * 根据验证值 设置data
     * @param resultMsg
     * @param data
     * @return
     */
    public ResultMsg setResultMsgData(ResultMsg resultMsg,Object data) {
        if (resultMsg.getCode() == 1){
            resultMsg.setData(data);
        }
        return resultMsg;
    }

}
