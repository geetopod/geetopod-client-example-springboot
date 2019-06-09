package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class ValidateTokenResponse extends BasicResponse {
    public String company = "";
    public boolean active = false;
    public String username = "";
    public String preferredUsername = "";
    public String email = "";
    public boolean emailVerified = false;
    public String firstName = "";
    public String lastName = "";
    public String fullName = "";
    public List<Permission> permissions = new ArrayList<Permission>();
}
