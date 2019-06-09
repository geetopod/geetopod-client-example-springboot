package com.geetopod.ceg.controllers;

import com.geetopod.ceg.WebApp;
import com.geetopod.ceg.tools.TemplateTool;
import com.geetopod.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@CrossOrigin
@Controller
public class PublicController {
    @GetMapping("/")
    public String getIndex(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "home";
    }

    @GetMapping("/s/gallery.html")
    public String getGallery() {
        return "redirect:/s/work.html";
    }

    @GetMapping("/s/work.html")
    public String getWork(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "work";
    }

    @GetMapping("/s/work-grid.html")
    public String getWorkGrid(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "work-grid";
    }

    @GetMapping("/s/work-grid-without-text.html")
    public String getWorkGridWithoutText(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "work-grid-without-text";
    }

    @GetMapping("/s/services.html")
    public String getServices(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "services";
    }

    @GetMapping("/s/blog.html")
    public String getBlog(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "blog";
    }

    @GetMapping("/s/about.html")
    public String getAbout(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "about";
    }

    @GetMapping("/s/shop.html")
    public String getShop(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "shop";
    }

    @GetMapping("/s/contact.html")
    public String getContact(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "contact";
    }

    @GetMapping("/logallout")
    public String getLogAllOut(HttpServletRequest request, HttpServletResponse response) {
        try {
            com.geetopod.clients.SSOClient client = com.geetopod.clients.Clients.instance().getClient();
            PutSSOTokenUrlRequest putSSOTokenUrlRequest = new PutSSOTokenUrlRequest();
            putSSOTokenUrlRequest.ssoToken = "";
            putSSOTokenUrlRequest.company = WebApp.instance().appConfig().ssoCompany;
            putSSOTokenUrlRequest.redirectUrl = WebApp.instance().appConfig().webUrl + "/postlogout";
            PutSSOTokenUrlResponse putSSOTokenUrlResponse = com.geetopod.clients.Services.instance().putSSOTokenUrl(putSSOTokenUrlRequest);
            String goUrl = "/";
            if (!putSSOTokenUrlResponse.isError) {
                goUrl = putSSOTokenUrlResponse.url;
            }
            try {
                client.logout(false);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
                HttpSession session = attr.getRequest().getSession();
                session.invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                request.logout();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return "redirect:" + goUrl;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @GetMapping("/prelogin")
    public String getPreLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            com.geetopod.clients.SSOClient client = com.geetopod.clients.Clients.instance().getClient();
            if (request.getParameterMap().containsKey("logout")) {
                String goUrl = "/";
                try {
                    client.logout(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    request.logout();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "redirect:" + goUrl;
            }
            if (client.online()) {
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @GetMapping("/register")
    public String getRegister(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        return "register";
    }

    @PostMapping("/register")
    public String postRegister(Model model, HttpSession session,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name = "passwordRetype") String passwordRetype,
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
        String message = "";
        model.addAttribute("username", username);
        model.addAttribute("email", email);
        model.addAttribute("phone", phone);
        model.addAttribute("firstName", firstName);
        model.addAttribute("lastName", lastName);
        model.addAttribute("middleName", middleName);
        model.addAttribute("mailingAddress", mailingAddress);
        model.addAttribute("billingAddress", billingAddress);
        model.addAttribute("country", country);
        model.addAttribute("state", state);
        model.addAttribute("postalCode", postalCode);
        try {
            RegisterRequest request = new RegisterRequest();
            request.company = WebApp.instance().appConfig().ssoCompany;
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
            request.password = password;
            RegisterResponse response = com.geetopod.clients.Services.instance().register(request);
            if (response.isError) {
                message = response.errorMessage;
            } else {
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("message", message);
        return "register";
    }

    @GetMapping("/login")
    public String getLogin(Model model, HttpSession session) {
        TemplateTool.instance().fillCommonModel(model, session);
        try {
            if (com.geetopod.clients.Clients.instance().getClient().online()) {
                return "redirect:/";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("username", "");
        model.addAttribute("password", "");
        model.addAttribute("message", "");
        return "login";
    }

    @GetMapping("/postlogin")
    public String getPostLogin(HttpSession session, @RequestParam(name = "sso_token") String ssoToken) {
        return "redirect:/u/profile";
    }

    @GetMapping("/postloginsso")
    public String getPostLoginSSO(HttpServletRequest request, HttpServletResponse response, HttpSession session, @RequestParam(name = "sso_token") String ssoToken) {
        try {
            if (ssoToken.length() == 0) {
                return "redirect:/";
            }

            com.geetopod.clients.SSOClient client = com.geetopod.clients.Clients.instance().getClient();

            ValidateSSOTokenRequest requestV = new ValidateSSOTokenRequest();
            requestV.company = WebApp.instance().appConfig().ssoCompany;
            requestV.ssoToken = ssoToken;
            ValidateSSOTokenResponse responseV = client.loginSSO(requestV);
            request.login("__________", ssoToken);
            return "redirect:/u/profile";
        } catch (Exception e) {
            return "redirect:/";
        }
    }

    @PostMapping("/login")
    public String postLogin(Model model, HttpSession session, HttpServletRequest request,
                            @RequestParam(name = "ssoInUse") boolean ssoInUse,
                            @RequestParam(name = "username") String username, @RequestParam(name = "password") String password) {
        TemplateTool.instance().fillCommonModel(model, session);
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        String message = "";

        try {
            if (ssoInUse) {
                GetSSOTokenUrlRequest getSSOTokenUrlRequest = new GetSSOTokenUrlRequest();
                getSSOTokenUrlRequest.redirectUrl = WebApp.instance().appConfig().webUrl + "/postloginsso";
                getSSOTokenUrlRequest.company = WebApp.instance().appConfig().ssoCompany;
                GetSSOTokenUrlResponse getSSOTokenUrlResponse = com.geetopod.clients.Services.instance().getSSOTokenUrl(getSSOTokenUrlRequest);
                if (!getSSOTokenUrlResponse.isError) {
                    String goUrl = getSSOTokenUrlResponse.url;
                    return "redirect:" + goUrl;
                } else {
                    message = getSSOTokenUrlResponse.errorMessage;
                }
            } else {
                request.login(username, password);

                com.geetopod.clients.SSOClient client = com.geetopod.clients.Clients.instance().getClient();
                String ssoToken = client.ssoToken();

                if (ssoToken != null && ssoToken.trim().length() > 0) {
                    PutSSOTokenUrlRequest putSSOTokenUrlRequest = new PutSSOTokenUrlRequest();
                    putSSOTokenUrlRequest.ssoToken = ssoToken;
                    putSSOTokenUrlRequest.company = WebApp.instance().appConfig().ssoCompany;
                    putSSOTokenUrlRequest.redirectUrl = WebApp.instance().appConfig().webUrl + "/postlogin";
                    PutSSOTokenUrlResponse putSSOTokenUrlResponse = com.geetopod.clients.Services.instance().putSSOTokenUrl(putSSOTokenUrlRequest);
                    String goUrl = "/";
                    if (!putSSOTokenUrlResponse.isError) {
                        goUrl = putSSOTokenUrlResponse.url;
                    }
                    return "redirect:" + goUrl;
                } else {
                    return "redirect:/u/profile";
                }
            }
        } catch (Exception e) {
            message = e.getMessage();
        }

        model.addAttribute("message", message);
        return "login";
    }

    @GetMapping("/postlogout")
    public String getPostLogout(HttpServletResponse response, HttpSession session) {
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String getLogout(HttpServletRequest request, HttpSession session, HttpServletResponse response) {
        return "redirect:/";
    }
}
