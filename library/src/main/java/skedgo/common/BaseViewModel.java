package skedgo.common;

import android.os.Bundle;
import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * Represents a ViewModel that is associated with fragment lifecycle.
 */
public abstract class BaseViewModel {
  private final PublishSubject<Void> destroyed = PublishSubject.create();

  public final @NonNull Observable<Void> destroyed() {
    return destroyed.asObservable();
  }

  /**
   * Often trigger at {@link Fragment#onDestroy()}.
   */
  @CallSuper public void onDestroy() {
    destroyed.onNext(null);
  }

  /**
   * Often triggered at {@link Fragment#onCreate(Bundle)}.
   *
   * @param savedInstanceState The same as one passed via {@link Fragment#onCreate(Bundle)}.
   */
  public void onCreate(@Nullable Bundle savedInstanceState) {}
}