package com.jobApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class JobApplication {
	WebDriver driver;
	Faker faker = new Faker();
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
	
	
	@BeforeClass
	public void setUp() {
		System.out.println("setting up WebDriver before class");
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
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
		phoneNumber = faker.phoneNumber().cellPhone().replaceAll(".", "");
		city = faker.address().city();
		state = faker.address().state();
		country = faker.address().country();
		salary = faker.number().numberBetween(50000, 150000);
		technologies = new ArrayList<>();
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
		certifications = new ArrayList<>();
		certifications.add("Java OCA");
		certifications.add("AWS");
		additional = faker.job().keySkills();

	}
	
}
