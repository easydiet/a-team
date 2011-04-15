package at.easydiet.util;

import java.util.Collection;
import java.util.Set;

import org.apache.pivot.collections.ArrayList;

public class CollectionUtils
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(CollectionUtils.class);
    
    @SuppressWarnings("unchecked")
    public static <T> ArrayList<T> toPivotList(Collection<T> data)
    {
        return new ArrayList<T>((T[])data.toArray());
    }
}