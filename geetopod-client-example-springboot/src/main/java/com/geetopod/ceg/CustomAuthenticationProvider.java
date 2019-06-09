package com.geetopod.ceg;

import com.geetopod.models.LoginRequest;
import com.geetopod.models.LoginResponse;
import com.geetopod.models.ValidateSSOTokenRequest;
import com.geetopod.models.ValidateSSOTokenResponse;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        try {
            com.geetopod.clients.Services.instance().gatewayUrl(WebApp.instance().appConfig().ssoGatewayUrl);
            if ("__________".equals(authentication.getName())) {
                ValidateSSOTokenRequest request = new ValidateSSOTokenRequest();
                request.company = WebApp.instance().appConfig().ssoCompany;
                request.ssoToken = authentication.getCredentials().toString();
                ValidateSSOTokenResponse response = com.geetopod.clients.Services.instance().validateSSOToken(request);
                if (response.isError) {
                    throw new AuthenticationServiceException("[" + response.errorCode + "] " + response.errorMessage, null);
                }
                return new UsernamePasswordAuthenticationToken(response.username, authentication.getCredentials().toString(), new ArrayList<>());
            } else {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.company = WebApp.instance().appConfig().ssoCompany;
                loginRequest.hasSSO = true;
                loginRequest.username = authentication.getName();
                loginRequest.password = authentication.getCredentials().toString();
                LoginResponse loginResponse = com.geetopod.clients.Clients.instance().getClient().login(loginRequest);
                if (loginResponse.isError) {
                    throw new AuthenticationServiceException("[" + loginResponse.errorCode + "] " + loginResponse.errorMessage, null);
                }
                return new UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password, new ArrayList<>());
            }
        } catch (AuthenticationException e) {
            throw e;
        } catch (Exception e) {
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
