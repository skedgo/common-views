package skedgo.common.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.skedgo.android.rx.util.RxFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * To save some boilerplate code when configuring content layout for a fragment.
 * Subclasses of this only needs to call {@link #setContentLayout(int)}
 * at {@link #onCreate(Bundle)} and inject necessary {@link BindView} fields.
 * We don't even need to care about unbinding view fields then too
 * because it's already done at superclass.
 */
public abstract class ButterKnifeFragment extends RxFragment {
  @LayoutRes private int contentLayout;
  @Nullable private Unbinder unbinder;

  @Nullable @Override public View onCreateView(
      LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    final View view = inflater.inflate(contentLayout, container, false);
    unbinder = ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onDestroyView() {
    // Nullify view fields so that they can be GC-ed.
    // (For example, when a fragment was detached from its activity.)
    if (unbinder != null) {
      unbinder.unbind();
    }

    super.onDestroyView();
  }

  /**
   * Should be invoked at {@link #onCreate(Bundle)}.
   *
   * @param contentLayout Used to create view for this fragment.
   *                      This layout id is the same as one
   *                      inflated at {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
   */
  protected void setContentLayout(@LayoutRes int contentLayout) {
    this.contentLayout = contentLayout;
  }
}