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

public class Startact_ViewBinding implements Unbinder {
  private Startact target;

  private View view7f0a0125;

  private View view7f0a01c8;

  private View view7f0a01b7;

  private View view7f0a00b0;

  private View view7f0a00ac;

  private View view7f0a0171;

  private View view7f0a0166;

  private View view7f0a0094;

  private View view7f0a0119;

  private View view7f0a01df;

  private View view7f0a0118;

  private View view7f0a0050;

  @UiThread
  public Startact_ViewBinding(Startact target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Startact_ViewBinding(final Startact target, View source) {
    this.target = target;

    View view;
    target.mobilenum = Utils.findRequiredViewAsType(source, R.id.mobilenum, "field 'mobilenum'", EditText.class);
    view = Utils.findRequiredView(source, R.id.one, "method 'onViewClicked'");
    view7f0a0125 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two, "method 'onViewClicked'");
    view7f0a01c8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.three, "method 'onViewClicked'");
    view7f0a01b7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.four, "method 'onViewClicked'");
    view7f0a00b0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.five, "method 'onViewClicked'");
    view7f0a00ac = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.six, "method 'onViewClicked'");
    view7f0a0171 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.seven, "method 'onViewClicked'");
    view7f0a0166 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.eight, "method 'onViewClicked'");
    view7f0a0094 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.nine, "method 'onViewClicked'");
    view7f0a0119 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.zero, "method 'onViewClicked'");
    view7f0a01df = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.next, "method 'onViewClicked'");
    view7f0a0118 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back, "method 'onViewClicked'");
    view7f0a0050 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    Startact target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.mobilenum = null;

    view7f0a0125.setOnClickListener(null);
    view7f0a0125 = null;
    view7f0a01c8.setOnClickListener(null);
    view7f0a01c8 = null;
    view7f0a01b7.setOnClickListener(null);
    view7f0a01b7 = null;
    view7f0a00b0.setOnClickListener(null);
    view7f0a00b0 = null;
    view7f0a00ac.setOnClickListener(null);
    view7f0a00ac = null;
    view7f0a0171.setOnClickListener(null);
    view7f0a0171 = null;
    view7f0a0166.setOnClickListener(null);
    view7f0a0166 = null;
    view7f0a0094.setOnClickListener(null);
    view7f0a0094 = null;
    view7f0a0119.setOnClickListener(null);
    view7f0a0119 = null;
    view7f0a01df.setOnClickListener(null);
    view7f0a01df = null;
    view7f0a0118.setOnClickListener(null);
    view7f0a0118 = null;
    view7f0a0050.setOnClickListener(null);
    view7f0a0050 = null;
  }
}
