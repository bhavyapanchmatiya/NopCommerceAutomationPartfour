package NopCommerceAutomationPartThree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class NopCommerceThreeModules  extends UtilsPage1 {


    @BeforeMethod


    public void openBrowser() {

        System.setProperty("webdriver.chrome.driver","src\\main\\Resources\\BrowserDriver\\chromedriver.exe");

        //Open the Browser
        openChromeVersion76();
        //driver = new ChromeDriver();

        //Maximise the Browser window screen
        manageWindow();
        //driver.manage().window().fullscreen();

        //Set implicity wait for driver object
        manageTimeoutImplicity();
        //driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        //Open the website
        openNopCommerce();
        //driver.get("https://demo.nopcommerce.com/");
    }


    @AfterMethod

    public void browserClose(){
        //driver.quit();
    }
    //Test 1
    @Test

    public void userShouldBeAbleToClickTwoDifferentItemsToCompareAndSeeThoseItemsInComparePage(){

//Click On electronics
        clickElement(By.xpath("//li/a[@href=\"/electronics\"]"));
//Click on Cellphone
        clickElement(By.xpath("//img[@alt = \"Picture for category Cell phones\"]"));
//Click on First Product HTC One M8 Android L 5.0 Lollipop
        clickElement(By.xpath("//div/a[@href = \"/htc-one-m8-android-l-50-lollipop\"]"));
//Click add to Compare
        clickElement(By.xpath("//div[@class=\"compare-products\"]"));

//To confirm if the First product is added to compare
        String actualResultTest1Module1 = extractText(By.xpath("//p/a[@href=\"/compareproducts\"]"));
        String expectedResultTest1Module1 = ("product comparison");
        Assert.assertEquals(actualResultTest1Module1,expectedResultTest1Module1);

//Click on Second product HTC One Mini Blue
        clickElement(By.xpath("//a[@title=\"Show details for HTC One Mini Blue\"]"));
//click on add to compare
        clickElement(By.xpath("//div[@class=\"compare-products\"]"));

//To confirm if the second product is added to compare
        String actualResultTest1Module2 = extractText(By.xpath("//p/a[@href=\"/compareproducts\"]"));
        String expectedResultTest1Module2 = ("product comparison");
        Assert.assertEquals(actualResultTest1Module2,expectedResultTest1Module2);

//Click on compare prodcuts
        clickElement(By.xpath("//p/a[@href=\"/compareproducts\"]"));

//To confirm the products in the compare list are the ones which we have added
        String actualResultTest1Module3 = extractText(By.xpath("//tr[@class=\"product-name\"]"));
        String expectedResultTest1Module3 = ("Name HTC One Mini Blue HTC One M8 Android L 5.0 Lollipop");
        Assert.assertEquals(actualResultTest1Module3,expectedResultTest1Module3);

//Click on Clear button
        clickElement(By.xpath("//a[@class=\"clear-list\"]"));

//To confirm al is cleared and message is desplayed
        String actualResultTest1Module4 = extractText(By.xpath("//div[@class=\"no-data\"]"));
        String expectedResultTest1Module4 = ("You have no items to compare.");
        Assert.assertEquals(actualResultTest1Module4,expectedResultTest1Module4);
    }
    //Test 2
    @Test

    public void userShouldBeAbleToNavigateToNewsAndTypeDetailsAndItShouldBeDisplayedAtBottom(){

        //Click on news
        clickElement(By.xpath("//li/a[@href=\"/news\"]"));
        //Click on Details
        clickElement(By.xpath("//a[@href=\"/new-online-store-is-open\" and @class=\"read-more\" ]"));

        //To confirm we can Leave our comments
        String actualResultTest2Module1 = extractText(By.id("comments"));
        String expectedResultTest2Module1 = ("Leave your comment\nTitle:\nComment:");
        Assert.assertEquals(actualResultTest2Module1,expectedResultTest2Module1);


        //To enter Title
        enterText(By.id("AddNewComment_CommentTitle"),getProperty("FirstName")+" "+ generateRandomFloat());
        //To enter comment
        enterText(By.id("AddNewComment_CommentText"),getProperty("Comment"));
        //Click Add Comments
        clickElement(By.name("add-comment"));

        //To confirm the message has been added

        String actualResultTest2Module2 = extractText(By.xpath("//div[@class=\"result\"]"));

        String expectedResultTest2Module2 = ("News comment is successfully added.");

        Assert.assertEquals(actualResultTest2Module2,expectedResultTest2Module2);


        //List<WebElement> commentList = driver.findElements(By.xpath("//div/strong[@class=\\\"comment-text\\\"]"));

        //WebElement last_element = commentList.get(commentList.size()-1);

        // System.out.println(last_element.getText());


        //WebElement location = driver.findElement(By.xpath("//p[contains(text(),'website for shopping')]"));

        //System.out.println(location.getText ());

        //List<WebElement> array = driver.findElements(By.xpath("//div/strong[@class=\"comment-text\"]"));

        //WebElement lastComent = comment.List.get(comment.List);




        //for(WebElement e:array){

        //System.out.println(e);

        //System.out.println("getText:" + e.findElement());
        //}
        //array[] a = driver.findElements(By.xpath("//div/strong[@class=\"comment-text\"]"));

        //System.out.println(array()-1);


        //String actualResultTest2Module3 = extractText(By.xpath("//div[@class=\"user-info\"]"));

        //String expectedResultTest2Module3 = ("Bhavya");

        //Assert.assertEquals(actualResultTest2Module3,expectedResultTest2Module3);
    }

    //Test 3
    @Test

    public void userShouldBeAbleToSearchByKeywordsUsingTheSearchBarAndAllTheResultsShouldHaveTheKeywordInThem(){

        //Enter KeyWord in Search Box : Card
        enterText(By.id("small-searchterms"),getProperty("KeyWord"));
        //Click Search Button
        clickElement(By.xpath("//input[@class=\"button-1 search-box-button\"]"));
        //To confirm all the result has the keyword in them
        //Result 1

        List<WebElement> al = driver.findElements(By.xpath("//div[@class=\"item-grid\"]"));

        System.out.println(al.size());

        int count = 0;

        for(WebElement e : al){
            if(e.getAttribute( "outerHTML").contains(getProperty("KeyWord"))){

                count++;

                System.out.println(e.getText());
                Assert.assertTrue((e.getText()).contains(getProperty("KeyWord")));
            }else{

                System.out.println("No KeyWord"+ e.getText());
            }
            System.out.println(count);
            Assert.assertEquals(al.size(),count);
        }





        //Enter random alphabets in search
        enterText(By.id("small-searchterms"),getProperty("RandomAlphabets"));
        //Click Search
        clickElement(By.xpath("//input[@class=\"button-1 search-box-button\"]"));


        //To Confirm it should show the error message
        String actualResultTest3Module4 = extractText(By.xpath("//div[@class=\"no-result\"]"));
        String expectedResultTest3Module4 = ("No products were found that matched your criteria.");
        Assert.assertEquals(actualResultTest3Module4,expectedResultTest3Module4);

        //click on Homepage Image
        clickElement(By.xpath("//img[@alt=\"nopCommerce demo store\"]"));
        //Enter 3 Spaces in SearchBox
        enterText(By.id("small-searchterms"),"   ");
        //Click Search Button
        clickElement(By.xpath("//input[@class=\"button-1 search-box-button\"]"));

        //To confirm the Warning message should show
        String actualResultTest3Module5 = extractText(By.xpath("//div[@class=\"warning\"]"));
        String expectedResultTest3Module5 = ("Search term minimum length is 3 characters");
        Assert.assertEquals(actualResultTest3Module5,expectedResultTest3Module5);


    }


}
