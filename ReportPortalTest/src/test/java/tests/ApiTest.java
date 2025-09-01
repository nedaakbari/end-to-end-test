package tests;

import org.testng.annotations.Test;

public class ApiTest {

    @Test
    public void sample() throws InterruptedException {
        Thread.sleep(10000);
        System.out.println("Current thread: sample    " + Thread.currentThread().getName());
    }
}
