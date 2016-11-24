package com.abcd.test.solr;

import java.io.Serializable;

public class PropertyItem implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7958642975057676341L;
	private Integer id;
	private String name;
	private String value;
	private Long itemId;
	private Integer propertyTypeId;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public Integer getPropertyTypeId() {
		return propertyTypeId;
	}
	public void setPropertyTypeId(Integer propertyTypeId) {
		this.propertyTypeId = propertyTypeId;
	}

}
