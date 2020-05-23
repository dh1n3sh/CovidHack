// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Order_Details_ViewBinding implements Unbinder {
  private Order_Details target;

  private View view7f0a0085;

  private View view7f0a01d8;

  private View view7f0a0149;

  @UiThread
  public Order_Details_ViewBinding(Order_Details target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Order_Details_ViewBinding(final Order_Details target, View source) {
    this.target = target;

    View view;
    target.dop = Utils.findRequiredViewAsType(source, R.id.dop, "field 'dop'", TextView.class);
    target.orderid = Utils.findRequiredViewAsType(source, R.id.orderid, "field 'orderid'", TextView.class);
    target.custname = Utils.findRequiredViewAsType(source, R.id.custname, "field 'custname'", TextView.class);
    view = Utils.findRequiredView(source, R.id.deliverorder, "field 'deliverorder' and method 'onViewClicked'");
    target.deliverorder = Utils.castView(view, R.id.deliverorder, "field 'deliverorder'", Button.class);
    view7f0a0085 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.viewcart, "method 'onViewClicked'");
    view7f0a01d8 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rejectorder, "method 'onViewClicked'");
    view7f0a0149 = view;
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
    Order_Details target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dop = null;
    target.orderid = null;
    target.custname = null;
    target.deliverorder = null;

    view7f0a0085.setOnClickListener(null);
    view7f0a0085 = null;
    view7f0a01d8.setOnClickListener(null);
    view7f0a01d8 = null;
    view7f0a0149.setOnClickListener(null);
    view7f0a0149 = null;
  }
}
