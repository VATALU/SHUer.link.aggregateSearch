package test.org.shuerlink.crawler;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class SeleniumTest {
    @Test
    public void testSelenium() throws IOException {
        DesiredCapabilities dcaps = new DesiredCapabilities();
        dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY,"D:\\driver\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
        PhantomJSDriver driver = new PhantomJSDriver(dcaps);
        driver.manage().window().maximize();
        try {
            driver.get("http://image.baidu.com/search/index?tn=baiduimage&ps=1&ct=201326592&lm=-1&cl=2&nc=1&ie=utf-8&word=idea");
            List<WebElement> elements = driver.findElements(By.cssSelector(".imgitem"));
            for(WebElement element : elements){
                String objurl = element.getAttribute("data-objurl");
                System.out.println(objurl);
            }
            System.out.println(elements.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
        driver.quit();
    }

}
