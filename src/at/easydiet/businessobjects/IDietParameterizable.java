package at.easydiet.businessobjects;

import org.apache.pivot.collections.List;

public interface IDietParameterizable
{
    public String getDisplayText();

    public List<DietParameterBO> getDietParameters();

    public void addDietParameters(DietParameterBO parameter);

    public void removeDietParameters(DietParameterBO parameters);
}