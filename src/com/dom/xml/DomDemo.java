package com.dom.xml;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class DomDemo {
     public static void main(String[] args){
  
   
   try {
	  // 1.解析器工厂类：DocumentBuilderFactory
	   DocumentBuilderFactory 	factory= DocumentBuilderFactory.newInstance();
	   //2.解析器：DocumentBuilder
	   DocumentBuilder builder=  factory.newDocumentBuilder();
	  // 3.文档树模型Document
	   /*  将需要解析的xml文档转化为输入流
	   InputStream is = new FileInputStream("books.xml");
	   Document doc = db.parse(is);
	    */
		Document document=builder.parse("books.xml");
		 //得到文档名称为book的元素的节点列表
		NodeList books=document.getElementsByTagName("book");
		System.out.println("总共有"+books.getLength()+" 本书");
		for(int i=0;i<books.getLength();i++){
			//获取属性的map集合
			 NamedNodeMap attrs=books.item(i).getAttributes();
			 System.out.println("第"+(i+1)+"本书，有"+attrs.getLength()+"个属性");
			for(int j=0;j<attrs.getLength();j++){
				System.out.print("第"+(i+1)+"本书的属性名："+attrs.item(j).getNodeName());
				System.out.println("第"+(i+1)+"本书的属性值："+attrs.item(j).getNodeValue());
			}
			//循环获取所有子节点
			 NodeList nlist=	books.item(i).getChildNodes();
			 System.out.println("第"+(i+1)+"本书共有"+nlist.getLength()+"个子节点");
			 for(int k=0;k<nlist.getLength();k++){
				Node node= nlist.item(k);
				if(node.getNodeType()!=Node.TEXT_NODE){
					System.out.print("属性名："+node.getNodeName());
					//System.out.println("---属性值："+node.getFirstChild().getNodeValue());
					System.out.println("---属性值："+node.getTextContent());
				}
			 }
		}
    } catch (ParserConfigurationException e) {
        	e.printStackTrace();
    }catch (SAXException e) {
 		e.printStackTrace();
 	} catch (IOException e) {
 		e.printStackTrace();
 	}
     }
}
