package EndtoEnd;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends Reusablemethod
{

	WebDriver dr;
	public LoginPage(WebDriver dr)
	{
		super(dr);
		this.dr=dr;
	}
	
	By email=By.id("userEmail");
	By password=By.id("userPassword");
	By login= By.id("login");
	By errorwait=By.cssSelector(".ngx-toastr");
	

	public void loginapplication(String username ,String password1)
	{
		dr.findElement(email).sendKeys(username);
		dr.findElement(password).sendKeys(password1);
		dr.findElement(login).click();
	}
	
	
	public String errormessage()
	{
		elementtoappear(errorwait);
		return dr.findElement(errorwait).getText();
	}
	
}
