package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.scopes.PerReceiver;
import org.owntracks.android.support.receiver.StartBackgroundServiceReceiver;

@Module(
  subcomponents =
      AndroindBindingModule_BindBackgroundServiceReceiver.StartBackgroundServiceReceiverSubcomponent
          .class
)
public abstract class AndroindBindingModule_BindBackgroundServiceReceiver {
  private AndroindBindingModule_BindBackgroundServiceReceiver() {}

  @Binds
  @IntoMap
  @ClassKey(StartBackgroundServiceReceiver.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      StartBackgroundServiceReceiverSubcomponent.Factory builder);

  @Subcomponent
  @PerReceiver
  public interface StartBackgroundServiceReceiverSubcomponent
      extends AndroidInjector<StartBackgroundServiceReceiver> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<StartBackgroundServiceReceiver> {}
  }
}
