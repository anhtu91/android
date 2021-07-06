package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.ContactsActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.contacts.ContactsActivity;

@Module(
  subcomponents = AndroindBindingModule_BindContactsActivity.ContactsActivitySubcomponent.class
)
public abstract class AndroindBindingModule_BindContactsActivity {
  private AndroindBindingModule_BindContactsActivity() {}

  @Binds
  @IntoMap
  @ClassKey(ContactsActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      ContactsActivitySubcomponent.Factory builder);

  @Subcomponent(modules = ContactsActivityModule.class)
  @PerActivity
  public interface ContactsActivitySubcomponent extends AndroidInjector<ContactsActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<ContactsActivity> {}
  }
}
