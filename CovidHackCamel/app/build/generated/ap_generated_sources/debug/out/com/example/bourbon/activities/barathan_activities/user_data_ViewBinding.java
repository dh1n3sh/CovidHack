// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.barathan_activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class user_data_ViewBinding implements Unbinder {
  private user_data target;

  private View view7f0a0166;

  @UiThread
  public user_data_ViewBinding(user_data target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public user_data_ViewBinding(final user_data target, View source) {
    this.target = target;

    View view;
    target.user_firstName = Utils.findRequiredViewAsType(source, R.id.first_name, "field 'user_firstName'", EditText.class);
    target.user_address = Utils.findRequiredViewAsType(source, R.id.address, "field 'user_address'", EditText.class);
    target.user_dob = Utils.findRequiredViewAsType(source, R.id.dob, "field 'user_dob'", EditText.class);
    target.user_gender = Utils.findRequiredViewAsType(source, R.id.gender, "field 'user_gender'", Spinner.class);
    view = Utils.findRequiredView(source, R.id.send, "field 'user_send' and method 'onSendClicked'");
    target.user_send = Utils.castView(view, R.id.send, "field 'user_send'", Button.class);
    view7f0a0166 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onSendClicked();
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    user_data target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.user_firstName = null;
    target.user_address = null;
    target.user_dob = null;
    target.user_gender = null;
    target.user_send = null;

    view7f0a0166.setOnClickListener(null);
    view7f0a0166 = null;
  }
}
