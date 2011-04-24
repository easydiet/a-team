/**
 * This ${pojo.getDeclarationType()} encapsules a ${pojo.getDeclarationName()} instance.
 */
${pojo.getClassModifiers()} ${pojo.getDeclarationType()} ${pojo.getDeclarationName()}BO <#if pojo.getExtendsDeclaration() != "">${pojo.getExtendsDeclaration()}BO</#if>
{
	private ${pojo.getDeclarationName()} _model;
	
    /**
     * Initializes a new instance of the {@link ${pojo.getDeclarationName()}BO} ${pojo.getDeclarationType()}.
     */
	public ${pojo.getDeclarationName()}BO()
	{
		// TODO: add default values
		this(new ${pojo.getDeclarationName()}());
	}
	
    /**
     * Initializes a new instance of the {@link ${pojo.getDeclarationName()}BO} ${pojo.getDeclarationType()}.
     * @param model the original model object
     */
	public ${pojo.getDeclarationName()}BO(${pojo.getDeclarationName()} model)
	{<#if pojo.getExtendsDeclaration() != "">
		super(model)</#if>
		_model = model;
	}
	
	/**
	 * Gets the original model object used as object store for this BusinessObject.
	 * @return the original {@link ${pojo.getDeclarationName()}} object.
	 */
 	public ${pojo.getDeclarationName()} getModel()
	{
		return _model;
	}
	
<#include "BoPropertyAccessors.ftl"/>
}