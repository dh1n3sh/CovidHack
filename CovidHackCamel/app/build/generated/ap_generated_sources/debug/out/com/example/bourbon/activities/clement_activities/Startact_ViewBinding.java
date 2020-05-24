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

  private View view7f0a0126;

  private View view7f0a01ce;

  private View view7f0a01bd;

  private View view7f0a00b2;

  private View view7f0a00ae;

  private View view7f0a0177;

  private View view7f0a0169;

  private View view7f0a0096;

  private View view7f0a011a;

  private View view7f0a01e5;

  private View view7f0a0119;

  private View view7f0a0051;

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
    view7f0a0126 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.two, "method 'onViewClicked'");
    view7f0a01ce = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.three, "method 'onViewClicked'");
    view7f0a01bd = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.four, "method 'onViewClicked'");
    view7f0a00b2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.five, "method 'onViewClicked'");
    view7f0a00ae = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.six, "method 'onViewClicked'");
    view7f0a0177 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.seven, "method 'onViewClicked'");
    view7f0a0169 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.eight, "method 'onViewClicked'");
    view7f0a0096 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.nine, "method 'onViewClicked'");
    view7f0a011a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.zero, "method 'onViewClicked'");
    view7f0a01e5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.next, "method 'onViewClicked'");
    view7f0a0119 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.back, "method 'onViewClicked'");
    view7f0a0051 = view;
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

    view7f0a0126.setOnClickListener(null);
    view7f0a0126 = null;
    view7f0a01ce.setOnClickListener(null);
    view7f0a01ce = null;
    view7f0a01bd.setOnClickListener(null);
    view7f0a01bd = null;
    view7f0a00b2.setOnClickListener(null);
    view7f0a00b2 = null;
    view7f0a00ae.setOnClickListener(null);
    view7f0a00ae = null;
    view7f0a0177.setOnClickListener(null);
    view7f0a0177 = null;
    view7f0a0169.setOnClickListener(null);
    view7f0a0169 = null;
    view7f0a0096.setOnClickListener(null);
    view7f0a0096 = null;
    view7f0a011a.setOnClickListener(null);
    view7f0a011a = null;
    view7f0a01e5.setOnClickListener(null);
    view7f0a01e5 = null;
    view7f0a0119.setOnClickListener(null);
    view7f0a0119 = null;
    view7f0a0051.setOnClickListener(null);
    view7f0a0051 = null;
  }
}
