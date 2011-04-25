package at.easydiet.businessobjects;

import java.util.Date;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;

import at.easydiet.model.DietParameter;
import at.easydiet.model.DietPlan;
import at.easydiet.model.TimeSpan;
import at.easydiet.util.CollectionCache;

/**
 * This class encapsules a DietPlan instance.
 */
public class DietPlanBO
{
    private DietPlan                    _model;
    private CollectionCache<TimeSpanBO> _sortedTimeSpans;

    /**
     * Initializes a new instance of the {@link DietPlanBO} class.
     */
    public DietPlanBO()
    {
        this(new DietPlan("", new Date(), PlanTypeBO.DIETPLAN.getModel(), null,
                null));
    }

    /**
     * Initializes a new instance of the {@link DietPlanBO} class.
     * @param model the original model object
     */
    public DietPlanBO(DietPlan model)
    {
        _model = model;
        _sortedTimeSpans = new CollectionCache<TimeSpanBO>()
        {

            @Override
            protected void refreshCache()
            {
                ArrayList<TimeSpanBO> sortedTimeSpans = new ArrayList<TimeSpanBO>(
                        getOriginal());
                ArrayList.sort(sortedTimeSpans, new TimeSpanComparator());
                setCache(sortedTimeSpans);
            }

            @Override
            protected Sequence<TimeSpanBO> getOriginal()
            {
                return getTimeSpans();
            }
        };
    }

    /**
     * Gets the original model object used as object store for this
     * BusinessObject.
     * @return the original {@link DietPlan} object.
     */
    public DietPlan getModel()
    {
        return _model;
    }

    /**
     * Gets the dietPlanId of this instance.
     * @return the dietPlanId currently set for this instance.
     */
    public long getDietPlanId()
    {
        return _model.getDietPlanId();
    }

    /**
     * Sets the dietPlanId of this instance.
     * @param dietPlanId the new dietPlanId of this instance.
     */
    public void setDietPlanId(long dietPlanId)
    {
        _model.setDietPlanId(dietPlanId);
    }

    /**
     * Gets the name of this instance.
     * @return the name currently set for this instance.
     */
    public String getName()
    {
        return _model.getName();
    }

    /**
     * Sets the name of this instance.
     * @param name the new name of this instance.
     */
    public void setName(String name)
    {
        _model.setName(name);
    }

    /**
     * Gets the createdOn of this instance.
     * @return the createdOn currently set for this instance.
     */
    public Date getCreatedOn()
    {
        return _model.getCreatedOn();
    }

    /**
     * Sets the createdOn of this instance.
     * @param createdOn the new createdOn of this instance.
     */
    public void setCreatedOn(Date createdOn)
    {
        _model.setCreatedOn(createdOn);
    }

    private PlanTypeBO _planType;

    /**
     * Gets the currently referenced PlanType of this instance.
     * @return the PlanType currently referenced in this DietPlan.
     */
    public PlanTypeBO getPlanType()
    {
        if (_planType == null)
        {
            _planType = PlanTypeBO.getForModel(_model.getPlanType());
        }
        return _planType;
    }

    /**
     * Sets the PlanType to be referenced in this instance
     * @param planType the PlanType to reference in this DietPlan.
     */
    public void setPlanType(PlanTypeBO planType)
    {
        _planType = planType;
        _model.setPlanType(planType.getModel());
    }

    private DietTreatmentBO _dietTreatment;

    /**
     * Gets the currently referenced DietTreatment of this instance.
     * @return the DietTreatment currently referenced in this DietPlan.
     */
    public DietTreatmentBO getDietTreatment()
    {
        if (_dietTreatment == null)
        {
            _dietTreatment = new DietTreatmentBO(_model.getDietTreatment());
        }
        return _dietTreatment;
    }

    /**
     * Sets the DietTreatment to be referenced in this instance
     * @param dietTreatment the DietTreatment to reference in this DietPlan.
     */
    public void setDietTreatment(DietTreatmentBO dietTreatment)
    {
        _dietTreatment = dietTreatment;
        _model.setDietTreatment(dietTreatment.getModel());
    }

