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

  private View view7f0a0128;

  private View view7f0a01cc;

  private View view7f0a01bb;

  private View view7f0a00b1;

  private View view7f0a00ad;

  private View view7f0a0175;

  private View view7f0a016a;

  private View view7f0a0095;

  private View view7f0a011c;

  private View view7f0a01e3;

  private View view7f0a011b;

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
    view7f0a0128 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two, "method 'onViewClicked'");
    view7f0a01cc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.three, "method 'onViewClicked'");
    view7f0a01bb = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.four, "method 'onViewClicked'");
    view7f0a00b1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.five, "method 'onViewClicked'");
    view7f0a00ad = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.six, "method 'onViewClicked'");
    view7f0a0175 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.seven, "method 'onViewClicked'");
    view7f0a016a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.eight, "method 'onViewClicked'");
    view7f0a0095 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.nine, "method 'onViewClicked'");
    view7f0a011c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.zero, "method 'onViewClicked'");
    view7f0a01e3 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.next, "method 'onViewClicked'");
    view7f0a011b = view;
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

    view7f0a0128.setOnClickListener(null);
    view7f0a0128 = null;
    view7f0a01cc.setOnClickListener(null);
    view7f0a01cc = null;
    view7f0a01bb.setOnClickListener(null);
    view7f0a01bb = null;
    view7f0a00b1.setOnClickListener(null);
    view7f0a00b1 = null;
    view7f0a00ad.setOnClickListener(null);
    view7f0a00ad = null;
    view7f0a0175.setOnClickListener(null);
    view7f0a0175 = null;
    view7f0a016a.setOnClickListener(null);
    view7f0a016a = null;
    view7f0a0095.setOnClickListener(null);
    view7f0a0095 = null;
    view7f0a011c.setOnClickListener(null);
    view7f0a011c = null;
    view7f0a01e3.setOnClickListener(null);
    view7f0a01e3 = null;
    view7f0a011b.setOnClickListener(null);
    view7f0a011b = null;
    view7f0a0050.setOnClickListener(null);
    view7f0a0050 = null;
  }
}
