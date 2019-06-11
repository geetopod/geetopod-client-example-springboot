package com.geetopod.models;

public class UpdateSmsProviderRequest extends AuthorizedRequest {
    public boolean enabled = false;
    public String twilioAccountId = "";
    public String twilioAuthCode = "";
    public String twilioFromPhone = "";
    public int smsQuota = 0;
    public int smsUsed = 0;
    public long smsQuotaResetTime = 0;
}
