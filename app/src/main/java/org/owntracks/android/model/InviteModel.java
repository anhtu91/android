package org.owntracks.android.model;

public class InviteModel {
    private String keyIDInvite;
    private String fieldNameInvite;
    private String dateInvite;
    private String timeInvite;
    private String emailInvite;

    public InviteModel(String keyIDInvite, String fieldNameInvite, String dateInvite, String timeInvite, String emailInvite) {
        this.keyIDInvite = keyIDInvite;
        this.fieldNameInvite = fieldNameInvite;
        this.dateInvite = dateInvite;
        this.timeInvite = timeInvite;
        this.emailInvite = emailInvite;
    }

    public String getKeyIDInvite() {
        return keyIDInvite;
    }

    public void setKeyIDInvite(String keyIDInvite) {
        this.keyIDInvite = keyIDInvite;
    }

    public String getFieldNameInvite() {
        return fieldNameInvite;
    }

    public void setFieldNameInvite(String fieldNameInvite) {
        this.fieldNameInvite = fieldNameInvite;
    }

    public String getDateInvite() {
        return dateInvite;
    }

    public void setDateInvite(String dateInvite) {
        this.dateInvite = dateInvite;
    }

    public String getTimeInvite() {
        return timeInvite;
    }

    public void setTimeInvite(String timeInvite) {
        this.timeInvite = timeInvite;
    }

    public String getEmailInvite() {
        return emailInvite;
    }

    public void setEmailInvite(String emailInvite) {
        this.emailInvite = emailInvite;
    }
}
