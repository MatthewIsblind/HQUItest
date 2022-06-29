package com.ui.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Form {

    private final WebDriver driver;

    public Form(WebDriver driver) {
        this.driver = driver;
    }


    public void setNameField(String name) {
        driver.findElement(By.id("name")).sendKeys(name);
    }

    public void setEmailField(String email) {
        driver.findElement(By.id("email")).sendKeys(email);
    }

    public void clickAgree() {
        var checkbox = driver.findElement(By.id("agree"));
        Actions action = new Actions(driver);
        action.moveToElement(checkbox).click().build().perform();
    }

    public void clickSubmit() {
        for (WebElement currentElement: driver.findElements(By.tagName("button"))) {
            if (currentElement.getText().equalsIgnoreCase("submit")) {
                currentElement.click();
                break;
            }
        }

    }


    public String getFormPopupText() {
        var popup = driver.findElement(By.className("popup-message"));
        new WebDriverWait(driver, 20).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
        return popup.getText();
    }

    public void selectState(String state) {
        driver.findElement(By.className("v-select__selections")).click();
        new WebDriverWait(driver, 5).until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[role=option]")));
        for (WebElement givenState:driver.findElements(By.cssSelector("[role=option]"))) {
            if (givenState.getText().equalsIgnoreCase(state)) {
                givenState.click();
                break;
            }
        }


    }


}
