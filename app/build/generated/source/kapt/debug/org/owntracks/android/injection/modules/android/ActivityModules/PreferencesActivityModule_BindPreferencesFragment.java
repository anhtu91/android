package org.owntracks.android.injection.modules.android.ActivityModules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.preferences.PreferencesFragment;

@Module(
  subcomponents =
      PreferencesActivityModule_BindPreferencesFragment.PreferencesFragmentSubcomponent.class
)
public abstract class PreferencesActivityModule_BindPreferencesFragment {
  private PreferencesActivityModule_BindPreferencesFragment() {}

  @Binds
  @IntoMap
  @ClassKey(PreferencesFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      PreferencesFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = PreferencesFragment.FragmentModule.class)
  @PerFragment
  public interface PreferencesFragmentSubcomponent extends AndroidInjector<PreferencesFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<PreferencesFragment> {}
  }
}
