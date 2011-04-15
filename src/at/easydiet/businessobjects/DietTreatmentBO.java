package at.easydiet.businessobjects;

import java.util.Calendar;
import java.util.Date;
import java.util.Set;

import at.easydiet.model.ContactJournal;
import at.easydiet.model.DietParameter;
import at.easydiet.model.DietPlan;
import at.easydiet.model.DietTreatment;
import at.easydiet.model.DietTreatmentSystemUser;
import at.easydiet.model.NutritionProtocol;
import at.easydiet.model.PatientState;
import at.easydiet.model.TreatmentState;

public class DietTreatmentBO
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietTreatmentBO.class);

    private DietTreatment                       _treatment;
    private Date                                _end;

    /**
     * Initializes a new instance of the {@link DietTreatmentBO} class.
     * @param treatment
     */
    public DietTreatmentBO(DietTreatment treatment)
    {
        super();
        _treatment = treatment;
    }

    /**
     * Gets the treatment.
     * @return the treatment
     */
    public DietTreatment getTreatment()
    {
        return _treatment;
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getDietTreatmentId()
     */
    public long getDietTreatmentId()
    {
        return _treatment.getDietTreatmentId();
    }

    /**
     * @param dietTreatmentId
     * @see at.easydiet.model.DietTreatment#setDietTreatmentId(long)
     */
    public void setDietTreatmentId(long dietTreatmentId)
    {
        _treatment.setDietTreatmentId(dietTreatmentId);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getStart()
     */
    public Date getStart()
    {
        return _treatment.getStart();
    }

    /**
     * @param start
     * @see at.easydiet.model.DietTreatment#setStart(java.util.Date)
     */
    public void setStart(Date start)
    {
        _treatment.setStart(start);
    }

    /**
     * Returns the end date of the diet.
     * @return
     */
    public Date getEnd()
    {
        if (_end == null)
        {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(getStart());
            calendar.add(Calendar.DATE, getDuration());
            _end = calendar.getTime();
        }
        return _end;
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getDuration()
     */
    public int getDuration()
    {
        return _treatment.getDuration();
    }

    /**
     * @param duration
     * @see at.easydiet.model.DietTreatment#setDuration(int)
     */
    public void setDuration(int duration)
    {
        _treatment.setDuration(duration);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getName()
     */
    public String getName()
    {
        return _treatment.getName();
    }

    /**
     * @param name
     * @see at.easydiet.model.DietTreatment#setName(java.lang.String)
     */
    public void setName(String name)
    {
        _treatment.setName(name);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getNutritionProtocols()
     */
    public Set<NutritionProtocol> getNutritionProtocols()
    {
        return _treatment.getNutritionProtocols();
    }

    /**
     * @param nutritionProtocols
     * @see at.easydiet.model.DietTreatment#setNutritionProtocols(java.util.Set)
     */
    public void setNutritionProtocols(Set<NutritionProtocol> nutritionProtocols)
    {
        _treatment.setNutritionProtocols(nutritionProtocols);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getDietPlans()
     */
    public Set<DietPlan> getDietPlans()
    {
        return _treatment.getDietPlans();
    }

    /**
     * @param dietPlans
     * @see at.easydiet.model.DietTreatment#setDietPlans(java.util.Set)
     */
    public void setDietPlans(Set<DietPlan> dietPlans)
    {
        _treatment.setDietPlans(dietPlans);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getDietParameters()
     */
    public Set<DietParameter> getDietParameters()
    {
        return _treatment.getDietParameters();
    }

    /**
     * @param dietParameters
     * @see at.easydiet.model.DietTreatment#setDietParameters(java.util.Set)
     */
    public void setDietParameters(Set<DietParameter> dietParameters)
    {
        _treatment.setDietParameters(dietParameters);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getPatientStates()
     */
    public Set<PatientState> getPatientStates()
    {
        return _treatment.getPatientStates();
    }

    /**
     * @param patientStates
     * @see at.easydiet.model.DietTreatment#setPatientStates(java.util.Set)
     */
    public void setPatientStates(Set<PatientState> patientStates)
    {
        _treatment.setPatientStates(patientStates);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getSystemUsers()
     */
    public Set<DietTreatmentSystemUser> getSystemUsers()
    {
        return _treatment.getSystemUsers();
    }

    /**
     * @param systemUsers
     * @see at.easydiet.model.DietTreatment#setSystemUsers(java.util.Set)
     */
    public void setSystemUsers(Set<DietTreatmentSystemUser> systemUsers)
    {
        _treatment.setSystemUsers(systemUsers);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getContactJournals()
     */
    public Set<ContactJournal> getContactJournals()
    {
        return _treatment.getContactJournals();
    }

    /**
     * @param contactJournals
     * @see at.easydiet.model.DietTreatment#setContactJournals(java.util.Set)
     */
    public void setContactJournals(Set<ContactJournal> contactJournals)
    {
        _treatment.setContactJournals(contactJournals);
    }

    /**
     * @return
     * @see at.easydiet.model.DietTreatment#getTreatmentState()
     */
    public TreatmentState getTreatmentState()
    {
        return _treatment.getTreatmentState();
    }

    /**
     * @param treatmentState
     * @see at.easydiet.model.DietTreatment#setTreatmentState(at.easydiet.model.TreatmentState)
     */
    public void setTreatmentState(TreatmentState treatmentState)
    {
        _treatment.setTreatmentState(treatmentState);
    }

}
