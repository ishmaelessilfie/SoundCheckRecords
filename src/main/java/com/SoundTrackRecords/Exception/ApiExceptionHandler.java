/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Exception;

import com.itextpdf.text.log.Logger;
import com.itextpdf.text.log.LoggerFactory;
import groovy.util.logging.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 *
 * @author Ish
 */
@Log4j2
@ControllerAdvice
@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    Logger log = LoggerFactory.getLogger(FileStorageException.class);

    @ExceptionHandler(FileStorageException.class)
    public ResponseEntity<ApiException> handleApiException(FileStorageException ex) {
        logException(ex);
        ApiException exception = new ApiException();
        exception.setCode("error-000505");
        exception.setType(HttpStatus.INTERNAL_SERVER_ERROR.value());
        exception.setMessage(ex.getMessage());
        //CustomApiException customApiException = new CustomApiException(Arrays.asList(exception));
        return new ResponseEntity<>(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private void logException(Throwable t) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : t.getStackTrace()) {
            sb.append(element.toString());
            sb.append("\n");
        }
        log.error("ERROR:  [\n" + sb.toString() + "]");

    }
}
