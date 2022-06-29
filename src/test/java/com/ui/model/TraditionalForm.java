package com.ui.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class TraditionalForm extends Form {

    private WebDriver driver;

    public TraditionalForm(WebDriver driver) {

        super(driver);
        this.driver = driver;
    }


    public void clickTraditional() {
        for (WebElement tabs : driver.findElements(By.cssSelector("[role=tab]"))){
            System.out.println(tabs.getText());
            if (tabs.getText().equalsIgnoreCase("Traditional")){
                tabs.click();
            }
        }
    }
}