    private List<DietParameterBO> _dietParameters;

/**
     * Gets a list of referenced DietParameters of this instance.
     * This list is cached, use {@link DietPlan#updateDietParametersCache()) to update this cache.
     * @return a cached list of referenced DietParameters wrapped into the correct businessobject. 
     */
    public List<DietParameterBO> getDietParameters()
    {
        if (_dietParameters == null)
        {
            _dietParameters = new ArrayList<DietParameterBO>();
            for (DietParameter dietParameters : _model.getDietParameters())
            {
                _dietParameters.add(new DietParameterBO(dietParameters));
            }
        }
        return _dietParameters;
    }

    /**
     * Adds a new DietParameter to the list of referenced dietParameters. The
     * cache will updated
     * @param dietParameters the DietParameter to add.
     */
    public void addDietParameters(DietParameterBO dietParameters)
    {
        getDietParameters().add(dietParameters);
        _model.getDietParameters().add(dietParameters.getModel());
    }

    /**
     * Removes the given DietParameter from the list of referenced
     * dietParameters. The cache will updated
     * @param dietParameters the timespan to add.
     */
    public void removeDietParameters(DietParameterBO dietParameters)
    {
        getDietParameters().remove(dietParameters);
        _model.getDietParameters().remove(dietParameters.getModel());
    }

    /**
     * Rebuilds the cache for referenced dietParameters.
     */
    public void updateDietParametersCache()
    {
        _dietParameters = null;
        getDietParameters();
    }

    private SystemUserBO _creator;

    /**
     * Gets the currently referenced Creator of this instance.
     * @return the SystemUser currently referenced in this DietPlan.
     */
    public SystemUserBO getCreator()
    {
        if (_creator == null)
        {
            _creator = new SystemUserBO(_model.getCreator());
        }
        return _creator;
    }

    /**
     * Sets the Creator to be referenced in this instance
     * @param creator the SystemUser to reference in this DietPlan.
     */
    public void setCreator(SystemUserBO creator)
    {
        _creator = creator;
        _model.setCreator(creator.getModel());
    }

    private List<TimeSpanBO> _timeSpans;

/**
     * Gets a list of referenced TimeSpans of this instance.
     * This list is cached, use {@link DietPlan#updateTimeSpansCache()) to update this cache.
     * @return a cached list of referenced TimeSpans wrapped into the correct businessobject. 
     */
    public List<TimeSpanBO> getTimeSpans()
    {
        if (_timeSpans == null)
        {
            _timeSpans = new ArrayList<TimeSpanBO>();
            for (TimeSpan timeSpans : _model.getTimeSpans())
            {
                _timeSpans.add(new TimeSpanBO(timeSpans));
            }
        }
        return _timeSpans;
    }

    /**
     * Adds a new TimeSpan to the list of referenced timeSpans. The cache will
     * updated
     * @param timeSpans the TimeSpan to add.
     */
    public void addTimeSpans(TimeSpanBO timeSpans)
    {
        timeSpans.setDietPlan(this);
        getTimeSpans().add(timeSpans);
        _model.getTimeSpans().add(timeSpans.getModel());
    }

    /**
     * Removes the given TimeSpan from the list of referenced timeSpans. The
     * cache will updated
     * @param timeSpans the timespan to add.
     */
    public void removeTimeSpans(TimeSpanBO timeSpans)
    {
        getTimeSpans().remove(timeSpans);
        _model.getTimeSpans().remove(timeSpans.getModel());
    }

    /**
     * Rebuilds the cache for referenced timeSpans.
     */
    public void updateTimeSpansCache()
    {
        _timeSpans = null;
        getTimeSpans();
    }

    public List<TimeSpanBO> getSortedTimeSpans()
    {
        return (List<TimeSpanBO>) _sortedTimeSpans.getCache();
    }

    public Date getStart()
    {
        List<TimeSpanBO> cache = ((List<TimeSpanBO>) _sortedTimeSpans
                .getCache());
        if (cache.getLength() == 0) return new Date();
        return cache.get(0).getStart();
    }

    public Date getEnd()
    {
        List<TimeSpanBO> cache = ((List<TimeSpanBO>) _sortedTimeSpans
                .getCache());
        if (cache.getLength() == 0) return new Date();
        return cache.get(cache.getLength() - 1).getEnd();
    }
    
    public List<TimeSpanBO> getSortedTimeSpanBOs(){
    	ArrayList<TimeSpanBO> source = ((ArrayList<TimeSpanBO>) _sortedTimeSpans
                .getCache());
    	return source;
    }
}