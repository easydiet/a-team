package at.easydiet.businessobjects;

import at.easydiet.model.CheckOperator;

/**
 * This class encapsules a CheckOperator instance.
 */
public enum CheckOperatorBO
{
    BIGGER (">"),
    SMALLER ("<"),
    EQUALORBIGGER (">="),
    EQUALORSMALLER ("<="),
    EQUAL ("="),
    NOTEQUAL ("!=");

    private CheckOperator _model;

    /**
     * Initializes a new instance of the {@link CheckOperatorBO} enum.
     * @param model the enum value
     */
    private CheckOperatorBO(String value)
    {
        _model = new CheckOperator(value);
    }

    /**
     * Gets the original model object used as object store for this
     * BusinessObject.
     * @return the original {@link CheckOperator} object.
     */
    public CheckOperator getModel()
    {
        return _model;
    }

    /**
     * Gets the name of this enum value.
     * @return the name of this enum value
     */
    public String getName()
    {
        return _model.getName();
    }
    
    /**
     * Returns the BusinessObject matching to the specified model object.
     * @returns the enum value matching to the given model or the default value.
     */
    public static CheckOperatorBO getForModel(CheckOperator model)
    {
        for (CheckOperatorBO bo : values())
        {
            if (bo.getModel().getName().equals(model.getName()))
            {
                return bo;
            }
        }
        return EQUAL;
    }
}