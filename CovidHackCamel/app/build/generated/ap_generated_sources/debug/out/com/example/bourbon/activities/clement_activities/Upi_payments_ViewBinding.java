// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Upi_payments_ViewBinding implements Unbinder {
  private Upi_payments target;

  @UiThread
  public Upi_payments_ViewBinding(Upi_payments target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Upi_payments_ViewBinding(Upi_payments target, View source) {
    this.target = target;

    target.name = Utils.findRequiredViewAsType(source, R.id.name, "field 'name'", EditText.class);
    target.amount = Utils.findRequiredViewAsType(source, R.id.amount, "field 'amount'", EditText.class);
    target.notes = Utils.findRequiredViewAsType(source, R.id.notes, "field 'notes'", EditText.class);
    target.send = Utils.findRequiredViewAsType(source, R.id.send, "field 'send'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Upi_payments target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.name = null;
    target.amount = null;
    target.notes = null;
    target.send = null;
  }
}
