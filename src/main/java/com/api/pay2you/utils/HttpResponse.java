package com.api.pay2you.utils;

public  class HttpResponse {
	 private String message;
	    private int status;
	    private String data;
	    
	    public HttpResponse() {
	    	
	    }

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getData() {
			return data;
		}

		public void setData(String data) {
			this.data = data;
		}
	   
}
