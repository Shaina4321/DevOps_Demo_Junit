package acit.junit.test;


import java.io.IOException;

import org.junit.Test;
import org.xml.sax.SAXException;
// import static org.junit.Assert.assertEquals;
 import org.custommonkey.xmlunit.XMLAssert;

import java.io.*;
import java.lang.String;

public class DevOps_Demo_TestCases {
	//String workDir =System.getProperty("user.dir");
	static String workDir = "//var//lib//jenkins//workspace//CheckoutSourceCode//DevOps_Demo//Buildscript";
	String message = readFileContent(workDir+ "//DevOps_Demo//expected//expectedTestCase1output.xml"); 
    String message1 = readFileContent(workDir+ "//DevOps_Demo//actual//actualTestCase1output.xml");
    String message2 = readFileContent(workDir+ "//DevOps_Demo//expected//expectedTestCase2output.xml"); 
    String message3 = readFileContent(workDir+ "//DevOps_Demo//actual//actualTestCase2output.xml");
    String message4 = readFileContent(workDir+ "//DevOps_Demo//expected//expectedTestCase3output.xml"); 
    String message5 = readFileContent(workDir+ "//DevOps_Demo//actual//actualTestCase3output.xml");
    String message6 = readFileContent(workDir+ "//DevOps_Demo//expected//expectedTestCase4output.xml"); 
    String message7 = readFileContent(workDir+ "//DevOps_Demo//actual//actualTestCase4output.xml");
    String message8 = readFileContent(workDir+ "//DevOps_Demo//expected//expectedTestCase5output.xml"); 
    String message9 = readFileContent(workDir+ "//DevOps_Demo//actual//actualTestCase5output.xml");

    @SuppressWarnings("finally")
	public String readFileContent(String fileName) {
		BufferedReader br = null;
		StringBuilder sb = new StringBuilder();
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = br.readLine();

			while (line != null) {
				sb.append(line);
				sb.append("\n");
				line = br.readLine();
			}
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			return sb.toString();
		}
	}

    @Test
	public void testPrintMessage() {
		try {

			XMLAssert.assertXMLEqual(message, message1);
			System.out.println("test1");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    @Test
	public void test2PrintMessage() {
		try {

			XMLAssert.assertXMLEqual(message2, message3);
			System.out.println("test2");
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
    
    @Test
   	public void test3PrintMessage() {
   		try {

   			XMLAssert.assertXMLEqual(message4, message5);
   			System.out.println("test3");
   		} catch (SAXException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}

   	}
       
    @Test
   	public void test4PrintMessage() {
   		try {

   			XMLAssert.assertXMLEqual(message6, message7);
   			System.out.println("test4");
   		} catch (SAXException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}

   	} 
  
    @Test
   	public void test5PrintMessage() {
   		try {

   			XMLAssert.assertXMLEqual(message8, message9);
   			System.out.println("test5");
   		} catch (SAXException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		} catch (IOException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}

   	} 
}
