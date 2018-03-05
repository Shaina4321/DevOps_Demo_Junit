package acit.junit.helper;

import java.io.File;
import acit.junit.helper.FileOperator;

import javax.xml.soap.*;
import javax.xml.transform.*;
import javax.xml.transform.stream.*;

public class DevOps_Demo_Helper {
	static String workDir = "//var//lib//jenkins//workspace//CheckoutSourceCode//DevOps_Demo//Buildscript";
	//static String workDir =System.getProperty("user.dir"); 
	 public static SOAPMessage createSOAPRequest(String testcase) throws Exception {
	        MessageFactory messageFactory = MessageFactory.newInstance();
	        SOAPMessage soapMessage = messageFactory.createMessage();
	        SOAPPart soapPart = soapMessage.getSOAPPart();
	        
	        String location = "//DevOps_Demo//request//"+testcase+".xml";
	        
	        FileOperator fileOperator = new FileOperator();
	        String request_message = fileOperator.fileReader(workDir+ location);
	        
	        String serverURI = "urn:examples:helloservice";

	        // SOAP Envelope
	        SOAPEnvelope envelope = soapPart.getEnvelope();
	        envelope.addNamespaceDeclaration("urn", serverURI);

	        // SOAP Body
	        SOAPBody soapBody = envelope.getBody();
	        SOAPElement soapBodyElem = soapBody.addChildElement("sayHello", "urn");
	        SOAPElement soapBodyElem1 = soapBodyElem.addChildElement("request");
	        soapBodyElem1.addTextNode(request_message);
	        
	        	        
	        MimeHeaders headers = soapMessage.getMimeHeaders();
	        headers.addHeader("SOAPAction", "sayHello");

	        soapMessage.saveChanges();

	        /* Print the request message */
	        System.out.print("Request SOAP Message = ");
	        soapMessage.writeTo(System.out);
	        
	        System.out.println();

	        return soapMessage;
	    }

	    /**
	     * Method used to print the SOAP Response
	     */
	    public static void printSOAPResponse(SOAPMessage soapResponse, String testcase) throws Exception {
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        Transformer transformer = transformerFactory.newTransformer();
	        Source sourceContent = soapResponse.getSOAPPart().getContent();
	       // System.out.print("\nResponse SOAP Message = ");
	       // StreamResult result = new StreamResult(System.out);
	        
	        String location = "//DevOps_Demo//actual//"+"actual"+testcase+"output.xml";
	        StreamResult result = new StreamResult(new File(workDir+ location));
	        transformer.transform(sourceContent, result);
	    }


}
