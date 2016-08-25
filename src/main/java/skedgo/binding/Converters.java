package skedgo.binding;

import android.databinding.BindingConversion;
import android.databinding.ObservableBoolean;
import android.view.View;

public final class Converters {
  private Converters() {}

  /**
   * Binds a boolean into {@link View#setVisibility(int)}.
   * Sample: `android:visibility="@{viewModel.isBusy}`
   * `isBusy` can be an {@link ObservableBoolean}.
   */
  @BindingConversion
  public static int convertBooleanToViewVisibility(boolean value) {
    return value ? View.VISIBLE : View.GONE;
  }
}