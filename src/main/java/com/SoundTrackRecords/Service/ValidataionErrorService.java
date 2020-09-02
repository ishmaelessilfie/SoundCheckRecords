/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.SoundTrackRecords.Service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

/**
 *
 * @author EDI
 */
@Service
public class ValidataionErrorService {
    public ResponseEntity validate(BindingResult result){
         if(result.hasErrors()) {
          Map<String,String> errorMap = new HashMap<>();
          result.getFieldErrors().forEach((error) -> {
              errorMap.put(error.getField(),error.getDefaultMessage());
            });
          return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
        }
      return null;
    }
    
}
