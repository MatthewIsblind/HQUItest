package com.ui.test;

import com.ui.model.ModernForm;
import com.ui.model.PlanetsPage;
import com.ui.model.TraditionalForm;
import com.ui.strategies.NameMatchingStrategy;
import com.ui.strategies.RadiusMatchingStrategy;
import com.ui.strategies.SunDistanceMatchingStrategy;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;


public class TestUISuite {

    private WebDriver driver;

    @BeforeEach
    public void Setup(){
        driver = new ChromeDriver();

        driver.get("https://d18u5zoaatmpxx.cloudfront.net/#/");
        driver.manage().window().maximize();
    }

    @Test
    public void ForenameInputSubmit(){

        driver.findElement(By.id("forename")).sendKeys("Matthew");
        driver.findElement(By.id("submit")).click();
        var popup = driver.findElement(By.className("popup-message"));

        new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
        Assertions.assertEquals("Hello Matthew", popup.getText());

    }

    @Test
    public void DetectButtonMovement(){

        Actions action = new Actions(driver);

        var ele = driver.findElement(By.cssSelector("#app > div.v-application--wrap > main > div > div > div:nth-child(3) > div:nth-child(2) > div:nth-child(3) > a"));
        var initial_location = ele.getLocation();
        ele.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(ele,"CLICK ME UP!"));
        var second_location = ele.getLocation();

        assertNotEquals(initial_location,second_location);

    }

    @Test
    public void VerifySubmitPopup(){
        driver.findElement(By.cssSelector("[aria-label=forms]")).click();

        var form = new ModernForm(driver);
        form.setNameField("Matthew");
        form.setEmailField("Matthew@mail.com");
        form.selectState("SA");
        form.clickAgree();

        form.clickSubmit();
        Assertions.assertEquals("Thanks for your feedback Matthew", form.getFormPopupText());


    }



    public void VerifyTraditionalSumbit(){
        driver.findElement(By.cssSelector("[aria-label=forms]")).click();

        TraditionalForm form = new TraditionalForm(driver);

        form.clickTraditional();


        driver.findElement(By.id("address")).sendKeys("20 netflix road,net,1234");
        driver.findElement(By.id("gender")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.elementToBeSelected(By.cssSelector("[value=M]")));
        driver.findElement(By.cssSelector("value=M")).click();
        driver.findElement(By.id("allow")).click();


    }


    @Test
    public void VerifyExplorePlanetEarth(){
        driver.findElement(By.cssSelector("[aria-label=planets]")).click();

        var planetsPage = new PlanetsPage(driver);
        planetsPage.clickExplore(new NameMatchingStrategy("Earth"));

        // planetsPage.clickPlanetButtonByName("Earth");
        
        Assertions.assertEquals("Exploring Earth",planetsPage.getPopUp());
    }


    @Test
    public void VerifyExploreSaturnRadius() {
        //arrange
        driver.findElement(By.cssSelector("[aria-label=planets]")).click();

        //act
        var planetsPage = new PlanetsPage(driver);
        planetsPage.clickExplore(new RadiusMatchingStrategy(58232));

        //assert
        Assertions.assertEquals("Exploring Saturn",planetsPage.getPopUp());
    }


    @Test
    public void VerifyExploreEarthDistanceFromSun() {

        //arrange
        driver.findElement(By.cssSelector("[aria-label=planets]")).click();

        //act
        var planetsPage = new PlanetsPage(driver);
        planetsPage.clickExplore(new SunDistanceMatchingStrategy(778500000L));

        //assert
        Assertions.assertEquals("Exploring Jupiter",planetsPage.getPopUp());

    }

    @Test
    public void ExplorePlanetWithLongestDistanceFromSun() {

        //arrange
        driver.findElement(By.cssSelector("[aria-label=planets]")).click();

        //act
        var planetsPage = new PlanetsPage(driver);
        planetsPage.clickLongestDistanceFromSun();

        //assert
        Assertions.assertEquals("Exploring Uranus",planetsPage.getPopUp());

    }


    @AfterEach
    public void tearDown() {
        driver.quit();
    }






}
