package com.UFlying.user.entity.base;

import java.io.Serializable;
import java.sql.Timestamp;

public class Contract implements Serializable {

	private static final long serialVersionUID = -4270116593124582451L;

	private int contractType;
	private String contractName;
	private String version;
	private Timestamp lastTimestamp;
	private String fileName;
	
	public int getContractType() {
		return contractType;
	}
	public void setContractType(int contractType) {
		this.contractType = contractType;
	}
	public String getContractName() {
		return contractName;
	}
	public void setContractName(String contractName) {
		this.contractName = contractName;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Timestamp getLastTimestamp() {
		return lastTimestamp;
	}
	public void setLastTimestamp(Timestamp lastTimestamp) {
		this.lastTimestamp = lastTimestamp;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
