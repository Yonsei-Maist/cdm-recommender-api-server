/**
 * Response message
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.global.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseMessage {

    private String id = "200";
    private String version = "0.1" ;
    private String result = "success";
    private Object data;

    public ResponseMessage () {}

    @Builder
    public ResponseMessage (Object data) {
        this.data = data;
    }
}
