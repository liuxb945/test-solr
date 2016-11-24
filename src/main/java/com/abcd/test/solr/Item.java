package com.abcd.test.solr;

import java.util.List;
import java.util.Map;

import org.apache.solr.client.solrj.beans.Field;

public class Item {
    @Field
    private long id;
    @Field
    private String subject;
    @Field
    private String content;
    @Field
    private int regionId;
    @Field
    private int categoryId;
    @Field
    private float price;
    @Field
    private String propertyValues;
    
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public int getRegionId() {
        return regionId;
    }
    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    public float getPrice() {
        return price;
    }
    public void setPrice(float price) {
        this.price = price;
    }
	public String getPropertyValues() {
		return propertyValues;
	}
	public void setPropertyValues(String propertyValues) {
		this.propertyValues = propertyValues;
	}
}
