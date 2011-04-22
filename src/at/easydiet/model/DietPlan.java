package at.easydiet.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a DietPlan
 */
public class DietPlan  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -1094183284575467262L;
    private long _dietPlanId;
    private String _name;
    private Date _createdOn;
    private PlanType _planType;
    private DietTreatment _dietTreatment;
    private Set<DietParameter> _dietParameters = new HashSet<DietParameter>(0);
    private SystemUser _creator;
    private Set<TimeSpan> _timeSpans = new HashSet<TimeSpan>(0);

    /**
     * Initializes a new instance of the {@link DietPlan} class.
     */
    public DietPlan() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link DietPlan} class.
     * @param name the name to set for this instance
     * @param createdOn the createdOn to set for this instance
     * @param planType the planType to set for this instance
     * @param dietTreatment the dietTreatment to set for this instance
     * @param creator the creator to set for this instance
     */
    public DietPlan(String name, Date createdOn, PlanType planType, DietTreatment dietTreatment, SystemUser creator) 
    {
        _name = name;
        _createdOn = createdOn;
        _planType = planType;
        _dietTreatment = dietTreatment;
        _creator = creator;
    }

    /**
     * Initializes a new instance of the {@link DietPlan} class.
     * @param name the name to set for this instance
     * @param createdOn the createdOn to set for this instance
     * @param planType the planType to set for this instance
     * @param dietTreatment the dietTreatment to set for this instance
     * @param dietParameters the dietParameters to set for this instance
     * @param creator the creator to set for this instance
     * @param timeSpans the timeSpans to set for this instance
     */
    public DietPlan(String name, Date createdOn, PlanType planType, DietTreatment dietTreatment, Set<DietParameter> dietParameters, SystemUser creator, Set<TimeSpan> timeSpans) 
    {
       _name = name;
       _createdOn = createdOn;
       _planType = planType;
       _dietTreatment = dietTreatment;
       _dietParameters = dietParameters;
       _creator = creator;
       _timeSpans = timeSpans;
    }
   
    /**       
     * Gets the dietPlanId of this instance. 
     * @return the dietPlanId currently set for this instance.
     */
    public long getDietPlanId() 
    {
        return _dietPlanId;
    }
    
    /**       
     * Sets the dietPlanId of this instance. 
     * @param dietPlanId the new dietPlanId of this instance.
     */    
    public void setDietPlanId(long dietPlanId) 
    {
        _dietPlanId = dietPlanId;
    }
    
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _name;
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _name = name;
    }
    
    /**       
     * Gets the createdOn of this instance. 
     * @return the createdOn currently set for this instance.
     */
    public Date getCreatedOn() 
    {
        return _createdOn;
    }
    
    /**       
     * Sets the createdOn of this instance. 
     * @param createdOn the new createdOn of this instance.
     */    
    public void setCreatedOn(Date createdOn) 
    {
        _createdOn = createdOn;
    }
    
    /**       
     * Gets the planType of this instance. 
     * @return the planType currently set for this instance.
     */
    public PlanType getPlanType() 
    {
        return _planType;
    }
    
    /**       
     * Sets the planType of this instance. 
     * @param planType the new planType of this instance.
     */    
    public void setPlanType(PlanType planType) 
    {
        _planType = planType;
    }
    
    /**       
     * Gets the dietTreatment of this instance. 
     * @return the dietTreatment currently set for this instance.
     */
    public DietTreatment getDietTreatment() 
    {
        return _dietTreatment;
    }
    
    /**       
     * Sets the dietTreatment of this instance. 
     * @param dietTreatment the new dietTreatment of this instance.
     */    
    public void setDietTreatment(DietTreatment dietTreatment) 
    {
        _dietTreatment = dietTreatment;
    }
    
    /**       
     * Gets the dietParameters of this instance. 
     * @return the dietParameters currently set for this instance.
     */
    public Set<DietParameter> getDietParameters() 
    {
        return _dietParameters;
    }
    
    /**       
     * Sets the dietParameters of this instance. 
     * @param dietParameters the new dietParameters of this instance.
     */    
    public void setDietParameters(Set<DietParameter> dietParameters) 
    {
        _dietParameters = dietParameters;
    }
    
    /**       
     * Gets the creator of this instance. 
     * @return the creator currently set for this instance.
     */
    public SystemUser getCreator() 
    {
        return _creator;
    }
    
    /**       
     * Sets the creator of this instance. 
     * @param creator the new creator of this instance.
     */    
    public void setCreator(SystemUser creator) 
    {
        _creator = creator;
    }
    
    /**       
     * Gets the timeSpans of this instance. 
     * @return the timeSpans currently set for this instance.
     */
    public Set<TimeSpan> getTimeSpans() 
    {
        return _timeSpans;
    }
    
    /**       
     * Sets the timeSpans of this instance. 
     * @param timeSpans the new timeSpans of this instance.
     */    
    public void setTimeSpans(Set<TimeSpan> timeSpans) 
    {
        _timeSpans = timeSpans;
    }
    
    /**
     * Returns a string representation of this instance.
     * @return a string
     */
    @Override
    public String toString() 
    {
        StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
        builder.append("]");
      
        return builder.toString();
    }
}
