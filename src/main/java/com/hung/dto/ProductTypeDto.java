package com.hung.dto;

import java.util.Date;

public class ProductTypeDto {

	private String typeId;
	private String productId;
	private String typeName;
	private String status;
	private Date uTimestamp;

	public String getTypeId() {
		return typeId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getuTimestamp() {
		return uTimestamp;
	}

	public void setuTimestamp(Date uTimestamp) {
		this.uTimestamp = uTimestamp;
	}

}
