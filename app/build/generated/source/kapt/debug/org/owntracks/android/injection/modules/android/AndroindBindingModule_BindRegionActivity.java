package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.RegionActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.region.RegionActivity;

@Module(subcomponents = AndroindBindingModule_BindRegionActivity.RegionActivitySubcomponent.class)
public abstract class AndroindBindingModule_BindRegionActivity {
  private AndroindBindingModule_BindRegionActivity() {}

  @Binds
  @IntoMap
  @ClassKey(RegionActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RegionActivitySubcomponent.Factory builder);

  @Subcomponent(modules = RegionActivityModule.class)
  @PerActivity
  public interface RegionActivitySubcomponent extends AndroidInjector<RegionActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RegionActivity> {}
  }
}
