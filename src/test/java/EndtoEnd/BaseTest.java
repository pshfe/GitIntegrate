package EndtoEnd;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest 
{
	public WebDriver dr;
	public Properties prop;
	
	
   public WebDriver initializedriver() throws IOException
   {
	 
	  prop= new Properties();
	  FileInputStream fis=new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\GlobalData.properties");
	  prop.load(fis);
	  String browsername=System.getProperty("browser")!=null ? System.getProperty("browser") : prop.getProperty("browser");
     
      
	  if(browsername.contains("chrome"))
	  {
		  WebDriverManager.chromedriver().setup();
		  ChromeOptions co= new ChromeOptions();
		  if (browsername.contains("headless"))
		  {
	            co.addArguments("headless");
		  }
          dr= new ChromeDriver(co);
          dr.manage().window().setSize(new Dimension(1440,900));
       System.out.println("Browser selected: "+browsername);
	  }
	  else if(browsername.equalsIgnoreCase("edge"))
	  {
		  WebDriverManager.edgedriver().setup();
		  dr= new EdgeDriver();
	  }
	  else if(browsername.equalsIgnoreCase("firefox"))
	  {
		  WebDriverManager.firefoxdriver().setup();
		  dr= new FirefoxDriver();
	  }
      dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      return dr;

  }
  
   public List<HashMap<String, String>> readjsondata(String filepath) throws IOException
   {
 	  String jsoncontent=FileUtils.readFileToString(new File(filepath),StandardCharsets.UTF_8);
 	  
 	  ObjectMapper mapper= new ObjectMapper();
 	  List<HashMap<String,String>>data= mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String,String>>>() {
 		  
 	  });
 	  return data;
   }
   
   public String getScreenshot(String testcasename, WebDriver driver) throws IOException
	{
		TakesScreenshot ts=(TakesScreenshot)driver;
		File source=ts.getScreenshotAs(OutputType.FILE);
		String destinationfile=System.getProperty("user.dir")+"\\ScreenshotReport\\"+testcasename+".png";
		FileUtils.copyFile(source, new File(destinationfile));
		return destinationfile;
	}
   
  //@BeforeTest(alwaysRun=true)
  	public void initialize() throws IOException
  		{
	           
  			   dr =initializedriver();
  			   dr.get(prop.getProperty("url"));
  			      
  		}
  @AfterMethod(alwaysRun=true)
	public void closedriver()
	{
	   
		dr.quit();
	}  
	
	
}
