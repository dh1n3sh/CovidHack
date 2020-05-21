// Generated code from Butter Knife. Do not modify!
package com.example.bourbon.activities.clement_activities;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.bourbon.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Order_Food_ViewBinding implements Unbinder {
  private Order_Food target;

  private View view7f0a00ea;

  private View view7f0a0043;

  private View view7f0a0197;

  private View view7f0a005c;

  @UiThread
  public Order_Food_ViewBinding(Order_Food target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Order_Food_ViewBinding(final Order_Food target, View source) {
    this.target = target;

    View view;
    target.storeName = Utils.findRequiredViewAsType(source, R.id.store_name, "field 'storeName'", TextView.class);
    target.itemName = Utils.findRequiredViewAsType(source, R.id.item_name, "field 'itemName'", EditText.class);
    target.itemQuantity = Utils.findRequiredViewAsType(source, R.id.item_quantity, "field 'itemQuantity'", EditText.class);
    view = Utils.findRequiredView(source, R.id.manuel, "field 'manuel' and method 'onViewClicked'");
    target.manuel = Utils.castView(view, R.id.manuel, "field 'manuel'", Button.class);
    view7f0a00ea = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.add_item, "method 'onViewClicked'");
    view7f0a0043 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.submit_list, "method 'onViewClicked'");
    view7f0a0197 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.cart, "method 'onViewClicked'");
    view7f0a005c = view;
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
    Order_Food target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.storeName = null;
    target.itemName = null;
    target.itemQuantity = null;
    target.manuel = null;

    view7f0a00ea.setOnClickListener(null);
    view7f0a00ea = null;
    view7f0a0043.setOnClickListener(null);
    view7f0a0043 = null;
    view7f0a0197.setOnClickListener(null);
    view7f0a0197 = null;
    view7f0a005c.setOnClickListener(null);
    view7f0a005c = null;
  }
}
