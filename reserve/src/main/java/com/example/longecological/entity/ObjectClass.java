package com.example.longecological.entity;

public class ObjectClass {
	
	private static final long serialVersionUID = 8828442426459020690L;

	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ObjectClass [data=" + data + "]";
	}

}
