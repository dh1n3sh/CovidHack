// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
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

public class Volunteer_Registration_ViewBinding implements Unbinder {
  private Volunteer_Registration target;

  private View view7f0a0191;

  @UiThread
  public Volunteer_Registration_ViewBinding(Volunteer_Registration target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Volunteer_Registration_ViewBinding(final Volunteer_Registration target, View source) {
    this.target = target;

    View view;
    target.work = Utils.findRequiredViewAsType(source, R.id.work, "field 'work'", Spinner.class);
    target.skills = Utils.findRequiredViewAsType(source, R.id.skills, "field 'skills'", EditText.class);
    view = Utils.findRequiredView(source, R.id.submit, "method 'onViewClicked'");
    view7f0a0191 = view;
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
    Volunteer_Registration target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.work = null;
    target.skills = null;

    view7f0a0191.setOnClickListener(null);
    view7f0a0191 = null;
  }
}
