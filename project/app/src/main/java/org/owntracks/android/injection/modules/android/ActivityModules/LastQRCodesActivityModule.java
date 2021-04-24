package org.owntracks.android.injection.modules.android.ActivityModules;

import androidx.appcompat.app.AppCompatActivity;

import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.lastqrcodes.LastQRCodesActivity;
import org.owntracks.android.ui.lastqrcodes.LastQRCodesMvvm;
import org.owntracks.android.ui.lastqrcodes.LastQRCodesViewModel;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseActivityModule.class)
public abstract class LastQRCodesActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity bindActivity(LastQRCodesActivity a);

    @Binds abstract LastQRCodesMvvm.ViewModel bindViewModel(LastQRCodesViewModel viewModel);
}
