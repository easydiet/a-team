package at.easydiet.businessobjects;

import java.util.Set;

import at.easydiet.model.DietParameterSet;
import at.easydiet.model.DietParameterTemplate;

public class DietParameterSetBO
{
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
    .getLogger(DietPlanBO.class);

	private DietParameterSet	_dietParameterSet;

	public DietParameterSetBO(DietParameterSet dietParameterSet) {
		super();
		_dietParameterSet = dietParameterSet;
	}

	public DietParameterSet getDietParameterSet() {
		return _dietParameterSet;
	}
	
	 /**       
     * Gets the dietParameterSetId of this instance. 
     * @return the dietParameterSetId currently set for this instance.
     */
    public long getDietParameterSetId() 
    {
        return _dietParameterSet.getDietParameterSetId();
    }
    
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _dietParameterSet.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _dietParameterSet.setName(name);
    }
    
    /**       
     * Gets the dietParameterTemplates of this instance. 
     * @return the dietParameterTemplates currently set for this instance.
     */
    public Set<DietParameterTemplate> getDietParameterTemplates() 
    {
        return _dietParameterSet.getDietParameterTemplates();
    }
    
    /**       
     * Sets the dietParameterTemplates of this instance. 
     * @param dietParameterTemplates the new dietParameterTemplates of this instance.
     */    
    public void setDietParameterTemplates(Set<DietParameterTemplate> dietParameterTemplates) 
    {
        _dietParameterSet.setDietParameterTemplates(dietParameterTemplates);
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
