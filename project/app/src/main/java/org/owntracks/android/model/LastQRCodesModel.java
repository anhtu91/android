package org.owntracks.android.model;

import androidx.databinding.BaseObservable;

public class LastQRCodesModel extends BaseObservable {
    private String lastQRCode;

    public String getLastQRCode() {
        return lastQRCode;
    }

    public void setLastQRCode(String lastQRCode) {
        this.lastQRCode = lastQRCode;
    }

    public LastQRCodesModel(String lastQRCode) {
        this.lastQRCode = lastQRCode;
    }
}
