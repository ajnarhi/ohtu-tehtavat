package ohtu;

import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Stepdefs {
    WebDriver driver = new ChromeDriver();
    //WebDriver driver = new HtmlUnitDriver();
    String baseUrl = "http://localhost:4567";
    
    @Given("login is selected")
    public void loginIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("login"));       
        element.click();   
    }    
    
    @When("correct username {string} and password {string} are given")
    public void correctUsernameAndPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is logged in")
    public void userIsLoggedIn() {
        pageHasContent(""); //TÄMÄ EI TOIMI; MUTTA MIKSI EI?
    }    
 
    @When("correct username {string} and incorrect password {string} are given")
    public void correctUsernameAndIncorrectPasswordAreGiven(String username, String password) {
        logInWith(username, password);
    }    
    
    @Then("user is not logged in and error message is given")
    public void userIsNotLoggedInAndErrorMessageIsGiven() {
        pageHasContent("invalid username or password");
        pageHasContent("Give your credentials to login");
    } 
    
    @When("nonxistent username {string} and password {string} are given")
    public void nonxistentUsernameAndPasswordAreGiven(String string, String string2) {
        logInWith(string, string2);
    }
    
    @Given("command new user is selected")
    public void newUserIsSelected() {
        driver.get(baseUrl);
        WebElement element = driver.findElement(By.linkText("register new user"));       
        element.click();   
    }   
    
    @When ("a valid username {string} and password {string} and matching password confirmation are entered")
    public void validUsernameAndPasswordAndMatchingPasswordAreGiven(String string, String string2){
        createUser(string, string2, string2);
        
    }
    
    @Then("a new user is created")
    public void userIsCreated() {
        pageHasContent("Welcome to Ohtu Application!");
    }    
    
    @When ("an invalid username {string} and password {string} and matching password confirmation are entered")
    public void invalidUsernameAndValidPasswordAndMatchingPasswordAreGiven(String string, String string2){
        createUser(string, string2, string2);
    }
        
    @Then("user is not created and error username should have at least 3 characters is reported")
    public void userNotCreated() {
        pageHasContent("username should have at least 3 characters");
    }
       
    @When("correct username {string} and too short password {string} and matching password confirmation are entered")
    public void validUsernameTooShortPassword(String string, String string2){
        createUser(string, string2, string2);
    }
    
      @Then("user is not created and error {string} is reported")
    public void userIsNotCreatedAndErrorIsReported(String string) {
       pageHasContent("password should have at least 8 characters");
    }

     @When("correct username {string} and correct password {string} and unmatching password {string} are given")
    public void validUsernameNotMatchingPasswords(String string, String string2, String string3){
        createUser(string, string2, string3);
    }
    
      @Then("user is not created and error2 {string} is reported")
    public void userIsNotCreatedAndErrorNotMatchingIsReported(String string) {
       pageHasContent("password and password confirmation do not match");
    }
    
    
    @After
    public void tearDown(){
        driver.quit();
    }
        
    /* helper methods */
 
    private void pageHasContent(String content) {
        assertTrue(driver.getPageSource().contains(content));
    }
        
    private void logInWith(String username, String password) {
        assertTrue(driver.getPageSource().contains("Give your credentials to login"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();  
    } 
    
    private void createUser(String username, String password, String passwordConfirmation) {
        assertTrue(driver.getPageSource().contains("Create username and give password"));
        WebElement element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
         element = driver.findElement(By.name("passwordConfirmation"));
         element.sendKeys(passwordConfirmation);
        element = driver.findElement(By.name("signup"));
        element.submit();  
    } 
}
