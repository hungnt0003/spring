package com.hung.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import com.hung.common.AbstractDto;

public class ProductDto {

	private String productId;
	private String productname;
	private String price;
	private Date stDate;
	// private String id_type;
	private String status;
	private String img;
	private String color;
	private String sale;
	private String brand;
	private String origin;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Date getStDate() {
		return stDate;
	}

	public void setStDate(Date stDate) {
		this.stDate = stDate;
	}

	public String getMadeIn() {
		return madeIn;
	}

	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	public Date getuTimestamp() {
		return uTimestamp;
	}

	public void setuTimestamp(Date uTimestamp) {
		this.uTimestamp = uTimestamp;
	}

	public String getIdpost() {
		return idpost;
	}

	public void setIdpost(String idpost) {
		this.idpost = idpost;
	}

	private String madeIn;
	private String size;
	private Date uTimestamp;
	private String idpost;

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getSale() {
		return sale;
	}

	public void setSale(String sale) {
		this.sale = sale;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

}
