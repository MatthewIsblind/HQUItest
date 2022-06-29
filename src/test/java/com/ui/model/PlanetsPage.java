package com.ui.model;

import com.ui.strategies.MatchingStrategy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PlanetsPage {

    private final WebDriver driver;
    private List<WebElement> planetsList;

    public PlanetsPage(WebDriver driver) {
        this.driver = driver;


    }


    public void clickExplore(MatchingStrategy strategy){
        for (Planet planet : getPlanets()) {
            if(strategy.match(planet)) {
                planet.clickExplore();
                waitForPopupMessage();
                break;
            }

        }
    }

    private void waitForPopupMessage() {
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(By.className("popup-message")));

    }


    public ArrayList<Planet> getPlanets(){
        ArrayList<Planet> planetList = new ArrayList<Planet>();

        for (WebElement planetWebElement : driver.findElements(By.className(("planet")))){
            Planet webElementPlanet = new Planet(planetWebElement);
            planetList.add(webElementPlanet);
        }

        return planetList;
    }



    public String getPopUp() {
        var messageIdentifier = By.className("popup-message");
        var popup = driver.findElement(messageIdentifier);
        new WebDriverWait(driver, 10).until(ExpectedConditions.visibilityOfElementLocated(messageIdentifier));
        return popup.getText();
    }



}
