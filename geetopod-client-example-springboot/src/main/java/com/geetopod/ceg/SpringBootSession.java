package com.geetopod.ceg;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

public class SpringBootSession extends com.geetopod.clients.SSOSession {
    public String getValue(String key) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        Object valueO = session.getAttribute(key);
        if (valueO == null) {
            return "";
        } else {
            return valueO + "";
        }
    }

    public void setValue(String key, String value) {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpSession session = attr.getRequest().getSession();
        session.setAttribute(key, value);
    }
}
