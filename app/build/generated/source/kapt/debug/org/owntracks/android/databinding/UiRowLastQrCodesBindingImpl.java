package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiRowLastQrCodesBindingImpl extends UiRowLastQrCodesBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textViewKeyIDLastQRCode, 4);
        sViewsWithIds.put(R.id.textViewFieldNameLastQRCode, 5);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiRowLastQrCodesBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private UiRowLastQrCodesBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            );
        this.dateTimeLastQRCode.setTag(null);
        this.fieldNameLastQRCode.setTag(null);
        this.keyIDLastQRCode.setTag(null);
        this.lastQRCodeRow.setTag(null);
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
        if (BR.lastQRCodes == variableId) {
            setLastQRCodes((org.owntracks.android.model.LastQRCodesModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setLastQRCodes(@Nullable org.owntracks.android.model.LastQRCodesModel LastQRCodes) {
        updateRegistration(0, LastQRCodes);
        this.mLastQRCodes = LastQRCodes;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.lastQRCodes);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeLastQRCodes((org.owntracks.android.model.LastQRCodesModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeLastQRCodes(org.owntracks.android.model.LastQRCodesModel LastQRCodes, int fieldId) {
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
        java.lang.String lastQRCodesGetFieldName = null;
        java.lang.String lastQRCodesGetDateTime = null;
        org.owntracks.android.model.LastQRCodesModel lastQRCodes = mLastQRCodes;
        java.lang.String lastQRCodesGetKeyID = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (lastQRCodes != null) {
                    // read lastQRCodes.getFieldName
                    lastQRCodesGetFieldName = lastQRCodes.getFieldName();
                    // read lastQRCodes.getDateTime
                    lastQRCodesGetDateTime = lastQRCodes.getDateTime();
                    // read lastQRCodes.getKeyID
                    lastQRCodesGetKeyID = lastQRCodes.getKeyID();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.dateTimeLastQRCode, org.owntracks.android.support.widgets.BindingConversions.convertToString(lastQRCodesGetDateTime));
            this.fieldNameLastQRCode.setText(org.owntracks.android.support.widgets.BindingConversions.convertToString(lastQRCodesGetFieldName));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.keyIDLastQRCode, org.owntracks.android.support.widgets.BindingConversions.convertToString(lastQRCodesGetKeyID));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): lastQRCodes
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}