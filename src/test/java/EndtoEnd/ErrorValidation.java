package EndtoEnd;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ErrorValidation extends BaseTest

{

    @Test(groups= {"ErrorValidation"}, retryAnalyzer=Retry.class)
	public void Loginerrorvalidate() throws IOException
	{
    	System.out.println("Error validation running");
    	initialize();
        LoginPage lp= new LoginPage(dr);
        lp.loginapplication("pooja.shetty@gmail.com","Qwerty@2021");
        String errormessage =lp.errormessage();
        System.out.println("Error Message is :"+errormessage);
        Assert.assertEquals(errormessage, "Incorrects email or password.");

	}
   
	
}
