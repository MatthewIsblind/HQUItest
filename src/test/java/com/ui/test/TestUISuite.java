package com.ui.test;

import com.ui.model.Form;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

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
        driver.findElement(By.cssSelector("[aria-label=forms")).click();

        var form = new Form(driver);
        form.updateNameField("Matthew");
        form.updateEmailField("Matthew@mail.com");
        form.clickAgree();
        form.clickSubmit();


        Assertions.assertEquals("Thanks for your feedback Matthew", form.getPopupText());

//        driver.findElement(By.className("v-select__selections")).click();

//        driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
//        var QLDoption = driver.findElement(By.id("list-item-156-2"));
//        Actions dropboxAction = new Actions(driver);
//        dropboxAction.moveToElement(QLDoption).click().build().perform();








    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }




}
