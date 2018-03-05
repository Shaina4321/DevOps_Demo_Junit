package acit.junit.helper;


import java.io.File;
import java.io.FileInputStream;
//import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPMessage;

//import org.custommonkey.xmlunit.XMLAssert;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
//import java.util.Properties;
//import org.xml.sax.SAXException;

import acit.junit.test.DevOps_Demo_TestCases;

public class TestRunner {
	
	static String status = "failure";
	public static void main(String[] args) {

		try {
			
			//shaina code starts
			 InputStream is = null;
		     Properties prop = null;

			        prop = new Properties();
			        is = new FileInputStream(new File("/var/lib/jenkins/continousdelivery/runtime/buildconfig/SharedConfigurationData_CheckOut.properties"));
			            prop.load(is);
			            System.out.println("basedir: "+prop.getProperty("USER_DIR"));

					 PropertyInfo.workDir = prop.getProperty("USER_DIR");
			        

		
			// Create SOAP Connection
			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory
					.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory
					.createConnection();

			// Send SOAP Message to SOAP Server
			String url = "http://localhost:7800/SayHello/";
			
			//TestCase1
			SOAPMessage soapResponse = soapConnection.call(DevOps_Demo_Helper.createSOAPRequest("TestCase1"), url);
	        
			System.out.print("Response SOAP Message = ");
			soapResponse.writeTo(System.out);
	        
	        System.out.println();
			// Process the SOAP Response
			DevOps_Demo_Helper.printSOAPResponse(soapResponse,"TestCase1");
		
			//TestCase2
			soapResponse = soapConnection.call(DevOps_Demo_Helper.createSOAPRequest("TestCase2"), url);
	        
			System.out.print("Response SOAP Message = ");
			soapResponse.writeTo(System.out);
	        
	        System.out.println();
			// Process the SOAP Response
			DevOps_Demo_Helper.printSOAPResponse(soapResponse,"TestCase2");
			
			//TestCase3
			soapResponse = soapConnection.call(DevOps_Demo_Helper.createSOAPRequest("TestCase3"), url);
	        
			System.out.print("Response SOAP Message = ");
			soapResponse.writeTo(System.out);
	        
	        System.out.println();
			// Process the SOAP Response
			DevOps_Demo_Helper.printSOAPResponse(soapResponse,"TestCase3");
		
			//TestCase4
			soapResponse = soapConnection.call(DevOps_Demo_Helper.createSOAPRequest("TestCase4"), url);
	        
			System.out.print("Response SOAP Message = ");
			soapResponse.writeTo(System.out);
	        
	        System.out.println();
			// Process the SOAP Response
			DevOps_Demo_Helper.printSOAPResponse(soapResponse,"TestCase4");
			
			//TestCase5
			soapResponse = soapConnection.call(DevOps_Demo_Helper.createSOAPRequest("TestCase5"), url);
	        
			System.out.print("Response SOAP Message = ");
			soapResponse.writeTo(System.out);
	        
	        System.out.println();
			// Process the SOAP Response
			DevOps_Demo_Helper.printSOAPResponse(soapResponse,"TestCase5");
			
			soapConnection.close();
		} catch (Exception e) {
			System.err.println("Error occurred while sending SOAP Request to Server");
			e.printStackTrace();
		}
		Result result = JUnitCore.runClasses(DevOps_Demo_TestCases.class);
		
		for (Failure failure : result.getFailures()) {
			System.out.println("Result Failure: " + failure.toString());
		}
		System.out.println("Run Count : "+result.getRunCount());
		System.out.println("Failure Count : "+result.getFailureCount());
		System.out.println("Result Success: " + result.wasSuccessful());
		System.out.println("buildStatus: " + result.wasSuccessful());
		
		if (result.wasSuccessful()) {
            status = "buildStatus=success";
        }else{
        	status = "buildStatus=failure";
        }
		try{
		FileOperator.fileWriter(status);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try{
			FileOperator.excelWriter(result, status);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	 
}