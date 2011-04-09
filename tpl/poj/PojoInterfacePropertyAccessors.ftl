<#foreach property in pojo.getAllPropertiesIterator()>
    /**       
<#if pojo.getJavaTypeName(property, jdk5) == "boolean">	
     * Gets a value indicating whether this instance is ${property.name}
     * @return true if this instance is ${property.name}, otherwise false
<#else>
     * Gets the ${property.name} of this instance. 
     * @return the ${property.name} currently set for this instance.
</#if>
     */
    ${pojo.getPropertyGetModifiers(property)} ${pojo.getJavaTypeName(property, jdk5)} ${pojo.getGetterSignature(property)}();
    
    /**       
<#if pojo.getJavaTypeName(property, jdk5) == "boolean">	
     * Sets a value indicating whether this instance is ${property.name}
     * @param ${property.name} true if this instance is ${property.name}, otherwise false
<#else>
     * Sets the ${property.name} of this instance. 
     * @param ${property.name} the new ${property.name} of this instance.
</#if>
     */    
    ${pojo.getPropertySetModifiers(property)} void set${pojo.getPropertyName(property)}(${pojo.getJavaTypeName(property, jdk5)} ${property.name});
    
</#foreach>