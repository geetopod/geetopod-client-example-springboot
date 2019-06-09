package com.geetopod.models;

public class UpdateUserInfoRequest extends AuthorizedRequest {
    public String username = "";
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
}
