package com.voidcorp.neon.data.util;

import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;

import java.util.List;

public class ListUtil {

    public static final int POSITION_NONE = -1;

    public static boolean isPositionValid(final List<?> list, final int position) {
        return !(list == null || list.isEmpty() || position < 0 || position >= list.size());
    }

    @Nullable
    public static <E> E getItemAt(final List<E> list, final int position) {
        if (isPositionValid(list, position)) {
            return list.get(position);
        } else {
            return null;
        }
    }

    public static boolean isPositionValid(final ArrayMap<?, ?> list, final int position) {
        return !(list == null || list.isEmpty() || position < 0 || position >= list.size());
    }

    @Nullable
    public static <V> V getItemAt(final ArrayMap<?, V> list, final int position) {
        if (isPositionValid(list, position)) {
            return list.valueAt(position);
        } else {
            return null;
        }
    }

    public static boolean isRangeValid(final List<?> list, final int start, final int end) {
        return isPositionValid(list, start) && isPositionValid(list, end);
    }

}
