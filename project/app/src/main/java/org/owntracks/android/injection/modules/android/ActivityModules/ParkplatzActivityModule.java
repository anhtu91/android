package org.owntracks.android.injection.modules.android.ActivityModules;

import androidx.appcompat.app.AppCompatActivity;

import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.parkplatz.ParkplatzActivity;
import org.owntracks.android.ui.parkplatz.ParkplatzMvvm;
import org.owntracks.android.ui.parkplatz.ParkplatzViewModel;

import dagger.Binds;
import dagger.Module;

@Module(includes = BaseActivityModule.class)
public abstract class  ParkplatzActivityModule {
    @Binds
    @PerActivity
    abstract AppCompatActivity bindActivity(ParkplatzActivity a);

    @Binds abstract ParkplatzMvvm.ViewModel bindViewModel(ParkplatzViewModel viewModel);
}
