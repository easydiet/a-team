package at.easydiet.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a DietTreatment
 */
public class DietTreatment  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -3443749060591605997L;
    private long _dietTreatmentId;
    private Date _start;
    private int _duration;
    private String _name;
    private String _shortDescription;
    private Set<NutritionProtocol> _nutritionProtocols = new HashSet<NutritionProtocol>(0);
    private Set<DietPlan> _dietPlans = new HashSet<DietPlan>(0);
    private Set<DietParameter> _dietParameters = new HashSet<DietParameter>(0);
    private Set<PatientState> _patientStates = new HashSet<PatientState>(0);
    private Set<DietTreatmentSystemUser> _systemUsers = new HashSet<DietTreatmentSystemUser>(0);
    private Set<ContactJournal> _contactJournals = new HashSet<ContactJournal>(0);
    private TreatmentState _treatmentState;
    private Patient _patient;

    /**
     * Initializes a new instance of the {@link DietTreatment} class.
     */
    public DietTreatment() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link DietTreatment} class.
     * @param start the start to set for this instance
     * @param duration the duration to set for this instance
     * @param name the name to set for this instance
     * @param treatmentState the treatmentState to set for this instance
     * @param patient the patient to set for this instance
     */
    public DietTreatment(Date start, int duration, String name, TreatmentState treatmentState, Patient patient) 
    {
        _start = start;
        _duration = duration;
        _name = name;
        _treatmentState = treatmentState;
        _patient = patient;
    }

    /**
     * Initializes a new instance of the {@link DietTreatment} class.
     * @param start the start to set for this instance
     * @param duration the duration to set for this instance
     * @param name the name to set for this instance
     * @param shortDescription the shortDescription to set for this instance
     * @param nutritionProtocols the nutritionProtocols to set for this instance
     * @param dietPlans the dietPlans to set for this instance
     * @param dietParameters the dietParameters to set for this instance
     * @param patientStates the patientStates to set for this instance
     * @param systemUsers the systemUsers to set for this instance
     * @param contactJournals the contactJournals to set for this instance
     * @param treatmentState the treatmentState to set for this instance
     * @param patient the patient to set for this instance
     */
    public DietTreatment(Date start, int duration, String name, String shortDescription, Set<NutritionProtocol> nutritionProtocols, Set<DietPlan> dietPlans, Set<DietParameter> dietParameters, Set<PatientState> patientStates, Set<DietTreatmentSystemUser> systemUsers, Set<ContactJournal> contactJournals, TreatmentState treatmentState, Patient patient) 
    {
       _start = start;
       _duration = duration;
       _name = name;
       _shortDescription = shortDescription;
       _nutritionProtocols = nutritionProtocols;
       _dietPlans = dietPlans;
       _dietParameters = dietParameters;
       _patientStates = patientStates;
       _systemUsers = systemUsers;
       _contactJournals = contactJournals;
       _treatmentState = treatmentState;
       _patient = patient;
    }
   
    /**       
     * Gets the dietTreatmentId of this instance. 
     * @return the dietTreatmentId currently set for this instance.
     */
    public long getDietTreatmentId() 
    {
        return _dietTreatmentId;
    }
    
    /**       
     * Sets the dietTreatmentId of this instance. 
     * @param dietTreatmentId the new dietTreatmentId of this instance.
     */    
    public void setDietTreatmentId(long dietTreatmentId) 
    {
        _dietTreatmentId = dietTreatmentId;
    }
    
    /**       
     * Gets the start of this instance. 
     * @return the start currently set for this instance.
     */
    public Date getStart() 
    {
        return _start;
    }
    
    /**       
     * Sets the start of this instance. 
     * @param start the new start of this instance.
     */    
    public void setStart(Date start) 
    {
        _start = start;
    }
    
    /**       
     * Gets the duration of this instance. 
     * @return the duration currently set for this instance.
     */
    public int getDuration() 
    {
        return _duration;
    }
    
    /**       
     * Sets the duration of this instance. 
     * @param duration the new duration of this instance.
     */    
    public void setDuration(int duration) 
    {
        _duration = duration;
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
     * Gets the shortDescription of this instance. 
     * @return the shortDescription currently set for this instance.
     */
    public String getShortDescription() 
    {
        return _shortDescription;
    }
    
    /**       
     * Sets the shortDescription of this instance. 
     * @param shortDescription the new shortDescription of this instance.
     */    
    public void setShortDescription(String shortDescription) 
    {
        _shortDescription = shortDescription;
    }
    
    /**       
     * Gets the nutritionProtocols of this instance. 
     * @return the nutritionProtocols currently set for this instance.
     */
    public Set<NutritionProtocol> getNutritionProtocols() 
    {
        return _nutritionProtocols;
    }
    
    /**       
     * Sets the nutritionProtocols of this instance. 
     * @param nutritionProtocols the new nutritionProtocols of this instance.
     */    
    public void setNutritionProtocols(Set<NutritionProtocol> nutritionProtocols) 
    {
        _nutritionProtocols = nutritionProtocols;
    }
    
    /**       
     * Gets the dietPlans of this instance. 
     * @return the dietPlans currently set for this instance.
     */
    public Set<DietPlan> getDietPlans() 
    {
        return _dietPlans;
    }
    
    /**       
     * Sets the dietPlans of this instance. 
     * @param dietPlans the new dietPlans of this instance.
     */    
    public void setDietPlans(Set<DietPlan> dietPlans) 
    {
        _dietPlans = dietPlans;
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
     * Gets the patientStates of this instance. 
     * @return the patientStates currently set for this instance.
     */
    public Set<PatientState> getPatientStates() 
    {
        return _patientStates;
    }
    
    /**       
     * Sets the patientStates of this instance. 
     * @param patientStates the new patientStates of this instance.
     */    
    public void setPatientStates(Set<PatientState> patientStates) 
    {
        _patientStates = patientStates;
    }
    
    /**       
     * Gets the systemUsers of this instance. 
     * @return the systemUsers currently set for this instance.
     */
    public Set<DietTreatmentSystemUser> getSystemUsers() 
    {
        return _systemUsers;
    }
    
    /**       
     * Sets the systemUsers of this instance. 
     * @param systemUsers the new systemUsers of this instance.
     */    
    public void setSystemUsers(Set<DietTreatmentSystemUser> systemUsers) 
    {
        _systemUsers = systemUsers;
    }
    
    /**       
     * Gets the contactJournals of this instance. 
     * @return the contactJournals currently set for this instance.
     */
    public Set<ContactJournal> getContactJournals() 
    {
        return _contactJournals;
    }
    
    /**       
     * Sets the contactJournals of this instance. 
     * @param contactJournals the new contactJournals of this instance.
     */    
    public void setContactJournals(Set<ContactJournal> contactJournals) 
    {
        _contactJournals = contactJournals;
    }
    
    /**       
     * Gets the treatmentState of this instance. 
     * @return the treatmentState currently set for this instance.
     */
    public TreatmentState getTreatmentState() 
    {
        return _treatmentState;
    }
    
    /**       
     * Sets the treatmentState of this instance. 
     * @param treatmentState the new treatmentState of this instance.
     */    
    public void setTreatmentState(TreatmentState treatmentState) 
    {
        _treatmentState = treatmentState;
    }
    
    /**       
     * Gets the patient of this instance. 
     * @return the patient currently set for this instance.
     */
    public Patient getPatient() 
    {
        return _patient;
    }
    
    /**       
     * Sets the patient of this instance. 
     * @param patient the new patient of this instance.
     */    
    public void setPatient(Patient patient) 
    {
        _patient = patient;
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
