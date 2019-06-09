package com.geetopod.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListResourceResponse extends BasicResponse {
    public List<String> resources = new ArrayList<>();
    public Map<String, List<String>> assignedResources = new HashMap<>();
}
