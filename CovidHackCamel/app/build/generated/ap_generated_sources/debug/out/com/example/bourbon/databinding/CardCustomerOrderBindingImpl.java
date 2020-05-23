package com.example.bourbon.databinding;
import com.example.bourbon.R;
import com.example.bourbon.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CardCustomerOrderBindingImpl extends CardCustomerOrderBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.customercard, 4);
        sViewsWithIds.put(R.id.vertical_covid_stats, 5);
        sViewsWithIds.put(R.id.custIdLabel, 6);
        sViewsWithIds.put(R.id.orderIdLabel, 7);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CardCustomerOrderBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 8, sIncludes, sViewsWithIds));
    }
    private CardCustomerOrderBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            , (androidx.cardview.widget.CardView) bindings[4]
            , (android.widget.TextView) bindings[7]
            , (android.widget.TextView) bindings[3]
            , (android.view.View) bindings[5]
            );
        this.custIdText.setTag(null);
        this.custName.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.orderIdText.setTag(null);
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
            setPd((com.example.bourbon.activities.harish_activities.model.CustomerOrder) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPd(@Nullable com.example.bourbon.activities.harish_activities.model.CustomerOrder Pd) {
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
        com.example.bourbon.activities.harish_activities.model.CustomerOrder pd = mPd;
        java.lang.String pdCustId = null;
        java.lang.String pdCustName = null;
        java.lang.String pdOrderId = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (pd != null) {
                    // read pd.custId
                    pdCustId = pd.getCustId();
                    // read pd.custName
                    pdCustName = pd.getCustName();
                    // read pd.orderId
                    pdOrderId = pd.getOrderId();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.custIdText, pdCustId);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.custName, pdCustName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.orderIdText, pdOrderId);
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