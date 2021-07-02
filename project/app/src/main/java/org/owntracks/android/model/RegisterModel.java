package org.owntracks.android.model;

public class RegisterModel {
    private String host;
    private String port;
    private String username;
    private String password;
    private String rePassword;
    private String email;
    private String certificatePassword;

    public RegisterModel(String host, String port, String username, String password, String rePassword, String email, String certificatePassword) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.rePassword = rePassword;
        this.email = email;
        this.certificatePassword = certificatePassword;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCertificatePassword() {
        return certificatePassword;
    }

    public void setCertificatePassword(String certificatePassword) {
        this.certificatePassword = certificatePassword;
    }
}
