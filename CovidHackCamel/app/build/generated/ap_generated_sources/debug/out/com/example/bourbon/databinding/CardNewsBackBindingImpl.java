package com.example.bourbon.databinding;
import com.example.bourbon.R;
import com.example.bourbon.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class CardNewsBackBindingImpl extends CardNewsBackBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = null;
    }
    // views
    @NonNull
    private final com.google.android.material.card.MaterialCardView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public CardNewsBackBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 4, sIncludes, sViewsWithIds));
    }
    private CardNewsBackBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[2]
            , (android.widget.TextView) bindings[1]
            );
        this.mboundView0 = (com.google.android.material.card.MaterialCardView) bindings[0];
        this.mboundView0.setTag(null);
        this.newsAuthor.setTag(null);
        this.newsDesc.setTag(null);
        this.newsTitle.setTag(null);
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
            setPd((com.example.bourbon.activities.harish_activities.model.NewsClassModel) variable);
        }
        else {
            variableSet = false;
        }
            return variableSet;
    }

    public void setPd(@Nullable com.example.bourbon.activities.harish_activities.model.NewsClassModel Pd) {
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
        com.example.bourbon.activities.harish_activities.model.NewsClassModel pd = mPd;
        java.lang.String pdTitle = null;
        java.lang.String pdAuthor = null;
        java.lang.String pdDesc = null;

        if ((dirtyFlags & 0x3L) != 0) {



                if (pd != null) {
                    // read pd.title
                    pdTitle = pd.getTitle();
                    // read pd.author
                    pdAuthor = pd.getAuthor();
                    // read pd.desc
                    pdDesc = pd.getDesc();
                }
        }
        // batch finished
        if ((dirtyFlags & 0x3L) != 0) {
            // api target 1

            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.newsAuthor, pdAuthor);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.newsDesc, pdDesc);
            androidx.databinding.adapters.TextViewBindingAdapter.setText(this.newsTitle, pdTitle);
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