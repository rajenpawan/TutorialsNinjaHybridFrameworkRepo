package com.tutorialsninja.qa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
 WebDriver driver;
 
 @FindBy(id="input-firstname")
 private WebElement firstNameField;
 
 @FindBy(id="input-lastname")
 private WebElement lastNameField;
 
 @FindBy(id="input-email")
 private WebElement emailAddressField;
 
 @FindBy(id="input-telephone")
 private WebElement telephoneField;
 
 @FindBy(id="input-password")
 private WebElement passwordField;
 
 @FindBy(id="input-confirm")
 private WebElement passwordConfirmField;
 
 @FindBy(name="agree")
 private WebElement privacyPolicyField;
 
 @FindBy(xpath="//input[@value='Continue']")
 private WebElement continueButton;
 
 @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
 private WebElement duplicateEmailAddressWarning;
 
 @FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
 private WebElement privacyPolicyWarning;
 
 @FindBy(xpath="//input[@id='input-firstname']/following-sibling::div")
 private WebElement firstNameWarning;
 
 @FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
 private WebElement lastNameWarning;
 
 @FindBy(xpath="//input[@id='input-email']/following-sibling::div")
 private WebElement emailWarning;
 
 @FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
 private WebElement telephoneWarning;
 
 @FindBy(xpath="//input[@id='input-password']/following-sibling::div")
 private WebElement passwordWarning;
 
 public RegisterPage(WebDriver driver) {
	 this.driver = driver;
	 PageFactory.initElements(driver, this);
 }
 
 public void enterFirstName(String firstNameText) {
	 firstNameField.sendKeys(firstNameText);
 }
 
 public void enterLastName(String lastNameText) {
	 lastNameField.sendKeys(lastNameText);
 }
 
 public void enterEmailAddress(String emailText) {
	 emailAddressField.sendKeys(emailText);
 }
 
 public void enterTelephone(String telephoneText) {
	 telephoneField.sendKeys(telephoneText);
 }
 
 public void enterPassword(String passwordText) {
	 passwordField.sendKeys(passwordText);
 }
 
 public void enterConfirmPassword(String confirmPasswordText) {
	 passwordConfirmField.sendKeys(confirmPasswordText);
 }
 
 public void selectPrivacyPolicy() {
	 privacyPolicyField.click();
 }
 
 public void clickOnContinueButton() {
	 continueButton.click();
 }
 
 public String retrieveDuplicateEmailAddressWarning() {
	 
	String duplicateEmailWarningText =  duplicateEmailAddressWarning.getText();
	return duplicateEmailWarningText;
 }
 
 public String retrievePrivacyPolicyWarning() {
	 String privacyPolicyWarningText = privacyPolicyWarning.getText();
	 return privacyPolicyWarningText;
 }
 
 public String retrieveFirstNameWarning() {
	 String firstNameWarningText = firstNameWarning.getText();
	 return firstNameWarningText;
 }
 
 public String retrievelastNameWarning() {
	 String lastNameWarningText = lastNameWarning.getText();
	 return lastNameWarningText;
 }
 
 public String retrieveemailWarning() {
	 String emailWarningText = emailWarning.getText();
	 return emailWarningText;
 }
 
 public String retrievetelephoneWarning() {
	 String telephoneWarningText = telephoneWarning.getText();
	 return telephoneWarningText;
 }
 
 public String retrievepasswordWarning() {
	 String passwordWarningText = passwordWarning.getText();
	 return passwordWarningText;
 }
 
}
