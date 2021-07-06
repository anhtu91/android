package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.PreferencesActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.preferences.PreferencesActivity;

@Module(
  subcomponents =
      AndroindBindingModule_BindPreferencesActivity.PreferencesActivitySubcomponent.class
)
public abstract class AndroindBindingModule_BindPreferencesActivity {
  private AndroindBindingModule_BindPreferencesActivity() {}

  @Binds
  @IntoMap
  @ClassKey(PreferencesActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      PreferencesActivitySubcomponent.Factory builder);

  @Subcomponent(modules = PreferencesActivityModule.class)
  @PerActivity
  public interface PreferencesActivitySubcomponent extends AndroidInjector<PreferencesActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<PreferencesActivity> {}
  }
}
