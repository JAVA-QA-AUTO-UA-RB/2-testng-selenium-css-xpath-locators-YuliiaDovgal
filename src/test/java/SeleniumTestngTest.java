import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;


public class SeleniumTestngTest extends BasicSetupTest {

    @Test
    public void abTestingPageHasSpecificTextTest() {
        browser.get("https://the-internet.herokuapp.com");
        WebElement abTestingTaskLink = browser.findElement(By.linkText("A/B Testing"));
        abTestingTaskLink.click();

        WebElement heading = browser.findElement(By.tagName("h3"));

        Assert.assertTrue(heading.getText().contains("A/B Test Control"), "The text 'A/B Test Control' is not found.");

    }

    @Test
    public void addRemoveElementsTest() {
        browser.get("https://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = browser.findElement(By.cssSelector("button[onclick='addElement()']"));

        for (int i = 0; i < 3; i++) {
            addButton.click();
        }
        Assert.assertEquals(browser.findElements(By.cssSelector(".added-manually")).size(), 3, "3 Delete buttons should be visible");
        for (WebElement deleteButton : browser.findElements(By.cssSelector(".added-manually"))) {
            deleteButton.click();
        }
        Assert.assertTrue(browser.findElements(By.cssSelector(".added-manually")).isEmpty(), "All delete buttons to be removed but at least one is still present");
    }

    @Test
    public void checkAllCheckBoxesTest() {
        browser.get ("https://the-internet.herokuapp.com/checkboxes");
        for (WebElement checkbox : browser.findElements(By.cssSelector("input[type='checkbox']"))) {
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
            Assert.assertTrue(checkbox.isSelected(), "Checkbox should be selected");
        }
    }

    @Test
    public void dropDownOptionTest() {
        browser.get ("https://the-internet.herokuapp.com/dropdown");
        WebElement dropDown = browser.findElement(By.id("dropdown"));
        dropDown.click();
        WebElement option2 = browser.findElement(By.cssSelector("option[value='2']"));
        option2.click();
        Assert.assertTrue(option2.isSelected(), "Option 2 should be selected");
    }

    @Test
    public void formAuthenticationTest() {
        browser.get ("https://the-internet.herokuapp.com/login");
        WebElement username = browser.findElement(By.id("username"));
        WebElement password = browser.findElement(By.id("password"));
        WebElement loginButton = browser.findElement(By.cssSelector("button[type='submit']"));

        username.sendKeys("tomsmith");
        password.sendKeys("SuperSecretPassword!");
        loginButton.click();

        WebElement logoutButton = browser.findElement(By.cssSelector("a[href='/logout']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button should be displayed");

        logoutButton.click();

        WebElement loginButtonAfterLogout = browser.findElement(By.cssSelector("button[type='submit']"));
        Assert.assertTrue(loginButtonAfterLogout.isDisplayed(), "Login button should be displayed after logout");
    }

    @Test
    public void DragAndDropTest() {
        browser.get ("https://the-internet.herokuapp.com/drag_and_drop");
        WebElement elementA = browser.findElement(By.id("column-a"));
        WebElement elementB = browser.findElement(By.id("column-b"));

        Actions action = new Actions(browser);
        action.dragAndDrop(elementA, elementB).perform();

        String textA = elementA.getText();
        String textB = elementB.getText();
        Assert.assertNotEquals(textA, textB, "Elements did not swap places correctly");
    }

    @Test
    public void HorizontalSliderTest() {
        browser.get("https://the-internet.herokuapp.com/horizontal_slider");
        WebElement slider = browser.findElement(By.cssSelector("input[type='range'][min='0.0'][max='5.0'][step='0.5']"));
        WebElement value = browser.findElement(By.id("range"));

        slider.sendKeys(org.openqa.selenium.Keys.ARROW_RIGHT);
        slider.sendKeys(org.openqa.selenium.Keys.ARROW_RIGHT);

        String sliderValue = value.getText();
        String expectedValue = "1";

        Assert.assertEquals(sliderValue, expectedValue, "Slider value is incorrect after moving it twice to the right.");
    }
}