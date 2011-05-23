package at.easydiet.businessobjects;

import java.sql.Clob;
import java.util.HashMap;
import java.util.Map;

import javax.naming.OperationNotSupportedException;

import org.apache.log4j.Logger;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.dao.DAOFactory;
import at.easydiet.domainlogic.DietParameterUnitController;
import at.easydiet.model.NutrimentParameter;
import at.easydiet.model.Recipe;
import at.easydiet.model.RecipeIngredient;

/**
 * This class encapsules a Recipe instance.
 */
public class RecipeBO implements IDietParameterizable
{
    private static final Logger   LOG = Logger.getLogger(RecipeBO.class);

    private Recipe                _model;

    private int                   _cookingTime;
    private List<DietParameterBO> _dietParameters;

    /**
     * Initializes a new instance of the {@link RecipeBO} class.
     */
    public RecipeBO()
    {
        this(new Recipe("", "", 1, DAOFactory.getInstance().createClob(""),
                DAOFactory.getInstance().createClob(""), DAOFactory
                        .getInstance().createClob(""), 0, null));
    }

    /**
     * Initializes a new instance of the {@link RecipeBO} class.
     * @param model the original model object
     */
    public RecipeBO(Recipe model)
    {
        _model = model;
        _dietParameters = new ArrayList<DietParameterBO>();
    }

    /**
     * Gets the cookingTime.
     * @return the cookingTime
     */
    public int getCookingTime()
    {
        return _cookingTime;
    }

    /**
     * Sets the cookingTime.
     * @param cookingTime the cookingTime to set
     */
    public void setCookingTime(int cookingTime)
    {
        _cookingTime = Math.max(0, cookingTime);
    }

    /**
     * Gets the original model object used as object store for this
     * BusinessObject.
     * @return the original {@link Recipe} object.
     */
    public Recipe getModel()
    {
        return _model;
    }

    /**
     * Gets the recipeId of this instance.
     * @return the recipeId currently set for this instance.
     */
    public long getRecipeId()
    {
        return _model.getRecipeId();
    }

    /**
     * Sets the recipeId of this instance.
     * @param recipeId the new recipeId of this instance.
     */
    public void setRecipeId(long recipeId)
    {
        _model.setRecipeId(recipeId);
    }

    /**
     * Gets the name of this instance.
     * @return the name currently set for this instance.
     */
    public String getName()
    {
        return _model.getName();
    }

    /**
     * Sets the name of this instance.
     * @param name the new name of this instance.
     */
    public void setName(String name)
    {
        _model.setName(name);
    }

    /**
     * Gets the blsCode of this instance.
     * @return the blsCode currently set for this instance.
     */
    public String getBlsCode()
    {
        return _model.getBlsCode();
    }

    /**
     * Sets the blsCode of this instance.
     * @param blsCode the new blsCode of this instance.
     */
    public void setBlsCode(String blsCode)
    {
        _model.setBlsCode(blsCode);
    }

    /**
     * Gets the difficulty of this instance.
     * @return the difficulty currently set for this instance.
     */
    public int getDifficulty()
    {
        return _model.getDifficulty();
    }

    /**
     * Sets the difficulty of this instance.
     * @param difficulty the new difficulty of this instance.
     */
    public void setDifficulty(int difficulty)
    {
        _model.setDifficulty(difficulty);
    }

    /**
     * Gets the description of this instance.
     * @return the description currently set for this instance.
     */
    public String getDescription()
    {
        return DAOFactory.getInstance().clobToString(_model.getDescription());
    }

    /**
     * Sets the description of this instance.
     * @param description the new description of this instance.
     */
    public void setDescription(String description)
    {
        _model.setDescription(DAOFactory.getInstance().createClob(description));
    }

    /**
     * Sets the description of this instance.
     * @param description the new description of this instance.
     */
    public void setDescription(Clob description)
    {
        _model.setDescription(description);
    }

    /**
     * Gets the benefits of this instance.
     * @return the benefits currently set for this instance.
     */
    public String getBenefits()
    {
        return DAOFactory.getInstance().clobToString(_model.getBenefits());
    }

    /**
     * Sets the benefits of this instance.
     * @param benefits the new benefits of this instance.
     */
    public void setBenefits(String benefits)
    {
        _model.setBenefits(DAOFactory.getInstance().createClob(benefits));
    }

    /**
     * Sets the benefits of this instance.
     * @param benefits the new benefits of this instance.
     */
    public void setBenefits(Clob benefits)
    {
        _model.setBenefits(benefits);
    }

    /**
     * Gets the cookInstructions of this instance.
     * @return the cookInstructions currently set for this instance.
     */
    public String getCookInstructions()
    {
        return DAOFactory.getInstance().clobToString(
                _model.getCookInstructions());
    }

    /**
     * Sets the cookInstructions of this instance.
     * @param cookInstructions the new cookInstructions of this instance.
     */
    public void setCookInstructions(String cookInstructions)
    {
        _model.setCookInstructions(DAOFactory.getInstance().createClob(
                cookInstructions));
    }

