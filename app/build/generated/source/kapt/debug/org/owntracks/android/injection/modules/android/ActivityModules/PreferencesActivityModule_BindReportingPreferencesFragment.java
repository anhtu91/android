package org.owntracks.android.injection.modules.android.ActivityModules;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerFragment;
import org.owntracks.android.ui.preferences.ReportingFragment;

@Module(
  subcomponents =
      PreferencesActivityModule_BindReportingPreferencesFragment.ReportingFragmentSubcomponent.class
)
public abstract class PreferencesActivityModule_BindReportingPreferencesFragment {
  private PreferencesActivityModule_BindReportingPreferencesFragment() {}

  @Binds
  @IntoMap
  @ClassKey(ReportingFragment.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ReportingFragmentSubcomponent.Factory builder);

  @Subcomponent(modules = ReportingFragment.FragmentModule.class)
  @PerFragment
  public interface ReportingFragmentSubcomponent extends AndroidInjector<ReportingFragment> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ReportingFragment> {}
  }
}
