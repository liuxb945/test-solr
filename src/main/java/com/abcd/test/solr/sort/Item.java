package com.abcd.test.solr.sort;

import java.util.Date;

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
    private Date createTime;
    @Field
    private long point;
    @Field
    private boolean vip;
    
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
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public long getPoint() {
        return point;
    }
    public void setPoint(long point) {
        this.point = point;
    }
    public boolean isVip() {
        return vip;
    }
    public void setVip(boolean vip) {
        this.vip = vip;
    }
}
