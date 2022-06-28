package com.ui.test;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestUISuite {
    @Test
    public void Forename_input_submit(){
        var driver = new ChromeDriver();

        driver.get("https://d18u5zoaatmpxx.cloudfront.net/#/");
        driver.manage().window().maximize();
        driver.findElement(By.id("forename")).sendKeys("Matthew Liu");
        driver.findElement(By.id("submit")).click();
        driver.quit();
    }
}
