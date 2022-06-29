package com.ui.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Planets {

    private final WebDriver driver;

    public Planets(WebDriver driver) {
        this.driver = driver;

    }

    public void clickPlanetButton(String planetName) {
        for (WebElement planet: driver.findElements(By.className(("planet")))){
            var planetTitle = planet.findElement(By.className("headline"));

            if (planetTitle.getText().equalsIgnoreCase(planetName)) {
                planet.findElement(By.tagName("button")).click();
                new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));
                break;
            }
        }
    }

    public String getEarthPopUp() {
        var messageIdentifier = By.className("popup-message");
        var popup = driver.findElement(messageIdentifier);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(messageIdentifier));
        return popup.getText();

    }


}
