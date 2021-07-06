package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.WelcomeActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.welcome.WelcomeActivity;

@Module(subcomponents = AndroindBindingModule_BindWelcomeActivity.WelcomeActivitySubcomponent.class)
public abstract class AndroindBindingModule_BindWelcomeActivity {
  private AndroindBindingModule_BindWelcomeActivity() {}

  @Binds
  @IntoMap
  @ClassKey(WelcomeActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      WelcomeActivitySubcomponent.Factory builder);

  @Subcomponent(modules = WelcomeActivityModule.class)
  @PerActivity
  public interface WelcomeActivitySubcomponent extends AndroidInjector<WelcomeActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<WelcomeActivity> {}
  }
}
