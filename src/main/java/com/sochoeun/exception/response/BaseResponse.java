package com.sochoeun.exception.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class BaseResponse {
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private Object object;

    public void getSuccess(Object object){
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.OK;
        this.message = "Get API Success";
        this.object = object;
    }

    public void createSuccess(Object object){
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.OK;
        this.message = "Created";
        this.object = object;
    }

    public void updatedSuccess(Object object){
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.OK;
        this.message = "Updated";
        this.object = object;
    }

    public void deletedSuccess(){
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.OK;
        this.message = "Deleted";
    }

    public void disableSuccess(Object object){
        this.timestamp = LocalDateTime.now();
        this.status = HttpStatus.OK;
        this.message = "Disabled";
        this.object = object;
    }
}
