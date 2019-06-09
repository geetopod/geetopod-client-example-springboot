package com.geetopod.clients;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class Clients {
    private static Clients __instance;
    private Map<String, SSOClient> _clientMap = new HashMap<String, SSOClient>();

    public static Clients instance() {
        if (__instance == null) {
            __instance = new Clients();
        }
        return __instance;
    }

    public SSOClient getClient() throws Exception {
        return getClient(null);
    }

    public SSOClient getClient(String code) throws Exception {
        if (code == null || code.trim().length() == 0) {
            if (Services.instance().session().getValue("SSOPOD_CLIENT_ID").length() > 0) {
                code = Services.instance().session().getValue("SSOPOD_CLIENT_ID");
            } else {
                code = UUID.randomUUID().toString().replaceAll("-", "");
                Services.instance().session().setValue("SSOPOD_CLIENT_ID", code);
            }
            return getClient(code);
        }
        if (_clientMap.containsKey(code)) {
            return _clientMap.get(code);
        }
        SSOClient client = new SSOClient(code);
        _clientMap.put(code, client);
        return client;
    }
}
