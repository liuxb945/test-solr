package com.abcd.test.solr;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

import org.apache.lucene.search.Query;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.wltea.analyzer.lucene.IKQueryParser;

public class SolrjSearch {

	public static void main(String[] args) throws Exception {

		// searchContent的内容,我们可以假想是从页面文件搜索文本框中输入的字符内容
		String searchContent = "good";

		// 远程服务端地址,得到solr服务
		String SOLR_URL = "http://localhost:8080/solr/mysolr";
		HttpSolrClient solrServer = null;
		SolrDocumentList docs = null;
		String finalquery = "";
		int start = 0;
		int rows = 10;// 默认是10

		// 建立solrQuery对象
		SolrQuery query = new SolrQuery().setFacet(true);

		if ("".equals(searchContent)) {
			query.setQuery("*:*");
		} else {
			try {
				// 分词后，再进行搜索(返回的是分词后的string)(先分词再搜索，否则会出现搜索不精确)
				// 此处entity是个string,全文搜索字段，copyfield中拷贝了需要索引的字段在里面
				Query q = IKQueryParser.parse("entity", searchContent);
//				Query q = IKQueryExpressionParser.
				finalquery = q.toString();// 最终查询语句
				System.out.println("finalquery = " + finalquery);

			} catch (IOException e) {
				e.printStackTrace();
			}
			query.setQuery(finalquery);

		}

		query.setStart(start);// 从多少条开始
		query.setRows(rows);// 每页显示多少行

		query.setHighlight(true);// 开启高亮组件
		query.setHighlight(true).setHighlightSnippets(1); // set other params as
															// needed
		query.setHighlightSimplePre("");
		query.setHighlightSimplePost("");
		query.setParam("hl.fl", "title", "content");
		query.setSort("content", SolrQuery.ORDER.asc);// 整体排序

		try {

			// 建立服务器
			solrServer = new HttpSolrClient(SOLR_URL);
			solrServer.setMaxTotalConnections(100);
			solrServer.setSoTimeout(10000); // socket read timeout
			solrServer.setConnectionTimeout(5000);
			// 查询并传值
			QueryResponse rsp = solrServer.query(query);
			docs = rsp.getResults();// 得到所有的documents（数据）

			System.out.println("rsp = " + rsp);
			System.out.println("docs = " + docs);

			for (SolrDocument solrDocument : docs) {// 将高亮后的数据填充doc

				String id = (String) solrDocument.getFieldValue("id");
				List highlightSnippets = rsp.getHighlighting().get(String.valueOf(id)).get("title");
				List highlightbrief = rsp.getHighlighting().get(String.valueOf(id)).get("content");
				if (highlightSnippets != null) {
					solrDocument.setField("title", highlightSnippets.get(0));
				}
				if (highlightbrief != null) {
					solrDocument.setField("content", highlightbrief.get(0));
				}

			}
		} catch (SolrServerException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

}