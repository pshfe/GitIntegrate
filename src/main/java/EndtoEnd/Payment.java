package EndtoEnd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Payment extends Reusablemethod
{
	WebDriver dr;
	public Payment(WebDriver dr) 
	{
		super(dr);
		this.dr=dr;
	}
	
	
	By countryname=By.xpath("//input[@placeholder='Select Country']");
	By countrywait=By.cssSelector(".ta-results");
	By countrynamelist=By.cssSelector(".ta-results button");
	By placeorder=By.cssSelector(".action__submit");
	
	public void selectcountry(String cname)
	{
		dr.findElement(countryname).sendKeys(cname);
	}
	
	public void getcountrylist()
	{
		System.out.println("Country Selection ");
		elementtoappear(countrywait);
		List<WebElement> cname= dr.findElements(countrynamelist);
	     System.out.println("No of countries in list :"+cname.size());
	    try
	    {
	    for(WebElement b:cname)
	     {
	    	 if(b.getText().equalsIgnoreCase("India"))
	    	 {
	    		 b.click();
	    		 break;
	    	 }
	    	 
	     }
	    }
	    catch(Exception e)
	    {
	    	System.out.println("Stale Exception found");
	    }
	}
	
	
	public Thankyoupage placeorder()
	{
		dr.findElement(placeorder).click();
		Thankyoupage tp= new Thankyoupage(dr);
		return tp;
	}
}
