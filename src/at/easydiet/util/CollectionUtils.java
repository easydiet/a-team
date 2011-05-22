package at.easydiet.util;

import java.util.Collection;

import org.apache.pivot.collections.ArrayList;

public class CollectionUtils
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CollectionUtils.class);

    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> toPivotList(Collection<T> data)
    {
        return new ArrayList<T>((T[]) data.toArray());
    }
}
