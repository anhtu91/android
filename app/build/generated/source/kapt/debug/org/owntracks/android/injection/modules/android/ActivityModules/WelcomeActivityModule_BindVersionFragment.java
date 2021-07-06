package org.owntracks.android.injection.modules.android.ActivityModules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.FragmentModules.VersionFragmentModule;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.welcome.version.VersionFragment;

@Module(subcomponents = WelcomeActivityModule_BindVersionFragment.VersionFragmentSubcomponent.class)
public abstract class WelcomeActivityModule_BindVersionFragment {
  private WelcomeActivityModule_BindVersionFragment() {}

  @Binds
  @IntoMap
  @ClassKey(VersionFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      VersionFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = VersionFragmentModule.class)
  @PerFragment
  public interface VersionFragmentSubcomponent extends AndroidInjector<VersionFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<VersionFragment> {}
  }
}
