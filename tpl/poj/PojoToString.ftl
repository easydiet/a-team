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
<#foreach property in pojo.getToStringPropertiesIterator()>        builder.append("${property.getName()}").append("='").append(${pojo.getGetterSignature(property)}()).append("' ");			
</#foreach>
        builder.append("]");
      
        return builder.toString();
    }