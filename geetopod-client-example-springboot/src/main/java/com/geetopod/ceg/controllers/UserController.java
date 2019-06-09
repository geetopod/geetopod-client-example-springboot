package com.geetopod.ceg.controllers;

import com.geetopod.ceg.WebApp;
import com.geetopod.ceg.tools.TemplateTool;
import com.geetopod.clients.SSOClient;
import com.geetopod.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
@RequestMapping("/u")
public class UserController {
    @GetMapping("profile")
    public String getProfile(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        GetUserInfoResponse response = new GetUserInfoResponse();
        String message = "";
        SSOClient client = null;
        try {
            GetUserInfoRequest request = new GetUserInfoRequest();
            client = com.geetopod.clients.Clients.instance().getClient();
            request.company = WebApp.instance().appConfig().ssoCompany;
            request.token = client.token();
            response = com.geetopod.clients.Services.instance().getUserInfo(request);
        } catch (Exception e) {
            message = e.getMessage();
            e.printStackTrace();
        }
        if (message.length() > 0) {
            if (client != null && client.ssoInUse()) {
                try {
                    ValidateSSOTokenRequest validateSSOTokenRequest = new ValidateSSOTokenRequest();
                    validateSSOTokenRequest.company = WebApp.instance().appConfig().ssoCompany;
                    validateSSOTokenRequest.ssoToken = client.ssoToken();
                    ValidateSSOTokenResponse validateSSOTokenResponse = com.geetopod.clients.Services.instance().validateSSOToken(validateSSOTokenRequest);
                    if (!validateSSOTokenResponse.isError) {
                        response.username = validateSSOTokenResponse.username;
                        response.firstName = validateSSOTokenResponse.firstName;
                        response.lastName = validateSSOTokenResponse.lastName;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        model.addAttribute("userinfo", response);
        model.addAttribute("message", message);
        return "profile";
    }

    @PostMapping("profile")
    public String postProfile(Model model, HttpSession session,
                              @RequestParam(name = "username") String username,
                              @RequestParam(name = "email") String email,
                              @RequestParam(name = "phone") String phone,
                              @RequestParam(name = "firstName") String firstName,
                              @RequestParam(name = "middleName") String middleName,
                              @RequestParam(name = "lastName") String lastName,
                              @RequestParam(name = "mailingAddress") String mailingAddress,
                              @RequestParam(name = "billingAddress") String billingAddress,
                              @RequestParam(name = "country") String country,
                              @RequestParam(name = "state") String state,
                              @RequestParam(name = "postalCode") String postalCode
    ) {
        TemplateTool.instance().fillCommonModel(model, session);
        GetUserInfoResponse responseGet = new GetUserInfoResponse();
        String message = "";
        try {
            UpdateUserInfoRequest request = new UpdateUserInfoRequest();
            request.company = WebApp.instance().appConfig().ssoCompany;
            request.token = com.geetopod.clients.Clients.instance().getClient().token();
            request.username = username;
            request.email = email;
            request.phone = phone;
            request.firstName = firstName;
            request.lastName = lastName;
            request.middleName = middleName;
            request.mailingAddress = mailingAddress;
            request.billingAddress = billingAddress;
            request.country = country;
            request.state = state;
            request.postalCode = postalCode;
            UpdateUserInfoResponse response = com.geetopod.clients.Services.instance().updateUserInfo(request);
            if (response.isError) {
                message = response.errorMessage;
            } else {
                return "redirect:/u/profile";
            }
            GetUserInfoRequest requestGet = new GetUserInfoRequest();
            request.company = WebApp.instance().appConfig().ssoCompany;
            request.token = com.geetopod.clients.Clients.instance().getClient().token();
            responseGet = com.geetopod.clients.Services.instance().getUserInfo(requestGet);
        } catch (Exception e) {
            message = e.getMessage();
            e.printStackTrace();
        }
        try {
            if (message.length() > 0) {
                SSOClient client = com.geetopod.clients.Clients.instance().getClient();
                if (client != null && client.ssoInUse()) {
                    try {
                        ValidateSSOTokenRequest validateSSOTokenRequest = new ValidateSSOTokenRequest();
                        validateSSOTokenRequest.company = WebApp.instance().appConfig().ssoCompany;
                        validateSSOTokenRequest.ssoToken = client.ssoToken();
                        ValidateSSOTokenResponse validateSSOTokenResponse = com.geetopod.clients.Services.instance().validateSSOToken(validateSSOTokenRequest);
                        if (!validateSSOTokenResponse.isError) {
                            responseGet.username = validateSSOTokenResponse.username;
                            responseGet.firstName = validateSSOTokenResponse.firstName;
                            responseGet.lastName = validateSSOTokenResponse.lastName;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            message = e.getMessage();
            e.printStackTrace();
        }
        model.addAttribute("message", message);
        model.addAttribute("userinfo", responseGet);
        return "profile";
    }
}
