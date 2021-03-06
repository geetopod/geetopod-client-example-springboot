package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class LoginOTPResponse extends BasicResponse {
    public String ssoToken = "";
    public String token = "";
    public String refreshToken = "";
    public long expiresIn = 0;
    public long refreshExpiresIn = 0;
    public List<Permission> permissions = new ArrayList<Permission>();
}