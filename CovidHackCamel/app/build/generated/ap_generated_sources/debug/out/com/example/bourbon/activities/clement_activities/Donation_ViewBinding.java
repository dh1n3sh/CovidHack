// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Donation_ViewBinding implements Unbinder {
  private Donation target;

  private View view7f0a0195;

  @UiThread
  public Donation_ViewBinding(Donation target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Donation_ViewBinding(final Donation target, View source) {
    this.target = target;

    View view;
    target.food = Utils.findRequiredViewAsType(source, R.id.food, "field 'food'", CheckBox.class);
    target.clothing = Utils.findRequiredViewAsType(source, R.id.clothing, "field 'clothing'", CheckBox.class);
    target.others = Utils.findRequiredViewAsType(source, R.id.others, "field 'others'", CheckBox.class);
    target.extra = Utils.findRequiredViewAsType(source, R.id.extra, "field 'extra'", EditText.class);
    view = Utils.findRequiredView(source, R.id.submit, "method 'onViewClicked'");
    view7f0a0195 = view;
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
    Donation target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.food = null;
    target.clothing = null;
    target.others = null;
    target.extra = null;

    view7f0a0195.setOnClickListener(null);
    view7f0a0195 = null;
  }
}
