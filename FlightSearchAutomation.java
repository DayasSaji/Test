package irctcFlight;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;



import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.Select;




public class FlightSearchAutomation {
 public static WebDriver driver;
 public static void main(String[] args) throws FileNotFoundException, InterruptedException, IOException {
	 @SuppressWarnings("resource")
	 Scanner sc = new Scanner(System.in);
     System.out.println("Enter browser name(chrome/firefox):");
     String browser = sc.nextLine();
     if(browser.equalsIgnoreCase("chrome"))
     {
    	
    	 System.setProperty("webdriver.chrome.driver","E:\\new\\chromedriver_win32\\chromedriver.exe");  
         driver= new ChromeDriver();
         driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
         driver.manage().window().maximize();
     }
    	 else if(browser.equalsIgnoreCase("firefox"))
    	 {
    		 System.setProperty("webdriver.gecko.driver","E:\\new\\geckodriver-v0.30.0-win64\\geckodriver.exe");  
             driver = new FirefoxDriver();
             driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
             driver.manage().window().maximize();
		}
     		driver.get("https://www.air.irctc.co.in/");

	
     		driver.findElement(By.id( "stationFrom")).sendKeys("Hyd");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[text()= 'Hyderabad (HYD)']")).click();
		   
			driver.findElement(By.id("stationTo")).sendKeys("Pune");
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//div[text()= 'Pune (PNQ)']")).click();
		
			
			
			driver.findElement(By.xpath("//input[@id='originDate']"));
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
			
			 WebElement date=driver.findElement(By.xpath("//span[@class='act active-red']"));
			 JavascriptExecutor jse = (JavascriptExecutor) driver;
			 jse.executeScript("arguments[0].click()",date);
			 
			 
			
			 	driver.findElement(By.id("noOfpaxEtc")).click();
			 	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			 	WebElement e =driver.findElement(By.id("travelClass"));
				Select sl = new Select(e) ;
				sl.selectByIndex(1) ;
                driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				driver.findElement(By.xpath("/html/body/app-root/app-index/div[3]/div[2]/div[2]/form/div[6]/button")).click();
				Thread.sleep(10000);
				String dc =driver.findElement(By.xpath("/html/body/app-root/app-oneway/div[1]/main/div/div/div/div[3]/div/div[2]/span")).getText();
				
				System.out.println("Validating the departure city");
				System.out.println(dc);
				
				String ac =driver.findElement(By.xpath("/html/body/app-root/app-oneway/div[1]/main/div/div/div/div[3]/div/div[3]/span")).getText();
				
				System.out.println("Validating the arrival city");
				System.out.println(ac);
		
				String vd=driver.findElement(By.xpath("/html/body/app-root/app-oneway/div[1]/main/div/div/div/div[1]/div/div/div[1]/a/span[1]")).getText();
				System.out.println("Validating the Date");
				System.out.println(vd);
						
						
				// Displaying name and number of flights
						
						
						List<WebElement> flname =driver.findElements(By.xpath("//div[@class='right-searchbarbtm']//b"));
						int flcount = flname.size();
						System.out.println( "Total flights available for today is "+ flcount) ;

						List<WebElement> flnum =driver.findElements(By.xpath("//div/b/following-sibling::span"));
						for(int i = 0; i<flcount; i++)
						{
							
							System.out.println("Flight name " +(i+1) + ":" +flname.get(i).getText());
							System.out.println("Flight number " +(i+1) + ":" +flnum.get(i).getText());
						
						}

				// Capturing screenshot
						TakesScreenshot scrn =(TakesScreenshot)driver;
						File sourceFile =scrn.getScreenshotAs(OutputType.FILE);
						File destinationFile = new File("C:\\Users\\acer\\eclipse-workspace\\FirstSeleniumProject\\src\\irctcFlight\\screenshot.png");
						FileHandler.copy(sourceFile,destinationFile) ;
						
				// Close the driver
						
						driver.close();


			}
 }



