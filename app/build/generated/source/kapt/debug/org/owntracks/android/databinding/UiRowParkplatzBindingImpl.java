package org.owntracks.android.databinding;
import org.owntracks.android.R;
import org.owntracks.android.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class UiRowParkplatzBindingImpl extends UiRowParkplatzBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.textViewKeyIDParkplatz, 4);
        sViewsWithIds.put(R.id.textViewFieldNameParkplatz, 5);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public UiRowParkplatzBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 6, sIncludes, sViewsWithIds));
    }
    private UiRowParkplatzBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 1
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[0]
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[4]
            );
        this.dateTimeParkplatz.setTag(null);
        this.fieldNameParkplatz.setTag(null);
        this.importQRCodeRow.setTag(null);
        this.keyIDParkplatz.setTag(null);
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
        if (BR.parkplatz == variableId) {
            setParkplatz((org.owntracks.android.model.ParkplatzModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setParkplatz(@Nullable org.owntracks.android.model.ParkplatzModel Parkplatz) {
        updateRegistration(0, Parkplatz);
        this.mParkplatz = Parkplatz;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.parkplatz);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
            case 0 :
                return onChangeParkplatz((org.owntracks.android.model.ParkplatzModel) object, fieldId);
        }
        return false;
    }
    private boolean onChangeParkplatz(org.owntracks.android.model.ParkplatzModel Parkplatz, int fieldId) {
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
        java.lang.String parkplatzGetDateTime = null;
        org.owntracks.android.model.ParkplatzModel parkplatz = mParkplatz;
        java.lang.String parkplatzGetKeyID = null;
        java.lang.String parkplatzGetFieldName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (parkplatz != null) {
                    // read parkplatz.getDateTime
                    parkplatzGetDateTime = parkplatz.getDateTime();
                    // read parkplatz.getKeyID
                    parkplatzGetKeyID = parkplatz.getKeyID();
                    // read parkplatz.getFieldName
                    parkplatzGetFieldName = parkplatz.getFieldName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.dateTimeParkplatz, org.owntracks.android.support.widgets.BindingConversions.convertToString(parkplatzGetDateTime));
            this.fieldNameParkplatz.setText(org.owntracks.android.support.widgets.BindingConversions.convertToString(parkplatzGetFieldName));
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.keyIDParkplatz, org.owntracks.android.support.widgets.BindingConversions.convertToString(parkplatzGetKeyID));
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): parkplatz
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}