package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.Date;
import java.util.Set;

import at.easydiet.model.DietParameter;
import at.easydiet.model.NutritionProtocol;
import at.easydiet.model.PlanType;
import at.easydiet.model.SystemUser;
import at.easydiet.model.TimeSpan;


public class NutritionProtocolBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(NutritionProtocolBO.class);

    private NutritionProtocol _nutritionProtocol;
    
    public NutritionProtocol getNutritionProtocol()
    {
        return _nutritionProtocol;
    }

    
    /** 
     * Initializes a new instance of the {@link NutritionProtocolBO} class. 
     * @param nutritionProtocol
     */
    public NutritionProtocolBO(NutritionProtocol nutritionProtocol)
    {
        super();
        _nutritionProtocol = nutritionProtocol;
    }
    
    /**
     * @return
     * @see at.easydiet.model.NutritionProtocol#getDate()
     */
    public Date getDate()
    {
        return _nutritionProtocol.getDate();
    }

    /**
     * @param date
     * @see at.easydiet.model.NutritionProtocol#setDate(java.util.Date)
     */
    public void setDate(Date date)
    {
        _nutritionProtocol.setDate(date);
    }

    /**
     * @return
     * @see at.easydiet.model.NutritionProtocol#getContact()
     */
    public String getContact()
    {
        return _nutritionProtocol.getContact();
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getDietPlanId()
     */
    public long getDietPlanId()
    {
        return _nutritionProtocol.getDietPlanId();
    }

    /**
     * @param contact
     * @see at.easydiet.model.NutritionProtocol#setContact(java.lang.String)
     */
    public void setContact(String contact)
    {
        _nutritionProtocol.setContact(contact);
    }

    /**
     * @param dietPlanId
     * @see at.easydiet.model.DietPlan#setDietPlanId(long)
     */
    public void setDietPlanId(long dietPlanId)
    {
        _nutritionProtocol.setDietPlanId(dietPlanId);
    }

    /**
     * @return
     * @see at.easydiet.model.NutritionProtocol#getNotice()
     */
    public Clob getNotice()
    {
        return _nutritionProtocol.getNotice();
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getName()
     */
    public String getName()
    {
        return _nutritionProtocol.getName();
    }

    /**
     * @param notice
     * @see at.easydiet.model.NutritionProtocol#setNotice(java.sql.Clob)
     */
    public void setNotice(Clob notice)
    {
        _nutritionProtocol.setNotice(notice);
    }

    /**
     * @param name
     * @see at.easydiet.model.DietPlan#setName(java.lang.String)
     */
    public void setName(String name)
    {
        _nutritionProtocol.setName(name);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getCreatedOn()
     */
    public Date getCreatedOn()
    {
        return _nutritionProtocol.getCreatedOn();
    }

    /**
     * @param createdOn
     * @see at.easydiet.model.DietPlan#setCreatedOn(java.util.Date)
     */
    public void setCreatedOn(Date createdOn)
    {
        _nutritionProtocol.setCreatedOn(createdOn);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getPlanType()
     */
    public PlanType getPlanType()
    {
        return _nutritionProtocol.getPlanType();
    }

    /**
     * @param planType
     * @see at.easydiet.model.DietPlan#setPlanType(at.easydiet.model.PlanType)
     */
    public void setPlanType(PlanType planType)
    {
        _nutritionProtocol.setPlanType(planType);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getDietParameters()
     */
    public Set<DietParameter> getDietParameters()
    {
        return _nutritionProtocol.getDietParameters();
    }

    /**
     * @param dietParameters
     * @see at.easydiet.model.DietPlan#setDietParameters(java.util.Set)
     */
    public void setDietParameters(Set<DietParameter> dietParameters)
    {
        _nutritionProtocol.setDietParameters(dietParameters);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getCreator()
     */
    public SystemUser getCreator()
    {
        return _nutritionProtocol.getCreator();
    }

    /**
     * @param creator
     * @see at.easydiet.model.DietPlan#setCreator(at.easydiet.model.SystemUser)
     */
    public void setCreator(SystemUser creator)
    {
        _nutritionProtocol.setCreator(creator);
    }

    /**
     * @return
     * @see at.easydiet.model.DietPlan#getTimeSpans()
     */
    public Set<TimeSpan> getTimeSpans()
    {
        return _nutritionProtocol.getTimeSpans();
    }

    /**
     * @param timeSpans
     * @see at.easydiet.model.DietPlan#setTimeSpans(java.util.Set)
     */
    public void setTimeSpans(Set<TimeSpan> timeSpans)
    {
        _nutritionProtocol.setTimeSpans(timeSpans);
    }
    
    
}
