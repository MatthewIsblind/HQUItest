package com.ui.model;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class Planet {


    private String name;
    private WebElement webelement;

    public Planet(WebElement planetWebElement){
        this.webelement = planetWebElement;

    }

    public String getName(){
        return this.webelement.findElement(By.className("headline")).getText();
    }


    public void clickExplore() {
        this.webelement.findElement(By.tagName("button")).click();
    }

    public Double getPlanetradius() {
        String raidusText = this.webelement.findElement(By.className("radius")).getText();

        raidusText = raidusText.replace(" km","");
        raidusText = raidusText.replace(",","");

        //NumberFormat.getInstance().parse(raidusText).doubleValue();
        return Double.parseDouble(raidusText);
    }

    public long getDistanceFromSun() {
        String raidusText = this.webelement.findElement(By.className("distance")).getText();

        raidusText = raidusText.replace(" km","");
        raidusText = raidusText.replace(",","");


        return Long.parseLong(raidusText);
    }
}
