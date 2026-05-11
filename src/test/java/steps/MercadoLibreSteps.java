package steps;

import hooks.Hooks;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.SearchResultsPage;

public class MercadoLibreSteps {

    HomePage homePage;
    SearchResultsPage resultsPage;

    String searchProduct;

    private Scenario scenario;

    @Before
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }

    @Given("The user navigates to Mercado Libre")
    public void the_user_navigates_to_mercado_libre() {

        homePage = new HomePage();
        resultsPage = new SearchResultsPage();

        homePage.goToMercadoLibre();
    }

    @And("The user selects Mexico country")
    public void the_user_selects_mexico_country() {
        homePage.selectMexico();
    }

    @When("The user searches for {string}")
    public void the_user_searches_for(String product) {

        searchProduct = product;
        homePage.searchProduct(product);
    }

    @And("The user filters by condition {string}")
    public void the_user_filters_by_condition(String condition) {
        resultsPage.filterByCondition(condition);
    }

    @And("The user sorts results by lowest price")
    public void the_user_sorts_results_by_lowest_price() {

        resultsPage.sortByLowestPrice();

        try {
            byte[] screenshot = Hooks.page.screenshot(
                new com.microsoft.playwright.Page.ScreenshotOptions()
                    .setFullPage(true)
            );

            scenario.attach(screenshot, "image/png", "After Sort Screenshot");

            System.out.println("📸 Screenshot agregado al reporte");

        } catch (Exception e) {
            System.out.println("⚠ Error screenshot: " + e.getMessage());
        }
    }

    @Then("The user prints the first {int} product names and prices")
    public void the_user_prints_the_first_product_names_and_prices(int quantity) {

        resultsPage.printFirstProducts(quantity, searchProduct);
    }
}