package com.geetopod.models;

public class ForgotPasswordSendByEmailRequest extends BasicRequest {
    public String company = "";
    public String email = "";
    public String subjectTemplate = "";
    public String bodyTextTemplate = "";
    public String bodyHtmlTemplate = "";
}
