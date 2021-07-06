package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ServiceModules.BackgroundServiceModule;
import org.owntracks.android.injection.scopes.PerService;
import org.owntracks.android.services.BackgroundService;

@Module(
  subcomponents = AndroindBindingModule_BindBackgroundService.BackgroundServiceSubcomponent.class
)
public abstract class AndroindBindingModule_BindBackgroundService {
  private AndroindBindingModule_BindBackgroundService() {}

  @Binds
  @IntoMap
  @ClassKey(BackgroundService.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      BackgroundServiceSubcomponent.Factory builder);

  @Subcomponent(modules = BackgroundServiceModule.class)
  @PerService
  public interface BackgroundServiceSubcomponent extends AndroidInjector<BackgroundService> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<BackgroundService> {}
  }
}
