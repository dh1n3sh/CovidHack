package com.example.bourbon.databinding;
import com.example.bourbon.R;
import com.example.bourbon.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CardHarishEmergencyContactNumBindingImpl extends CardHarishEmergencyContactNumBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CardHarishEmergencyContactNumBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private CardHarishEmergencyContactNumBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[1]
            , (android.widget.TextView) bindings[3]
            , (androidx.cardview.widget.CardView) bindings[0]
            , (android.widget.TextView) bindings[2]
            );
        this.emerDistName.setTag(null);
        this.emerLandLineNo.setTag(null);
        this.emergencyCard.setTag(null);
        this.emergencyNo.setTag(null);
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
        if (BR.pd == variableId) {
            setPd((com.example.bourbon.activities.clement_activities.model.ProductDetails) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPd(@Nullable com.example.bourbon.activities.clement_activities.model.ProductDetails Pd) {
        this.mPd = Pd;
        synchronized(this) {
            mDirtyFlags |= 0x1L;
        }
        notifyPropertyChanged(BR.pd);
        super.requestRebind();
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
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
        com.example.bourbon.activities.clement_activities.model.ProductDetails pd = mPd;
        java.lang.String pdLandLineNo = null;
        java.lang.String pdEmergencyNo = null;
        java.lang.String pdDistrictName = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (pd != null) {
                    // read pd.landLineNo
                    pdLandLineNo = pd.getLandLineNo();
                    // read pd.emergencyNo
                    pdEmergencyNo = pd.getEmergencyNo();
                    // read pd.districtName
                    pdDistrictName = pd.getDistrictName();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.emerDistName, pdDistrictName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.emerLandLineNo, pdLandLineNo);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.emergencyNo, pdEmergencyNo);
        }
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): pd
        flag 1 (0x2L): null
    flag mapping end*/
    //end
}