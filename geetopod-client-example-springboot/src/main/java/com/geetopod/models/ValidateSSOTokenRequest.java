package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class ValidateSSOTokenRequest extends AuthorizedRequest {
    public List<String> accessResources = new ArrayList<String>();
}
