/**
 * 
 */
package com.clavrit.response;

/**
 * @author Parveen
 *
 */
public class StudentResponse extends StandardResponse {
	private String userType = "USER";

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

}
