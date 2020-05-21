// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import android.widget.Button;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Voip_ViewBinding implements Unbinder {
  private Voip target;

  @UiThread
  public Voip_ViewBinding(Voip target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Voip_ViewBinding(Voip target, View source) {
    this.target = target;

    target.call = Utils.findRequiredViewAsType(source, R.id.call, "field 'call'", Button.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    Voip target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.call = null;
  }
}
