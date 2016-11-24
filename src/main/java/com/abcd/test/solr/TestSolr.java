package com.abcd.test.solr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.lucene.search.Query;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrQuery.ORDER;
import org.apache.solr.client.solrj.SolrQuery.SortClause;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.impl.XMLResponseParser;
import org.apache.solr.client.solrj.response.FacetField;
import org.apache.solr.client.solrj.response.FacetField.Count;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.FacetParams;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKQueryParser;


public class TestSolr {
    private static HashMap<Integer, String> mapRegion = new HashMap<Integer, String>();
    private static HashMap<Integer, String> mapCategory = new HashMap<Integer, String>();
    private static HashMap<Integer, PropertyType> mapPropertyType=new HashMap<Integer,PropertyType>();
    public static void main(String[] args) throws IOException,
            SolrServerException {
        // ------------------------------------------------------
        // Set map
        // ------------------------------------------------------
        mapRegion.put(1, "罗湖区");
        mapRegion.put(2, "南山区");
        mapRegion.put(3, "龙岗区");
        mapRegion.put(4, "福田区");
        mapCategory.put(1, "单间");
        mapCategory.put(2, "2房1厅");
        mapCategory.put(3, "3房2厅");
        mapCategory.put(4, "1房1厅");
        
        List<PropertyItem> lstPropItem1=new ArrayList<PropertyItem>();
        PropertyItem p11=new PropertyItem();
        p11.setId(11);
        p11.setName("颜色");
        p11.setValue("黄");
        p11.setPropertyTypeId(1);
        lstPropItem1.add(p11);
        PropertyItem p12=new PropertyItem();
        p12.setId(12);
        p12.setName("颜色");
        p12.setValue("蓝");
        p12.setPropertyTypeId(1);
        lstPropItem1.add(p12);
        PropertyType prop1=new PropertyType(1,"颜色",lstPropItem1);
        
        List<PropertyItem> lstPropItem2=new ArrayList<PropertyItem>();
        PropertyItem p21=new PropertyItem();
        p21.setId(11);
        p21.setName("结构");
        p21.setValue("混凝土");
        p21.setPropertyTypeId(2);
        lstPropItem1.add(p11);
        PropertyItem p22=new PropertyItem();
        p22.setId(22);
        p22.setName("结构");
        p22.setValue("砖");
        p22.setPropertyTypeId(2);
        lstPropItem2.add(p12);
        PropertyType prop2=new PropertyType(2,"结构",lstPropItem2);
        
        List<PropertyItem> lstPropItem3=new ArrayList<PropertyItem>();
        PropertyItem p31=new PropertyItem();
        p31.setId(31);
        p31.setName("楼高");
        p31.setValue("高层");
        p31.setPropertyTypeId(3);
        lstPropItem1.add(p11);
        PropertyItem p32=new PropertyItem();
        p32.setId(32);
        p32.setName("楼高");
        p32.setValue("中层");
        p32.setPropertyTypeId(3);
        lstPropItem3.add(p32);
        PropertyType prop3=new PropertyType(3,"楼高",lstPropItem3);
        
        String url = "http://localhost:8080/solr/myproduct";
        HttpSolrServer core = new HttpSolrServer(url);
        core.setMaxRetries(1);
        core.setConnectionTimeout(5000);
        core.setParser(new XMLResponseParser()); // binary parser is used by
                                                    // default
        core.setSoTimeout(1000); // socket read timeout
        core.setDefaultMaxConnectionsPerHost(100);
        core.setMaxTotalConnections(100);
        core.setFollowRedirects(false); // defaults to false
        core.setAllowCompression(true);

        // ------------------------------------------------------
        // remove all data
        // ------------------------------------------------------
        core.deleteByQuery("*:*");
        List<Item> items = new ArrayList<Item>();
        items.add(makeItem(items.size() + 1, "龙城公寓一房一厅", "豪华城城公寓1房1厅，拧包入住", 1, 1, 1200f,"1_11,2_21,3_31"));
        items.add(makeItem(items.size() + 1, "兴新宿舍楼 1室0厅", " 中等装修 招女性合租", 1, 1, 1000f,"1_11,2_22"));
        items.add(makeItem(items.size() + 1, "西丽新屋村新宿舍楼单间", " 无敌装修只招女性", 2, 1, 1000f,"1_11,3_32"));
        items.add(makeItem(items.size() + 1, "大芬村信和爱琴居地铁口2房1厅", " 地铁口 + 出行便利=居家首选", 3, 2, 2000f,"1_12,2_21"));
        items.add(makeItem(items.size() + 1, "龙岗富豪花园3房2厅出租", " 离地铁口只要5分钟，快来秒杀吧", 3, 3, 4500f,"1_11,2_21"));
        items.add(makeItem(items.size() + 1, "海景房园3房2厅出租", "无敌海景，可以看到伦敦", 4, 3, 8500f,"1_11,2_21"));
        items.add(makeItem(items.size() + 1, "天域花园1房1厅出租", "男女不限，入住免水电一月", 2, 4, 1500f,"1_11,2_21"));
        items.add(makeItem(items.size() + 1, "神一样的漂亮，玉馨山庄3房2厅", "心动不如行动，拧包即可入住，来吧！", 1, 3, 9500f,"1_12,2_22"));
        items.add(makeItem(items.size() + 1, "玉馨山庄2房1厅，情侣最爱", "宅男宅女快来吧只要2500，走过路过，别再错过", 1, 2, 2500f,"1_11,2_21"));
        items.add(makeItem(items.size() + 1, "天域花园3房2厅出租", "都来看看，都来瞄瞄，3房只要7500.", 4, 3, 7500f,"1_11,2_21"));
        items.add(makeItem(items.size() + 1, "深都花园出租3房2厅", "找爱干净的人氏，全新装修", 4, 3, 5200f,"1_11,3_31"));
        core.addBeans(items);
        // commit
        core.commit();

        // ------------------------------------------------------
        // search
        // ------------------------------------------------------
        SolrQuery query = new SolrQuery();
        query.setQuery("*:*");
        query.setStart(0); // query的开始行数(分页使用)
        query.setRows(100); // query的返回行数(分页使用)
        query.setFacet(true); // 设置使用facet
        query.setFacetMinCount(1); // 设置facet最少的统计数量
        query.setFacetLimit(10); // facet结果的返回行数
        query.addFacetField("categoryId", "regionId"); // facet的字段
        query.setFacetSort(FacetParams.FACET_SORT_COUNT);
        query.addSort(new SortClause("id", ORDER.asc)); // 排序
        QueryResponse response = core.query(query);
        List<Item> items_rep = response.getBeans(Item.class);
        List<FacetField> facetFields = response.getFacetFields();
        // 因为上面的start和rows均设置为0，所以这里不会有query结果输出
        System.out.println("--------------------");
        System.out.println("Search result:");
        for (Item item : items_rep) {
            System.out.println("id=" + item.getId() + "\tsubject=" + item.getSubject()
                    + "\tregion=" + mapRegion.get(item.getRegionId())
                    + "\tcategory=" + mapCategory.get(item.getCategoryId())
                    + "\tprice=" + item.getPrice()+"\tpropertyValues="+item.getPropertyValues());
        }
        // 打印所有facet
        for (FacetField ff : facetFields) {
            System.out.println("--------------------");
            System.out.println("name=" + ff.getName() + "\tcount=" + ff.getValueCount());
            System.out.println("--------------------");
            switch (ff.getName()) {
            case "regionId":
                printOut(mapRegion, ff.getValues());
                break;
            case "categoryId":
                printOut(mapCategory, ff.getValues());
                break;
            default:
            	for (Count count : ff.getValues()) {
                    System.out.println("name=" + count.getName() + "\tcount=" + count.getCount());
                }
                System.out.println("--------------------");
                break;
            }
        }
    }
    
