package EndtoEnd;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OrderDetails extends Reusablemethod {

	
	WebDriver dr;
	public OrderDetails(WebDriver dr)
	{
		super(dr);
		this.dr=dr;
	}

	
	By orderlist=By.xpath("//tr/td[2]");
	public boolean myorder(String prodname)
	{ 
		boolean v=false;
		orderdetails();
		List<WebElement> orderlists=dr.findElements(orderlist);
		for (WebElement b:orderlists)
		{
			if(b.getText().equalsIgnoreCase(prodname))
			{
				System.out.println("Item selected: "+prodname+ " is successfully present in order details");
				v=true;
				break;
			}
		}
		return v;
		
		
	}
}
