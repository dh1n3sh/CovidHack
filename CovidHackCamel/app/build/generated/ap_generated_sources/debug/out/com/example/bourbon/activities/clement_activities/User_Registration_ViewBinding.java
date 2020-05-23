// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class User_Registration_ViewBinding implements Unbinder {
  private User_Registration target;

  private View view7f0a0147;

  @UiThread
  public User_Registration_ViewBinding(User_Registration target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public User_Registration_ViewBinding(final User_Registration target, View source) {
    this.target = target;

    View view;
    target.firstName = Utils.findRequiredViewAsType(source, R.id.first_name, "field 'firstName'", EditText.class);
    target.address = Utils.findRequiredViewAsType(source, R.id.address, "field 'address'", EditText.class);
    target.dob = Utils.findRequiredViewAsType(source, R.id.dob, "field 'dob'", EditText.class);
    target.pincode = Utils.findRequiredViewAsType(source, R.id.pincode, "field 'pincode'", EditText.class);
    target.male = Utils.findRequiredViewAsType(source, R.id.male, "field 'male'", RadioButton.class);
    target.female = Utils.findRequiredViewAsType(source, R.id.female, "field 'female'", RadioButton.class);
    view = Utils.findRequiredView(source, R.id.register, "method 'onViewClicked'");
    view7f0a0147 = view;
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
    User_Registration target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.firstName = null;
    target.address = null;
    target.dob = null;
    target.pincode = null;
    target.male = null;
    target.female = null;

    view7f0a0147.setOnClickListener(null);
    view7f0a0147 = null;
  }
}
