package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.LastQRCodesActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.history.LastQRCodesActivity;

@Module(
  subcomponents =
      AndroindBindingModule_BindLastQRCodesActivity.LastQRCodesActivitySubcomponent.class
)
public abstract class AndroindBindingModule_BindLastQRCodesActivity {
  private AndroindBindingModule_BindLastQRCodesActivity() {}

  @Binds
  @IntoMap
  @ClassKey(LastQRCodesActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      LastQRCodesActivitySubcomponent.Factory builder);

  @Subcomponent(modules = LastQRCodesActivityModule.class)
  @PerActivity
  public interface LastQRCodesActivitySubcomponent extends AndroidInjector<LastQRCodesActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<LastQRCodesActivity> {}
  }
}
