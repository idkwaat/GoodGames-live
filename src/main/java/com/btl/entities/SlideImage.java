package com.btl.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="slideimages")
@Entity
public class SlideImage {
	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	@Column(name="slideid")
	private int slideId;
	@Column(name="picture", length=500)
	private String picture;
	@Column(name="title", length=500)
	private String title;
	@Column(name="brief", length=1000)
	private String brief;
	@Column(name="link", length=500)
	private String link;
	private boolean status;
	
	public SlideImage() {
		// TODO Auto-generated constructor stub
	}

	public SlideImage(int slideId, String picture, String title, String brief, String link, boolean status) {
		super();
		this.slideId = slideId;
		this.picture = picture;
		this.title = title;
		this.brief = brief;
		this.link = link;
		this.status = status;
	}
	public SlideImage(String picture, String title, String brief, String link, boolean status) {
		super();
		this.picture = picture;
		this.title = title;
		this.brief = brief;
		this.link = link;
		this.status = status;
	}

	public int getSlideId() {
		return slideId;
	}

	public void setSlideId(int slideId) {
		this.slideId = slideId;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBrief() {
		return brief;
	}

	public void setBrief(String brief) {
		this.brief = brief;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
}