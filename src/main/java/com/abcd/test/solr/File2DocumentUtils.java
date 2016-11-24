package com.abcd.test.solr;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;

public class File2DocumentUtils {

    // 文件：name, content, size, path （文件到document的转换）
    public static Document file2Document(String path) {
        File file = new File(path);

        Document doc = new Document();//Document是field的集合，很多键值对
        doc.add(new Field("name", file.getName(), Store.YES, Index.ANALYZED));//Index.ANALYZED分词后进行索引
        doc.add(new Field("content", readFileContent(file), Store.YES, Index.ANALYZED));
        doc.add(new Field("size", String.valueOf(file.length()), Store.YES,Index.NOT_ANALYZED));//Index.NOT_ANALYZED不分词直接索引
        doc.add(new Field("path", file.getAbsolutePath(), Store.YES, Index.NOT_ANALYZED));
        return doc;
    }

    // public static void document2File(Document doc ){
    //        
    // }

    /**
     * 读取文件内容
     */
    public static String readFileContent(File file) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            StringBuffer content = new StringBuffer();

            for (String line = null; (line = reader.readLine()) != null;) {
                content.append(line).append("\n");
            }

            return content.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * <pre>
     * 获取 name 属性的值的两种方法：
     * 1，Field f = doc.getField("name");
     *    f.stringValue();
     * 2，doc.get(&quot;name&quot;);
     * </pre>
     * 
     * @param doc
     */
    public static void printDocumentInfo(Document doc) {
        // Field f = doc.getField("name");
        // f.stringValue();
        System.out.println("------------------------------");
        System.out.println("name     = " + doc.get("name"));
        System.out.println("content  = " + doc.get("content"));
        System.out.println("size     = " + Long.parseLong((doc.get("size"))));
        System.out.println("path     = " + doc.get("path"));
    }

}
