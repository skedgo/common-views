package skedgo.common.view;

import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.IdRes;
import android.support.annotation.Nullable;
import android.view.View;

import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.SupportMapFragment;
import com.skedgo.android.rx.util.RxFragment;

/**
 * A sort of fragment that allows subclasses to have
 * one {@link MapView} inside its layout. The lifecycle of the {@link MapView}
 * is already controlled here, so subclasses shouldn't worry about it.
 * One benefit compared with {@link SupportMapFragment} is that
 * it'd be easier to perform data binding to the {@link MapView} defined in the xml layout.
 */
public abstract class BindableSingleMapFragment extends RxFragment {
  private static final String KEY_MAP_STATE = "mapState";
  protected MapView mapView;

  @Override public void onStart() {
    super.onStart();
    mapView.onStart();
  }

  @Override public void onStop() {
    super.onStop();
    mapView.onStop();
  }

  @Override public void onResume() {
    super.onResume();
    mapView.onResume();
  }

  @Override public void onPause() {
    super.onPause();
    mapView.onPause();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override public void onLowMemory() {
    super.onLowMemory();
    mapView.onLowMemory();
  }

  @Override public void onSaveInstanceState(Bundle outState) {
    Bundle mapState = new Bundle();
    mapView.onSaveInstanceState(mapState);
    outState.putBundle(KEY_MAP_STATE, mapState);
    super.onSaveInstanceState(outState);
  }

  @CallSuper @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    mapView = (MapView) view.findViewById(getMapViewId());
    Bundle mapState = null;
    if (null != savedInstanceState) {
      mapState = savedInstanceState.getBundle(KEY_MAP_STATE);
    }

    mapView.onCreate(mapState);
  }

  /**
   * @return Id of the existent {@link MapView} defined in fragment layout.
   * If the id is invalid, {@link #onViewCreated(View, Bundle)} will crash the app.
   */
  @IdRes protected abstract int getMapViewId();
}