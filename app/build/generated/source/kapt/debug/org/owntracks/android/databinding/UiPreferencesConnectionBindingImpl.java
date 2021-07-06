package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiPreferencesConnectionBindingImpl extends UiPreferencesConnectionBinding implements org.owntracks.android.generated.callback.OnClickListener.Listener {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.toolbar, 7);
        sViewsWithIds.put(R.id.scrollView, 8);
        sViewsWithIds.put(R.id.hostTitle, 9);
    }
    // views
    @NonNull
    private final android.widget.ImageView mboundView3;
    @NonNull
    private final android.widget.ImageView mboundView5;
    // variables
    @Nullable
    private final android.view.View.OnClickListener mCallback3;
    @Nullable
    private final android.view.View.OnClickListener mCallback4;
    @Nullable
    private final android.view.View.OnClickListener mCallback1;
    @Nullable
    private final android.view.View.OnClickListener mCallback2;
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiPreferencesConnectionBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 10, sIncludes, sViewsWithIds));
    }
    private UiPreferencesConnectionBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (androidx.coordinatorlayout.widget.CoordinatorLayout) bindings[0]
            , (android.widget.LinearLayout) bindings[1]
            , (android.widget.TextView) bindings[9]
            , (android.widget.LinearLayout) bindings[2]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.ScrollView) bindings[8]
            , (android.widget.LinearLayout) bindings[4]
            , (androidx.appcompat.widget.Toolbar) bindings[7]
            );
        this.frame.setTag(null);
        this.host.setTag(null);
        this.identification.setTag(null);
        this.mboundView3 = (android.widget.ImageView) bindings[3];
        this.mboundView3.setTag(null);
        this.mboundView5 = (android.widget.ImageView) bindings[5];
        this.mboundView5.setTag(null);
        this.parameters.setTag(null);
        this.security.setTag(null);
        setRootTag(root);
        // listeners
        mCallback3 = new org.owntracks.android.generated.callback.OnClickListener(this, 3);
        mCallback4 = new org.owntracks.android.generated.callback.OnClickListener(this, 4);
        mCallback1 = new org.owntracks.android.generated.callback.OnClickListener(this, 1);
        mCallback2 = new org.owntracks.android.generated.callback.OnClickListener(this, 2);
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x2L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
        if (BR.vm == variableId) {
            setVm((org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setVm(@Nullable org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel Vm) {
        updateRegistration(0, Vm);
        this.mVm = Vm;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.vm);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeVm((org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeVm(org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel Vm, int fieldId) {
        if (fieldId == BR._all) {
            synchronized(this) {
                    mDirtyFlags |= 0x1L;
            }
            return true;
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        boolean vmModeIdMessageProcessorEndpointHttpMODEID = false;
        org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel<? extends org.owntracks.android.ui.base.view.MvvmView> vm = mVm;
        int vmModeId = 0;

        if ((dirtyFlags & 0x3L) != 0) {



                if (vm != null) {
                    // read vm.modeId
                    vmModeId = vm.getModeId();
                }


                // read vm.modeId != MessageProcessorEndpointHttp.MODE_ID
                vmModeIdMessageProcessorEndpointHttpMODEID = (vmModeId) != (org.owntracks.android.services.MessageProcessorEndpointHttp.MODE_ID);
        }
        // batch finished
        if ((dirtyFlags & 0x2L) != 0) {
            // api target 1

            this.host.setOnClickListener(mCallback1);
            this.identification.setOnClickListener(mCallback2);
            this.parameters.setOnClickListener(mCallback4);
            this.security.setOnClickListener(mCallback3);
        }
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.mboundView3, vmModeIdMessageProcessorEndpointHttpMODEID);
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.mboundView5, vmModeIdMessageProcessorEndpointHttpMODEID);
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.parameters, vmModeIdMessageProcessorEndpointHttpMODEID);
            org.owntracks.android.support.widgets.BindingConversions.setVisibility(this.security, vmModeIdMessageProcessorEndpointHttpMODEID);
        }
    }
    // Listener Stub Implementations
    // callback impls
    public final void _internalCallbackOnClick(int sourceId , android.view.View callbackArg_0) {
        switch(sourceId) {
            case 3: {
                // localize variables for thread safety
                // vm != null
                boolean vmJavaLangObjectNull = false;
                // vm
                org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel vm = mVm;



                vmJavaLangObjectNull = (vm) != (null);
                if (vmJavaLangObjectNull) {


                    vm.onSecurityClick();
                }
                break;
            }
            case 4: {
                // localize variables for thread safety
                // vm != null
                boolean vmJavaLangObjectNull = false;
                // vm
                org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel vm = mVm;



                vmJavaLangObjectNull = (vm) != (null);
                if (vmJavaLangObjectNull) {


                    vm.onParametersClick();
                }
                break;
            }
            case 1: {
                // localize variables for thread safety
                // vm != null
                boolean vmJavaLangObjectNull = false;
                // vm
                org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel vm = mVm;



                vmJavaLangObjectNull = (vm) != (null);
                if (vmJavaLangObjectNull) {


                    vm.onHostClick();
                }
                break;
            }
            case 2: {
                // localize variables for thread safety
                // vm != null
                boolean vmJavaLangObjectNull = false;
                // vm
                org.owntracks.android.ui.preferences.connection.ConnectionMvvm.ViewModel vm = mVm;



                vmJavaLangObjectNull = (vm) != (null);
                if (vmJavaLangObjectNull) {


                    vm.onIdentificationClick();
                }
                break;
            }
        }
    }
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): vm
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}