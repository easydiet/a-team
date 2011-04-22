package at.easydiet.businessobjects;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.util.CalendarDate;

import at.easydiet.model.DietParameter;
import at.easydiet.model.DietPlan;
import at.easydiet.model.Meal;
import at.easydiet.model.TimeSpan;
import at.easydiet.view.MealBO;

public class TimeSpanBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(TimeSpanBO.class);

    private TimeSpan _timeSpan;

    /**
     * Gets the timeSpan.
     * @return the timeSpan
     */
    public TimeSpan getTimeSpan()
    {
        return _timeSpan;
    }

    /** 
     * Initializes a new instance of the {@link TimeSpanBO} class. 
     */
    public TimeSpanBO()
    {
        this(new TimeSpan(new Date(), 1, null));
    }
    /** 
     * Initializes a new instance of the {@link TimeSpanBO} class. 
     * @param timeSpan
     */
    public TimeSpanBO(TimeSpan timeSpan)
    {
        super();
        _timeSpan = timeSpan;
    }

    /**
     * @return
     * @see at.easydiet.model.TimeSpan#getTimeSpanId()
     */
    public long getTimeSpanId()
    {
        return _timeSpan.getTimeSpanId();
    }

    /**
     * @param timeSpanId
     * @see at.easydiet.model.TimeSpan#setTimeSpanId(long)
     */
    public void setTimeSpanId(long timeSpanId)
    {
        _timeSpan.setTimeSpanId(timeSpanId);
    }
    
    

    /**
     * @return
     * @see at.easydiet.model.TimeSpan#getDietPlan()
     */
    public DietPlan getDietPlan()
    {
        return _timeSpan.getDietPlan();
    }

    /**
     * @param dietPlan
     * @see at.easydiet.model.TimeSpan#setDietPlan(at.easydiet.model.DietPlan)
     */
    public void setDietPlan(DietPlan dietPlan)
    {
        _timeSpan.setDietPlan(dietPlan);
    }

    /**
     * @return
     * @see at.easydiet.model.TimeSpan#getStart()
     */
    public Date getStart()
    {
        return _timeSpan.getStart();
    }
    
    public CalendarDate getStartDate()
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(_timeSpan.getStart());
        return new CalendarDate(calendar);
    }

    /**
     * @param start
     * @see at.easydiet.model.TimeSpan#setStart(java.util.Date)
     */
    public void setStart(Date start)
    {
        _timeSpan.setStart(start);
    }
    

    public Date getEnd()
    {
        GregorianCalendar g = new GregorianCalendar();
        g.setTime(getStart());
        g.add(GregorianCalendar.DAY_OF_YEAR, getDuration());
        return g.getTime();
    }
    
    
    

    /**
     * @return
     * @see at.easydiet.model.TimeSpan#getDuration()
     */
    public int getDuration()
    {
        return _timeSpan.getDuration();
    }

    /**
     * @param duration
     * @see at.easydiet.model.TimeSpan#setDuration(int)
     */
    public void setDuration(int duration)
    {
        _timeSpan.setDuration(duration);
    }
    
    
    public CalendarDate getEndDate()
    {
        CalendarDate end = getStartDate();
        end.add(getDuration());
        return end;
    }


    /**
     * @return
     * @see at.easydiet.model.TimeSpan#getDietParameters()
     */
    public Set<DietParameter> getDietParameters()
    {
        return _timeSpan.getDietParameters();
    }

    /**
     * @param dietParameters
     * @see at.easydiet.model.TimeSpan#setDietParameters(java.util.Set)
     */
    public void setDietParameters(Set<DietParameter> dietParameters)
    {
        _timeSpan.setDietParameters(dietParameters);
    }

    /**
     * @return
     * @see at.easydiet.model.TimeSpan#getMeals()
     */
    public List<Meal> getMeals()
    {
        return _timeSpan.getMeals();
    }

    /**
     * @param meals
     * @see at.easydiet.model.TimeSpan#setMeals(java.util.Set)
     */
    public void setMeals(List<Meal> meals)
    {
        _timeSpan.setMeals(meals);
    }
    
    public void removeFromDietPlan()
    {
        if(_timeSpan.getDietPlan() != null)
        {
            _timeSpan.getDietPlan().getTimeSpans().remove(this);
            _timeSpan.setDietPlan(null);
        }
    }
    
    public void addToDietPlan(DietPlanBO dietPlan)
    {
        dietPlan.getDietPlan().getTimeSpans().add(_timeSpan);
        _timeSpan.setDietPlan(dietPlan.getDietPlan());
    }

    public ArrayList<MealBO> getMealBOs()
    {
        ArrayList<MealBO> bos = new ArrayList<MealBO>();
        for (Meal meal : getMeals())
        {
            bos.add(new MealBO(meal));
        }
        return bos;
    }

}
