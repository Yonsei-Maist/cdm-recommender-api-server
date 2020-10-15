package com.yonsei.recommender.global.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseMessage {

    private String id;
    private String version = "0.1" ;
    private String result = "fail";
    private Object message;

    @Builder
    public ErrorResponseMessage (String id, Object message) {
        this.id = id;
        this.message = message;
    }


}
