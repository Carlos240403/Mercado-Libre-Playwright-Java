package pages;

import com.microsoft.playwright.Page;

import hooks.Hooks;

public class BasePage {

    protected Page page;

    public BasePage() {

        this.page = Hooks.page;
    }

    public void navigate(String url) {

        page.navigate(url);
    }
}