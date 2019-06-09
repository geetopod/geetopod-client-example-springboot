package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class LoginRequest extends BasicRequest {
    public String company = "";
    public String username = "";
    public String password = "";
    public List<String> accessResources = new ArrayList<String>();
    public boolean hasOTP = false;
    public boolean hasSSO = false;
    public String bodyTextTemplate = "";
}
