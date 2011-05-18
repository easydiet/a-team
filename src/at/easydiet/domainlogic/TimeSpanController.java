package at.easydiet.domainlogic;

import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.TimeSpanBO;

public class TimeSpanController
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(TimeSpanController.class);

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static TimeSpanController           _singleton;

    public static TimeSpanController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new TimeSpanController();
        }
        return _singleton;
    }

    private TimeSpanController()
    {}

    /**
     * Checks if the specified timespan collides with any other elements within
     * the patient context and returns a list of all collisions.
     * @param timespan the
     * @return
     */
    public List<Object> validateCollisions(TimeSpanBO timespan)
    {
        List<Object> collisions = new ArrayList<Object>();

        // collisions with any timespans in same plan?
        List<TimeSpanBO> timeSpans = timespan.getDietPlan().getTimeSpans();
        for (TimeSpanBO other : timeSpans)
        {
            if (other == timespan || (timespan.getTimeSpanId() > 0 && timespan.equals(other))) continue;
            if (isCollision(timespan.getStart(), timespan.getEnd(), other.getStart(), other.getEnd()))
            {
                collisions.add(other);
            }
        }

        // collision with any other dietplans in same treatment?
        List<DietPlanBO> plans = timespan.getDietPlan().getDietTreatment()
                .getDietPlans();
        for (DietPlanBO other : plans)
        {
            if (other.equals(timespan.getDietPlan())) continue;
            if (isCollision(timespan.getStart(), timespan.getEnd(), other.getStart(), other.getEnd()))
            {
                collisions.add(other);
            }
        }
        
        // collision with any other treaments
        List<DietTreatmentBO> treatments = timespan.getDietPlan().getDietTreatment().getPatient().getTreatments();
        for (DietTreatmentBO other : treatments)
        {
            if (other.equals(timespan.getDietPlan().getDietTreatment())) continue;
            if (isCollision(timespan.getStart(), timespan.getEnd(), other.getStart(), other.getEnd()))
            {
                collisions.add(other);
            }
        }
        
        return collisions;
    }

    /**
     * Checks whether two timeranges collide
     * @param currentStart
     * @param currentEnd
     * @param otherStart
     * @param otherEnd
     * @return
     */
    private boolean isCollision(Date currentStart, Date currentEnd, Date otherStart, Date otherEnd)
    {
        return currentStart.compareTo(otherEnd) <= 0 && otherStart.compareTo(currentEnd) <= 0;
    }
}
