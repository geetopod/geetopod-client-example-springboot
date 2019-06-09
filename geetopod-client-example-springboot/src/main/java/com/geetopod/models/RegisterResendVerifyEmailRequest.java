package com.geetopod.models;

public class RegisterResendVerifyEmailRequest extends AuthorizedRequest {
    public String subjectTemplate = "";
    public String bodyTextTemplate = "";
    public String bodyHtmlTemplate = "";
}
