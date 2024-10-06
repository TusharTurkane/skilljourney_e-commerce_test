package Object.utilities;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductDetailPage {

	public static WebDriver driver;
	public Actions myActions;
	public static WebDriverWait wait;

	@FindBy(xpath = "//button[text()='Add to cart']")
	private WebElement addToCartButton;
	
	@FindBy(xpath = "//div[contains(@class ,'CxhGGd')]")
	private WebElement productPrice;
	
	@FindBy(xpath = "//a[text()= 'Login']")
	private WebElement loginButton;
	
	
	

	public ProductDetailPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addToCart() throws InterruptedException {
		Thread.sleep(2000);
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(loginButton));
		myActions = new Actions(driver);
		myActions.scrollToElement(addToCartButton).perform();
		wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
		addToCartButton.click();

	}

	public int getTotalAmountProductPage() {
		String subString = productPrice.getText().replace("₹", "");
		String totalAmountinstring = subString.replace(",", "").trim();
		System.out.println(totalAmountinstring);
		int totalAmountProdcutPage = Integer.parseInt(totalAmountinstring);
		System.out.println(totalAmountProdcutPage);
		return totalAmountProdcutPage ;
	}

}
