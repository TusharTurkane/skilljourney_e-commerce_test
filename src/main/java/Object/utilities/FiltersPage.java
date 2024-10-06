package Object.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FiltersPage {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public Actions myActions;
	public Select minPrice ;
	public Select maxPrice ;

	@FindBy(xpath = "//input[@placeholder='Search Brand']")
	private WebElement brandSearchBox;

	@FindBy(xpath = "//div[text() ='SAMSUNG']/../input/following-sibling::div")
	private WebElement brandFilterCheckbox;

	@FindBy(xpath = "(//select[@class='Gn+jFg'])[1]")
	private WebElement MinPriceListBox;

	@FindBy(xpath = "(//select[@class='Gn+jFg'])[2]")
	private WebElement MaxPriceListBox;

	@FindBy(xpath = "//div[text()= 'Availability']/..")
	private WebElement availabilityFilter;

	@FindBy(xpath = "//div[text()='Exclude Out of Stock']")
	private WebElement excludeOutofStockCheckBox;

	@FindBy(xpath = "//div[text() = 'Discount']")
	private WebElement discountFilter;

	@FindBy(xpath = "//div[text()='30% or more']")
	private WebElement discountPercentage;

	@FindBy(xpath = "//span[contains(text(),'results')]")
	private WebElement resultAfterFiltersApplied;
	
	@FindBy(xpath = "//span[contains(text() ,\"Page\")]")
	private WebElement pages;
	
	public FiltersPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void applyBrandFilter(String brandName) {

		brandSearchBox.sendKeys(brandName);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.elementToBeClickable(brandFilterCheckbox));
		brandFilterCheckbox.click();

	}

	public void applyPriceFilter(String minPrices , String maxPrices) {

		//myActions = new Actions(driver);
		//myActions.dragAndDropBy(MinPriceDrag, 10, 0);
		 minPrice = new Select(MinPriceListBox);
		 minPrice.selectByValue(minPrices);
		 maxPrice = new Select(MaxPriceListBox);
		 maxPrice.selectByValue(maxPrices);
		
	}

	public void applyAvialiblityFilter() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		myActions = new Actions(driver);
		myActions.scrollToElement(availabilityFilter);
		wait.until(ExpectedConditions.elementToBeClickable(availabilityFilter));
		availabilityFilter.click();
		wait.until(ExpectedConditions.visibilityOf(excludeOutofStockCheckBox));
		excludeOutofStockCheckBox.click();

	}

	public void applyDiscountFilter() {
		myActions = new Actions(driver);
		myActions.scrollToElement(discountFilter);
		// discountFilter.click();
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(discountPercentage));
		discountPercentage.click();

	}

	public int verifyNumberofResultDisplayed() {
		myActions = new Actions(driver);
		myActions.scrollToElement(resultAfterFiltersApplied);
		String[] rawresultCount = resultAfterFiltersApplied.getText().split("of");
		String[] resultCount = rawresultCount[1].split("results");
		int NumberofResultDisplayed = Integer.parseInt(resultCount[0].trim());
		return NumberofResultDisplayed ;
	}

	

}
