package at.easydiet.businessobjects;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.DietParameterSet;
import at.easydiet.model.DietParameterTemplate;

/**
 * This class encapsules a DietParameterSet instance.
 */
public class DietParameterSetBO implements IDietParameterizable
{
    private DietParameterSet _model;

    /**
     * Initializes a new instance of the {@link DietParameterSetBO} class.
     */
    public DietParameterSetBO()
    {
        // TODO: add default values
        this(new DietParameterSet());
    }

    /**
     * Initializes a new instance of the {@link DietParameterSetBO} class.
     * @param model the original model object
     */
    public DietParameterSetBO(DietParameterSet model)
    {
        _model = model;
    }

    /**
     * Gets the original model object used as object store for this
     * BusinessObject.
     * @return the original {@link DietParameterSet} object.
     */
    public DietParameterSet getModel()
    {
        return _model;
    }

    /**
     * Gets the dietParameterSetId of this instance.
     * @return the dietParameterSetId currently set for this instance.
     */
    public long getDietParameterSetId()
    {
        return _model.getDietParameterSetId();
    }

    /**
     * Sets the dietParameterSetId of this instance.
     * @param dietParameterSetId the new dietParameterSetId of this instance.
     */
    public void setDietParameterSetId(long dietParameterSetId)
    {
        _model.setDietParameterSetId(dietParameterSetId);
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

    private List<DietParameterTemplateBO> _dietParameterTemplates;

/**
     * Gets a list of referenced DietParameterTemplates of this instance.
     * This list is cached, use {@link DietParameterSet#updateDietParameterTemplatesCache()) to update this cache.
     * @return a cached list of referenced DietParameterTemplates wrapped into the correct businessobject. 
     */
    public List<DietParameterTemplateBO> getDietParameterTemplates()
    {
        if (_dietParameterTemplates == null)
        {
            _dietParameterTemplates = new ArrayList<DietParameterTemplateBO>();
            for (DietParameterTemplate dietParameterTemplates : _model
                    .getDietParameterTemplates())
            {
                _dietParameterTemplates.add(new DietParameterTemplateBO(
                        dietParameterTemplates));
            }
        }
        return _dietParameterTemplates;
    }

    /**
     * Adds a new DietParameterTemplate to the list of referenced
     * dietParameterTemplates. The cache will updated
     * @param dietParameterTemplates the DietParameterTemplate to add.
     */
    public void addDietParameterTemplates(
            DietParameterTemplateBO dietParameterTemplates)
    {
        getDietParameterTemplates().add(dietParameterTemplates);
        _model.getDietParameterTemplates().add(
                dietParameterTemplates.getModel());
    }

    /**
     * Removes the given DietParameterTemplate from the list of referenced
     * dietParameterTemplates. The cache will updated
     * @param dietParameterTemplates the timespan to add.
     */
    public void removeDietParameterTemplates(
            DietParameterTemplateBO dietParameterTemplates)
    {
        getDietParameterTemplates().remove(dietParameterTemplates);
        _model.getDietParameterTemplates().remove(
                dietParameterTemplates.getModel());
    }

    /**
     * Rebuilds the cache for referenced dietParameterTemplates.
     */
    public void updateDietParameterTemplatesCache()
    {
        _dietParameterTemplates = null;
        getDietParameterTemplates();
    }

    @Override
    public String getDisplayText()
    {
        return "Neues Parameterset anlegen";
    }

    @Override
    public List<DietParameterTemplateBO> getDietParameters()
    {
        if (_dietParameterTemplates == null)
        {
            _dietParameterTemplates = new ArrayList<DietParameterTemplateBO>();
            for (DietParameterTemplate dietParameterTemplate : _model
                    .getDietParameterTemplates())
            {
                _dietParameterTemplates.add(new DietParameterTemplateBO(
                        dietParameterTemplate));
            }
        }
        return _dietParameterTemplates;
    }

    @Override
    public void addDietParameters(DietParameterTemplateBO parameter)
    {
        getDietParameters().add(parameter);
        _model.getDietParameterTemplates().add(parameter.getModel());
    }

    @Override
    public void removeDietParameters(DietParameterTemplateBO parameter)
    {
        getDietParameters().remove(parameter);
        _model.getDietParameterTemplates().remove(parameter.getModel());
    }

}