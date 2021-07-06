package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.RegionsActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.regions.RegionsActivity;

@Module(subcomponents = AndroindBindingModule_BindRegionsActivity.RegionsActivitySubcomponent.class)
public abstract class AndroindBindingModule_BindRegionsActivity {
  private AndroindBindingModule_BindRegionsActivity() {}

  @Binds
  @IntoMap
  @ClassKey(RegionsActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RegionsActivitySubcomponent.Factory builder);

  @Subcomponent(modules = RegionsActivityModule.class)
  @PerActivity
  public interface RegionsActivitySubcomponent extends AndroidInjector<RegionsActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RegionsActivity> {}
  }
}
