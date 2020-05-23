// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Store_Menu_ViewBinding implements Unbinder {
  private Store_Menu target;

  private View view7f0a018a;

  private View view7f0a018b;

  private View view7f0a0112;

  @UiThread
  public Store_Menu_ViewBinding(Store_Menu target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Store_Menu_ViewBinding(final Store_Menu target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.storeorder, "field 'storeorder' and method 'onViewClicked'");
    target.storeorder = Utils.castView(view, R.id.storeorder, "field 'storeorder'", Button.class);
    view7f0a018a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.imageView7 = Utils.findRequiredViewAsType(source, R.id.imageView7, "field 'imageView7'", ImageView.class);
    view = Utils.findRequiredView(source, R.id.storeregister, "method 'onViewClicked'");
    view7f0a018b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.nearbystore, "method 'onViewClicked'");
    view7f0a0112 = view;
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
    Store_Menu target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.storeorder = null;
    target.imageView7 = null;

    view7f0a018a.setOnClickListener(null);
    view7f0a018a = null;
    view7f0a018b.setOnClickListener(null);
    view7f0a018b = null;
    view7f0a0112.setOnClickListener(null);
    view7f0a0112 = null;
  }
}
