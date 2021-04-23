package org.owntracks.android.ui.lastqrcodes;

import android.content.Context;

import org.owntracks.android.injection.qualifier.AppContext;
import org.owntracks.android.model.LastQRCodesModel;
import org.owntracks.android.support.sqlite.SQLiteForLastQRCodes;
import org.owntracks.android.ui.base.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.Collection;

import timber.log.Timber;

public class LastQRCodesViewModel extends BaseViewModel<LastQRCodesMvvm.View> implements LastQRCodesMvvm.ViewModel<LastQRCodesMvvm.View>{

    private Context currentContext;

    public LastQRCodesViewModel(@AppContext Context context){
        this.currentContext = context;
    }

    @Override
    public Collection<LastQRCodesModel> getLastQRCodes() {

        SQLiteForLastQRCodes sqLiteForLastQRCodes = new SQLiteForLastQRCodes(currentContext);

        ArrayList<LastQRCodesModel> lastQRCodeCollection = new ArrayList<LastQRCodesModel>();

        lastQRCodeCollection = sqLiteForLastQRCodes.getAllLastQRCodes();

        Timber.v("Get collection of last qr code "+lastQRCodeCollection.toString());

        return lastQRCodeCollection;
    }

    @Override
    public void onLastQRCodesClick(LastQRCodesModel object) {

    }
}
