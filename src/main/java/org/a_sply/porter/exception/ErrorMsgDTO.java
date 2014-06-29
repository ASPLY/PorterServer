package org.a_sply.porter.exception;

public class ErrorMsgDTO {
	
	private String errorMsg;

	public ErrorMsgDTO(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
