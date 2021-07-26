package com.livenow.week1.controller.responsemessage;

import lombok.Setter;

@Setter
public class Message {

    private StatusEnum status;
    private String message;
    private Object data;

    public Message() {
        this.status = StatusEnum.OK;
        this.message = "성공완료";
        this.data = null;
    }
}
