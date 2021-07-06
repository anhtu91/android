package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.ManagementAccountActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.managementaccount.ManagementAccountActivity;

@Module(
  subcomponents =
      AndroindBindingModule_BindManagementAcouuntActivity.ManagementAccountActivitySubcomponent
          .class
)
public abstract class AndroindBindingModule_BindManagementAcouuntActivity {
  private AndroindBindingModule_BindManagementAcouuntActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ManagementAccountActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ManagementAccountActivitySubcomponent.Factory builder);

  @Subcomponent(modules = ManagementAccountActivityModule.class)
  @PerActivity
  public interface ManagementAccountActivitySubcomponent
      extends AndroidInjector<ManagementAccountActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ManagementAccountActivity> {}
  }
}
