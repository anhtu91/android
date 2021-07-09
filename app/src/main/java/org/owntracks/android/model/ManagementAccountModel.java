package org.owntracks.android.model;

import androidx.databinding.BaseObservable;

public class ManagementAccountModel extends BaseObservable implements Comparable<ManagementAccountModel> {
    private String keyID;
    private String fieldName;

    public ManagementAccountModel(String keyID, String fieldName) {
        this.keyID = keyID;
        this.fieldName = fieldName;
    }

    public String getKeyID() {
        return "KeyID: "+ keyID;
    }

    public void setKeyID(String keyID) {
        this.keyID = keyID;
    }

    public String getFieldName() {
        return "FieldName: "+ fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    @Override
    public int compareTo(ManagementAccountModel o) {
        return 0;
    }
}
