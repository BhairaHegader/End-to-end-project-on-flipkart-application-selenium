package flipkart1;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkartapplication 
{
	WebDriver driver;

	
	//set up off browser
	@BeforeClass
	@Parameters({"browser","URl"})
	public void setup(String br,String appURl)
	{
		
		if(br.equals("chrome"))
		{
		  WebDriverManager.chromedriver().setup();
		  driver=new ChromeDriver();
		}
		else if(br.equals("Microsoftedge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();		
		}
		else {
			System.out.println("no browser to choose");
		}
		driver.get(appURl);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
	}
	
	
	//locating Flipkart image
	@Test(priority=1)
	public void homepage()
	{		
		
		String text=driver.findElement(By.xpath("//img[@title='Flipkart']")).getText();
		System.out.println("the text is"+text);
		
		
		
	}
	
	//verify login button
	@Test(priority=2)
	public void loignbutton()
	{
		driver.findElement(By.xpath("//span[normalize-space()='Login']")).click();
		String Act_titlet=driver.findElement(By.xpath("//span[contains(text(),'Login')]")).getText();
		String Exp_title="Login";
		Assert.assertEquals(Act_titlet, Exp_title,"text not match");
		System.out.println("Actual Title"  +Act_titlet);
		System.out.println("Expected title"  +Exp_title);
		
		
		//Navigate to previous page
		driver.navigate().back();
	}
	
	//verifying cart folder
	@Test(priority=3)
	public void verify_cartfolder()
	{
		
		  boolean status=driver.findElement(By.xpath("//img[@alt='Cart']")).isDisplayed();
		  Assert.assertTrue(status);
		  System.out.println(status);
          
		  //click on cart to view cart folder
		  driver.findElement(By.xpath("//a[@class='_3RX0a-']")).click();
		  String text=driver.findElement(By.xpath("//span[normalize-space()='Flipkart.com']")).getText();
		  System.out.println(text);             
	}
	
	
	//search product on search bar
	@Test(priority=4)
	public void searchproduct() throws InterruptedException
	{
		driver.findElement(By.name("q")).sendKeys("iphone");
		driver.findElement(By.xpath("//button[@class=\"L0Z3Pu\"]")).click();
		
		///filters added
		WebElement dropdown=driver.findElement(By.xpath("//div[@class='_3uDYxP']//select[@class='_2YxCDZ']"));
		Select selectddown=new Select(dropdown);
		selectddown.selectByIndex(4);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='_3879cV'][normalize-space()='Apple']")).click();
		Thread.sleep(4000);
		
		driver.findElement(By.xpath("//div[@title='4★ & above']//div[@class='_24_Dny']")).click();  
		Thread.sleep(3000);
		driver.findElement(By.xpath("//div[contains(text(),'Internal Storage')]")).click();
		driver.findElement(By.xpath("//div[@title='256 GB & Above']//div[@class='_24_Dny']")).click();
	
	
		
		try{
		String exp_txt=driver.findElement(By.xpath("//div[normalize-space()='Apple iPhone 14 (Blue, 256 GB)']")).getText();
		String Act_text="Apple iPhone 14 (Blue, 256 GB)";
		Assert.assertEquals(exp_txt, Act_text,"text not match");
		System.out.println("Expected title"+  exp_txt);
		System.out.println("Actual title"+  Act_text);
		}
		catch(Exception e)
		{
			System.out.println("data not found");
		}
		
	    Thread.sleep(3000);
	    
	    String parenthandle=driver.getWindowHandle();
	    System.out.println("id1"  +parenthandle);
	    driver.findElement(By.xpath("//div[normalize-space()='Apple iPhone 14 (Blue, 256 GB)']")).click();
	    Set<String> handles=driver.getWindowHandles();
	    
	    
	   for(String handle: handles)
	   {
		   if (!handle.equals(parenthandle))
		   {
			   driver.switchTo().window(handle);
			   Thread.sleep(3000);
			   driver.findElement(By.xpath("//button[@class='_2KpZ6l _2U9uOA _3v1-ww']")).click();
			   Thread.sleep(3000);
			   try{
			   String text=driver.findElement(By.xpath("//a[normalize-space()='Apple iPhone 14 (Blue, 256 GB)']")).getText();
			   String exp_text=text;
		       Assert.assertEquals(exp_text, "Apple iPhone 14 (Blue, 256 GB)");
			   System.out.println(exp_text);
			   System.out.println("Apple iPhone 14 (Blue, 256 GB)");
			   }catch(Exception e)
			   {
				   System.out.println("text not found");
			   }
			   driver.close();
			   Thread.sleep(3000);
			  driver.switchTo().window(parenthandle);
		   }
	   }
	   
	}
	

	@Test(priority=5)
	public void verifycart() throws InterruptedException
	{
		driver.findElement(By.xpath("//span[normalize-space()='Cart']")).click();
		Thread.sleep(3000);
		String first_price=driver.findElement(By.cssSelector("._2-ut7f._1WpvJ7")).getText();
		System.out.println("first price--"+first_price);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//input[@value='1']")).sendKeys("2");
		Thread.sleep(4000);
		String secandprice=driver.findElement(By.cssSelector("._2-ut7f._1WpvJ7")).getText();
		System.out.println("secand--price"+secandprice);
	}	
}










