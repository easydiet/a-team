package at.easydiet.model;
// Generated 06.04.2011 14:32:00 by Hibernate Tools 3.4.0.CR1



/**
 * DietParameterTemplate generated by hbm2java
 */
public class DietParameterTemplate  implements java.io.Serializable {


     private long dietParameterTemplateId;
     private CheckOperator checkOperator;
     private int duration;
     private String value;
     private DietParameterType dietParameterType;
     private ParameterDefinition parameterDefinition;

    public DietParameterTemplate() {
    }

	
    public DietParameterTemplate(CheckOperator checkOperator, DietParameterType dietParameterType, ParameterDefinition parameterDefinition) {
        this.checkOperator = checkOperator;
        this.dietParameterType = dietParameterType;
        this.parameterDefinition = parameterDefinition;
    }
    public DietParameterTemplate(CheckOperator checkOperator, int duration, String value, DietParameterType dietParameterType, ParameterDefinition parameterDefinition) {
       this.checkOperator = checkOperator;
       this.duration = duration;
       this.value = value;
       this.dietParameterType = dietParameterType;
       this.parameterDefinition = parameterDefinition;
    }
   
    public long getDietParameterTemplateId() {
        return this.dietParameterTemplateId;
    }
    
    public void setDietParameterTemplateId(long dietParameterTemplateId) {
        this.dietParameterTemplateId = dietParameterTemplateId;
    }
    public CheckOperator getCheckOperator() {
        return this.checkOperator;
    }
    
    public void setCheckOperator(CheckOperator checkOperator) {
        this.checkOperator = checkOperator;
    }
    public int getDuration() {
        return this.duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getValue() {
        return this.value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    public DietParameterType getDietParameterType() {
        return this.dietParameterType;
    }
    
    public void setDietParameterType(DietParameterType dietParameterType) {
        this.dietParameterType = dietParameterType;
    }
    public ParameterDefinition getParameterDefinition() {
        return this.parameterDefinition;
    }
    
    public void setParameterDefinition(ParameterDefinition parameterDefinition) {
        this.parameterDefinition = parameterDefinition;
    }




}


