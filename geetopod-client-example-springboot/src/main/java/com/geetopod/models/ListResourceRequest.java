package com.geetopod.models;

import java.util.ArrayList;
import java.util.List;

public class ListResourceRequest extends AuthorizedRequest {
    public List<String> usernames = new ArrayList<>();
}
