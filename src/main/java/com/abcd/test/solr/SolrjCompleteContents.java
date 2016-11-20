package com.abcd.test.solr;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.util.NamedList;

public class SolrjCompleteContents {

	   
    public static void main(String[] args) {       
       
    	HttpSolrClient service=null;
       
        service = new HttpSolrClient("http://localhost:8080/solr/mysolr");  
       
        List list = new ArrayList();  
        //查询响应结果
        QueryResponse queryResponse = new QueryResponse();  
        //查询传输参数封装
        SolrQuery query = new SolrQuery();  
       
        try {  
           
               query.setFacet(true);  
               query.setRows(0);  
               query.setQuery("title:你们");  
               query.setFacetPrefix("你们"); 
               query.addFacetField("title_auto");
               query.setFacetLimit(20);
              
               System.out.println("query.toString() = "+query.toString());  
              
               queryResponse = service.query(query); 
               NamedList nl = queryResponse.getResponse(); 

               System.out.println("nl = "+nl);
              
               NamedList nl2 = (NamedList)nl.get("facet_counts");  
               NamedList nl3 = (NamedList)nl2.get("facet_fields");  
               NamedList nl4 = (NamedList)nl3.get("title_auto");  
              
               System.out.println("nl4.size() = "+nl4.size());  
              
               Iterator it = nl4.iterator();
              
               while (it.hasNext()) {
                  
                   Entry entry = (Entry) it.next();  
                   System.out.println(entry.getKey() + "____" + entry.getValue()); 
                  
               }
              
            } catch (Exception e) {
                e.printStackTrace();  
            } 
        }
    }
