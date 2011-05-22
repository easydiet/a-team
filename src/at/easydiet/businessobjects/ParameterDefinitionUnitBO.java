package at.easydiet.businessobjects;

import at.easydiet.model.ParameterDefinitionUnit;

/**
 * This class encapsules a ParameterDefinitionUnit instance.
 */
public class ParameterDefinitionUnitBO
{
    private ParameterDefinitionUnit _model;

    /**
     * Initializes a new instance of the {@link ParameterDefinitionUnitBO}
     * class.
     */
    public ParameterDefinitionUnitBO()
    {
        // TODO: add default values
        this(new ParameterDefinitionUnit());
    }

    /**
     * Initializes a new instance of the {@link ParameterDefinitionUnitBO}
     * class.
     * @param model the original model object
     */
    public ParameterDefinitionUnitBO(ParameterDefinitionUnit model)
    {
        _model = model;
    }

    /**
     * Gets the original model object used as object store for this
     * BusinessObject.
     * @return the original {@link ParameterDefinitionUnit} object.
     */
    public ParameterDefinitionUnit getModel()
    {
        return _model;
    }

    /**
     * Gets the parameterDefinitionUnitId of this instance.
     * @return the parameterDefinitionUnitId currently set for this instance.
     */
    public long getParameterDefinitionUnitId()
    {
        return _model.getParameterDefinitionUnitId();
    }

    /**
     * Sets the parameterDefinitionUnitId of this instance.
     * @param parameterDefinitionUnitId the new parameterDefinitionUnitId of
     *            this instance.
     */
    public void setParameterDefinitionUnitId(long parameterDefinitionUnitId)
    {
        _model.setParameterDefinitionUnitId(parameterDefinitionUnitId);
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

    private ParameterDefinitionDataTypeBO _type;

    /**
     * Gets the currently referenced Type of this instance.
     * @return the ParameterDefinitionDataType currently referenced in this
     *         ParameterDefinitionUnit.
     */
    public ParameterDefinitionDataTypeBO getType()
    {
        if (_type == null)
        {
            _type = ParameterDefinitionDataTypeBO.getForModel(_model.getType());
        }
        return _type;
    }

    /**
     * Sets the Type to be referenced in this instance
     * @param type the ParameterDefinitionDataType to reference in this
     *            ParameterDefinitionUnit.
     */
    public void setType(ParameterDefinitionDataTypeBO type)
    {
        _type = type;
        _model.setType(type.getModel());
    }

    @Override
    public String toString()
    {
        return getName();
    }

    /**
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode()
    {
        final int prime = 31;
        int result = 1;
        result = prime * result;
        if (_model != null)
        {
            result += (int) (_model.getParameterDefinitionUnitId() ^ (_model
                    .getParameterDefinitionUnitId() >>> 32));
        }
        return result;
    }

    /**
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof ParameterDefinitionUnitBO)) return false;
        ParameterDefinitionUnitBO other = (ParameterDefinitionUnitBO) obj;
        if (_model == null && other._model != null) return false;
        if (_model == null || other._model == null) return false;
        if (_model.getParameterDefinitionUnitId() != other._model
                .getParameterDefinitionUnitId()) return false;
        return true;
    }
}