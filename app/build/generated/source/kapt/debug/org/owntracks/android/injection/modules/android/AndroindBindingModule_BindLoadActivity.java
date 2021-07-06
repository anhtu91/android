package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.LoadActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.preferences.load.LoadActivity;

@Module(subcomponents = AndroindBindingModule_BindLoadActivity.LoadActivitySubcomponent.class)
public abstract class AndroindBindingModule_BindLoadActivity {
  private AndroindBindingModule_BindLoadActivity() {}

  @Binds
  @IntoMap
  @ClassKey(LoadActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LoadActivitySubcomponent.Factory builder);

  @Subcomponent(modules = LoadActivityModule.class)
  @PerActivity
  public interface LoadActivitySubcomponent extends AndroidInjector<LoadActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LoadActivity> {}
  }
}
