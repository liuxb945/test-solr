package com.abcd.test.solr;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;

public class SolrjUpdate {

	   
    public static void main(String[] args) {
       
       
        String person = "http://localhost:8080/solr/mysolr/dataimport?command=delta-import&clean=true&commit=true";
        //构造HttpClient的实例
        String str=HttpUtils.sendGet(person);
        System.out.println(str);
    }

}
