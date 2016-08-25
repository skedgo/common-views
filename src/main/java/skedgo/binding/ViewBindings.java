package skedgo.binding;

import android.databinding.BindingAdapter;
import android.view.View;

import java.util.List;

public final class ViewBindings {
  private ViewBindings() {}

  @BindingAdapter("emptiness")
  public static void bindEmptiness(View v, List<?> list) {
    v.setVisibility(
        (list != null && list.size() > 0)
            ? View.GONE
            : View.VISIBLE
    );
  }
}