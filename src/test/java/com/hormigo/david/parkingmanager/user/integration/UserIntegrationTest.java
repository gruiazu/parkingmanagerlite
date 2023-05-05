package com.hormigo.david.parkingmanager.user.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import com.hormigo.david.parkingmanager.user.domain.User;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

public class UserIntegrationTest {
    @Value("${local.server.port}")
    private static int port;
    private static ChromeDriver cDriver;
    @BeforeAll
    public static void PWebDriver() {
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote--allow-origins=*");
        cDriver = new ChromeDriver(options);
    }

    @Test
    public static void checkUserList() {
        List<User> users = newArrayList<>();
        users.add(new User("prueba@prueba.com","prueba","prueba","prueba"));
        when(userService.getAll().thenReturn(users));
        String url = "http://localhost" + port + "/users";
        cDriver.get(url);
        String Title = cDriver.getTitle();
        WebElement createButton = cDriver.findElement(By.id("users-button-create"));
        WebElement actualHeading = cDriver.findElement(By.id("users-button-create"));
        WebElement table = cDriver.findElement(By.className("users-button-create"));
        String actualHeadingText = actualHeading.getText();

        
        assertALL("Prueba que la página se muestra",
        () -> {assertNotNull(createButton);},
        () -> {assertEquals("Usuarios",Title);},
        () -> {assertEquals("Usuarios",actualHeadingText);},
        () -> {assertNotNul(table);}
        )
        

        cDriver.quit();
    }
    
}
