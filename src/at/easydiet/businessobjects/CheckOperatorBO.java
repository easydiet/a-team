package at.easydiet.businessobjects;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.model.CheckOperator;

/**
 * This class encapsules a CheckOperator instance.
 */
public enum CheckOperatorBO
{
    BIGGER(">"), SMALLER("<"), EQUALORBIGGER(">="), EQUALORSMALLER("<="), EQUAL(
            "="), NOTEQUAL("!=");

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

    /**
     * Returns a list of all Operators
     * @return List of all Operators
     * @author Mathias
     */
    public static List<CheckOperatorBO> getAllOperators()
    {
        ArrayList<CheckOperatorBO> list = new ArrayList<CheckOperatorBO>();
        for (CheckOperatorBO current : values())
        {
            list.add(current);
        }
        return list;
    }

    public String toString()
    {
        return getName();
    }

    /**
     * Checks if the checkoperator applies to the given two value
     * @param originalValue the value which needs to apply
     * @param actualValue the value which needs to get checked if it applies to
     *            the operator
     * @return null if the operator is valid, otherwise the Operator which
     *         applies for the error (i.E. if actualValue is to small for the
     *         BIGGER this method will return EQUALORSMALLER)
     */
    public CheckOperatorBO isValid(float originalValue, float actualValue)
    {
        switch (this)
        {
            case BIGGER:
                if (actualValue <= originalValue)
                {
                    return EQUALORSMALLER;
                }
            break;
            case SMALLER:
                if (actualValue >= originalValue)
                {
                    return EQUALORBIGGER;
                }
            break;
            case EQUAL:
                if (actualValue != originalValue)
                {
                    return NOTEQUAL;
                }
            break;
            case EQUALORBIGGER:
                if (actualValue < originalValue)
                {
                    return SMALLER;
                }
            break;
            case EQUALORSMALLER:
                if (actualValue > originalValue)
                {
                    return BIGGER;
                }
            break;
        }
        return null;
    }
}