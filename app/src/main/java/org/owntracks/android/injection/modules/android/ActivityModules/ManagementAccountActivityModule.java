package org.owntracks.android.injection.modules.android.ActivityModules;

import androidx.appcompat.app.AppCompatActivity;

import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.managementaccount.ManagementAccountActivity;
import org.owntracks.android.ui.managementaccount.ManagementAccountMvvm;
import org.owntracks.android.ui.managementaccount.ManagementAccountViewModel;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseActivityModule.class)
public abstract class ManagementAccountActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity bindActivity(ManagementAccountActivity a);

    @Binds abstract ManagementAccountMvvm.ViewModel bindViewModel(ManagementAccountViewModel viewModel);
}
