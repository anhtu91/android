package org.owntracks.android.injection.modules.android;

import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.multibindings.ClassKey;
import dagger.multibindings.IntoMap;
import org.owntracks.android.injection.modules.android.ActivityModules.EditorActivityModule;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.preferences.editor.EditorActivity;

@Module(subcomponents = AndroindBindingModule_BindEditorActivity.EditorActivitySubcomponent.class)
public abstract class AndroindBindingModule_BindEditorActivity {
  private AndroindBindingModule_BindEditorActivity() {}

  @Binds
  @IntoMap
  @ClassKey(EditorActivity.class)
  abstract AndroidInjector.Factory<?> bindAndroidInjectorFactory(
      EditorActivitySubcomponent.Factory builder);

  @Subcomponent(modules = EditorActivityModule.class)
  @PerActivity
  public interface EditorActivitySubcomponent extends AndroidInjector<EditorActivity> {
    @Subcomponent.Factory
    interface Factory extends AndroidInjector.Factory<EditorActivity> {}
  }
}
