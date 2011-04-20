package at.easydiet.businessobjects;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import at.easydiet.model.DietParameter;
import at.easydiet.model.DietPlan;
import at.easydiet.model.PlanType;
import at.easydiet.model.SystemUser;
import at.easydiet.model.TimeSpan;

public class DietPlanBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietPlanBO.class);

    private DietPlan                            _dietPlan;

    /**
     * Initializes a new instance of the {@link DietPlanBO} class.
     * @param dietPlan
     */
    public DietPlanBO(DietPlan dietPlan)
    {
        super();
        _dietPlan = dietPlan;
    }
    
    public Date getStart()
    {
        //TODO: get start of first timespan
        return new Date();
    }

    public Date getEnd()
    {
        //TODO: get end of last timespan
        return new Date();
    }
    
    /**
     * Gets the dietPlan.
     * @return the dietPlan
     */
    public DietPlan getDietPlan()
    {
        return _dietPlan;
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getDietPlanId()
     */
    public long getDietPlanId()
    {
        return _dietPlan.getDietPlanId();
    }

    /**
     * @param dietPlanId
     * @see at.easydiet.model.DietPlan#setDietPlanId(long)
     */
    public void setDietPlanId(long dietPlanId)
    {
        _dietPlan.setDietPlanId(dietPlanId);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getName()
     */
    public String getName()
    {
        return _dietPlan.getName();
    }

    /**
     * @param name
     * @see at.easydiet.model.DietPlan#setName(java.lang.String)
     */
    public void setName(String name)
    {
        _dietPlan.setName(name);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getCreatedOn()
     */
    public Date getCreatedOn()
    {
        return _dietPlan.getCreatedOn();
    }

    /**
     * @param createdOn
     * @see at.easydiet.model.DietPlan#setCreatedOn(java.util.Date)
     */
    public void setCreatedOn(Date createdOn)
    {
        _dietPlan.setCreatedOn(createdOn);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getPlanType()
     */
    public PlanType getPlanType()
    {
        return _dietPlan.getPlanType();
    }

    /**
     * @param planType
     * @see at.easydiet.model.DietPlan#setPlanType(at.easydiet.model.PlanType)
     */
    public void setPlanType(PlanType planType)
    {
        _dietPlan.setPlanType(planType);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getDietParameters()
     */
    public Set<DietParameter> getDietParameters()
    {
        return _dietPlan.getDietParameters();
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getDietParameters()
     */
    public Set<DietParameterBO> getDietParameterBOs()
    {
    	//TODO
    	Set<DietParameterBO> dietParameterBOSet = new HashSet<DietParameterBO>();
    	Set<DietParameter> dietParameterSet = _dietPlan.getDietParameters();
    	for(DietParameter dietParameter : dietParameterSet)
    	{
    		dietParameterBOSet.add(new DietParameterBO(dietParameter));
    	}
        return dietParameterBOSet;
    }
    
    /**
     * @param dietParameters
     * @see at.easydiet.model.DietPlan#setDietParameters(java.util.Set)
     */
    public void setDietParameters(Set<DietParameter> dietParameters)
    {
        _dietPlan.setDietParameters(dietParameters);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getCreator()
     */
    public SystemUser getCreator()
    {
        return _dietPlan.getCreator();
    }

    /**
     * @param creator
     * @see at.easydiet.model.DietPlan#setCreator(at.easydiet.model.SystemUser)
     */
    public void setCreator(SystemUser creator)
    {
        _dietPlan.setCreator(creator);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getTimeSpans()
     */
    public Set<TimeSpan> getTimeSpans()
    {
        return _dietPlan.getTimeSpans();
    }

    /**
     * @param timeSpans
     * @see at.easydiet.model.DietPlan#setTimeSpans(java.util.Set)
     */
    public void setTimeSpans(Set<TimeSpan> timeSpans)
    {
        _dietPlan.setTimeSpans(timeSpans);
    }
    

}
