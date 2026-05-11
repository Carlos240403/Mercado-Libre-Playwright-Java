package pages;

import com.microsoft.playwright.Locator;

public class HomePage extends BasePage {

    public void goToMercadoLibre() {

        navigate("https://www.mercadolibre.com/");
    }

    public void selectMexico() {

        page.getByText("México").click();
    }

    public void searchProduct(String product) {

        page.waitForLoadState();

        Locator searchBox = page.locator("//*[@id=\"cb1-edit\"]");

        searchBox.waitFor();
        searchBox.fill(product);
        searchBox.press("Enter");
    }
}