package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.RegisterActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.register.RegisterActivity;

@Module(
  subcomponents = AndroindBindingModule_BindRegisterActivity.RegisterActivitySubcomponent.class
)
public abstract class AndroindBindingModule_BindRegisterActivity {
  private AndroindBindingModule_BindRegisterActivity() {}

  @Binds
  @IntoMap
  @ClassKey(RegisterActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      RegisterActivitySubcomponent.Factory builder);

  @Subcomponent(modules = RegisterActivityModule.class)
  @PerActivity
  public interface RegisterActivitySubcomponent extends AndroidInjector<RegisterActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<RegisterActivity> {}
  }
}
