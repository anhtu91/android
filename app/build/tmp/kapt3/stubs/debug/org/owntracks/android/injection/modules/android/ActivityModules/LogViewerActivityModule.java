package org.owntracks.android.injection.modules.android.ActivityModules;

import androidx.appcompat.app.AppCompatActivity;
import dagger.Binds;
import dagger.Module;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.ui.preferences.LogViewerActivity;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\'\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\'\u00a8\u0006\u0007"}, d2 = {"Lorg/owntracks/android/injection/modules/android/ActivityModules/LogViewerActivityModule;", "", "()V", "bindActivity", "Landroidx/appcompat/app/AppCompatActivity;", "a", "Lorg/owntracks/android/ui/preferences/LogViewerActivity;", "app_debug"})
@dagger.Module(includes = {org.owntracks.android.injection.modules.android.ActivityModules.BaseActivityModule.class})
public abstract class LogViewerActivityModule {
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.injection.scopes.PerActivity()
    @dagger.Binds()
    public abstract androidx.appcompat.app.AppCompatActivity bindActivity(@org.jetbrains.annotations.NotNull()
    org.owntracks.android.ui.preferences.LogViewerActivity a);
    
    public LogViewerActivityModule() {
        super();
    }
}