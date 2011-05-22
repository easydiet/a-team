package at.easydiet.businessobjects;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.model.SystemUserFunction;

/**
 * This class encapsules a SystemUserFunction instance.
 */
public enum SystemUserFunctionBO
{
    TREATING_DOCTOR("Zugeteilter Arzt"),
    TREATING_ASSISTANT("Zugeteilter Diätassistent"),
    REPRESENTING_DOCTOR("Vertrender Arzt"),
    REPRESENTING_ASSISTANT("Vertetender Diätassistent");

    private SystemUserFunction _model;

    /**
     * Initializes a new instance of the {@link SystemUserFunctionBO} enum.
     * @param model the enum value
     */
    private SystemUserFunctionBO(String value)
    {
        _model = new SystemUserFunction(value);
    }

    /**
     * Gets the original model object used as object store for this
     * BusinessObject.
     * @return the original {@link SystemUserFunction} object.
     */
    public SystemUserFunction getModel()
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
    public static SystemUserFunctionBO getForModel(SystemUserFunction model)
    {
        for (SystemUserFunctionBO bo : values())
        {
            if (bo.getModel().getName().equals(model.getName()))
            {
                return bo;
            }
        }
        return TREATING_ASSISTANT;
    }

    public static List<SystemUserFunctionBO> getAllValues()
    {
        List<SystemUserFunctionBO> list = new ArrayList<SystemUserFunctionBO>();
        for (SystemUserFunctionBO bo : values())
        {
            list.add(bo);
        }
        return list;
    }

    public String toString()
    {
        return getName();
    }
}