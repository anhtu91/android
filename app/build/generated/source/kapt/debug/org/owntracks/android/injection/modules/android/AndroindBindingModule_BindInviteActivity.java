package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.InviteActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.invite.InviteActivity;

@Module(subcomponents = AndroindBindingModule_BindInviteActivity.InviteActivitySubcomponent.class)
public abstract class AndroindBindingModule_BindInviteActivity {
  private AndroindBindingModule_BindInviteActivity() {}

  @Binds
  @IntoMap
  @ClassKey(InviteActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      InviteActivitySubcomponent.Factory builder);

  @Subcomponent(modules = InviteActivityModule.class)
  @PerActivity
  public interface InviteActivitySubcomponent extends AndroidInjector<InviteActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<InviteActivity> {}
  }
}
