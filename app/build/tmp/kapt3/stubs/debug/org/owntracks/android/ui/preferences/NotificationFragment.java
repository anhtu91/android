package org.owntracks.android.ui.preferences;

import android.os.Bundle;
import dagger.Binds;
import dagger.Module;
import org.owntracks.android.R;
import org.owntracks.android.injection.modules.android.FragmentModules.BaseFragmentModule;
import org.owntracks.android.injection.scopes.PerFragment;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001:\u0001\tB\u0005\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016\u00a8\u0006\n"}, d2 = {"Lorg/owntracks/android/ui/preferences/NotificationFragment;", "Lorg/owntracks/android/ui/preferences/AbstractPreferenceFragment;", "()V", "onCreatePreferencesFix", "", "savedInstanceState", "Landroid/os/Bundle;", "rootKey", "", "FragmentModule", "app_debug"})
@org.owntracks.android.injection.scopes.PerFragment()
public final class NotificationFragment extends org.owntracks.android.ui.preferences.AbstractPreferenceFragment {
    private java.util.HashMap _$_findViewCache;
    
    @java.lang.Override()
    public void onCreatePreferencesFix(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState, @org.jetbrains.annotations.Nullable()
    java.lang.String rootKey) {
    }
    
    public NotificationFragment() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b!\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\'\u00a8\u0006\u0006"}, d2 = {"Lorg/owntracks/android/ui/preferences/NotificationFragment$FragmentModule;", "", "()V", "bindFragment", "Lorg/owntracks/android/ui/preferences/NotificationFragment;", "reportingFragment", "app_debug"})
    @dagger.Module(includes = {org.owntracks.android.injection.modules.android.FragmentModules.BaseFragmentModule.class})
    public static abstract class FragmentModule {
        
        @org.jetbrains.annotations.NotNull()
        @org.owntracks.android.injection.scopes.PerFragment()
        @dagger.Binds()
        public abstract org.owntracks.android.ui.preferences.NotificationFragment bindFragment(@org.jetbrains.annotations.NotNull()
        org.owntracks.android.ui.preferences.NotificationFragment reportingFragment);
        
        public FragmentModule() {
            super();
        }
    }
}