    @Test
    public void testSearch() throws SolrServerException, IOException{
    	// ------------------------------------------------------
        // Set map
        // ------------------------------------------------------
        mapRegion.put(1, "罗湖区");
        mapRegion.put(2, "南山区");
        mapRegion.put(3, "龙岗区");
        mapRegion.put(4, "福田区");
        mapCategory.put(1, "单间");
        mapCategory.put(2, "2房1厅");
        mapCategory.put(3, "3房2厅");
        mapCategory.put(4, "1房1厅");
    	String url = "http://localhost:8080/solr/myproduct";
        HttpSolrServer core = new HttpSolrServer(url);
        core.setMaxRetries(1);
        core.setConnectionTimeout(5000);
        core.setParser(new XMLResponseParser()); // binary parser is used by
                                                    // default
        core.setSoTimeout(1000); // socket read timeout
        core.setDefaultMaxConnectionsPerHost(100);
        core.setMaxTotalConnections(100);
        core.setFollowRedirects(false); // defaults to false
        core.setAllowCompression(true);
        String searchContent="大芬村";
//        SolrQuery query = new SolrQuery();
//        query.setQuery("龙城公寓 大学宿舍");
        Query q = IKQueryParser.parse("subject", searchContent);
//		Query q = IKQueryExpressionParser.
		String finalquery = q.toString();// 最终查询语句
		System.out.println("finalquery = " + finalquery);
		SolrQuery query = new SolrQuery();
		query.setQuery(finalquery);
        query.setParam("q.op", "OR");
        query.setParam("df", "subject");
        query.setStart(0); // query的开始行数(分页使用)
        query.setRows(100); // query的返回行数(分页使用)
        query.setFacet(true); // 设置使用facet
        query.setFacetMinCount(1); // 设置facet最少的统计数量
        query.setFacetLimit(10); // facet结果的返回行数
        query.addFacetField("categoryId", "regionId","propertyValues"); // facet的字段
        query.setFacetSort(FacetParams.FACET_SORT_COUNT);
        query.addSort(new SortClause("id", ORDER.asc)); // 排序
        QueryResponse response = core.query(query);
        List<Item> items_rep = response.getBeans(Item.class);
        List<FacetField> facetFields = response.getFacetFields();
        // 因为上面的start和rows均设置为0，所以这里不会有query结果输出
        System.out.println("--------------------");
        System.out.println("Search result:");
        for (Item item : items_rep) {
            System.out.println("id=" + item.getId() + "\tsubject=" + item.getSubject()
                    + "\tregion=" + mapRegion.get(item.getRegionId())
                    + "\tcategory=" + mapCategory.get(item.getCategoryId())
                    + "\tprice=" + item.getPrice()+"\tpropertyValues="+item.getPropertyValues());
        }
        // 打印所有facet
        for (FacetField ff : facetFields) {
            System.out.println("--------------------");
            System.out.println("name=" + ff.getName() + "\tcount=" + ff.getValueCount());
            System.out.println("--------------------");
            switch (ff.getName()) {
            case "regionId":
                printOut(mapRegion, ff.getValues());
                break;
            case "categoryId":
                printOut(mapCategory, ff.getValues());
                break;
            default:
            	for (Count count : ff.getValues()) {
                    System.out.println("name=" + count.getName() + "\tcount=" + count.getCount());
                }
                System.out.println("--------------------");
                break;
            }
        }
    }
    
    @SuppressWarnings({ "rawtypes" })
    private static void printOut(HashMap map, List<Count> counts) {
        for (Count count : counts) {
            System.out.println("name=" + map.get(Integer.parseInt(count.getName())) + "\tcount=" + count.getCount());
        }
        System.out.println("--------------------");
    }

    private static Item makeItem(long id, String subject, String content, int regionId, int categoryId, float price,String propertyValues) {
        Item item = new Item();
        item.setId(id);
        item.setSubject(subject);
        item.setContent(content);
        item.setRegionId(regionId);
        item.setCategoryId(categoryId);
        item.setPrice(price);
        item.setPropertyValues(propertyValues);
        return item;
    }
}
