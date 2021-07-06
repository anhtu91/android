package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.ParkplatzActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.parkplatz.ParkplatzActivity;

@Module(
  subcomponents = AndroindBindingModule_BindParkplatzActivity.ParkplatzActivitySubcomponent.class
)
public abstract class AndroindBindingModule_BindParkplatzActivity {
  private AndroindBindingModule_BindParkplatzActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ParkplatzActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ParkplatzActivitySubcomponent.Factory builder);

  @Subcomponent(modules = ParkplatzActivityModule.class)
  @PerActivity
  public interface ParkplatzActivitySubcomponent extends AndroidInjector<ParkplatzActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ParkplatzActivity> {}
  }
}
