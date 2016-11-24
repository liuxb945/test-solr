package com.abcd.test.solr;

import java.io.Serializable;
import java.util.List;

public class PropertyType implements Serializable {
	public PropertyType(Integer id, String name, List<PropertyItem> propItems) {
		super();
		this.id = id;
		this.name = name;
		this.propItems = propItems;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = -9030755385522266241L;
	private Integer id;
	private String name;
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
	public List<PropertyItem> getPropItems() {
		return propItems;
	}
	public void setPropItems(List<PropertyItem> propItems) {
		this.propItems = propItems;
	}
	private List<PropertyItem> propItems;

}
