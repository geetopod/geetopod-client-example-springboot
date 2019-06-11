package com.geetopod.clients;

import com.google.gson.Gson;
import com.geetopod.models.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

public class Services {
    private final int TIMEOUT = 1000 * 60 * 60;

    private static Services __instance;
    protected Gson _gson = new Gson();
    protected String _gatewayUrl = "http://gw.geetopod.com";
    protected boolean _isDebug = false;
    protected SSOSession _session = new SSOSession();

    public SSOSession session() {
        return _session;
    }

    public Services session(SSOSession session) {
        _session = session;
        return this;
    }

    public boolean isDebug() {
        return _isDebug;
    }

    public Services isDebug(boolean isDebug) {
        _isDebug = isDebug;
        return this;
    }

    public static Services instance() {
        if (__instance == null) {
            __instance = new Services();
        }
        return __instance;
    }

    public String gatewayUrl() {
        return _gatewayUrl;
    }

    public Services gatewayUrl(String gatewayUrl) {
        int index = gatewayUrl.indexOf("/", "https://".length());
        if (index >= 0) {
            gatewayUrl = gatewayUrl.substring(0, index);
        }
        _gatewayUrl = gatewayUrl;
        return this;
    }

    public ChangePasswordResponse changePassword(ChangePasswordRequest request) throws Exception {
        String postUrl = getUrl("/api/password/change");
        ChangePasswordResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), ChangePasswordResponse.class);
        return response;
    }

    public ForgotPasswordSendByEmailResponse forgotPasswordSendByEmail(ForgotPasswordSendByEmailRequest request) throws Exception {
        String postUrl = getUrl("/api/forgot/password/sendby/email");
        ForgotPasswordSendByEmailResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), ForgotPasswordSendByEmailResponse.class);
        return response;
    }

    public ForgotPasswordSendByPhoneResponse forgotPasswordSendByPhone(ForgotPasswordSendByPhoneRequest request) throws Exception {
        String postUrl = getUrl("/api/forgot/password/sendby/phone");
        ForgotPasswordSendByPhoneResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), ForgotPasswordSendByPhoneResponse.class);
        return response;
    }

    public GetSSOTokenUrlResponse getSSOTokenUrl(GetSSOTokenUrlRequest request) throws Exception {
        String postUrl = getUrl("/api/ssotoken/url/get");
        GetSSOTokenUrlResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), GetSSOTokenUrlResponse.class);
        return response;
    }

    public PutSSOTokenUrlResponse putSSOTokenUrl(PutSSOTokenUrlRequest request) throws Exception {
        String postUrl = getUrl("/api/ssotoken/url/put");
        PutSSOTokenUrlResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), PutSSOTokenUrlResponse.class);
        return response;
    }


    public SocialLoginUrlResponse socialLoginUrl(SocialLoginUrlRequest request) throws Exception {
        String postUrl = getUrl("/api/social/login/url");
        SocialLoginUrlResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), SocialLoginUrlResponse.class);
        return response;
    }

    public SocialLoginProcessResponse socialLoginProcess(SocialLoginProcessRequest request) throws Exception {
        String postUrl = getUrl("/api/social/login/process");
        SocialLoginProcessResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), SocialLoginProcessResponse.class);
        return response;
    }


    public CreateResourceResponse createResource(CreateResourceRequest request) throws Exception {
        String postUrl = getUrl("/api/resource/create");
        CreateResourceResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), CreateResourceResponse.class);
        return response;
    }

    public ListResourceResponse listResource(ListResourceRequest request) throws Exception {
        String postUrl = getUrl("/api/resource/list");
        ListResourceResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), ListResourceResponse.class);
        return response;
    }

    public DeleteResourceResponse deleteResource(DeleteResourceRequest request) throws Exception {
        String postUrl = getUrl("/api/resource/delete");
        DeleteResourceResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), DeleteResourceResponse.class);
        return response;
    }

    public AssignResourceResponse assignResource(AssignResourceRequest request) throws Exception {
        String postUrl = getUrl("/api/resource/assign");
        AssignResourceResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), AssignResourceResponse.class);
        return response;
    }

    public UnassignResourceResponse unassignResource(AssignResourceRequest request) throws Exception {
        String postUrl = getUrl("/api/resource/unassign");
        UnassignResourceResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), UnassignResourceResponse.class);
        return response;
    }


    public ForgotPasswordResponse forgotPassword(ForgotPasswordRequest request) throws Exception {
        String postUrl = getUrl("/api/forgot/password");
        ForgotPasswordResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), ForgotPasswordResponse.class);
        return response;
    }

    public GetUserInfoResponse getUserInfo(GetUserInfoRequest request) throws Exception {
        String postUrl = getUrl("/api/userinfo");
        GetUserInfoResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), GetUserInfoResponse.class);
        return response;
    }

    public LoginByPhoneResponse loginByPhone(LoginByPhoneRequest request) throws Exception {
        String postUrl = getUrl("/api/login/byphone");
        LoginByPhoneResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), LoginByPhoneResponse.class);
        return response;
    }

    public LoginResponse login(LoginRequest request) throws Exception {
        String postUrl = getUrl("/api/login");
        LoginResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), LoginResponse.class);
        return response;
    }

    public LoginOTPResponse loginOTP(LoginOTPRequest request) throws Exception {
        String postUrl = getUrl("/api/login/otp");
        LoginOTPResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), LoginOTPResponse.class);
        return response;
    }

    public LogoutResponse logout(LogoutRequest request) throws Exception {
        String postUrl = getUrl("/api/logout");
        LogoutResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), LogoutResponse.class);
        return response;
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest request) throws Exception {
        String postUrl = getUrl("/api/token/refresh");
        RefreshTokenResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RefreshTokenResponse.class);
        return response;
    }

    public RegisterByPhoneResponse registerByPhone(RegisterByPhoneRequest request) throws Exception {
        String postUrl = getUrl("/api/register/byphone");
        RegisterByPhoneResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RegisterByPhoneResponse.class);
        return response;
    }

    public RegisterCaptchaResponse registerCaptcha(RegisterCaptchaRequest request) throws Exception {
        String postUrl = getUrl("/api/register/captcha");
        RegisterCaptchaResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RegisterCaptchaResponse.class);
        return response;
    }

    public RegisterResendVerifyEmailResponse registerResendVerifyEmail(RegisterResendVerifyEmailRequest request) throws Exception {
        String postUrl = getUrl("/api/register/resend/verify/email");
        RegisterResendVerifyEmailResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RegisterResendVerifyEmailResponse.class);
        return response;
    }

    public RegisterSendVerifyPhoneResponse registerSendVerifyPhone(RegisterSendVerifyPhoneRequest request) throws Exception {
        String postUrl = getUrl("/api/register/send/verify/phone");
        RegisterSendVerifyPhoneResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RegisterSendVerifyPhoneResponse.class);
        return response;
    }

    public RegisterResponse register(RegisterRequest request) throws Exception {
        String postUrl = getUrl("/api/register");
        RegisterResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RegisterResponse.class);
        return response;
    }

    public RegisterCompanyResponse registerCompany(RegisterCompanyRequest request) throws Exception {
        String postUrl = getUrl("/api/register/company");
        RegisterCompanyResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RegisterCompanyResponse.class);
        return response;
    }

    public RegisterVerifyEmailResponse registerVerifyEmail(RegisterVerifyEmailRequest request) throws Exception {
        String postUrl = getUrl("/api/register/verify/email");
        RegisterVerifyEmailResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RegisterVerifyEmailResponse.class);
        return response;
    }

    public RegisterVerifyPhoneResponse registerVerifyPhone(RegisterVerifyPhoneRequest request) throws Exception {
        String postUrl = getUrl("/api/register/verify/phone");
        RegisterVerifyPhoneResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), RegisterVerifyPhoneResponse.class);
        return response;
    }

    public UpdateUserInfoResponse updateUserInfo(UpdateUserInfoRequest request) throws Exception {
        String postUrl = getUrl("/api/userinfo/update");
        UpdateUserInfoResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), UpdateUserInfoResponse.class);
        return response;
    }

    public ValidateTokenResponse validateToken(ValidateTokenRequest request) throws Exception {
        String postUrl = getUrl("/api/token/validate");
        ValidateTokenResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), ValidateTokenResponse.class);
        return response;
    }

    public ValidateSSOTokenResponse validateSSOToken(ValidateSSOTokenRequest request) throws Exception {
        String postUrl = getUrl("/api/ssotoken/validate");
        ValidateSSOTokenResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), ValidateSSOTokenResponse.class);
        return response;
    }


    public GetSmsProviderResponse getSmsProvider(GetSmsProviderRequest request) throws Exception {
        String postUrl = getUrl("/api/sms/provider");
        GetSmsProviderResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), GetSmsProviderResponse.class);
        return response;
    }

    public GetSystemSmsProviderResponse getSystemSmsProvider(GetSystemSmsProviderRequest request) throws Exception {
        String postUrl = getUrl("/api/sms/provider/system");
        GetSystemSmsProviderResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), GetSystemSmsProviderResponse.class);
        return response;
    }

    public UpdateSmsProviderResponse updateSmsProvider(UpdateSmsProviderRequest request) throws Exception {
        String postUrl = getUrl("/api/sms/provider/update");
        UpdateSmsProviderResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), UpdateSmsProviderResponse.class);
        return response;
    }

    public UpdateSystemSmsProviderResponse updateSystemSmsProvider(UpdateSystemSmsProviderRequest request) throws Exception {
        String postUrl = getUrl("/api/sms/provider/system/update");
        UpdateSystemSmsProviderResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), UpdateSystemSmsProviderResponse.class);
        return response;
    }

    public GetSmtpProviderResponse getSmtpProvider(GetSmtpProviderRequest request) throws Exception {
        String postUrl = getUrl("/api/smtp/provider");
        GetSmtpProviderResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), GetSmtpProviderResponse.class);
        return response;
    }

    public GetSystemSmtpProviderResponse getSystemSmtpProvider(GetSystemSmtpProviderRequest request) throws Exception {
        String postUrl = getUrl("/api/smtp/provider/system");
        GetSystemSmtpProviderResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), GetSystemSmtpProviderResponse.class);
        return response;
    }

    public UpdateSmtpProviderResponse updateSmtpProvider(UpdateSmtpProviderRequest request) throws Exception {
        String postUrl = getUrl("/api/smtp/provider/update");
        UpdateSmtpProviderResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), UpdateSmtpProviderResponse.class);
        return response;
    }

    public UpdateSystemSmtpProviderResponse updateSystemSmtpProvider(UpdateSystemSmtpProviderRequest request) throws Exception {
        String postUrl = getUrl("/api/smtp/provider/system/update");
        UpdateSystemSmtpProviderResponse response = _gson.fromJson(post(postUrl, _gson.toJson(request)), UpdateSystemSmtpProviderResponse.class);
        return response;
    }


    protected String getUrl(String path) {
        return gatewayUrl() + path;
    }

    protected String post(String url, String inputJson) throws Exception {
        String responseJson = Jsoup.connect(url)
                .method(Connection.Method.POST)
                .timeout(TIMEOUT)
                .followRedirects(true)
                .ignoreHttpErrors(true)
                .ignoreContentType(true)
                .header("Content-Type", "application/json")
                .requestBody(inputJson)
                .execute().body();

        if (isDebug()) {
            System.out.println("[" + url + "]: " + responseJson);
        }

        return responseJson;
    }
}