package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class ValidateSSOTokenResponse extends BasicResponse {
    public boolean active = false;
    public String company = "";
    public String username = "";
    public String firstName = "";
    public String lastName = "";
    public List<Permission> permissions = new ArrayList<Permission>();
}
