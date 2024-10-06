package Object.utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchResultPage {
	
	public static WebDriver driver;

	@FindBy(xpath = "//span[contains(text(),'results')]")
	private WebElement searchResultsText;

	@FindBy(xpath = "//div[@class='cN1yYO']//div/span")
	private List<WebElement> discountList;

	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public int getCountforSearchResults() {

		String resultText = searchResultsText.getText();
		String[] resultCount = resultText.split("of");
		String[] resultCountValue = resultCount[1].split("results");
		int totalNumberOfResult = Integer.parseInt(resultCountValue[0].trim().replace(",", ""));
		System.out.println("total number of results found : " + totalNumberOfResult);
		return totalNumberOfResult;

	}

	public String verifySearchResults() {

		String resultText = searchResultsText.getText();
		String[] productNames = resultText.split("for");
		String actualProductName = productNames[1].replace("\"", "").trim();
		//System.out.println(actualProductName);
		return actualProductName;

	}

	public void clickResultWithHighestDiscount() {
		
		List<Integer> discList = new ArrayList<Integer>();

		for (WebElement discount : discountList) {

			String[] discountValues = discount.getText().split("%");
		     discList.add(Integer.parseInt(discountValues[0]));
		}
		Collections.sort(discList);
		//System.out.println(discList);
		int MaxDiscount = discList.get(discList.size()-1);
		//System.out.println(MaxDiscount);
		
		for (WebElement discount : discountList) {

			String[] discountValues = discount.getText().split("%");
		    if(Integer.parseInt(discountValues[0])==MaxDiscount) {
		    	
		    	discount.click();
		    }
		}

	}

	public void switchTab() {
		Set<String> AllWindowIds = driver.getWindowHandles();
		// System.out.println(AllWindowIds);
		ArrayList<String> ListofWindowids = new ArrayList<String>(AllWindowIds);
		//System.out.println("Child window Id : " + ListofWindowids.get(1));
		String ChildWindowId = ListofWindowids.get(1);
		driver.switchTo().window(ChildWindowId);

	}

}
