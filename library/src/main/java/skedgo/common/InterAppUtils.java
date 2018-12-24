package skedgo.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;

import java.util.List;

/**
 * Contains some util methods to check whether there's any app to handle given {@link Intent}.
 * If not, just navigate to Play store to ask users to install it.
 */
public final class InterAppUtils {
  private InterAppUtils() {}

  public static void viewAppOnPlayStore(Activity activity, String appId) {
    activity.startActivity(
        new Intent(Intent.ACTION_VIEW)
            .setData(Uri.parse("market://details?id=" + appId))
    );
  }

  /**
   * Checks whether there are applications installed which are able to handle the given intent.
   *
   * @return true if there are apps which will respond to this intent
   */
  public static boolean canHandleIntent(Context context, Intent intent) {
    final List<ResolveInfo> list = context.getPackageManager()
        .queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
    return !list.isEmpty();
  }
}