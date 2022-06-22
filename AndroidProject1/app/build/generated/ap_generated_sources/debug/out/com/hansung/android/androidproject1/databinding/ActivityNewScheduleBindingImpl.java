package com.hansung.android.androidproject1.databinding;
import com.hansung.android.androidproject1.R;
import com.hansung.android.androidproject1.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class ActivityNewScheduleBindingImpl extends ActivityNewScheduleBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.Title, 1);
        sViewsWithIds.put(R.id.TitleText, 2);
        sViewsWithIds.put(R.id.Start, 3);
        sViewsWithIds.put(R.id.snum1, 4);
        sViewsWithIds.put(R.id.snum2, 5);
        sViewsWithIds.put(R.id.snum3, 6);
        sViewsWithIds.put(R.id.End, 7);
        sViewsWithIds.put(R.id.enum1, 8);
        sViewsWithIds.put(R.id.enum2, 9);
        sViewsWithIds.put(R.id.enum3, 10);
        sViewsWithIds.put(R.id.Location, 11);
        sViewsWithIds.put(R.id.Findbtn, 12);
        sViewsWithIds.put(R.id.UserText, 13);
        sViewsWithIds.put(R.id.Up, 14);
        sViewsWithIds.put(R.id.Can, 15);
        sViewsWithIds.put(R.id.del, 16);
        sViewsWithIds.put(R.id.sql_id, 17);
        sViewsWithIds.put(R.id.result, 18);
        sViewsWithIds.put(R.id.listview, 19);
    }
    // views
    @NonNull
    private final android.widget.ScrollView mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public ActivityNewScheduleBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 20, sIncludes, sViewsWithIds));
    }
    private ActivityNewScheduleBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.widget.Button) bindings[15]
            , (android.widget.TextView) bindings[7]
            , (android.widget.Button) bindings[12]
            , (android.widget.EditText) bindings[11]
            , (android.widget.TextView) bindings[3]
            , (android.widget.TextView) bindings[1]
            , (android.widget.EditText) bindings[2]
            , (android.widget.Button) bindings[14]
            , (android.widget.EditText) bindings[13]
            , (android.widget.Button) bindings[16]
            , (android.widget.NumberPicker) bindings[8]
            , (android.widget.NumberPicker) bindings[9]
            , (android.widget.NumberPicker) bindings[10]
            , (android.widget.ListView) bindings[19]
            , (android.widget.TextView) bindings[18]
            , (android.widget.NumberPicker) bindings[4]
            , (android.widget.NumberPicker) bindings[5]
            , (android.widget.NumberPicker) bindings[6]
            , (android.widget.EditText) bindings[17]
            );
        this.mboundView0 = (android.widget.ScrollView) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
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
            return variableSet;
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
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}