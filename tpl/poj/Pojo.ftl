${pojo.getPackageDeclaration()}
<#assign classbody>
<#include "PojoTypeDeclaration.ftl"/> 

{

<#if !pojo.isInterface()>
<#include "PojoFields.ftl"/>

<#include "PojoConstructors.ftl"/>
   
<#include "PojoPropertyAccessors.ftl"/>
<#include "PojoToString.ftl"/>

<#else>
<#include "PojoInterfacePropertyAccessors.ftl"/>

</#if>
}
</#assign>

${pojo.generateImports()}
${classbody}