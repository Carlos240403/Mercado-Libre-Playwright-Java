package hooks;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    public static Playwright playwright;
    public static Browser browser;
    public static Page page;

    @Before
    public void setUp() {

        playwright = Playwright.create();

        boolean headless = Boolean.parseBoolean(
                System.getProperty("headless", "true")
        );

        browser = playwright.firefox().launch(
                new BrowserType.LaunchOptions()
                        .setHeadless(headless)
                        .setSlowMo(1000)
        );

        page = browser.newPage(
            new Browser.NewPageOptions()
                .setViewportSize(1920, 1080)
        );
    } // ✅ ← ESTE TE FALTABA

    // ✅ MÉTODO DE SCREENSHOT (ya fuera del setUp)
    public static void takeScreenshot(String name) {
        try {
            page.screenshot(
                new Page.ScreenshotOptions()
                    .setPath(java.nio.file.Paths.get("target/screenshots/" + name + ".png"))
                    .setFullPage(true)
            );
            System.out.println("📸 Screenshot guardado: " + name);
        } catch (Exception e) {
            System.out.println("⚠ Error screenshot: " + e.getMessage());
        }
    }

    @After
    public void tearDown(Scenario scenario) {

        if (scenario.isFailed() && page != null) {

            byte[] screenshot = page.screenshot(
                    new Page.ScreenshotOptions()
                            .setFullPage(true)
            );

            scenario.attach(
                    screenshot,
                    "image/png",
                    "Failure Screenshot"
            );

            System.out.println("📸 Screenshot capturado");
        }

        if (page != null) page.close();
        if (browser != null) browser.close();
        if (playwright != null) playwright.close();
    }
}