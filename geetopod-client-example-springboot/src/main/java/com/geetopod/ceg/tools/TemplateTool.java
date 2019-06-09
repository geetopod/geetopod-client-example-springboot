package com.geetopod.ceg.tools;

import com.geetopod.ceg.WebApp;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public class TemplateTool {
    private static TemplateTool __instance;

    public static TemplateTool instance() {
        if (__instance == null) {
            __instance = new TemplateTool();
        }
        return __instance;
    }

    public void fillCommonModel(Model model, HttpSession session) {
        boolean online = false;
        boolean ssoInUse = false;
        try {
            com.geetopod.clients.Services.instance().gatewayUrl(WebApp.instance().appConfig().ssoGatewayUrl);
            com.geetopod.clients.SSOClient client = com.geetopod.clients.Clients.instance().getClient();
            online = client.online();
            if (online) {
                ssoInUse = client.ssoInUse();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("online", online);
        model.addAttribute("ssoInUse", ssoInUse);
    }
}
