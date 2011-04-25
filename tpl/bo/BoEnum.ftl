/**
 * This ${pojo.getDeclarationType()} encapsules a ${pojo.getDeclarationName()} instance.
 */
${pojo.getClassModifiers()} enum ${pojo.getDeclarationName()}BO
{
	// TODO: Add correct values here
	DEFAULT("")
	;

	private ${pojo.getDeclarationName()} _model;

    /**
     * Initializes a new instance of the {@link ${pojo.getDeclarationName()}BO} enum.
     * @param model the enum value
     */
	private ${pojo.getDeclarationName()}BO(String value)
	{
		_model = new ${pojo.getDeclarationName()}(value);
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link ${pojo.getDeclarationName()}} object.
	 */
 	public ${pojo.getDeclarationName()} getModel()
	{
		return _model;
	}
	
	/**
	 * Gets the name of this enum value.
	 * @return the name of this enum value
	 */
	public String getName()
	{
		return _model.getName();
	}
	
	/**
	 * Returns the BusinessObject matching to the specified model object.
	 * @returns the enum value matching to the given model or the default value. 
	 */
	public static ${pojo.getDeclarationName()}BO getForModel(${pojo.getDeclarationName()} model)
	{
		for(${pojo.getDeclarationName()}BO bo : values())
		{
			if(bo.getModel().getName().equals(model.getName()))
			{
				return bo;
			}
		}
		return DEFAULT;
	}
}