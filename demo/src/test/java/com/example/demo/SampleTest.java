package com.example.demo;

import io.qameta.allure.*;
import org.testng.annotations.Test;

//mvn clean test
//allure serve target/allure-results
public class SampleTest {

    @Test
    @Description("This is a sample test case")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login Feature")
    @Story("Valid Login")
    public void testValidLogin() {
// Test logic here
        System.out.println("Executing valid login test");
    }


    @Test
    @Description("This is a sample test case")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("Login Feature")
    @Story("Valid Login")
    public void testValidLogin2() {
// Test logic here
        System.out.println("Executing valid login test");
    }
}