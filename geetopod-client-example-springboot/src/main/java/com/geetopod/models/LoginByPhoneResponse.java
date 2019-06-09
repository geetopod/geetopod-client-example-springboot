package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class LoginByPhoneResponse extends BasicResponse {
    public String token = "";
    public String refreshToken = "";
    public long expiresIn = 0;
    public long refreshExpiresIn = 0;
    public List<Permission> permissions = new ArrayList<Permission>();
}
