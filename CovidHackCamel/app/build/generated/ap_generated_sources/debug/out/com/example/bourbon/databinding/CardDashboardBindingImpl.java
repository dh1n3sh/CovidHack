package com.example.bourbon.databinding;
import com.example.bourbon.R;
import com.example.bourbon.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CardDashboardBindingImpl extends CardDashboardBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.cardImage, 1);
        sViewsWithIds.put(R.id.cardTitle, 2);
    }
    // views
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CardDashboardBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 3, sIncludes, sViewsWithIds));
    }
    private CardDashboardBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.ImageView) bindings[1]
            , (android.widget.TextView) bindings[2]
            , (android.widget.RelativeLayout) bindings[0]
            );
        this.dashboardCard.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x4L;
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
            setPd((com.example.bourbon.activities.harish_activities.model.DashboardCards) variable);
        }
        else if (BR.pd2 == variableId) {
            setPd2((com.example.bourbon.activities.harish_activities.model.ActivityNames) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPd(@Nullable com.example.bourbon.activities.harish_activities.model.DashboardCards Pd) {
        this.mPd = Pd;
    }
    public void setPd2(@Nullable com.example.bourbon.activities.harish_activities.model.ActivityNames Pd2) {
        this.mPd2 = Pd2;
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
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): pd
        flag 1 (0x2L): pd2
        flag 2 (0x3L): null
    flag mapping end*/
    //end
}