package com.everis.model;

public class DBReturn {
	private String dbOrderNumber;

	public String getDbOrderNumber() {
		return dbOrderNumber;
	}

	public void setDbOrderNumber(String dbOrderNumber) {
		this.dbOrderNumber = dbOrderNumber;
	}

	@Override
	public String toString() {
		return dbOrderNumber;
	}
}
