package com.ui.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public abstract class Form {

    private WebDriver driver;

    public Form(WebDriver driver){
        this.driver = driver;
    }

    public void clickSpecificButton(By byLocator, String buttonBodyText) {
        for (WebElement tabs : driver.findElements(byLocator)){

            if (tabs.getText().equalsIgnoreCase(buttonBodyText)){
                tabs.click();
            }
        }
    }
}
