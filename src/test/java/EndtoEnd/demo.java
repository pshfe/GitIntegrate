package EndtoEnd;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class demo extends BaseTest

{

	@Test(dataProvider="getdata1",groups={"providingdata"})
	public void Mainordersubmit(String data) throws IOException
	{
       initialize();// beforetest is used so  need to call here.
       LoginPage lp= new LoginPage(dr);
       String[] arr1=data.split(",");
       lp.loginapplication(arr1[0],arr1[1]);
      
  
       Productselection ps= new Productselection(dr);
       String productname= ps.getproductlist(arr1[2]);
       System.out.println("Product added to cart is :"+productname);
       ps.gettext();
       ps.getcartclick();
      
      
       MyCart mc= new MyCart(dr);
       mc.productcheck(arr1[2]);
       mc.getcheckout();
      
       Payment m =new Payment(dr);
       m.selectcountry("ind");
       m.getcountrylist();
       Thankyoupage tp= m.placeorder();    //we can create object of next page in this page-last method.Instead of creating object here.
       String ordermessage=tp.orderplacedmessage();
       System.out.println("Message printed :"+ordermessage);
       Assert.assertEquals(ordermessage,"THANKYOU FOR THE ORDER.");
       
       
	}
	
 @Test(dataProvider="getdata1",dependsOnMethods= {"Mainordersubmit"})
	public void Myorders(String data2) throws IOException
	{
	     initialize();
		LoginPage lp= new LoginPage(dr);
		String [] arr2= data2.split(",");
        lp.loginapplication(arr2[0],arr2[1]);
        OrderDetails od= new OrderDetails(dr);
        Assert.assertTrue(od.myorder(arr2[2]));
	} 
	
	

	@DataProvider
	public String[] getdata1() throws IOException, ParseException
	{
		JSONParser jp= new JSONParser();
		FileReader r= new FileReader(System.getProperty("user.dir")+"\\src\\main\\java\\EndtoEnd\\Testdata2.json");    
		Object o=jp.parse(r);
		JSONObject jo=(JSONObject)o;
		JSONArray a= (JSONArray) jo.get("userlogins");
		String[] arr= new String[a.size()];
		for (int i=0;i<a.size();i++)
		{
			 JSONObject p=  (JSONObject) a.get(i);
			String e= p.get("email").toString();
			String f= p.get("password").toString();
			String g= p.get("Product").toString();
			arr[i]=e+","+f+","+g;
		}
		return arr;
		
	}
	
	
		
}
