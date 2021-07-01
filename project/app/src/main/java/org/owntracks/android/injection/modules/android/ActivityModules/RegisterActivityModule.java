package org.owntracks.android.injection.modules.android.ActivityModules;

import androidx.appcompat.app.AppCompatActivity;

import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.register.RegisterActivity;
import org.owntracks.android.ui.register.RegisterMvvm;
import org.owntracks.android.ui.register.RegisterViewModel;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseActivityModule.class)
public abstract class RegisterActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity bindActivity(RegisterActivity a);

    @Binds abstract RegisterMvvm.ViewModel bindViewModel(RegisterViewModel viewModel);
}
