package skedgo.common.view;

import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;

/**
 * Tints tab icon with given color.
 * A benefit is that we don't provide yet another colored version of tab icon.
 */
public class TintTabIconDecorator implements TabLayout.OnTabSelectedListener {
  private final Resources resources;
  @ColorRes private final int selectedColor;
  @ColorRes private final int unselectedColor;

  /**
   * @param selectedColor   Color that indicates selection.
   * @param unselectedColor Color that indicates being unselected.
   */
  public TintTabIconDecorator(
      @NonNull Resources resources,
      @ColorRes int selectedColor,
      @ColorRes int unselectedColor) {
    this.resources = resources;
    this.selectedColor = selectedColor;
    this.unselectedColor = unselectedColor;
  }

  /**
   * @see <a href="http://blog.danlew.net/2014/08/18/fast-android-asset-theming-with-colorfilter/">
   * Fast Android asset theming with ColorFilter</a>
   */
  @NonNull
  public static Drawable getTintedDrawable(
      @NonNull Resources resources,
      @NonNull Drawable drawable,
      @ColorRes int colorResId) {
    drawable.setColorFilter(
        resources.getColor(colorResId),
        PorterDuff.Mode.SRC_IN
    );
    return drawable;
  }

  @Override
  public void onTabSelected(TabLayout.Tab tab) {
    final Drawable icon = tab.getIcon();
    if (icon != null) {
      tab.setIcon(getTintedDrawable(
          resources,
          icon,
          selectedColor
      ));
    }
  }

  @Override
  public void onTabUnselected(TabLayout.Tab tab) {
    final Drawable icon = tab.getIcon();
    if (icon != null) {
      tab.setIcon(getTintedDrawable(
          resources,
          icon,
          unselectedColor
      ));
    }
  }

  @Override
  public void onTabReselected(TabLayout.Tab tab) {
    final Drawable icon = tab.getIcon();
    if (icon != null) {
      tab.setIcon(getTintedDrawable(
          resources,
          icon,
          selectedColor
      ));
    }
  }
}