package com.ahmedabdelghafar.legarage;

import android.support.v7.widget.CardView;

/**
 * Created by orcl on 21/02/2018.
 */

public interface CardAdapter {
    public final int MAX_ELEVATION_FACTOR = 8;

    float getBaseElevation();

    CardView getCardViewAt(int position);

    int getCount();
}
