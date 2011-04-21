package at.easydiet.businessobjects;

import at.easydiet.model.DietParameterType;

public class DietParameterTypeBO {
	private DietParameterType _dietParameterType;
	
	public DietParameterTypeBO(DietParameterType dietParameterType) {
		_dietParameterType =  dietParameterType;
	}
   
    /**       
     * Gets the name of this instance. 
     * @return the name currently set for this instance.
     */
    public String getName() 
    {
        return _dietParameterType.getName();
    }
    
    /**       
     * Sets the name of this instance. 
     * @param name the new name of this instance.
     */    
    public void setName(String name) 
    {
        _dietParameterType.setName(name);
    }
}
