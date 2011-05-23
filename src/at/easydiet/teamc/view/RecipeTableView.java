/**
 * This File is part of EasyDiet
 * Created on: 06.05.2011
 * Created by: Michael
 * File: RecipeTableView.java
 */
package at.easydiet.teamc.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.wtk.TableView;

import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.RecipeBO;

/**
 * Represents a table view for recipes
 * 
 * @author Michael
 */
public class RecipeTableView extends TableView
{

    // instance variables
    private List<HashMap<String, Object>> _recipes;

    {
        _recipes = new ArrayList<HashMap<String, Object>>();
        setTableData(_recipes);
        setTooltipDelay(500);
    }

    /**
     * Add recipe to table view
     * 
     * @param r Recipe to add
     */
    public void addRecipe(RecipeBO r)
    {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("recipe", r); // recipe name is set by overridden toString
                              // method of RecipeData
        map.put("quantity", 100);
        _recipes.add(map);
    }

    /**
     * Removes a recipe from the table view
     * 
     * @param r
     */
    public void removeRecipe(RecipeBO r)
    {
        List<HashMap<String, Object>> tableData = getTableData();

        // search corresponding hashmap and remove it
        for (HashMap<String, Object> h : tableData)
        {
            if (h.get("recipe") == r)
            {
                tableData.remove(h);
                break;
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HashMap<String, Object>> getTableData()
    {
        return (List<HashMap<String, Object>>) super.getTableData();
    }

    /**
     * Check if a RecipeData is already in list
     * 
     * @param r
     * @return
     */
    public boolean containsRecipeData(RecipeBO r)
    {
        List<HashMap<String, Object>> tableData = getTableData();
        for (HashMap<String, Object> h : tableData)
        {
            if (h.get("recipe") == r)
            {
                return true;
            }
        }
        return false;
    }

    /**
     * Set unit in a specific row
     * 
     * @param p
     * @param index
     */
    public void setUnit(ParameterDefinitionUnitBO p, int index)
    {
        HashMap<String, Object> map = _recipes.get(index);
        final String s = "unit";

        // check if already set
        if (map.containsKey(s))
        {
            map.remove(s);
        }

        map.put(s, p);
    }

    /**
     * Get a recipe from a specific row
     * 
     * @param row
     * @return
     */
    public RecipeBO getRecipe(int row)
    {
        HashMap<String, Object> map = _recipes.get(row);
        return (RecipeBO) map.get("recipe");
    }

    /**
     * Get a unit from a specific row
     * 
     * @param row
     * @return
     */
    public ParameterDefinitionUnitBO getUnit(int row)
    {
        HashMap<String, Object> map = _recipes.get(row);
        return (ParameterDefinitionUnitBO) map.get("unit");
    }

    /**
     * Get the quanity from a specific row
     * 
     * @param row
     * @return
     */
    public float getQuantity(int row)
    {
        HashMap<String, Object> map = _recipes.get(row);
        return Float.parseFloat(map.get("quantity").toString());
    }
}
