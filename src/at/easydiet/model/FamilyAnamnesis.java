package at.easydiet.model;


/**
 * Represents a FamilyAnamnesis
 */
public class FamilyAnamnesis  implements java.io.Serializable
{

    /**
     * A unique serialization id. 
     */
    private static final long serialVersionUID = 5503821760732964686L;
    private long _familyAnamnesisId;
    private String _person;
    private String[] _illnesses;

    /**
     * Initializes a new instance of the {@link FamilyAnamnesis} class.
     */
    public FamilyAnamnesis() 
    {
        // no initialization
    }

    /**
     * Initializes a new instance of the {@link FamilyAnamnesis} class.
     * @param person the person to set for this instance
     */
    public FamilyAnamnesis(String person) 
    {
        _person = person;
    }

    /**
     * Initializes a new instance of the {@link FamilyAnamnesis} class.
     * @param person the person to set for this instance
     * @param illnesses the illnesses to set for this instance
     */
    public FamilyAnamnesis(String person, String[] illnesses) 
    {
       _person = person;
       _illnesses = illnesses;
    }
   
    /**       
     * Gets the familyAnamnesisId of this instance. 
     * @return the familyAnamnesisId currently set for this instance.
     */
    public long getFamilyAnamnesisId() 
    {
        return _familyAnamnesisId;
    }
    
    /**       
     * Sets the familyAnamnesisId of this instance. 
     * @param familyAnamnesisId the new familyAnamnesisId of this instance.
     */    
    public void setFamilyAnamnesisId(long familyAnamnesisId) 
    {
        _familyAnamnesisId = familyAnamnesisId;
    }
    
    /**       
     * Gets the person of this instance. 
     * @return the person currently set for this instance.
     */
    public String getPerson() 
    {
        return _person;
    }
    
    /**       
     * Sets the person of this instance. 
     * @param person the new person of this instance.
     */    
    public void setPerson(String person) 
    {
        _person = person;
    }
    
    /**       
     * Gets the illnesses of this instance. 
     * @return the illnesses currently set for this instance.
     */
    public String[] getIllnesses() 
    {
        return _illnesses;
    }
    
    /**       
     * Sets the illnesses of this instance. 
     * @param illnesses the new illnesses of this instance.
     */    
    public void setIllnesses(String[] illnesses) 
    {
        _illnesses = illnesses;
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
