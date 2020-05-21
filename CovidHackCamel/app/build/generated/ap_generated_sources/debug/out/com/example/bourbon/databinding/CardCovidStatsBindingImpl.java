package com.example.bourbon.databinding;
import com.example.bourbon.R;
import com.example.bourbon.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CardCovidStatsBindingImpl extends CardCovidStatsBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.statsCard, 5);
        sViewsWithIds.put(R.id.linearLayout1, 6);
        sViewsWithIds.put(R.id.green_ball, 7);
        sViewsWithIds.put(R.id.linearLayout2, 8);
        sViewsWithIds.put(R.id.yellow_ball, 9);
        sViewsWithIds.put(R.id.linearLayout3, 10);
        sViewsWithIds.put(R.id.red_ball, 11);
    }
    // views
    @NonNull
    private final android.widget.RelativeLayout mboundView0;
    @NonNull
    private final android.widget.TextView mboundView4;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CardCovidStatsBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 12, sIncludes, sViewsWithIds));
    }
    private CardCovidStatsBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[3]
            , (android.widget.ImageView) bindings[7]
            , (android.widget.LinearLayout) bindings[6]
            , (android.widget.LinearLayout) bindings[8]
            , (android.widget.LinearLayout) bindings[10]
            , (android.widget.TextView) bindings[1]
            , (android.widget.ImageView) bindings[11]
            , (android.widget.TextView) bindings[2]
            , (androidx.cardview.widget.CardView) bindings[5]
            , (android.widget.ImageView) bindings[9]
            );
        this.activeCasesText2.setTag(null);
        this.mboundView0 = (android.widget.RelativeLayout) bindings[0];
        this.mboundView0.setTag(null);
        this.mboundView4 = (android.widget.TextView) bindings[4];
        this.mboundView4.setTag(null);
        this.recoveredCasesText2.setTag(null);
        this.someCases.setTag(null);
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
            setPd((com.example.bourbon.activities.harish_activities.model.CovidStatus) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPd(@Nullable com.example.bourbon.activities.harish_activities.model.CovidStatus Pd) {
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
        java.lang.String pdDeaths = null;
        java.lang.String pdCityName = null;
        com.example.bourbon.activities.harish_activities.model.CovidStatus pd = mPd;
        java.lang.String pdRecovered = null;
        java.lang.String pdActiveCases = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (pd != null) {
                    // read pd.deaths
                    pdDeaths = pd.getDeaths();
                    // read pd.cityName
                    pdCityName = pd.getCityName();
                    // read pd.recovered
                    pdRecovered = pd.getRecovered();
                    // read pd.activeCases
                    pdActiveCases = pd.getActiveCases();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.activeCasesText2, pdDeaths);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.mboundView4, pdCityName);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.recoveredCasesText2, pdRecovered);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.someCases, pdActiveCases);
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