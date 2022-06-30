package com.ui.model;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class HomePage {

    private final WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void setProductQuantity(int quantity, String productName) {
        List<WebElement> tableCells = driver.findElements(By.tagName("td"));
        for(int i = 0; i < tableCells.size() ; i++) {
            WebElement cell = tableCells.get(i);
            if (cell.getText().equalsIgnoreCase(productName)) {
                System.out.println(cell.getText());
                tableCells.get(i-1).findElement(By.className("qty")).sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
                tableCells.get(i-1).findElement(By.className("qty")).sendKeys(String.valueOf(quantity));
                break;
            }
        }


    }

    public double getProductSubtotal(String productName) {

        List<WebElement> tableCells = driver.findElements(By.tagName("td"));
        for(int i = 0; i < tableCells.size() ; i++) {
            WebElement cell = tableCells.get(i);
            if (cell.getText().equalsIgnoreCase(productName)) {
                System.out.println(cell.getText());
                System.out.println(tableCells.get(i + 2).getText());
                return Double.parseDouble(tableCells.get(i + 2).getText());

            }
        }

        throw new NoSuchElementException("no such product");
    }
}
