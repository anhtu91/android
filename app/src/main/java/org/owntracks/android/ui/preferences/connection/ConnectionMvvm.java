package org.owntracks.android.ui.preferences.connection;

import org.owntracks.android.ui.base.view.MvvmView;
import org.owntracks.android.ui.base.viewmodel.MvvmViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionHostMqttDialogViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionIdentificationViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionParametersViewModel;
import org.owntracks.android.ui.preferences.connection.dialog.ConnectionSecurityViewModel;


public interface ConnectionMvvm {

    interface View extends MvvmView {
        //void showRegisterDialog();
        void showHostDialog();
        void showIdentificationDialog();
        void showSecurityDialog();
        void showParametersDialog();
        void recreateOptionsMenu();

        }

    interface ViewModel<V extends MvvmView> extends MvvmViewModel<V> {
        //void onRegisterClick();
        void onHostClick();
        void onIdentificationClick();
        void onSecurityClick();
        void onParametersClick();

        int getModeId();
        void setModeId(int newModeId);

        ConnectionHostMqttDialogViewModel getHostDialogViewModelMqtt();
        //ConnectionHostHttpDialogViewModel getHostDialogViewModelHttp();
        //ConnectionModeDialogViewModel getModeDialogViewModel();
        ConnectionIdentificationViewModel getIdentificationDialogViewModel();
        ConnectionSecurityViewModel getConnectionSecurityViewModel();
        ConnectionParametersViewModel getConnectionParametersViewModel();
    }
}
