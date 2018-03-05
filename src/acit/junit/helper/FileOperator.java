package acit.junit.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

//import javax.xml.soap.SOAPBody;

import org.custommonkey.xmlunit.DetailedDiff;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Assert;
import org.junit.runner.Result;
import org.xml.sax.SAXException;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


//import com.junit.mq.MQConnector;

/**
 * @author jaya.c.atmakuri
 *
 */
public class FileOperator{
	//static String workDir =System.getProperty("user.dir"); 
	static String workDir = "//var//lib//jenkins//workspace//CheckoutSourceCode//DevOps_Demo//Buildscript";
	public static void fileWriter(String message) throws IOException{
		 BufferedWriter bufferedWriter = null;
		 //File jarPath=new File(MQConnector.class.getProtectionDomain().getCodeSource().getLocation().getPath());
		 String propertiesPath = workDir+"//DevOps_Demo//resources//";
         try {
        	 String file = propertiesPath + "result.properties";
             File myFile = new File(file);
             // check if file exist, otherwise create the file before writing
             if (!myFile.exists()) {
                 myFile.createNewFile();
             }
             Writer writer = new FileWriter(myFile);
             bufferedWriter = new BufferedWriter(writer);
             bufferedWriter.write(message);
         } catch (IOException e) {
             e.printStackTrace();
         } finally{
         	if(bufferedWriter != null) bufferedWriter.close();
         }
	}
	
	 @SuppressWarnings("finally")
		public String fileReader(String fileName) {
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
	
		public void compareResult(String expected, String actual) {
			try {
				 DetailedDiff diff = new DetailedDiff(XMLUnit.compareXML(expected, actual));
				 List<?> allDifferences = diff.getAllDifferences();
			     Assert.assertEquals("Differences found: "+ diff.toString(), 0, allDifferences.size());
			     System.out.println("SUCCESS : Actual and Expected results Matched");
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		
		public static void excelWriter(Result result, String status) throws IOException{
	        try {
	            
	        	//reading build number
	        	FileOperator fileOperator = new FileOperator();
		        String request_message = fileOperator.fileReader("//var//lib//jenkins//jobs//CheckoutSourceCode" + "nextBuildNumber");
		        int buildNumber = Integer.parseInt(request_message.trim());
	        	
	            //Read the spreadsheet that needs to be updated
	            FileInputStream fsIP= new FileInputStream(new File("//var//lib//jenkins//continousdelivery//reports//junittestcase//CheckoutSourceCode//WMBSmokeTestReview_DemoApp.xls"));  
	            //Access the workbook                  
	            HSSFWorkbook wb = new HSSFWorkbook(fsIP);
	            //Access the worksheet, so that we can update / modify it. 
	            HSSFSheet worksheet = wb.getSheet("TestReview");
	            
	            HSSFRow rowNum = worksheet.createRow((short) buildNumber);
	           
	            // Access the second cell in second row to update the value
	            HSSFCell cell = rowNum.createCell((short) 1);
	            cell.setCellValue(buildNumber);
	            HSSFCell cell1 = rowNum.createCell((short) 2);
	            cell1.setCellValue(result.getRunCount());
	            HSSFCell cell2 = rowNum.createCell((short) 3);
	            cell2.setCellValue(result.getFailureCount());
	            HSSFCell cell3 = rowNum.createCell((short) 4);
	            cell3.setCellValue(status);
	            HSSFCell cell4 = rowNum.createCell((short) 5);
	            cell4.setCellValue("Failure report : "+result.getFailures());
	            //Close the InputStream  
	            fsIP.close(); 
	            //Open FileOutputStream to write updates
	            FileOutputStream output_file =new FileOutputStream(new File("//var//lib//jenkins//continousdelivery//reports//junittestcase//CheckoutSourceCode//WMBSmokeTestReview_DemoApp.xls"));  
	             //write changes
	            wb.write(output_file);
	            //close the stream
	            output_file.flush();
	            output_file.close();
	           
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	    }

		
}