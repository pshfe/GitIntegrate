package EndtoEnd;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standalone extends BaseTest {

	
	//String name="IPHONE 13 PRO";
	
	@Test(dataProvider="getdata",groups={"providingdata"})
	public void Mainordersubmit(String email ,String password,String name) throws IOException
	{
		System.out.println("Excel data");
       initialize(); //if beforetest is used no  need to call here initialize loginpage object over there and return loginpageobject
       LoginPage lp= new LoginPage(dr);
       lp.loginapplication(email,password);
       
       Productselection ps= new Productselection(dr);
       String productname= ps.getproductlist(name);
       System.out.println("Product added to cart is :"+productname);
       ps.gettext();
       ps.getcartclick();
      
      
       MyCart mc= new MyCart(dr);
       mc.productcheck(name);
       mc.getcheckout();
      
       Payment m =new Payment(dr);
       m.selectcountry("ind");
       m.getcountrylist();
       Thankyoupage tp= m.placeorder();    //we can create object of next page in this page-last method.Instead of creating object here.
       String ordermessage=tp.orderplacedmessage();
       System.out.println("Message printed :"+ordermessage);
       Assert.assertEquals(ordermessage,"THANKYOU FOR THE ORDER.");
      /* OrderDetails od= new OrderDetails(dr);
       Assert.assertTrue(od.myorder(name)); */
       
	}
	
	@Test(dataProvider="getdata",dependsOnMethods= {"Mainordersubmit"})
	public void Myorders(String email ,String password,String name) throws IOException
	{
	    initialize();
		LoginPage lp= new LoginPage(dr);
        lp.loginapplication(email,password);
        OrderDetails od= new OrderDetails(dr);
        Assert.assertTrue(od.myorder(name));
	}
	
	//Both data provider working
	
/*	@DataProvider
	public Object[][] getdata()
	{
		return new Object[][] {{"pooja.shetty@gmail.com","Qwerty@2022","IPHONE 13 PRO"},{"pooja.shetty@gmail.com","Qwerty@2022","ADIDAS ORIGINAL"}};
	}*/
	
	@DataProvider
	public Object[][] getdata() throws IOException
	{
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\EndtoEnd\\testdataexcell.xlsx");
		XSSFWorkbook wb= new XSSFWorkbook(fis);
		XSSFSheet s=wb.getSheet("login");
		int totalrows=s.getLastRowNum();
		XSSFRow r=s.getRow(0);
		int totalcols=r.getLastCellNum();
		DataFormatter f= new DataFormatter();
		String testdata[][]= new String[totalrows][totalcols];
		for(int i=1;i<=totalrows;i++)
		{
			for (int j=0;j<totalcols;j++)
			{
				testdata[i-1][j]=f.formatCellValue(s.getRow(i).getCell(j));
			}
		}
		return testdata;
	}

}
