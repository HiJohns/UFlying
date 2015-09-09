package com.UFlying.json.response;

public class ResponseLoginInfo extends AbstractReturn {
	private int type;
	private Object account;
	
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Object getAccount() {
		return account;
	}
	public void setAccount(Object account) {
		this.account = account;
	}
	
}
