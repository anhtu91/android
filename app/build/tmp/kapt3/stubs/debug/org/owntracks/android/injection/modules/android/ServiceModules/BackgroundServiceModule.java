package org.owntracks.android.injection.modules.android.ServiceModules;

import android.app.Service;
import android.content.Context;
import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import org.owntracks.android.injection.qualifier.ServiceContext;
import org.owntracks.android.injection.scopes.PerActivity;
import org.owntracks.android.injection.scopes.PerService;
import org.owntracks.android.services.BackgroundService;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\'\u0018\u0000 \u00072\u00020\u0001:\u0001\u0007B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\'\u00a8\u0006\b"}, d2 = {"Lorg/owntracks/android/injection/modules/android/ServiceModules/BackgroundServiceModule;", "", "()V", "bindService", "Landroid/app/Service;", "s", "Lorg/owntracks/android/services/BackgroundService;", "Companion", "app_debug"})
@dagger.Module()
public abstract class BackgroundServiceModule {
    public static final org.owntracks.android.injection.modules.android.ServiceModules.BackgroundServiceModule.Companion Companion = null;
    
    @org.jetbrains.annotations.Nullable()
    @org.owntracks.android.injection.scopes.PerActivity()
    @dagger.Binds()
    public abstract android.app.Service bindService(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.services.BackgroundService s);
    
    public BackgroundServiceModule() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    @org.owntracks.android.injection.qualifier.ServiceContext()
    @org.owntracks.android.injection.scopes.PerService()
    @dagger.Provides()
    public static final android.content.Context serviceContext(@org.jetbrains.annotations.NotNull()
    android.app.Service service) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0007"}, d2 = {"Lorg/owntracks/android/injection/modules/android/ServiceModules/BackgroundServiceModule$Companion;", "", "()V", "serviceContext", "Landroid/content/Context;", "service", "Landroid/app/Service;", "app_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        @org.owntracks.android.injection.qualifier.ServiceContext()
        @org.owntracks.android.injection.scopes.PerService()
        @dagger.Provides()
        public final android.content.Context serviceContext(@org.jetbrains.annotations.NotNull()
        android.app.Service service) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}