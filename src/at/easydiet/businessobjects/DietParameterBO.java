package at.easydiet.businessobjects;


import java.util.Date;

import at.easydiet.model.DietParameter;

/**
 * This class encapsules a DietParameter instance.
 */
public class DietParameterBO extends DietParameterTemplateBO
{
	private DietParameter _model;
	
    /**
     * Initializes a new instance of the {@link DietParameterBO} class.
     */
	public DietParameterBO()
	{
		// TODO: add default values
		this(new DietParameter());
	}
	
    /**
     * Initializes a new instance of the {@link DietParameterBO} class.
     * @param model the original model object
     */
	public DietParameterBO(DietParameter model)
	{
	    super(model);
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link DietParameter} object.
	 */
 	public DietParameter getModel()
	{
		return _model;
	}
	
    /**       
     * Gets the start of this instance. 
     * @return the start currently set for this instance.
     */
    public Date getStart() 
    {
        return _model.getStart();
    }
    
    /**       
     * Sets the start of this instance. 
     * @param start the new start of this instance.
     */    
    public void setStart(Date start) 
    {
        _model.setStart(start);
    }
    
    public String toString()
    {
    	StringBuilder builder = new StringBuilder();
        builder.append(getClass().getName()).append("@").append(Integer.toHexString(hashCode())).append(" [");
		// interesting values
        builder.append("DietParameterTemplateID").append("='").append(getDietParameterTemplateId()).append("' ");		
        builder.append("Duration").append("='").append(getDuration()).append("' ");		
        builder.append("Value").append("='").append(getValue()).append("' ");
        builder.append("CheckOperator").append("='").append(getCheckOperator()).append("' ");
        builder.append("DietParameterType").append("='").append(getDietParameterType()).append("' ");	
        builder.append("ParameterDefinition").append("='").append(getParameterDefinition()).append("' ");		
        builder.append("ParameterDefinitionUnit").append("='").append(getParameterDefinitionUnit()).append("' ");
        builder.append("Start").append("='").append(getStart()).append("' ");		
        builder.append("]");
      
        return builder.toString();
    }

    public float getFloatValue()
    {
        // TODO: Mention datatype here!
        try
        {
            return Float.valueOf(getValue());
        }
        catch(Exception e)
        {
            return 0;
        }
    }

}