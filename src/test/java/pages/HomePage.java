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

        Locator searchBox = page.locator("#cb1-edit");

        searchBox.waitFor();
        searchBox.click(); // ✅ asegura foco
        searchBox.fill(product);
        searchBox.press("Enter");

        page.waitForLoadState();
    }

}