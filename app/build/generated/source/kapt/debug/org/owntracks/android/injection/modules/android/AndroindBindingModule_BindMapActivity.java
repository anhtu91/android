package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.MapActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.map.MapActivity;

@Module(subcomponents = AndroindBindingModule_BindMapActivity.MapActivitySubcomponent.class)
public abstract class AndroindBindingModule_BindMapActivity {
  private AndroindBindingModule_BindMapActivity() {}

  @Binds
  @IntoMap
  @ClassKey(MapActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      MapActivitySubcomponent.Factory builder);

  @Subcomponent(modules = MapActivityModule.class)
  @PerActivity
  public interface MapActivitySubcomponent extends AndroidInjector<MapActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<MapActivity> {}
  }
}
