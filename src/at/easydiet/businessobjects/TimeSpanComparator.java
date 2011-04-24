package at.easydiet.businessobjects;

import java.util.Comparator;

import at.easydiet.model.TimeSpan;

public class TimeSpanComparator implements Comparator<TimeSpan>
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(TimeSpanComparator.class);

    public int compare(TimeSpan o1, TimeSpan o2)
    {
        return o1.getStart().compareTo(o2.getStart());
    }
}
