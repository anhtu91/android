package org.owntracks.android.injection.modules.android.ActivityModules;

import androidx.appcompat.app.AppCompatActivity;

import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.invite.InviteActivity;
import org.owntracks.android.ui.invite.InviteMvvm;
import org.owntracks.android.ui.invite.InviteViewModel;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseActivityModule.class)
public abstract class InviteActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity bindActivity(InviteActivity a);

    @Binds abstract InviteMvvm.ViewModel bindViewModel(InviteViewModel viewModel);
}
