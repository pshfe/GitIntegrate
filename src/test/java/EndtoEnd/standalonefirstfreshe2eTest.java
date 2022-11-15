 package EndtoEnd;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.deser.Deserializers.Base;

import io.github.bonigarcia.wdm.WebDriverManager;

public class standalonefirstfreshe2eTest {

	public static void main(String[] args) throws InterruptedException
	{
		System.out.println("Firststandalone");
      WebDriverManager.chromedriver().setup();
      WebDriver dr= new ChromeDriver(); 
	
      String name="IPHONE 13 PRO";
      WebDriverWait w= new WebDriverWait(dr, Duration.ofSeconds(5));

      dr.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
      System.out.println("First E2E Test");
      dr.get("https://rahulshettyacademy.com/client");
      dr.findElement(By.id("userEmail")).sendKeys("pooja.shetty@gmail.com");
      dr.findElement(By.id("userPassword")).sendKeys("Qwerty@2022");
      dr.findElement(By.id("login")).click();
      List<WebElement> s= dr.findElements(By.cssSelector(".mb-3"));
      System.out.println("No of Products: "+s.size());
      for (WebElement e:s)
      {
    	  String pname=e.findElement(By.cssSelector("b")).getText();
    	  System.out.println("Product name : "+pname);
    	  if(pname.equalsIgnoreCase(name))
    	  {
    		  e.findElement(By.cssSelector(".card-body button:last-of-type")).click();
    		  //e.findElement(By.xpath("//div[@class='card']/div/button[2]")).click(); [xpath not working]
    		  break;
    	  }
    	
      }
      
      w.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
      System.out.println(dr.findElement(By.xpath("//div[@role='alertdialog']")).getText());
      dr.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
      List<WebElement> cartproducts=dr.findElements(By.cssSelector(".cartSection h3"));
      for (WebElement a:cartproducts)
      {
    	  if(a.getText().equalsIgnoreCase(name))
    	  {
    		  Assert.assertTrue(true);
    		  System.out.println("Proper product added");
    	  }
      }
      dr.findElement(By.cssSelector(".totalRow button")).click();
      dr.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("ind");
      w.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".ta-results")));
      List<WebElement> cname= dr.findElements(By.cssSelector(".ta-results button"));
     System.out.println(cname.size());
    try
    {
    for(WebElement b:cname)
     {
    	 if(b.getText().equalsIgnoreCase("India"))
    	 {
    		 b.click();
    	 }
    	 
     }
    }
    catch(Exception e)
    {
    	System.out.println("Stale Exception found");
    }
     dr.findElement(By.cssSelector(".action__submit")).click();
     String message=dr.findElement(By.cssSelector(".hero-primary")).getText();
     System.out.println("Message printed :"+message);
     Assert.assertEquals(message,"THANKYOU FOR THE ORDER.");
     dr.quit();
	}
	

}
