package com.capstone.network.DTO;
public class ForwardingRequest {
	private String targetGroup;
	private String message;
	public ForwardingRequest(String targetGroup, String message) {
		 this.targetGroup = targetGroup;
		 this.message = message;
	}
	public String getTargetGroup() {
		return targetGroup;
	}
	public void setTargetGroup(String targetGroup) {
		this.targetGroup = targetGroup;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
