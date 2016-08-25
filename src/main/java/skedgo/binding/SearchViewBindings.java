package skedgo.binding;

import android.databinding.BindingAdapter;
import android.support.v7.widget.SearchView;

public final class SearchViewBindings {
  private SearchViewBindings() {}

  @BindingAdapter("onQueryTextListener")
  public static void setOnQueryTextListener(SearchView v, SearchView.OnQueryTextListener listener) {
    v.setOnQueryTextListener(listener);
  }
}