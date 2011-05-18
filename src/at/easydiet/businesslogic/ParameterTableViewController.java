package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietParameterTemplateBO;
import at.easydiet.businessobjects.DietParameterTypeBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.domainlogic.DietPlanEditingController;
import at.easydiet.model.ParameterDefinition;
import at.easydiet.validation.ParameterTemplateValidator;
import at.easydiet.view.ParameterTableView;

/**
 * Provides data and actions for the {@link ParameterTableView}.
 */
public class ParameterTableViewController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger     LOG = org.apache.log4j.Logger
                                                                 .getLogger(MealContainerController.class);

    /**
     * This object provides access to it's parameters
     */
    private IDietParameterizable                     _parameterProvider;

    /**
     * Contains all possible parameter definitions
     */
    private ArrayList<ParameterDefinitionBO>         _definitions;

    /**
     * Defines if the parameter table uses templates or parameters
     */
    private Class<? extends DietParameterTemplateBO> _newInstanceType;

    /**
     * Initializes a new instance of the {@link ParameterTableViewController}
     * class.
     */
    public ParameterTableViewController()
    {
        _newInstanceType = DietParameterBO.class;
    }

    /**
     * Gets the newInstanceType.
     * 
     * @return the newInstanceType
     */
    public Class<? extends DietParameterTemplateBO> getNewInstanceType()
    {
        return _newInstanceType;
    }

    /**
     * Sets the newInstanceType.
     * 
     * @param newInstanceType
     *            the newInstanceType to set
     */
    public void setNewInstanceType(
            Class<? extends DietParameterTemplateBO> newInstanceType)
    {
        _newInstanceType = newInstanceType;
    }

    /**
     * Gets all possible parameterdefinitions out of the database. Loads once
     * per instance.
     * 
     * @return returns list of parameter definitions
     */
    public List<ParameterDefinitionBO> getAllDefinitions()
    {
        if (_definitions == null || _definitions.isEmpty())
        {
            _definitions = new ArrayList<ParameterDefinitionBO>();

            // TODO: own controller?
            for (ParameterDefinition parameterDefinition : DAOFactory
                    .getInstance().getParameterDefinitionDAO().findAll())
            {
                _definitions
                        .add(new ParameterDefinitionBO(parameterDefinition));
            }
        }
        return _definitions;
    }

    /**
     * Creates a new instance from type T
     * 
     * @param <T>
     *            Type of the new entry
     * @return new Instance of T
     */
    @SuppressWarnings("unchecked")
    private <T extends DietParameterTemplateBO> T createNewEntry()
    {
        try
        {
            return (T) _newInstanceType.newInstance();
        }
        catch (Exception e)
        {
            return null;
        }
    }

    /**
     * Creates a new dietParameter for use as a Template
     * 
     * @return returns a new dietParameter
     */
    public DietParameterTemplateBO getParameterTemplate()
    {
        DietParameterTemplateBO newBo = createNewEntry();

        ParameterDefinitionBO newDefinition = getAllDefinitions().get(0);
        newBo.setCheckOperator(CheckOperatorBO.SMALLER);
        newBo.setDietParameterType(DietParameterTypeBO.TARGET_PARAMETER);

        newBo.setParameterDefinition(newDefinition);

        newBo.setValue("1");

        newBo.setParameterDefinitionUnit(newBo.getParameterDefinition()
                .getUnits().get(0));

        return newBo;
    }

    /**
     * Add a dietParameter to the _parameterProvider and the tableView
     */
    public void addTemplate()
    {
        _parameterProvider.addDietParameters(getParameterTemplate());
        isValid();
        // TODO: start validation in corresponding controller
        // DietPlanEditingController.getInstance().validateDietPlan();
    }

    /**
     * Removes a dietParameter from the _parametProvider and the tableView
     * 
     * @param dietParameter
     *            dietParameter to remove
     */
    public void remove(DietParameterTemplateBO dietParameter)
    {
        _parameterProvider.removeDietParameters(dietParameter);
        isValid();
        DietPlanEditingController.getInstance().validateDietPlan();
    }

    /**
     * Set the parameter data provider for this view
     * 
     * @param provider
     */
    public void setParameterProvider(IDietParameterizable provider)
    {
        _parameterProvider = provider;
    }

    /**
     * Gets the parameterizable object of this table view
     * 
     * @return Parameterizable object
     */
    public IDietParameterizable getParameterProvider()
    {
        return _parameterProvider;
    }

    /**
     * Check if the current selection of parameters is valid
     * 
     * @return true if no conflicts are found
     */
    public boolean isValid()
    {
        return getValidator().isValid(_parameterProvider);
    }

    /**
     * Returns the parameterValidator
     * 
     * @return ParameterTemplateValidator instance
     */
    public ParameterTemplateValidator getValidator()
    {
        return ParameterTemplateValidator.getInstance();
    }
}
