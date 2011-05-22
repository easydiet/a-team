package at.easydiet.model;


/**
 * Represents a PatientLike
 */
public class PatientLike  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 5256207249128153092L;
    private long _patientLikeId;
    private Patient _patient;
    private String _blsPattern;
    private PatientLikeGrade _grade;
    private String _notice;

    /**
     * Initializes a new instance of the {@link PatientLike} class.
     */
    public PatientLike() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link PatientLike} class.
     * @param patient the patient to set for this instance
     * @param blsPattern the blsPattern to set for this instance
     * @param grade the grade to set for this instance
     */
    public PatientLike(Patient patient, String blsPattern, PatientLikeGrade grade) 
    {
        _patient = patient;
        _blsPattern = blsPattern;
        _grade = grade;
    }

    /**
     * Initializes a new instance of the {@link PatientLike} class.
     * @param patient the patient to set for this instance
     * @param blsPattern the blsPattern to set for this instance
     * @param grade the grade to set for this instance
     * @param notice the notice to set for this instance
     */
    public PatientLike(Patient patient, String blsPattern, PatientLikeGrade grade, String notice) 
    {
       _patient = patient;
       _blsPattern = blsPattern;
       _grade = grade;
       _notice = notice;
    }
   
    /**       
     * Gets the patientLikeId of this instance. 
     * @return the patientLikeId currently set for this instance.
     */
    public long getPatientLikeId() 
    {
        return _patientLikeId;
    }
    
    /**       
     * Sets the patientLikeId of this instance. 
     * @param patientLikeId the new patientLikeId of this instance.
     */    
    public void setPatientLikeId(long patientLikeId) 
    {
        _patientLikeId = patientLikeId;
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
     * Gets the blsPattern of this instance. 
     * @return the blsPattern currently set for this instance.
     */
    public String getBlsPattern() 
    {
        return _blsPattern;
    }
    
    /**       
     * Sets the blsPattern of this instance. 
     * @param blsPattern the new blsPattern of this instance.
     */    
    public void setBlsPattern(String blsPattern) 
    {
        _blsPattern = blsPattern;
    }
    
    /**       
     * Gets the grade of this instance. 
     * @return the grade currently set for this instance.
     */
    public PatientLikeGrade getGrade() 
    {
        return _grade;
    }
    
    /**       
     * Sets the grade of this instance. 
     * @param grade the new grade of this instance.
     */    
    public void setGrade(PatientLikeGrade grade) 
    {
        _grade = grade;
    }
    
    /**       
     * Gets the notice of this instance. 
     * @return the notice currently set for this instance.
     */
    public String getNotice() 
    {
        return _notice;
    }
    
    /**       
     * Sets the notice of this instance. 
     * @param notice the new notice of this instance.
     */    
    public void setNotice(String notice) 
    {
        _notice = notice;
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
