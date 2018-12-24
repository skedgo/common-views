package skedgo.binding;

import android.widget.ProgressBar;

import androidx.databinding.ObservableBoolean;
import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

public final class Utils {
  private Utils() {}

  /**
   * A use case for this would be like, we fetch data from network or do
   * some sort of heavy computation off from the main thread, and we would like
   * to show its progress via views like {@link ProgressBar} for example.
   * Then we'll define an {@link ObservableBoolean} var (e.g. isBusy),
   * and bind it with {@link ProgressBar#setVisibility(int)}.
   * When the {@link Observable} begins to execute, isBusy will be set to true,
   * making the view visible. Then after the {@link Observable} is done, isBusy becomes false
   * and hides the view accordingly.
   * <p/>
   * This should be used with {@link Observable#compose(Observable.Transformer)}.
   *
   * @param isBusy This bindable property will be mutated to indicate
   *               if an {@link Observable} is executing its work.
   */
  public static <T> Observable.Transformer<T, T> reportStatus(final ObservableBoolean isBusy) {
    return new Observable.Transformer<T, T>() {
      @Override public Observable<T> call(Observable<T> observable) {
        return observable
            .doOnRequest(new Action1<Long>() {
              @Override public void call(Long unused) {
                isBusy.set(true);
              }
            })
            .doOnTerminate(new Action0() {
              @Override public void call() {
                isBusy.set(false);
              }
            });
      }
    };
  }
}