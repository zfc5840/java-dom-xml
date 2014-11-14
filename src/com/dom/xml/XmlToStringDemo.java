package com.dom.xml;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class XmlToStringDemo {
	
	public static void main(String[] args){
		//使用Dom方式解析xml
	   //定义工厂 API，使应用程序能够从 XML 文档获取生成 DOM 对象树的解析器。
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	   //定义 API， 使其从 XML 文档获取 DOM 文档实例。使用此类，应用程序员可以从 XML 获取一个 Document。
	    DocumentBuilder builder;
	    Document doc = null;
		try {
			builder = factory.newDocumentBuilder();
			doc = (Document) builder.parse("books.xml");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    //将xml转换为String
	   //TransformerFactory 实例可用于创建 Transformer 和 Templates 对象
	    TransformerFactory  tf  =  TransformerFactory.newInstance();
	    // 此抽象类的实例能够将源树转换为结果树。 
        //  可以通过 TransformerFactory.newTransformer 方法获取此类的实例。然后可以使用此实例处理来自不同源的 XML，并将转换输出写入各种接收器
	    Transformer t;
	    ByteArrayOutputStream  bos  =  new  ByteArrayOutputStream();
		try {
			t = tf.newTransformer();
			t.transform(new DOMSource((Node) doc), new StreamResult(bos));
		    // 设置转换中实际的输出属性
		    t.setOutputProperty("encoding","utf-8");//解决中文问题,试过用GBK不行
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}catch (TransformerException e) {
			e.printStackTrace();
		}

	    /*
	     * 可使用 toByteArray() 和 toString() 获取数据
	     */
	    String xmlStr = bos.toString();

	    System.out.println("字符串为："+xmlStr);
	}
		
		
}
