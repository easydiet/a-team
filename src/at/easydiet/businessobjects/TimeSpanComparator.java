package at.easydiet.businessobjects;

import java.util.Comparator;

public class TimeSpanComparator implements Comparator<TimeSpanBO>
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(TimeSpanComparator.class);

    public int compare(TimeSpanBO o1, TimeSpanBO o2)
    {
        return o1.getStart().compareTo(o2.getStart());
    }
}
