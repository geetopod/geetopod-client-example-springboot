package com.geetopod.models;

public class UpdateSystemSmtpProviderRequest extends AuthorizedRequest {
    public String smtpCompany = "";
    public boolean enabled = false;
    public String smtpHost = "";
    public int smtpPort = 0;
    public String smtpUsername = "";
    public String smtpPassword = "";
    public String smtpFromEmail = "";
    public String smtpFromName = "";
    public int smtpQuota = 0;
    public int smtpUsed = 0;
    public long smtpQuotaResetTime = 0;
}
