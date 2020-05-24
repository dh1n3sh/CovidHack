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

public class Customer_Order_Details_ViewBinding implements Unbinder {
  private Customer_Order_Details target;

  private View view7f0a0146;

  private View view7f0a01da;

  @UiThread
  public Customer_Order_Details_ViewBinding(Customer_Order_Details target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Customer_Order_Details_ViewBinding(final Customer_Order_Details target, View source) {
    this.target = target;

    View view;
    target.dop = Utils.findRequiredViewAsType(source, R.id.dop, "field 'dop'", TextView.class);
    target.orderid = Utils.findRequiredViewAsType(source, R.id.orderid, "field 'orderid'", TextView.class);
    target.custname = Utils.findRequiredViewAsType(source, R.id.custname, "field 'custname'", TextView.class);
    view = Utils.findRequiredView(source, R.id.rejectorder, "field 'rejectorder' and method 'onViewClicked'");
    target.rejectorder = Utils.castView(view, R.id.rejectorder, "field 'rejectorder'", Button.class);
    view7f0a0146 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.customer = Utils.findRequiredViewAsType(source, R.id.customer, "field 'customer'", TextView.class);
    view = Utils.findRequiredView(source, R.id.viewcart, "method 'onViewClicked'");
    view7f0a01da = view;
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
    Customer_Order_Details target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.dop = null;
    target.orderid = null;
    target.custname = null;
    target.rejectorder = null;
    target.customer = null;

    view7f0a0146.setOnClickListener(null);
    view7f0a0146 = null;
    view7f0a01da.setOnClickListener(null);
    view7f0a01da = null;
  }
}
