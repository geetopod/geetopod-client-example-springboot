package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class SocialLoginProcessRequest extends BasicRequest {
    public String company = "";
    public String verifiedToken = "";
    public boolean hasSSO = false;
    public List<String> accessResources = new ArrayList<String>();
}
