package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiRowManagementAccBindingImpl extends UiRowManagementAccBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textViewKeyID, 3);
        sViewsWithIds.put(R.id.textViewFieldName, 4);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiRowManagementAccBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 5, sIncludes, sViewsWithIds));
    }
    private UiRowManagementAccBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TextView) bindings[4]
            , (android.widget.TextView) bindings[3]
            );
        this.fieldNameManageAccount.setTag(null);
        this.keyIDManageAccount.setTag(null);
        this.managementParkingSpot.setTag(null);
        setRootTag(root);
        // listeners
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
        if (BR.managementAccount == variableId) {
            setManagementAccount((org.owntracks.android.model.ManagementAccountModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setManagementAccount(@Nullable org.owntracks.android.model.ManagementAccountModel ManagementAccount) {
        updateRegistration(0, ManagementAccount);
        this.mManagementAccount = ManagementAccount;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.managementAccount);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeManagementAccount((org.owntracks.android.model.ManagementAccountModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeManagementAccount(org.owntracks.android.model.ManagementAccountModel ManagementAccount, int fieldId) {
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
        java.lang.String managementAccountGetFieldName = null;
        org.owntracks.android.model.ManagementAccountModel managementAccount = mManagementAccount;
        java.lang.String managementAccountGetKeyID = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (managementAccount != null) {
                    // read managementAccount.getFieldName
                    managementAccountGetFieldName = managementAccount.getFieldName();
                    // read managementAccount.getKeyID
                    managementAccountGetKeyID = managementAccount.getKeyID();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            this.fieldNameManageAccount.setText(org.owntracks.android.support.widgets.BindingConversions.convertToString(managementAccountGetFieldName));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.keyIDManageAccount, org.owntracks.android.support.widgets.BindingConversions.convertToString(managementAccountGetKeyID));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): managementAccount
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}