package at.easydiet.util;

import java.util.Collection;

import org.apache.pivot.collections.ArrayList;

/**
 * Provides handy actions for collections
 */
public class CollectionUtils
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(CollectionUtils.class);

    /**
     * Convert a collection to a pivot array list
     * 
     * @param <T>
     *            The type of the collection elements
     * @param data
     *            The collection to convert
     * @return A new instance of {@link ArrayList}
     */
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> toPivotList(Collection<T> data)
    {
        return new ArrayList<T>((T[]) data.toArray());
    }
}
