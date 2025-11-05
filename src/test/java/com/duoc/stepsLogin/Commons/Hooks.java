package com.duoc.stepsLogin.Commons;

import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;

public class Hooks {

    @BeforeAll
    public static void setUpAll() {
        DriverManager.initDriver();
    }

    @AfterAll
    public static void tearDownAll() {
        DriverManager.quitDriver();
    }
}


