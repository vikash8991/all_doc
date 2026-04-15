package base;

import driver.DriverFactory;

public class BaseTest {

    @BeforeMethod
    public void setUp() {
        DriverFactory.getDriver();
    }

    @AfterMethod
    public void tearDown() {
        DriverFactory.quitDriver();
    }
}
