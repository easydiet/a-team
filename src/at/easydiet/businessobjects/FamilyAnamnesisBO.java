package at.easydiet.businessobjects;


import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.FamilyAnamnesis;
import at.easydiet.model.Illness;

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


	private List<IllnessBO> _illnesses;
	
    /**
     * Gets a list of referenced Illnesses of this instance.
     * This list is cached, use {@link FamilyAnamnesis#updateIllnessesCache()) to update this cache.
     * @return a cached list of referenced Illnesses wrapped into the correct businessobject. 
     */
    public List<IllnessBO> getIllnesses()
    {
        if(_illnesses == null) 
        {
            _illnesses = new ArrayList<IllnessBO>();
            for(Illness illnesses : _model.getIllnesses())
            {
                _illnesses.add(new IllnessBO(illnesses));
            }
        }
        return _illnesses;
    }
	
    /**
     * Adds a new Illness to the list of referenced illnesses.
     * The cache will updated
     * @param illnesses the Illness to add. 
     */
    public void addIllnesses(IllnessBO illnesses)
    {
        getIllnesses().add(illnesses);
        _model.getIllnesses().add(illnesses.getModel());
    }
    
        
    /**
     * Removes the given Illness from the list of referenced illnesses.
     * The cache will updated
     * @param illnesses the timespan to add. 
     */
    public void removeIllnesses(IllnessBO illnesses)
    {
        getIllnesses().remove(illnesses);
        _model.getIllnesses().remove(illnesses.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced illnesses.
     */
    public void updateIllnessesCache()
    {
        _illnesses = null;
        getIllnesses();
    }

}