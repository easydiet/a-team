package at.easydiet.businessobjects;


import java.util.Set;

import at.easydiet.model.FamilyAnamnesis;

/**
 * This class encapsules a FamilyAnamnesis instance.
 */
public class FamilyAnamnesisBO
{
	private FamilyAnamnesis _model;
	
    /**
     * Initializes a new instance of the {@link FamilyAnamnesisBO} class.
     */
	public FamilyAnamnesisBO()
	{
		// TODO: add default values
		this(new FamilyAnamnesis());
	}
	
    /**
     * Initializes a new instance of the {@link FamilyAnamnesisBO} class.
     * @param model the original model object
     */
	public FamilyAnamnesisBO(FamilyAnamnesis model)
	{
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link FamilyAnamnesis} object.
	 */
 	public FamilyAnamnesis getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the familyAnamnesisId of this instance. 
     * @return the familyAnamnesisId currently set for this instance.
     */
    public long getFamilyAnamnesisId() 
    {
        return _model.getFamilyAnamnesisId();
    }
    
    /**       
     * Sets the familyAnamnesisId of this instance. 
     * @param familyAnamnesisId the new familyAnamnesisId of this instance.
     */    
    public void setFamilyAnamnesisId(long familyAnamnesisId) 
    {
        _model.setFamilyAnamnesisId(familyAnamnesisId);
    }

    /**       
     * Gets the person of this instance. 
     * @return the person currently set for this instance.
     */
    public String getPerson() 
    {
        return _model.getPerson();
    }
    
    /**       
     * Sets the person of this instance. 
     * @param person the new person of this instance.
     */    
    public void setPerson(String person) 
    {
        _model.setPerson(person);
    }

    /**       
     * Gets the illnesses of this instance. 
     * @return the illnesses currently set for this instance.
     */
    public Set<String> getIllnesses() 
    {
        return _model.getIllnesses();
    }
    
    /**       
     * Sets the illnesses of this instance. 
     * @param illnesses the new illnesses of this instance.
     */    
    public void setIllnesses(Set<String> illnesses) 
    {
        _model.setIllnesses(illnesses);
    }

}