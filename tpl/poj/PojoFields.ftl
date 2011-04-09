<#foreach field in pojo.getAllPropertiesIterator()>
    ${pojo.getFieldModifiers(field)} ${pojo.getJavaTypeName(field, jdk5)} _${field.name}<#if pojo.hasFieldInitializor(field, jdk5)> = ${pojo.getFieldInitialization(field, jdk5)}</#if>;
</#foreach>