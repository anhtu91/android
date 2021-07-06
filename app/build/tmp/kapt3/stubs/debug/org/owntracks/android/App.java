package org.owntracks.android;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.os.StrictMode.VmPolicy;
import androidx.work.Configuration;
import androidx.work.WorkManager;
import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;
import org.conscrypt.Conscrypt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.owntracks.android.injection.components.AppComponentProvider;
import org.owntracks.android.injection.components.DaggerAppComponent;
import org.owntracks.android.services.MessageProcessor;
import org.owntracks.android.support.Events.RestartApp;
import org.owntracks.android.support.Preferences;
import org.owntracks.android.support.RunThingsOnOtherThreads;
import org.owntracks.android.support.TimberDebugLogTree;
import org.owntracks.android.ui.map.MapActivity;
import timber.log.Timber;
import timber.log.Timber.DebugTree;
import java.security.Security;
import javax.inject.Inject;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00010\fH\u0014J\b\u0010\r\u001a\u00020\u000eH\u0016J\u0012\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0007R\u0014\u0010\u0003\u001a\u0004\u0018\u00010\u00048\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u00068\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u0004\u0018\u00010\b8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\u0004\u0018\u00010\n8\u0006@\u0006X\u0087\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0012"}, d2 = {"Lorg/owntracks/android/App;", "Ldagger/android/support/DaggerApplication;", "()V", "eventBus", "Lorg/greenrobot/eventbus/EventBus;", "messageProcessor", "Lorg/owntracks/android/services/MessageProcessor;", "preferences", "Lorg/owntracks/android/support/Preferences;", "runThingsOnOtherThreads", "Lorg/owntracks/android/support/RunThingsOnOtherThreads;", "applicationInjector", "Ldagger/android/AndroidInjector;", "onCreate", "", "onEvent", "e", "Lorg/owntracks/android/support/Events$RestartApp;", "app_debug"})
public final class App extends dagger.android.support.DaggerApplication {
    @org.jetbrains.annotations.Nullable()
    @javax.inject.Inject()
    public org.owntracks.android.support.Preferences preferences;
    @org.jetbrains.annotations.Nullable()
    @javax.inject.Inject()
    public org.owntracks.android.support.RunThingsOnOtherThreads runThingsOnOtherThreads;
    @org.jetbrains.annotations.Nullable()
    @javax.inject.Inject()
    public org.owntracks.android.services.MessageProcessor messageProcessor;
    @org.jetbrains.annotations.Nullable()
    @javax.inject.Inject()
    public org.greenrobot.eventbus.EventBus eventBus;
    
    @java.lang.Override()
    public void onCreate() {
    }
    
    @org.greenrobot.eventbus.Subscribe()
    public final void onEvent(@org.jetbrains.annotations.Nullable()
    org.owntracks.android.support.Events.RestartApp e) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    protected dagger.android.AndroidInjector<? extends dagger.android.support.DaggerApplication> applicationInjector() {
        return null;
    }
    
    public App() {
        super();
    }
}