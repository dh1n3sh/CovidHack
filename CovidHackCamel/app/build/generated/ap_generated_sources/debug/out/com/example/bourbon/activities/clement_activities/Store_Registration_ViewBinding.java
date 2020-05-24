// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Store_Registration_ViewBinding implements Unbinder {
  private Store_Registration target;

  private View view7f0a0197;

  @UiThread
  public Store_Registration_ViewBinding(Store_Registration target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Store_Registration_ViewBinding(final Store_Registration target, View source) {
    this.target = target;

    View view;
    target.pincode = Utils.findRequiredViewAsType(source, R.id.pincode, "field 'pincode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.submit, "method 'onViewClicked'");
    view7f0a0197 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Store_Registration target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.pincode = null;

    view7f0a0197.setOnClickListener(null);
    view7f0a0197 = null;
  }
}
