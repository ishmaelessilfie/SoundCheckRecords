package com.SoundTrackRecords.utils;

import java.util.List;


import org.codehaus.jettison.json.JSONObject;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ErrorUtils {
	public static String customErrors(List<ObjectError> errors) {
		JSONObject jsonObject = new JSONObject();
                String errorMesssage = "";
                for (ObjectError objectError : errors) {
                    if(objectError instanceof FieldError) {
                        FieldError fieldError = (FieldError) objectError;
                        errorMesssage +=  MethodUtils.convertString(fieldError.getField())+" : </b>"+fieldError.getDefaultMessage()+"</br>";
                    }
                }
                return	 errorMesssage;
		
	}
}
