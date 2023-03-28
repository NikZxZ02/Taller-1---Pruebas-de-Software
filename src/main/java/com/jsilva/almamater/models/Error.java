/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jsilva.almamater.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

/**
 *
 * @author JSilva
 */
public class Error {
    private String msgError;
    private HttpStatus httpStatus;

    public Error(String msgError, HttpStatus httpStatus) {
        this.msgError = msgError;
        this.httpStatus = httpStatus;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    @JsonIgnore
    public HttpStatus getCode() {
        return httpStatus;
    }

    public void setCode(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
    
    
    
}
