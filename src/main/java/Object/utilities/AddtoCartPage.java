package Object.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class AddtoCartPage {
	
	public static WebDriver driver;
	
	@FindBy(xpath = "//div[text()='Price (1 item)']")
	private WebElement productPrice;
	
	@FindBy(xpath = "//div[text() = 'Discount']")
	private WebElement discountPrice;
	
	@FindBy(xpath = "//div[text()='Delivery Charges']")
	private WebElement deliveryCharges;
	
	@FindBy(xpath = "//div[text()='Total Amount']")
	private WebElement totalAmount;
	
	@FindBy(xpath = "//div[@class='PWd9A7']//span[contains(text(), '₹')]")
	private WebElement TotalAmountText;
	
	public AddtoCartPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void verifyPageFields() {
		
		boolean isproductPriceDisplayed = productPrice.isDisplayed();
		Assert.assertTrue(isproductPriceDisplayed);
		boolean isdiscountPriceDisplayed = discountPrice.isDisplayed();
		Assert.assertTrue(isdiscountPriceDisplayed);
		boolean isdeliveryChargesDisplayed = deliveryCharges.isDisplayed();
		Assert.assertTrue(isdeliveryChargesDisplayed);
		boolean istotalAmountDisplayed = totalAmount.isDisplayed();
		Assert.assertTrue(istotalAmountDisplayed);
		
	}
	
	public int getTotalAmountCartPage() {
		String partialString = TotalAmountText.getText().replace("₹", "");
		int totalAmountCartPage = Integer.parseInt(partialString.replace(",", "").trim());
		return totalAmountCartPage ;
	}

}
