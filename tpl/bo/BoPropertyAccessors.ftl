<#foreach property in pojo.getAllPropertiesIterator()>
<#assign propType = pojo.getJavaTypeName(property, false)>
<#assign fullPropType = pojo.getPackageName() + "." + propType>
<#assign genericType = pojo.getJavaTypeName(property, true).replace("Set<", "").replace("List<", "").replace(">", "")>
<#function isPrimitive type>
	<#return (type == "byte" || type == "short" || type == "int" ||
			type == "long" || type == "float" || type == "double" ||
			type == "boolean" || type == "char" || type == "String" ||
			type == "Date" || type == "Clob")>
</#function>
<#if (propType == "Set" || propType == "List") && !isPrimitive(genericType)>

	private List<${genericType}BO> _${property.name};
	
    /**
     * Gets a list of referenced ${pojo.getPropertyName(property)} of this instance.
     * This list is cached, use {@link ${pojo.getDeclarationName()}#update${pojo.getPropertyName(property)}Cache()) to update this cache.
     * @return a cached list of referenced ${pojo.getPropertyName(property)} wrapped into the correct businessobject. 
     */
    public List<${genericType}BO> get${pojo.getPropertyName(property)}()
    {
        if(_${property.name} == null) 
        {
            _${property.name} = new ArrayList<${genericType}BO>();
            for(${genericType} ${property.name} : _model.get${pojo.getPropertyName(property)}())
            {
                _${property.name}.add(new ${genericType}BO(${property.name}));
            }
        }
        return _${property.name};
    }
	
    /**
     * Adds a new ${genericType} to the list of referenced ${property.name}.
     * The cache will updated
     * @param ${property.name} the ${genericType} to add. 
     */
    public void add${pojo.getPropertyName(property)}(${genericType}BO ${property.name})
    {
        get${pojo.getPropertyName(property)}().add(${property.name});
        _model.get${pojo.getPropertyName(property)}().add(${property.name}.getModel());
    }
    
        
    /**
     * Removes the given ${genericType} from the list of referenced ${property.name}.
     * The cache will updated
     * @param ${property.name} the timespan to add. 
     */
    public void remove${pojo.getPropertyName(property)}(${genericType}BO ${property.name})
    {
        get${pojo.getPropertyName(property)}().remove(${property.name});
        _model.get${pojo.getPropertyName(property)}().remove(${property.name}.getModel());
    }
	
    /**
     * Rebuilds the cache for referenced ${property.name}.
     */
    public void update${pojo.getPropertyName(property)}Cache()
    {
        _${property.name} = null;
        get${pojo.getPropertyName(property)}();
    }

<#elseif propType != "Set" && propType != "List" && !isPrimitive(propType)>
	
    private ${propType}BO _${property.name};
    
    /**
     * Gets the currently referenced ${pojo.getPropertyName(property)} of this instance.
     * @return the ${genericType} currently referenced in this ${pojo.getDeclarationName()}. 
     */
    public ${propType}BO get${pojo.getPropertyName(property)}()
    {
        if(_${property.name} == null)
        {
<#if property.value.mappings.getClass(pojo.getPackageName() + "." + propType).metaAttributes.containsKey("is-enum")>
            _${property.name} = ${propType}BO.getForModel(_model.get${pojo.getPropertyName(property)}());
<#else>
            _${property.name} = new ${propType}BO(_model.get${pojo.getPropertyName(property)}());
</#if>
        }
        return _${property.name};
    }
    
    /**
     * Sets the ${pojo.getPropertyName(property)} to be referenced in this instance
     * @param ${property.name} the ${genericType} to reference in this ${pojo.getDeclarationName()}. 
     */
    public void set${pojo.getPropertyName(property)}(${propType}BO ${property.name})
    {
        _${property.name} = ${property.name};
        _model.set${pojo.getPropertyName(property)}(${property.name}.getModel());
    }
<#else>
    /**       
<#if pojo.getJavaTypeName(property, jdk5) == "boolean">	
     * Gets a value indicating whether this instance is ${property.name}
     * @return true if this instance is ${property.name}, otherwise false
<#else>
     * Gets the ${property.name} of this instance. 
     * @return the ${property.name} currently set for this instance.
</#if>
     */
    ${pojo.getPropertyGetModifiers(property)} ${pojo.getJavaTypeName(property, jdk5)} get${pojo.getPropertyName(property)}() 
    {
        return _model.get${pojo.getPropertyName(property)}();
    }
    
    /**       
<#if pojo.getJavaTypeName(property, jdk5) == "boolean">	
     * Sets a value indicating whether this instance is ${property.name}
     * @param ${property.name} true if this instance is ${property.name}, otherwise false
<#else>
     * Sets the ${property.name} of this instance. 
     * @param ${property.name} the new ${property.name} of this instance.
</#if>
     */    
    ${pojo.getPropertySetModifiers(property)} void set${pojo.getPropertyName(property)}(${pojo.getJavaTypeName(property, jdk5)} ${property.name}) 
    {
        _model.set${pojo.getPropertyName(property)}(${property.name});
    }

</#if> 
</#foreach>