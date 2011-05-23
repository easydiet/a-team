package at.easydiet.model;

import java.sql.Clob;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a PatientState
 */
public class PatientState  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = -8596807076914792722L;
    private long _patientStateId;
    private Date _date;
    private Clob _anamnesis;
    private int _weight;
    private float _weightPercentile;
    private int _height;
    private float _heightPercentile;
    private int _compliance;
    private int _motivation;
    private PatientStateType _type;
    private SystemUser _creator;
    private Set<LaborReport> _laborReports = new HashSet<LaborReport>(0);
    private Set<DietTreatment> _dietTreatments = new HashSet<DietTreatment>(0);
    private Patient _patient;

    /**
     * Initializes a new instance of the {@link PatientState} class.
     */
    public PatientState() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link PatientState} class.
     * @param date the date to set for this instance
     * @param type the type to set for this instance
     * @param creator the creator to set for this instance
     * @param patient the patient to set for this instance
     */
    public PatientState(Date date, PatientStateType type, SystemUser creator, Patient patient) 
    {
        _date = date;
        _type = type;
        _creator = creator;
        _patient = patient;
    }

    /**
     * Initializes a new instance of the {@link PatientState} class.
     * @param date the date to set for this instance
     * @param anamnesis the anamnesis to set for this instance
     * @param weight the weight to set for this instance
     * @param weightPercentile the weightPercentile to set for this instance
     * @param height the height to set for this instance
     * @param heightPercentile the heightPercentile to set for this instance
     * @param compliance the compliance to set for this instance
     * @param motivation the motivation to set for this instance
     * @param type the type to set for this instance
     * @param creator the creator to set for this instance
     * @param laborReports the laborReports to set for this instance
     * @param dietTreatments the dietTreatments to set for this instance
     * @param patient the patient to set for this instance
     */
    public PatientState(Date date, Clob anamnesis, int weight, float weightPercentile, int height, float heightPercentile, int compliance, int motivation, PatientStateType type, SystemUser creator, Set<LaborReport> laborReports, Set<DietTreatment> dietTreatments, Patient patient) 
    {
       _date = date;
       _anamnesis = anamnesis;
       _weight = weight;
       _weightPercentile = weightPercentile;
       _height = height;
       _heightPercentile = heightPercentile;
       _compliance = compliance;
       _motivation = motivation;
       _type = type;
       _creator = creator;
       _laborReports = laborReports;
       _dietTreatments = dietTreatments;
       _patient = patient;
    }
   
    /**       
     * Gets the patientStateId of this instance. 
     * @return the patientStateId currently set for this instance.
     */
    public long getPatientStateId() 
    {
        return _patientStateId;
    }
    
    /**       
     * Sets the patientStateId of this instance. 
     * @param patientStateId the new patientStateId of this instance.
     */    
    public void setPatientStateId(long patientStateId) 
    {
        _patientStateId = patientStateId;
    }
    
    /**       
     * Gets the date of this instance. 
     * @return the date currently set for this instance.
     */
    public Date getDate() 
    {
        return _date;
    }
    
    /**       
     * Sets the date of this instance. 
     * @param date the new date of this instance.
     */    
    public void setDate(Date date) 
    {
        _date = date;
    }
    
    /**       
     * Gets the anamnesis of this instance. 
     * @return the anamnesis currently set for this instance.
     */
    public Clob getAnamnesis() 
    {
        return _anamnesis;
    }
    
    /**       
     * Sets the anamnesis of this instance. 
     * @param anamnesis the new anamnesis of this instance.
     */    
    public void setAnamnesis(Clob anamnesis) 
    {
        _anamnesis = anamnesis;
    }
    
    /**       
     * Gets the weight of this instance. 
     * @return the weight currently set for this instance.
     */
    public int getWeight() 
    {
        return _weight;
    }
    
    /**       
     * Sets the weight of this instance. 
     * @param weight the new weight of this instance.
     */    
    public void setWeight(int weight) 
    {
        _weight = weight;
    }
    
    /**       
     * Gets the weightPercentile of this instance. 
     * @return the weightPercentile currently set for this instance.
     */
    public float getWeightPercentile() 
    {
        return _weightPercentile;
    }
    
    /**       
     * Sets the weightPercentile of this instance. 
     * @param weightPercentile the new weightPercentile of this instance.
     */    
    public void setWeightPercentile(float weightPercentile) 
    {
        _weightPercentile = weightPercentile;
    }
    
    /**       
     * Gets the height of this instance. 
     * @return the height currently set for this instance.
     */
    public int getHeight() 
    {
        return _height;
    }
    
    /**       
     * Sets the height of this instance. 
     * @param height the new height of this instance.
     */    
    public void setHeight(int height) 
    {
        _height = height;
    }
    
    /**       
     * Gets the heightPercentile of this instance. 
     * @return the heightPercentile currently set for this instance.
     */
    public float getHeightPercentile() 
    {
        return _heightPercentile;
    }
    
    /**       
     * Sets the heightPercentile of this instance. 
     * @param heightPercentile the new heightPercentile of this instance.
     */    
    public void setHeightPercentile(float heightPercentile) 
    {
        _heightPercentile = heightPercentile;
    }
    
    /**       
     * Gets the compliance of this instance. 
     * @return the compliance currently set for this instance.
     */
    public int getCompliance() 
    {
        return _compliance;
    }
    
    /**       
     * Sets the compliance of this instance. 
     * @param compliance the new compliance of this instance.
     */    
    public void setCompliance(int compliance) 
    {
        _compliance = compliance;
    }
    
    /**       
     * Gets the motivation of this instance. 
     * @return the motivation currently set for this instance.
     */
    public int getMotivation() 
    {
        return _motivation;
    }
    
    /**       
     * Sets the motivation of this instance. 
     * @param motivation the new motivation of this instance.
     */    
    public void setMotivation(int motivation) 
    {
        _motivation = motivation;
    }
    
    /**       
     * Gets the type of this instance. 
     * @return the type currently set for this instance.
     */
    public PatientStateType getType() 
    {
        return _type;
    }
    
    /**       
     * Sets the type of this instance. 
     * @param type the new type of this instance.
     */    
    public void setType(PatientStateType type) 
    {
        _type = type;
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
     * Gets the laborReports of this instance. 
     * @return the laborReports currently set for this instance.
     */
    public Set<LaborReport> getLaborReports() 
    {
        return _laborReports;
    }
    
    /**       
     * Sets the laborReports of this instance. 
     * @param laborReports the new laborReports of this instance.
     */    
    public void setLaborReports(Set<LaborReport> laborReports) 
    {
        _laborReports = laborReports;
    }
    
    /**       
     * Gets the dietTreatments of this instance. 
     * @return the dietTreatments currently set for this instance.
     */
    public Set<DietTreatment> getDietTreatments() 
    {
        return _dietTreatments;
    }
    
    /**       
     * Sets the dietTreatments of this instance. 
     * @param dietTreatments the new dietTreatments of this instance.
     */    
    public void setDietTreatments(Set<DietTreatment> dietTreatments) 
    {
        _dietTreatments = dietTreatments;
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
