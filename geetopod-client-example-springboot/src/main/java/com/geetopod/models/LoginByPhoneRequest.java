package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class LoginByPhoneRequest extends BasicRequest {
    public String company = "";
    public String phone = "";
    public String verifiedToken = "";
    public List<String> accessResources = new ArrayList<String>();
}
