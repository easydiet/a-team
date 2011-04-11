    /**
     * Initializes a new instance of the {@link ${pojo.getDeclarationName()}} ${pojo.getDeclarationType()}.
     */
    public ${pojo.getDeclarationName()}() 
    {
        // no initialization
    }

<#if pojo.needsMinimalConstructor()>	
    /**
     * Initializes a new instance of the {@link ${pojo.getDeclarationName()}} ${pojo.getDeclarationType()}.
<#foreach field in pojo.getPropertiesForMinimalConstructor()>
     * @param ${field.name} the ${field.name} to set for this instance
</#foreach>     
     */
    public ${pojo.getDeclarationName()}(${c2j.asParameterList(pojo.getPropertyClosureForMinimalConstructor(), jdk5, pojo)}) 
    {
<#if pojo.isSubclass() && !pojo.getPropertyClosureForSuperclassMinimalConstructor().isEmpty()>
        super(${c2j.asArgumentList(pojo.getPropertyClosureForSuperclassMinimalConstructor())});        
</#if>
<#foreach field in pojo.getPropertiesForMinimalConstructor()>
        _${field.name} = ${field.name};
</#foreach>
    }
</#if>    

<#if pojo.needsFullConstructor()>
    /**
     * Initializes a new instance of the {@link ${pojo.getDeclarationName()}} ${pojo.getDeclarationType()}.
<#foreach field in pojo.getPropertiesForFullConstructor()>
     * @param ${field.name} the ${field.name} to set for this instance
</#foreach>     
     */
    public ${pojo.getDeclarationName()}(${c2j.asParameterList(pojo.getPropertyClosureForFullConstructor(), jdk5, pojo)}) 
    {
<#if pojo.isSubclass() && !pojo.getPropertyClosureForSuperclassFullConstructor().isEmpty()>
        super(${c2j.asArgumentList(pojo.getPropertyClosureForSuperclassFullConstructor())});        
</#if>
<#foreach field in pojo.getPropertiesForFullConstructor()> 
       _${field.name} = ${field.name};
</#foreach>
    }
</#if>