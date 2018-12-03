package br.com.doghero.dhproject.util;

import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.view.View;
import android.widget.TextView;

import br.com.doghero.dhproject.R;

public class BottomNavigationMenuUtil {

    /**
     * https://github.com/material-components/material-components-android/issues/139#issuecomment-435876996
     */
    public static void removePaddingFromActiveItem(BottomNavigationView bnv) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) bnv.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
            View activeLabel = item.findViewById(R.id.largeLabel);
            if (activeLabel instanceof TextView) {
                activeLabel.setPadding(0, 0, 0, 0);
            }
        }
    }

}
