package com.geetopod.models;

public class RegisterByPhoneResponse extends BasicResponse {
    public String token = "";
    public String refreshToken = "";
    public long expiresIn = 0;
    public long refreshExpiresIn = 0;
}
