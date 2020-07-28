package com.SoundTrackRecords.Model;

public class AppResponse {

        private String message;
	private String code;
	

	public AppResponse() {

	}

	public AppResponse(String message,String code) {
		
		this.message = message;
                this.code = code;
	}

	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
        
        public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


}
