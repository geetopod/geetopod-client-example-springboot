package com.geetopod.models;

public class RegisterCompanyRequest extends BasicRequest {
    public String companyCode = "";
    public String companyName = "";

    public String adminUsername = "";
    public String adminPassword = "";
    public String adminEmail = "";
    public String adminFirstName = "";
    public String adminLastName = "";
    public String adminMiddleName = "";
    public String adminPhone = "";
    public String adminMailingAddress = "";
    public String adminBillingAddress = "";
    public String adminCountry = "";
    public String adminState = "";
    public String adminPostalCode = "";

    public String captchaCode = "";
    public String captchaText = "";

    public String verifyEmailSubjectTemplate = "";
    public String verifyEmailBodyTextTemplate = "";
    public String verifyEmailBodyHtmlTemplate = "";
}
