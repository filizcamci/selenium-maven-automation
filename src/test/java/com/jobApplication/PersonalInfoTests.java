package com.jobApplication;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;
import static org.testng.Assert.*;

public class PersonalInfoTests {
	WebDriver driver;
	String firstName;
	String lastName;
	int gender;
	String dob;
	String email;
	String phoneNumber;
	String city;
	String state;
	String country;
	int salary;
	List<String> technologies;
	int experience;
	String education;
	String github;
	List<String> certifications;
	String additional;
	Faker faker = new Faker();
	Random rd = new Random();

	@BeforeClass
	public void setUp() {
		System.out.println("setting up WebDriver before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@BeforeMethod // runs before each test
	public void navigateToHomepage() {
		System.out.println("navigating to homepage");
		driver.get(
				"https://forms.zohopublic.com/murodil/form/JobApplicationForm/formperma/kOqgtfkv1dMJ4Df6k4_mekBNfNLIconAHvfdIk3CJSQ");
		firstName = faker.name().firstName();
		lastName = faker.name().lastName();
		// gender = rd.nextInt(2) + 1;// min 1 max 2
		gender = faker.number().numberBetween(1, 3);
		dob = faker.date().birthday().toString();
		email = "filizcamci@gmail.com";
		phoneNumber = faker.phoneNumber().cellPhone().replace(".", "");
		city = faker.address().city();
		state = faker.address().state();
		country = faker.address().country();
		// salary = rd.nextInt(150000) + 50000;// min 50000 max 150000
		salary = faker.number().numberBetween(50000, 150000);
		technologies = new ArrayList();
		// technologies.add("Java-Expert");
		// technologies.add("Html-Proficient");
		// technologies.add("Selenium WebDriver-Beginner");
		// technologies.add("TestNG-Expert");
		// technologies.add("Maven-Beginner");
		technologies.add("Java-" + faker.number().numberBetween(1, 4));
		technologies.add("HTML-" + faker.number().numberBetween(1, 4));
		technologies.add("Selenium WebDriver-" + faker.number().numberBetween(1, 4));
		technologies.add("Maven-" + faker.number().numberBetween(1, 4));
		technologies.add("Git-" + faker.number().numberBetween(1, 4));
		technologies.add("TestNG-" + faker.number().numberBetween(1, 4));
		technologies.add("JUnit-" + faker.number().numberBetween(1, 4));
		technologies.add("Cucumber-" + faker.number().numberBetween(1, 4));
		technologies.add("API Automation-" + faker.number().numberBetween(1, 4));
		technologies.add("JDBC-" + faker.number().numberBetween(1, 4));
		technologies.add("SQL-" + faker.number().numberBetween(1, 4));
		experience = faker.number().numberBetween(0, 11);
		education = faker.number().numberBetween(1, 4) + "";
		github = "https://github.com/CybertekSchool/selenium-maven-automation.git";
		certifications = new ArrayList();
		certifications.add("Java OCA");
		certifications.add("AWS");
		additional = faker.job().keySkills();

	}

//	@Test
//	public void fullNameEmptyTest() {
//		// first assert that you are on the correct page
//		assertEquals(driver.getTitle(), "SDET Job Application");
//		// driver.findElement(By.name("Name_First")).clear();
//		// <input type="text" maxlength="255" elname="first" name="Name_First" value=""
//		// invlovedinsalesiq="false">
//		driver.findElement(By.xpath("//input[@name='Name_First']")).clear();
//		;
//		driver.findElement(By.xpath("//input[@name='Name_Last']")).clear();
//		// <em> Next </em>
//		driver.findElement(By.xpath("//em[.=' Next ']")).click();
//		// <p class="errorMessage" elname="error" id="error-Name" style="display:
//		// block;">Enter a value for this field.</p>
//		// write xpath with tagname+id
//		// get the text and assert equal
//		String errorMessage = driver.findElement(By.xpath("//p[@id='error-Name']")).getText();
//		assertEquals(errorMessage, "Enter a value for this field.");
//	}

	@Test(priority=1)
	public void submitFullApplication() throws InterruptedException {
		// driver.findElement(By.name("Name_First")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name='Name_First']")).sendKeys(firstName);
		driver.findElement(By.name("Name_Last")).sendKeys(lastName);
		setGender(gender);
		setdob(dob);
		driver.findElement(By.xpath("//input[@name='Email']")).clear();
		driver.findElement(By.xpath("//input[@name='Email']")).sendKeys(email);
		driver.findElement(By.xpath("//input[@name='countrycode']")).sendKeys(phoneNumber);
		driver.findElement(By.xpath("//input[@name='Address_City']")).sendKeys(city);
		driver.findElement(By.xpath("//input[@name='Address_Region']")).sendKeys(state);
		Select countryselect = new Select(driver.findElement(By.xpath("//select[@id='Address_Country']")));
		countryselect.selectByIndex(faker.number().numberBetween(1, countryselect.getOptions().size()));// to select
																										// random
																										// country
		// driver.findElement(By.xpath("//input[@name='Number']")).sendKeys(""+salary);
		driver.findElement(By.xpath("//input[@name='Number']")).sendKeys(String.valueOf(salary) + Keys.TAB);
		verifySalaryCalculations(salary);
		//driver.findElement(By.xpath("//div[@class=' formRelative inlineBlock submitWrapper ']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id=\"formAccess\"]/div[1]/div/div/button")).click();
		
		// SECOND PAGE ACTIONS
		setSkillset(technologies);

		if (experience > 0) {
			driver.findElement(By.xpath("//a[@rating_value='" + experience + "']")).click();
		}
		Select educationList = new Select(driver.findElement(By.xpath("//select[@name='Dropdown']")));
		educationList.selectByIndex(faker.number().numberBetween(1, educationList.getOptions().size()));
		checkCertifications(certifications);
		driver.findElement(By.xpath("//textarea[@name='MultiLine']")).clear();
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("window.scrollBy(0,250)", "");
		// jse.executeScript("scroll(0, 250);");
		//click apply button:
		driver.findElement(By.xpath("//*[@id=\"formAccess\"]/div[1]/div[2]/div/button/em")).click();
		
		//verify everything is correct:
		
		String applicationId=driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[8]")).getText();
		String id=applicationId.substring(16);
		System.out.println("id: "+id);
		
		String ipa=driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[6]")).getText();
		String ip=ipa.substring(12);
		System.out.println("ip address: "+ip);
		
		String full=driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[1]")).getText();
		int index=full.indexOf(" ");
		String fullName=full.substring(index+1,full.length()-1);
		System.out.println("fullname:"+fullName);
		
		String dateOfB=driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[10]")).getText();
		String dateOfBirth=dateOfB.substring(15);
		System.out.println("DOB: "+dateOfBirth);
		
		String elmail=driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[11]")).getText();
		String electronicMail=elmail.substring(7);
		System.out.println("email: "+electronicMail);
		
		
		String telephone=driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[12]")).getText();
		String TelphoneNumber=telephone.substring(7);
		System.out.println("phonenumber: "+TelphoneNumber);
		
		String addressapp=driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[13]")).getText();
		String applicantAddress=addressapp.substring(9);
		System.out.println("address: "+applicantAddress);
		
		String appSalary=driver.findElement(By.xpath("//*[@id=\"richTxtMsgSpan\"]/label/div[14]")).getText();
		String currentSalary=appSalary.substring(15);
		System.out.println("current salary: $"+currentSalary);
		
		//WebDriver driver2=new ChromeDriver();
		driver.get("https://www.google.com/");
		driver.findElement(By.id("lst-ib")).sendKeys("what is my ip"+Keys.ENTER);
		String ipg=driver.findElement(By.xpath("//div[@class='pIpgAc xyYs1c XO51F xsLG9d']")).getText();
		
		assertEquals(ipg,ip);
		
		//driver2.close();
		
		
		//WebDriver driver1=new ChromeDriver();
		driver.get("https://mail.google.com/mail/u/0/#inbox");
		driver.findElement(By.xpath("//a[.='Sign In']")).click();;
		driver.findElement(By.id("identifierId")).sendKeys("filizcamci@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("FDAR0306c"+Keys.ENTER);
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@class='zA zE btb']")).click();
		
		String appId=driver.findElement(By.xpath("//*[@class='ii gt']/div/table/tbody/tr[2]/td[3]")).getText();
		assertEquals(appId,id);
		
		String nameEmail=driver.findElement(By.xpath("//*[@class='ii gt']/div/table/tbody/tr[3]/td[3]")).getText();
		String ename=nameEmail.replaceAll(",", " ");
		assertEquals(ename,fullName);
		
		String edateofBirth=driver.findElement(By.xpath("//*[@class='ii gt']/div/table/tbody/tr[5]/td[3]")).getText();
		assertEquals(dateOfBirth,edateofBirth);
		
		String eAddress=driver.findElement(By.xpath("//*[@class='ii gt']/div/table/tbody/tr[6]/td[3]")).getText();
		assertEquals(electronicMail,eAddress);
		
		String pnumber=driver.findElement(By.xpath("//*[@class='ii gt']/div/table/tbody/tr[7]/td[3]")).getText();
		assertEquals(TelphoneNumber,pnumber);
		
		String mailAddress=driver.findElement(By.xpath("//*[@class='ii gt']/div/table/tbody/tr[8]/td[3]")).getText();
		String[] mailadrs=mailAddress.split(",");
		String madrs="";
		for(int i=0; i<mailadrs.length; i++) {
			if(i==mailadrs.length-1)
				madrs+=mailadrs[i];
			else
				madrs+=mailadrs[i]+","+" ";
		}
		assertEquals(applicantAddress,madrs);
		
		String cSalary=driver.findElement(By.xpath("//*[@class='ii gt']/div/table/tbody/tr[9]/td[3]")).getText();
		assertEquals(currentSalary,cSalary);
//		

	}

	public void setSkillset(List<String> tech) throws InterruptedException {

		for (String skill : tech) {
			String technology = skill.substring(0, skill.length() - 2);
			int rate = Integer.parseInt(skill.substring(skill.length() - 1));

			String level = "";

			switch (rate) {
			case 1:
				level = "Expert";
				break;
			case 2:
				level = "Proficient";
				break;
			case 3:
				level = "Beginner";
				break;
			default:
				fail(rate + " is not a valid level");
			}

			String xpath = "//input[@rowvalue='" + technology + "' and @columnvalue='" + level + "']";
			Thread.sleep(1000);
			driver.findElement(By.xpath(xpath)).click();

		}
	}

	//@Test
	public void verifySalaryCalculations(int salary) {
		String monthly = driver.findElement(By.xpath("//input[@name='Formula']")).getAttribute("value");
		String weekly = driver.findElement(By.xpath("//input[@name='Formula1']")).getAttribute("value");
		String hourly = driver.findElement(By.xpath("//input[@name='Formula2']")).getAttribute("value");
		DecimalFormat formatter = new DecimalFormat("#.##");

		assertEquals(Double.parseDouble(monthly), Double.parseDouble(formatter.format((double) salary / 12.0)));
		assertEquals(Double.parseDouble(weekly), Double.parseDouble(formatter.format((double) salary / 52.0)));
		assertEquals(Double.parseDouble(hourly), Double.parseDouble(formatter.format((double) salary / 52.0 / 40.0)));
		// assertEquals(Double.parseDouble(monthly), (double)salary/(double)12);
		// assertEquals(Double.parseDouble(weekly), (double)salary/(double)52);
		// assertEquals(Double.parseDouble(hourly), (double)salary/(double)52/40.0);
	}

	public void setGender(int n) {
		if (n == 1) {
			driver.findElement(By.xpath("//input[@value='Male']")).click();
		} else {
			driver.findElement(By.xpath("//input[@value='Female']")).click();
		}

	}

	public void setdob(String dob) {
		String[] str = dob.split(" ");
		String DOB = str[2] + "-" + str[1] + "-" + str[str.length - 1];
		driver.findElement(By.xpath("//input[@id='Date-date']")).sendKeys(DOB);

	}

	public void checkCertifications(List<String> certifications) {
		for (int i = 0; i < certifications.size(); i++) {
			String xpath = "//input[@id='Checkbox_" + (i + 1) + "']";
			driver.findElement(By.xpath(xpath)).click();
		}
	}
	
	
		

		
	
//	 @AfterClass
//	 public void tearDown() throws InterruptedException {
//	 Thread.sleep(2000);
//	 driver.close();
//	 }
}
