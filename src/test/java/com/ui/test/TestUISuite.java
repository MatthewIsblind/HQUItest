package com.ui.test;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
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
    public void Forename_input_submit(){

        driver.findElement(By.id("forename")).sendKeys("Matthew");
        driver.findElement(By.id("submit")).click();
        var popup = driver.findElement(By.className("popup-message"));

        new WebDriverWait(driver, 1).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
        Assertions.assertEquals("Hello Matthew", popup.getText());

    }

    @Test
    public void Detect_button_movement(){

        Actions action = new Actions(driver);
        
        var ele = driver.findElement(By.cssSelector("#app > div.v-application--wrap > main > div > div > div:nth-child(3) > div:nth-child(2) > div:nth-child(3) > a"));
        var initial_location = ele.getLocation();
        ele.click();

        new WebDriverWait(driver, 10).until(ExpectedConditions.textToBePresentInElement(ele,"CLICK ME UP!"));
        var second_location = ele.getLocation();

        assertNotEquals(initial_location,second_location);

    }

    @Test
    public void Formpage_popup(){
        driver.findElement(By.cssSelector("[aria-label=forms")).click();
        driver.findElement(By.id("name")).sendKeys("Matthew");
        driver.findElement(By.id("email")).sendKeys("Matthew@mail.com");

        var checkbox = driver.findElement(By.id("agree"));
        Actions action = new Actions(driver);
        action.moveToElement(checkbox).click().build().perform();

//        var QLDoption = driver.findElement(By.id("list-item-156-2"));
//        driver.findElement(By.className("v-select__selections")).click();
//        Actions dropboxAction = new Actions(driver);
//        dropboxAction.moveToElement(QLDoption).click().build().perform();




        for (WebElement currentElement: driver.findElements(By.tagName("button"))) {
            if (currentElement.getText().equalsIgnoreCase("submit")) {
                currentElement.click();
                break;
            }
        }

        var popup = driver.findElement(By.className("popup-message"));

        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
        Assertions.assertEquals("Thanks for your feedback Matthew", popup.getText());

    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }




}
