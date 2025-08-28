package ir.mohaymen.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DiffrenceData {
    @DataProvider(name = "apiData")
    public Object[][] createData() {
        return new Object[][]{
                {"data1"},
                {"data2"},
                {"data3"}
        };
    }

    @Test(dataProvider = "apiData")
    public void testApiWithDifferentData(String data) {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++ strat DiffrenceData testApiWithDifferentData");
        System.out.println("Running test with " + data);
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++ end DiffrenceData testApiWithDifferentData");
        // تست با داده‌های مختلف
        //در اینجا، تست با داده‌های مختلف به صورت موازی اجرا می‌شود.
    }


    //TODO paraller
    //شما می‌توانید پیکربندی موازی بودن را در سطح متد، کلاس یا تست‌ها انجام دهید.
    //هنگام اجرای تست‌ها به صورت موازی، اگر تست‌ها به منابع مشترک دسترسی دارند (مثلاً دیتابیس، فایل‌ها و غیره)، مطمئن شوید که از synchronization مناسب استفاده کنید تا با مشکلات همزمانی مواجه نشوید.
    //پیکربندی موازی بودن در کلاس‌های Java (با استفاده از @Test و @Before یا @BeforeClass)
    //
    //اگر می‌خواهید از Java برای پیکربندی موازی بودن استفاده کنید، می‌توانید از @Test و @BeforeMethod برای اطمینان از اینکه تست‌ها به طور موازی اجرا می‌شوند استفاده کنید.


    @BeforeMethod
    public void setup() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++ DiffrenceData setup");
        // این متد قبل از هر تست اجرا می‌شود
    }

    @Test
    public void testApi1() {
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++ start testApi1");
        System.out.println("++++++++++++++++++++++++++++++++++++++++++++ end testApi1");
        // تست API 1
    }
}
