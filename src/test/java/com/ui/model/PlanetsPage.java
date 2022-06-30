package com.ui.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class PlanetsPage {

    private final WebDriver driver;
    private List<WebElement> planetsList;

    public PlanetsPage(WebDriver driver) {
        this.driver = driver;


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
        new WebDriverWait(driver , 5).until(driver1 -> driver1.findElement(By.className("popup-message")).isDisplayed());
        return popup.getText();
    }


    public void clickLongestDistanceFromSun() {
        long distance = 0L;
        Planet furthestPlanet = null;
        for (Planet planet : getPlanets()) {
            if (planet.getDistanceFromSun() > distance){
                distance = planet.getDistanceFromSun();
                furthestPlanet = planet;
            }

        }

        furthestPlanet.clickExplore();


    }

    public Planet exploreWithLambda(Predicate<Planet> testLogic) {
        for (WebElement planetElement : driver.findElements(By.className("planet"))) {
            var planet = new Planet(planetElement);
            if (testLogic.test(planet)){
                return planet;
            }

        }

        throw new NoSuchElementException("no such planet");
    }
}
