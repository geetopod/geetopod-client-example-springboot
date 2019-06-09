package com.geetopod.clients;

import com.geetopod.models.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SSOClient {
    private final int TIMEOUT = 1000 * 60 * 60;

    private long _lastTime = 0;
    private long _expiresIn = 0;
    private String _token = "";
    private String _refreshToken = "";
    private long _refreshExpiresIn = 0;
    private boolean _online = false;
    private boolean _waitingOTP = false;
    private String _username = "";
    private String _password = "";
    private List<String> _accessResources = new ArrayList<String>();
    private boolean _ssoInUse = false;
    private String _ssoToken = "";
    private String _clientId = "";
    private String _company = "";

    public String clientId() {
        return _clientId;
    }

    public boolean ssoInUse() {
        return _ssoInUse;
    }

    public String ssoToken() {
        return _ssoToken;
    }

    public SSOClient(String clientId) {
        if (clientId != null) {
            _clientId = clientId;
        } else {
            if (Services.instance().session().getValue("SSOPOD_CLIENT_ID").length() == 0) {
                _clientId = Services.instance().session().getValue("SSOPOD_CLIENT_ID");
            } else {
                _clientId = UUID.randomUUID().toString().replaceAll("-", "");
            }
        }
        if (Services.instance().session().getValue("SSOPOD_CLIENT_ID").length() > 0 && Services.instance().session().getValue("SSOPOD_CLIENT_ID").equals(_clientId)) {
            if (Services.instance().session().getValue("SSOPOD_" + _clientId + "_online").length() > 0) {
                _lastTime = Long.parseLong(Services.instance().session().getValue("SSOPOD_" + _clientId + "_lastTime"));
                _expiresIn = Long.parseLong(Services.instance().session().getValue("SSOPOD_" + _clientId + "_expiresIn"));
                _token = Services.instance().session().getValue("SSOPOD_" + _clientId + "_token");
                _refreshToken = Services.instance().session().getValue("SSOPOD_" + _clientId + "_refreshToken");
                _refreshExpiresIn = Long.parseLong(Services.instance().session().getValue("SSOPOD_" + _clientId + "_refreshExpiresIn"));
                _online = "true".equalsIgnoreCase(Services.instance().session().getValue("SSOPOD_" + _clientId + "_online"));
                _waitingOTP = "true".equalsIgnoreCase(Services.instance().session().getValue("SSOPOD_" + _clientId + "_waitingOTP"));
                _username = Services.instance().session().getValue("SSOPOD_" + _clientId + "_username");
                _password = Services.instance().session().getValue("SSOPOD_" + _clientId + "_password");
                _company = Services.instance().session().getValue("SSOPOD_" + _clientId + "_company");
                _accessResources = new ArrayList<>();
                String[] fields = Services.instance().session().getValue("SSOPOD_" + _clientId + "_accessResources").split(";");
                for (int i = 0; i < fields.length; i++) {
                    String fd = fields[i].trim();
                    if (fd.length() == 0) continue;
                    _accessResources.add(fd);
                }
                _ssoInUse = "true".equalsIgnoreCase(Services.instance().session().getValue("SSOPOD_" + _clientId + "_ssoInUse"));
                _ssoToken = Services.instance().session().getValue("SSOPOD_" + _clientId + "_ssoToken");
            } else {
                Services.instance().session().setValue("SSOPOD_CLIENT_ID", _clientId);
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_lastTime", _lastTime + "");
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_expiresIn", _expiresIn + "");
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_token", _token);
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshToken", _refreshToken);
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshExpiresIn", _refreshExpiresIn + "");
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_online", _online + "");
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_waitingOTP", _waitingOTP + "");
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_username", _username);
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_password", _password);
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoInUse", _ssoInUse + "");
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoToken", _ssoToken + "");
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_accessResources", String.join(";", _accessResources));
                Services.instance().session().setValue("SSOPOD_" + _clientId + "_company", _company);
            }
        } else {
            Services.instance().session().setValue("SSOPOD_CLIENT_ID", _clientId);
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_lastTime", _lastTime + "");
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_expiresIn", _expiresIn + "");
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_token", _token);
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshToken", _refreshToken);
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshExpiresIn", _refreshExpiresIn + "");
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_online", _online + "");
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_waitingOTP", _waitingOTP + "");
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_username", _username);
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_password", _password);
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoInUse", _ssoInUse + "");
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoToken", _ssoToken + "");
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_accessResources", String.join(";", _accessResources));
            Services.instance().session().setValue("SSOPOD_" + _clientId + "_company", _company);
        }
    }

    public ValidateSSOTokenResponse loginSSO(ValidateSSOTokenRequest request) throws Exception {
        _ssoInUse = true;
        _company = request.company;
        ValidateSSOTokenResponse response = Services.instance().validateSSOToken(request);
        if (response.isError || !response.active) {
            _waitingOTP = false;
            _online = false;
            _lastTime = 0;
            _expiresIn = 0;
            _token = "";
            _refreshToken = "";
            _refreshExpiresIn = 0;
            _ssoToken = "";
            _ssoInUse = false;
            _company = "";

            _username = "";
            _password = "";
            _accessResources = new ArrayList<String>();
        } else {
            _waitingOTP = false;
            _online = true;
            _lastTime = 0;
            _expiresIn = 0;
            _token = "";
            _refreshToken = "";
            _refreshExpiresIn = 0;
            _ssoToken = request.ssoToken;

            _username = "";
            _password = "";
            _accessResources = new ArrayList<String>();
        }
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_lastTime", _lastTime + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_expiresIn", _expiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_token", _token);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshToken", _refreshToken);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshExpiresIn", _refreshExpiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_online", _online + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_waitingOTP", _waitingOTP + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_username", _username);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_password", _password);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoInUse", _ssoInUse + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoToken", _ssoToken + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_accessResources", String.join(";", _accessResources));
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_company", _company);
        return response;
    }

    public SocialLoginProcessResponse loginSocial(SocialLoginProcessRequest request) throws Exception {
        _ssoInUse = false;
        _company = request.company;
        SocialLoginProcessResponse response = Services.instance().socialLoginProcess(request);
        if (response.isError) {
            _waitingOTP = false;
            _online = false;
            _lastTime = 0;
            _expiresIn = 0;
            _token = "";
            _refreshToken = "";
            _refreshExpiresIn = 0;
            _ssoToken = "";
            _ssoInUse = false;
            _company = "";

            _username = "";
            _password = "";
            _accessResources = new ArrayList<String>();
        } else {
            _waitingOTP = false;
            _online = true;
            _lastTime = System.currentTimeMillis();
            _expiresIn = response.expiresIn;
            _token = response.token;
            _refreshToken = response.refreshToken;
            _refreshExpiresIn = response.refreshExpiresIn;
            _ssoToken = response.ssoToken;

            ValidateTokenRequest requestV = new ValidateTokenRequest();
            requestV.company = request.company;
            requestV.token = response.token;
            ValidateTokenResponse responseV = Services.instance().validateToken(requestV);

            _username = responseV.username;
            _password = request.verifiedToken;
            _accessResources = new ArrayList<>();
            for (int i = 0; i < request.accessResources.size(); i++) {
                _accessResources.add(request.accessResources.get(i));
            }
        }
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_lastTime", _lastTime + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_expiresIn", _expiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_token", _token);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshToken", _refreshToken);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshExpiresIn", _refreshExpiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_online", _online + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_waitingOTP", _waitingOTP + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_username", _username);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_password", _password);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoInUse", _ssoInUse + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoToken", _ssoToken + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_accessResources", String.join(";", _accessResources));
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_company", _company);
        return response;
    }

    public LoginResponse login(LoginRequest request) throws Exception {
        _ssoInUse = false;
        _company = request.company;
        LoginResponse response = Services.instance().login(request);
        if (!response.isError) {
            _lastTime = System.currentTimeMillis();
            _expiresIn = response.expiresIn;
            _token = response.token;
            _refreshToken = response.refreshToken;
            _refreshExpiresIn = response.refreshExpiresIn;
            _online = true;
            _ssoToken = response.ssoToken;

            _username = request.username;
            _password = request.password;
            _accessResources = new ArrayList<String>();
            for (int i = 0; i < request.accessResources.size(); i++) {
                _accessResources.add(request.accessResources.get(i));
            }
            if (request.hasOTP) {
                _waitingOTP = true;
                _online = false;
            } else {
                _waitingOTP = false;
            }
        } else {
            _waitingOTP = false;
            _online = false;
            _lastTime = 0;
            _expiresIn = 0;
            _token = "";
            _refreshToken = "";
            _refreshExpiresIn = 0;
            _ssoToken = "";
            _company = "";

            _username = "";
            _password = "";
            _accessResources = new ArrayList<String>();
        }
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_lastTime", _lastTime + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_expiresIn", _expiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_token", _token);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshToken", _refreshToken);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshExpiresIn", _refreshExpiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_online", _online + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_waitingOTP", _waitingOTP + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_username", _username);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_password", _password);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoInUse", _ssoInUse + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoToken", _ssoToken + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_accessResources", String.join(";", _accessResources));
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_company", _company);
        return response;
    }

    public LoginOTPResponse loginOTP(LoginOTPRequest request) throws Exception {
        _ssoInUse = false;
        _company = request.company;
        LoginOTPResponse response = Services.instance().loginOTP(request);
        if (!response.isError) {
            _lastTime = System.currentTimeMillis();
            _expiresIn = response.expiresIn;
            _token = response.token;
            _refreshToken = response.refreshToken;
            _refreshExpiresIn = response.refreshExpiresIn;
            _online = true;
            _waitingOTP = false;
            _ssoToken = response.ssoToken;
        } else {
            _waitingOTP = false;
            _online = false;
            _lastTime = 0;
            _expiresIn = 0;
            _token = "";
            _refreshToken = "";
            _refreshExpiresIn = 0;
            _ssoToken = "";
            _company = "";

            _username = "";
            _password = "";
            _accessResources = new ArrayList<String>();
        }
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_lastTime", _lastTime + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_expiresIn", _expiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_token", _token);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshToken", _refreshToken);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshExpiresIn", _refreshExpiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_online", _online + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_waitingOTP", _waitingOTP + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_username", _username);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_password", _password);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoInUse", _ssoInUse + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoToken", _ssoToken + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_accessResources", String.join(";", _accessResources));
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_company", _company);
        return response;
    }

    public String company() {
        return _company;
    }

    public boolean waitingOTP() {
        return _waitingOTP;
    }

    public boolean online() {
        try {
            if (_ssoInUse) {
                ValidateSSOTokenRequest request = new ValidateSSOTokenRequest();
                request.company = _company;
                request.ssoToken = _ssoToken;
                loginSSO(request);
            } else {
                token();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return _online;
    }

    public LogoutResponse logout(boolean allClients) throws Exception {
        LogoutResponse response = new LogoutResponse();
        if (allClients) {
            LogoutRequest request = new LogoutRequest();
            request.company = _company;
            request.token = _token;
            response = Services.instance().logout(request);
            if (response.isError) {
                return response;
            }
        }
        _ssoInUse = false;
        _ssoToken = "";
        _online = false;
        _lastTime = 0;
        _expiresIn = 0;
        _token = "";
        _refreshToken = "";
        _refreshExpiresIn = 0;
        _company = "";
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_lastTime", _lastTime + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_expiresIn", _expiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_token", _token);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshToken", _refreshToken);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshExpiresIn", _refreshExpiresIn + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_online", _online + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_waitingOTP", _waitingOTP + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_username", _username);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_password", _password);
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoInUse", _ssoInUse + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoToken", _ssoToken + "");
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_accessResources", String.join(";", _accessResources));
        Services.instance().session().setValue("SSOPOD_" + _clientId + "_company", _company);
        return response;
    }

    public String token() throws Exception {
        if (_ssoInUse) {
            throw new Exception("Single sign on is in use!");
        }
        if (_token == null || _token.trim().length() == 0) {
            throw new Exception("Client is not online!");
        }
        if (_lastTime == 0 || _lastTime < System.currentTimeMillis() - _expiresIn * 1000) {
            if (_lastTime == 0 || (_lastTime < System.currentTimeMillis() - (_refreshExpiresIn - 5) * 1000)) {
                LoginRequest loginRequest = new LoginRequest();
                loginRequest.company = _company;
                loginRequest.username = _username;
                loginRequest.password = _password;
                for (int i = 0; i < _accessResources.size(); i++) {
                    loginRequest.accessResources.add(_accessResources.get(i));
                }
                LoginResponse loginResponse = this.login(loginRequest);
                if (loginResponse.isError) {
                    throw new Exception("[" + loginResponse.errorCode + "] " + loginResponse.errorMessage);
                } else {
                    return _token;
                }
            } else {
                RefreshTokenRequest refreshTokenRequest = new RefreshTokenRequest();
                refreshTokenRequest.company = _company;
                refreshTokenRequest.refreshToken = _refreshToken;
                RefreshTokenResponse refreshTokenResponse = Services.instance().refreshToken(refreshTokenRequest);
                if (refreshTokenResponse.isError) {
                    throw new Exception("[" + refreshTokenResponse.errorCode + "] " + refreshTokenResponse.errorMessage);
                } else {
                    _lastTime = System.currentTimeMillis();
                    _expiresIn = refreshTokenResponse.expiresIn;
                    _token = refreshTokenResponse.token;
                    _refreshToken = refreshTokenResponse.refreshToken;
                    _refreshExpiresIn = refreshTokenResponse.refreshExpiresIn;
                    _online = true;
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_lastTime", _lastTime + "");
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_expiresIn", _expiresIn + "");
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_token", _token);
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshToken", _refreshToken);
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_refreshExpiresIn", _refreshExpiresIn + "");
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_online", _online + "");
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_waitingOTP", _waitingOTP + "");
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_username", _username);
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_password", _password);
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoInUse", _ssoInUse + "");
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_ssoToken", _ssoToken + "");
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_accessResources", String.join(";", _accessResources));
                    Services.instance().session().setValue("SSOPOD_" + _clientId + "_company", _company);
                    return _token;
                }
            }
        } else {
            return _token;
        }
    }
}
