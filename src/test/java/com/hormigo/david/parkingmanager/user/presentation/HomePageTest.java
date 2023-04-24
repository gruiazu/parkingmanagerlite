package com.hormigo.david.parkingmanager.user.presentation;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HomePageTest {
    @Value("${local.server.port}")
    private int port;
    public WebDriver driver;

    @BeforeEach
    public void prepareTests() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.driver = new ChromeDriver(options);
    }

    @Test
    public void checkHomePageIsRendered() {
        this.driver.get("http://localhost:" + port + "/");
        assertAll("Home page is rendered",
                () -> {
                    assertTrue(driver.findElement(By.id("home-title")).isDisplayed());
                },
                () -> {
                    assertTrue(driver.findElement(By.id("users-link")).isDisplayed());
                },
                () -> {
                    assertTrue(driver.findElement(By.id("draws-link")).isDisplayed());
                });
    }

    @Test
    public void navigatesFromHomeToUsers() {
        this.driver.get("http://localhost:" + port + "/");
        driver.findElement(By.id("users-link")).click();
        System.out.println(driver.getCurrentUrl());

    }

}
