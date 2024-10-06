package Object.utilities;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartHomePage {

	public static WebDriver driver;
	public static WebDriverWait wait;

	@FindBy(xpath = "//p[text()='Get access to your Orders, Wishlist and Recommendations']")
	private WebElement loginPopUpText;

	@FindBy(xpath = "//span[text()='✕']")
	private WebElement closeLoginPopUp;

	@FindBy(xpath = "//input[@name='q']")
	private WebElement searchBar;

	@FindBy(xpath = "//form[contains(@class , 'header-form-search')]//ul//li//span")
	private List<WebElement> autosuggestionSearchBox;

	public FlipkartHomePage(WebDriver driver) {
        this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void closeLoginPopUp() {

		try {
			wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			wait.until(ExpectedConditions.visibilityOf(loginPopUpText));
			Thread.sleep(2000);
			wait.until(ExpectedConditions.visibilityOf(closeLoginPopUp)).click();
			System.out.println("Login popup closed");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Login popup did not appear");
		}
	}

	public void enterProductInSearchBar(String productName) {
		searchBar.sendKeys(productName);
	}

	public void hitEnterButtonAfterSearchText() {
		searchBar.sendKeys(Keys.ENTER);
	}
	
	public int verifyAutoSuggestionCount() {
		
	int suggestionCount= autosuggestionSearchBox.size();
	
	return suggestionCount;
	
	}

	public boolean verifyAutoSuggestionText(String ExpectedString) {

		for (WebElement suggestion : autosuggestionSearchBox) {

			String autosuggestionText = suggestion.getText();

			if (suggestion.getText().contains(ExpectedString)) {

				return true;
			}

		}
		return false;

	}

}
