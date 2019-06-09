package com.geetopod.models;

public class RegisterRequest extends BasicRequest {
    public String company = "";
    public String username = "";
    public String password = "";
    public String email = "";
    public String firstName = "";
    public String lastName = "";
    public String middleName = "";
    public String phone = "";
    public String mailingAddress = "";
    public String billingAddress = "";
    public String country = "";
    public String state = "";
    public String postalCode = "";

    public String captchaCode = "";
    public String captchaText = "";

    public String verifyEmailSubjectTemplate = "";
    public String verifyEmailBodyTextTemplate = "";
    public String verifyEmailBodyHtmlTemplate = "";
}
