package skedgo.binding;

import android.view.View;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import skedgo.common.view.BuildConfig;

import static org.assertj.core.api.Assertions.assertThat;
import static skedgo.binding.Converters.convertBooleanToViewVisibility;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class ConvertersTest {
  @Test public void viewVisibilityIsVisibleIfValueIsTrue() {
    assertThat(convertBooleanToViewVisibility(true)).isEqualTo(View.VISIBLE);
  }

  @Test public void viewVisibilityIsGoneIfValueIsFalse() {
    assertThat(convertBooleanToViewVisibility(false)).isEqualTo(View.GONE);
  }
}