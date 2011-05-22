package at.easydiet.businessobjects;

import org.apache.pivot.collections.List;

public interface IDietParameterizable
{
    public String getDisplayText();

    public List<? extends DietParameterTemplateBO> getDietParameters();

    public void addDietParameters(DietParameterTemplateBO parameter);

    public void removeDietParameters(DietParameterTemplateBO parameter);
    
    //public List<DietParameterTemplateBO> getPossibleParameters();
}