    /**
     * Sets the cookInstructions of this instance.
     * @param cookInstructions the new cookInstructions of this instance.
     */
    public void setCookInstructions(Clob cookInstructions)
    {
        _model.setCookInstructions(cookInstructions);
    }

    /**
     * Gets the amount of this instance.
     * @return the amount currently set for this instance.
     */
    public float getAmount()
    {
        return _model.getAmount();
    }

    /**
     * Sets the amount of this instance.
     * @param amount the new amount of this instance.
     */
    public void setAmount(float amount)
    {
        _model.setAmount(amount);
    }

    private ParameterDefinitionUnitBO _unit;

    /**
     * Gets the currently referenced Unit of this instance.
     * @return the ParameterDefinitionUnit currently referenced in this Recipe.
     */
    public ParameterDefinitionUnitBO getUnit()
    {
        if (_unit == null)
        {
            _unit = new ParameterDefinitionUnitBO(_model.getUnit());
        }
        return _unit;
    }

    /**
     * Sets the Unit to be referenced in this instance
     * @param unit the ParameterDefinitionUnit to reference in this Recipe.
     */
    public void setUnit(ParameterDefinitionUnitBO unit)
    {
        _unit = unit;
        _model.setUnit(unit.getModel());
    }

    private List<RecipeIngredientBO> _ingredients;

/**
     * Gets a list of referenced Ingredients of this instance.
     * This list is cached, use {@link Recipe#updateIngredientsCache()) to update this cache.
     * @return a cached list of referenced Ingredients wrapped into the correct businessobject. 
     */
    public List<RecipeIngredientBO> getIngredients()
    {
        if (_ingredients == null)
        {
            _ingredients = new ArrayList<RecipeIngredientBO>();
            for (RecipeIngredient ingredients : _model.getIngredients())
            {
                _ingredients.add(new RecipeIngredientBO(ingredients));
            }
        }
        return _ingredients;
    }

    /**
     * Adds a new RecipeIngredient to the list of referenced ingredients. The
     * cache will updated
     * @param ingredients the RecipeIngredient to add.
     */
    public void addIngredients(RecipeIngredientBO ingredients)
    {
        getIngredients().add(ingredients);
        _model.getIngredients().add(ingredients.getModel());
    }

    /**
     * Removes the given RecipeIngredient from the list of referenced
     * ingredients. The cache will updated
     * @param ingredients the timespan to add.
     */
    public void removeIngredients(RecipeIngredientBO ingredients)
    {
        getIngredients().remove(ingredients);
        _model.getIngredients().remove(ingredients.getModel());
    }

    /**
     * Rebuilds the cache for referenced ingredients.
     */
    public void updateIngredientsCache()
    {
        _ingredients = null;
        getIngredients();
    }

    private List<NutrimentParameterBO> _nutrimentParameters;

/**
     * Gets a list of referenced NutrimentParameters of this instance.
     * This list is cached, use {@link Recipe#updateNutrimentParametersCache()) to update this cache.
     * @return a cached list of referenced NutrimentParameters wrapped into the correct businessobject. 
     */
    public List<NutrimentParameterBO> getNutrimentParameters()
    {
        if (_nutrimentParameters == null)
        {
            _nutrimentParameters = new ArrayList<NutrimentParameterBO>();
            for (NutrimentParameter nutrimentParameters : _model
                    .getNutrimentParameters())
            {
                _nutrimentParameters.add(new NutrimentParameterBO(
                        nutrimentParameters));
            }
        }
        return _nutrimentParameters;
    }

    /**
     * Adds a new NutrimentParameter to the list of referenced
     * nutrimentParameters. The cache will updated
     * @param nutrimentParameters the NutrimentParameter to add.
     */
    public void addNutrimentParameters(NutrimentParameterBO nutrimentParameters)
    {
        getNutrimentParameters().add(nutrimentParameters);
        _model.getNutrimentParameters().add(nutrimentParameters.getModel());
    }

    /**
     * Removes the given NutrimentParameter from the list of referenced
     * nutrimentParameters. The cache will updated
     * @param nutrimentParameters the timespan to add.
     */
    public void removeNutrimentParameters(
            NutrimentParameterBO nutrimentParameters)
    {
        getNutrimentParameters().remove(nutrimentParameters);
        _model.getNutrimentParameters().remove(nutrimentParameters.getModel());
    }
    
    private void clearNutrimentParameters()
    {
        while(getNutrimentParameters().getLength() > 0)
        {
            NutrimentParameterBO bo = getNutrimentParameters().get(0);
            removeNutrimentParameters(bo);
        }
    }


    /**
     * Rebuilds the cache for referenced nutrimentParameters.
     */
    public void updateNutrimentParametersCache()
    {
        _nutrimentParameters = null;
        getNutrimentParameters();
    }

    @Override
    public String getDisplayText()
    {
        return getName();
    }

    @Override
    public List<? extends DietParameterTemplateBO> getDietParameters()
    {
        return _dietParameters;
    }

