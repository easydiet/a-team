${pojo.getPackageDeclaration()}

${pojo.generateImports()}
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

<#if pojo.getMetaAsBool("is-enum")>
<#include "BoEnum.ftl"/>
<#else>
<#include "BoClass.ftl"/>	
</#if>