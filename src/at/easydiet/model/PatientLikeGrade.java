package at.easydiet.model;


/**
 * Represents a PatientLikeGrade
 */
public class PatientLikeGrade  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 4976665902101126051L;
    private long _patientLikeGradeId;
    private String _name;

    /**
     * Initializes a new instance of the {@link PatientLikeGrade} class.
     */
    public PatientLikeGrade() 
    {
        // no initialization
    }


    /**
     * Initializes a new instance of the {@link PatientLikeGrade} class.
     * @param patientLikeGradeId the patientLikeGradeId to set for this instance
     * @param name the name to set for this instance
     */
    public PatientLikeGrade(long patientLikeGradeId, String name) 
    {
       _patientLikeGradeId = patientLikeGradeId;
       _name = name;
    }
   
    /**       
     * Gets the patientLikeGradeId of this instance. 
     * @return the patientLikeGradeId currently set for this instance.
     */
    public long getPatientLikeGradeId() 
    {
        return _patientLikeGradeId;
    }
    
    /**       
     * Sets the patientLikeGradeId of this instance. 
     * @param patientLikeGradeId the new patientLikeGradeId of this instance.
     */    
    public void setPatientLikeGradeId(long patientLikeGradeId) 
    {
        _patientLikeGradeId = patientLikeGradeId;
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