    @Override
    public void addDietParameters(DietParameterTemplateBO parameter)
    {
        if (!(parameter instanceof DietParameterBO)) return;
        _dietParameters.add((DietParameterBO) parameter);
    }

    @Override
    public void removeDietParameters(DietParameterTemplateBO parameter)
    {
        if (!(parameter instanceof DietParameterBO)) return;
        _dietParameters.remove((DietParameterBO) parameter);
    }

    public void calcParameters()
    {
        // mapping of the special parameter to its sum value class
        Map<Long, NutrimentParameterBO> summedIngredientParameter = new HashMap<Long, NutrimentParameterBO>();

        long currParameterdefid;
        float sumValue;

        // reset and calculate new weight
        setAmount(0);

        // calculate new parameter values
        for (RecipeIngredientBO recipeIngredient : getIngredients())
        {
            // add amount to recipe
            float amountInRecipeUnit;

            // convert if needed
            if (getUnit().equals(recipeIngredient.getIngredient().getUnit()))
            {
                amountInRecipeUnit = recipeIngredient.getAmount();
            }
            else
            {
                float amountInIngredientUnit = recipeIngredient.getAmount();

                try
                {
                    amountInRecipeUnit = DietParameterUnitController
                            .getInstance().convert(
                                    recipeIngredient.getIngredient().getUnit(),
                                    getUnit(), amountInIngredientUnit);
                }
                catch (OperationNotSupportedException e)
                {
                    // TODO: warning - could not add ungredient
                    amountInRecipeUnit = 0;
                }
            }
            setAmount(getAmount() + amountInRecipeUnit);
        }
        // calculate new parameter values
        for (RecipeIngredientBO recipeIngredient : getIngredients())
        {
            // all ingredients

            // recipeIngredient.getIngredient().getAmount()  has X kcal
            // recipeIngredient.getAmount() has n kcal
            
            // n = X * brtaifactor
            float brtaifactor = (recipeIngredient.getAmount() / recipeIngredient
                    .getIngredient().getAmount());// baserecipe to as
                                              // ingredient factor for
                                              // absolute units
            
            // 100g has X kcal
            // recipeIngredient.getAmount() has n kcal
            
            // n = X * fractionOfRecipe
            float fractionofrecipe = recipeIngredient.getIngredient().getAmount() / getAmount();// fractionofrecipe
                                                                                // needed
                                                                                // to
                                                                                // get
                                                                                // the
                                                                                // right
                                                                                // fractins
                                                                                // of
                                                                                // relative
                                                                                // units

            for (NutrimentParameterBO parameter : recipeIngredient
                    .getIngredient().getNutrimentParameters())
            {
                currParameterdefid = parameter.getParameterDefinition()
                        .getParameterDefinitionId();
                if (summedIngredientParameter.containsKey(currParameterdefid))
                {
                    // if parameter already exists
                    NutrimentParameterBO sum = summedIngredientParameter
                            .get(currParameterdefid);

                    ParameterDefinitionUnitBO target = sum.getUnit();
                    ParameterDefinitionUnitBO source = parameter.getUnit();

                    try
                    {// converting the type and then sum it

                        sumValue = DietParameterUnitController.getInstance()
                                .convert(source, target,
                                        Float.parseFloat(parameter.getValue()));

                        if (target.getName().contains("/"))
                        {
                            sum.setValue(sum.getFloatValue()
                                    + (sumValue * fractionofrecipe));
                        }
                        else
                        {
                            sum.setValue(sum.getFloatValue()
                                    + (sumValue * brtaifactor));
                        }

                    }
                    catch (NumberFormatException e)
                    {
                        LOG.debug(e);
                    }
                    catch (OperationNotSupportedException e)
                    {
                        LOG.debug(e);
                    }
                }
                else
                {
                    // check if the parameter has a unit (some bls specific
                    // parameters which don't need to be summed)
                    if (parameter.getUnit().getParameterDefinitionUnitId() != 0)
                    {
                        NutrimentParameterBO validSum = new NutrimentParameterBO();
                        validSum.setUnit(parameter.getUnit());
                        validSum.setParameterDefinition(parameter.getParameterDefinition());
                        
                        summedIngredientParameter.put(currParameterdefid,
                                validSum);

                        if (validSum.getUnit().getName().contains("/"))
                        {
                            validSum.setValue(parameter.getFloatValue()
                                    * fractionofrecipe);
                        }
                        else
                        {
                            validSum.setValue(parameter.getFloatValue()
                                    * brtaifactor);
                        }
                    }
                }

            }
        }

        
        clearNutrimentParameters();
        // all recipes with the correct sum and unit will be added to the recipe
        for (NutrimentParameterBO parameter : summedIngredientParameter
                .values())
        {
            addNutrimentParameters(parameter);
        }
    }

   
    public NutrimentParameterBO getNutrimentParameter(
            ParameterDefinitionBO parameterDefinition)
    {
        for (NutrimentParameterBO nutriment : getNutrimentParameters())
        {
            if (nutriment.getParameterDefinition().equals(parameterDefinition))
            {
                return nutriment;
            }
        }
        return null;
    }

}