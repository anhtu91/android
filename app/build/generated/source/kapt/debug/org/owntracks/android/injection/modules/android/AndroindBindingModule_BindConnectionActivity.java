package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.ConnectionActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.preferences.connection.ConnectionActivity;

@Module(
  subcomponents = AndroindBindingModule_BindConnectionActivity.ConnectionActivitySubcomponent.class
)
public abstract class AndroindBindingModule_BindConnectionActivity {
  private AndroindBindingModule_BindConnectionActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ConnectionActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ConnectionActivitySubcomponent.Factory builder);

  @Subcomponent(modules = ConnectionActivityModule.class)
  @PerActivity
  public interface ConnectionActivitySubcomponent extends AndroidInjector<ConnectionActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ConnectionActivity> {}
  }
}
