package com.seleniumScript;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GmailTest2 {

	WebDriver driver=null;
	
	@Parameters({"username","password"})
	@Test
    public void gmailTest(String username,String password) throws InterruptedException {
	  		
			driver.findElement(By.id("identifierId")).sendKeys(username);;
			 
			WebElement next=driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
			next.click();
			
			driver.findElement(By.name("password")).sendKeys(password);
			
			WebElement signin=driver.findElement(By.xpath("//span[contains(text(),'Next')]"));
			signin.click();
			
			new WebDriverWait(driver,30).until(ExpectedConditions.titleContains("Inbox"));
			
			//getting subject of unread mail
			WebElement sub=driver.findElement(By.xpath("//div[@class='xT']/div/span"));
			
			String s=sub.getText();
			System.out.println("subject of 1st unread mail is:"+s);
			
			//click on the compose link..
			driver.findElement(By.xpath("//div[contains(text(),'Compose')]")).click();
			Thread.sleep(1000);
			
			//find the mail id editbox
			WebElement e=driver.findElement(By.name("to"));
			Thread.sleep(3000);
			
			//type the mail id to send
			e.sendKeys("sunilpro@gmail.com");
			Actions act=new Actions(driver);
			act.moveToElement(e).sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();
			
			//enter the subject in subject edit text 
			driver.findElement(By.className("aoT")).sendKeys("Sample Test Mail");
			
			act.sendKeys(Keys.TAB).sendKeys(Keys.TAB).perform();
			
			act.moveToElement(driver.findElement(By.xpath("//div[text()='Send']"))).click().perform();
			Thread.sleep(3000);
			
			new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[contains(text(),'sent')]")));
			
			//find signout
			driver.findElement(By.xpath("//span[contains(@class,'gbii')]")).click();
			
			new WebDriverWait(driver, 5).until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Sign out")));
			
			driver.findElement(By.partialLinkText("Sign out")).click();
			Thread.sleep(3000);
  }
  
  @BeforeTest
  public void openUrl() {
	  System.setProperty("webdriver.chrome.driver", "E:\\testing\\chromedriver.exe");
	  driver=new ChromeDriver();
	  driver.manage().window().maximize();
	  driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
	  driver.get("http://gmail.com");
  }

  @AfterTest
  public void closeUrl() {
	  driver.quit();
  }

}
