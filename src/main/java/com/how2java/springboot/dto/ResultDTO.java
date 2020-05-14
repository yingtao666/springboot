package com.how2java.springboot.dto;

public class ResultDTO {
    private boolean result;
    private String msg;
    private Object obj;
    private ResultDTO() {
        super();
    }
    public static ResultDTO newDTO(boolean result, String msg) {
        ResultDTO dto = new ResultDTO();
        dto.result = result;
        dto.msg = msg;
        return dto;
    }
    public boolean isResult() {
        return result;
    }
    public void setResult(boolean result) {
        this.result = result;
    }
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Object getObj() {
        return obj;
    }
    public void setObj(Object obj) {
        this.obj = obj;
    }
    public static ResultDTO putSuccess(String msg) {
        ResultDTO dto = new ResultDTO();
        dto.setResult(true);
        dto.setMsg(msg);
        return dto;
    }
    public static ResultDTO putError(String msg) {
        ResultDTO dto = new ResultDTO();
        dto.setResult(false);
        dto.setMsg(msg);
        return dto;
    }
}
