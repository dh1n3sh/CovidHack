// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Verifi_ViewBinding implements Unbinder {
  private Verifi target;

  private View view7f0a011b;

  private View view7f0a0128;

  private View view7f0a01cc;

  private View view7f0a01bb;

  private View view7f0a00b1;

  private View view7f0a00ad;

  private View view7f0a0175;

  private View view7f0a016a;

  private View view7f0a0095;

  private View view7f0a011c;

  private View view7f0a0050;

  private View view7f0a01e3;

  @UiThread
  public Verifi_ViewBinding(Verifi target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Verifi_ViewBinding(final Verifi target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.next, "method 'onViewClicked'");
    view7f0a011b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked();
      }
    });
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
    view = Utils.findRequiredView(source, R.id.back, "method 'onViewClicked'");
    view7f0a0050 = view;
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
  }

  @Override
  @CallSuper
  public void unbind() {
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    target = null;


    view7f0a011b.setOnClickListener(null);
    view7f0a011b = null;
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
    view7f0a0050.setOnClickListener(null);
    view7f0a0050 = null;
    view7f0a01e3.setOnClickListener(null);
    view7f0a01e3 = null;
  }
}
