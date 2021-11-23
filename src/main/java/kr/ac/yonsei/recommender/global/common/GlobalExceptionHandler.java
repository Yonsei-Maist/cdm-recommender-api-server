/**
 * Handles all errors that occur in the controller
 * @author Mina Kim, Yonsei Univ. Researcher, since 2020.08~
 * @Date 2020.10.19
 */
package kr.ac.yonsei.recommender.global.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle exceptions that occur when there is no corresponding value
     * @param e IllegalArgumentException
     * @return Response message
     */
/*    @ExceptionHandler(IllegalArgumentException.class)
    protected ResponseEntity<ErrorResponseMessage> handleIllegalArgumentException(IllegalArgumentException e) {
        log.error("IllegalArgumentException", e);

        ErrorResponseMessage responseMessage = ErrorResponseMessage.builder()
                .id("400")
                .message("Invalid Input Value")
                .build();

        return new ResponseEntity<ErrorResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
    }*/

    /**
     * Handle exceptions that occur when essential values are not sent
     * @param e MethodArgumentNotValidException
     * @return Response message
     */
/*    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<ErrorResponseMessage> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);

        ErrorResponseMessage responseMessage = ErrorResponseMessage.builder()
                .id("400")
                .message(e.getBindingResult().getFieldError().getField()+" Empty")
                .build();

        return new ResponseEntity<ErrorResponseMessage>(responseMessage, HttpStatus.BAD_REQUEST);
    }*/

    /**
     * Handle exceptions that occur when requesting a method of an unsupported type
     * @param e HttpRequestMethodNotSupportedException
     * @return Response message
     */
/*    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ErrorResponseMessage> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("handleHttpRequestMethodNotSupportedException", e);

        ErrorResponseMessage responseMessage = ErrorResponseMessage.builder()
                .id("405")
                .message("Method Not Allowed")
                .build();

        return new ResponseEntity<ErrorResponseMessage>(responseMessage, HttpStatus.METHOD_NOT_ALLOWED);
    }*/

    /**
     * Handle all errors
     * @param e Exception
     * @return Response message
     */
/*    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseMessage> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);

        ErrorResponseMessage responseMessage = ErrorResponseMessage.builder()
                .id("500")
                .message("Server Error")
                .build();

        return new ResponseEntity<ErrorResponseMessage>(responseMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }*/
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseMessage> handleException(Exception e) {
        log.error("handleEntityNotFoundException", e);

        ErrorResponseMessage responseMessage = ErrorResponseMessage.builder()
                .id("200")
                .message(e.getMessage())
                .build();

        return new ResponseEntity<ErrorResponseMessage>(responseMessage, HttpStatus.OK);
    }
}
