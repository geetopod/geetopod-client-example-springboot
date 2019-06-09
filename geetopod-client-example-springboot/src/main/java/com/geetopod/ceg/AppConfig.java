package com.geetopod.ceg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Value("${sso.gateway.url}")
    public String ssoGatewayUrl;

    @Value("${sso.company}")
    public String ssoCompany;

    @Value("${isDebug}")
    public boolean isDebug;

    @Value("${web.url}")
    public String webUrl;
}
