package at.easydiet.domainlogic;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.naming.OperationNotSupportedException;

import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.ParameterDefinitionUnitDAO;
import at.easydiet.model.ParameterDefinitionUnit;

/**
 * Converts the units of the parameters
 */
public class DietParameterUnitController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger   LOG = org.apache.log4j.Logger
                                                               .getLogger(DietParameterUnitController.class);

    /**
     * Converter storage by name since we don't know the exact Objects yet
     */
    private static Map<String, Map<String, Float>> _fixedConversions;

    static
    {
        // initialize convertors by name
        _fixedConversions = new HashMap<String, Map<String, Float>>();

        // converters for kg
        Map<String, Float> kg = new HashMap<String, Float>();
        _fixedConversions.put("g", kg);
        kg.put("kg", 1f);
        kg.put("g", 1 / 1000f);
        kg.put("mg", (1 / 1000f) / 1000f);
        kg.put("myg", ((1 / 1000f) / 1000f) / 1000f); // micro
        // kg.put("m?g", (((1 / 1000f) / 1000f)/1000f)/1000f); ???

        // converters for g
        Map<String, Float> g = new HashMap<String, Float>();
        _fixedConversions.put("g", g);
        g.put("kg", 1000f);
        g.put("g", 1f);
        g.put("mg", 1 / 1000f);
        g.put("myg", (1 / 1000f) / 1000f); // micro
        // g.put("m?g", ((1/1000f)/1000f)/1000f); ???

        // converters for mg
        Map<String, Float> mg = new HashMap<String, Float>();
        _fixedConversions.put("mg", mg);
        mg.put("kg", 1000f * 1000f);
        mg.put("g", 1000f);
        mg.put("mg", 1f);
        mg.put("myg", 1 / 1000f); // micro
        // mg.put("m?g", (1/1000f)/1000f); ???

        // converters for myg
        Map<String, Float> myg = new HashMap<String, Float>();
        _fixedConversions.put("myg", myg);
        myg.put("kg", 1000f * 1000f * 1000f);
        myg.put("g", 1000f * 1000f);
        myg.put("mg", 1000f);
        myg.put("myg", 1f); // micro
        // g.put("m?g", (1/1000f)); ???

        // converters for mg/100g
        Map<String, Float> mg100g = new HashMap<String, Float>();
        _fixedConversions.put("mg/100g", myg);
        mg100g.put("mg/100g", 1f);
        mg100g.put("myg/100g", 1 / 1000f);
        // mg100g.put("m?g/100g", (1/1000f)/1000f);

        // converters for mg/100g
        Map<String, Float> my100g = new HashMap<String, Float>();
        _fixedConversions.put("myg/100g", myg);
        my100g.put("mg/100g", 1000f);
        my100g.put("myg/100g", 1f);
        // mg100g.put("m?g/100g", (1/1000f)/1000f);

        // converters for l
        Map<String, Float> l = new HashMap<String, Float>();
        _fixedConversions.put("l", l);
        l.put("l", 1f);
        l.put("ml", 1 / 1000f);

        // converters for ml
        Map<String, Float> ml = new HashMap<String, Float>();
        _fixedConversions.put("ml", ml);
        ml.put("l", 1000f);
        ml.put("ml", 1f);

        // converters for kcal
        Map<String, Float> kcal = new HashMap<String, Float>();
        _fixedConversions.put("kcal", kcal);
        kcal.put("kcal", 1f);
        kcal.put("kJ", 0.2388f);

        // converters for kJ
        Map<String, Float> kJ = new HashMap<String, Float>();
        _fixedConversions.put("kJ", kJ);
        kJ.put("kcal", 4.1868f);
        kJ.put("kJ", 1f);
    }

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static DietParameterUnitController     _singleton;

    /**
     * Get the instance of this {@link DietParameterUnitController}
     * 
     * @return Instance of {@link DietParameterUnitController}
     */
    public static DietParameterUnitController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new DietParameterUnitController();
        }
        return _singleton;
    }

    /**
     * Stores the unit converters corresponding to their
     * {@link ParameterDefinitionUnitBO}
     */
    private Map<ParameterDefinitionUnitBO, Map<ParameterDefinitionUnitBO, Float>> _unitConverters;

    /**
     * Stores the units by their name
     */
    private Map<String, ParameterDefinitionUnitBO>                                _unitsByName;

    /**
     * Initializes a new instance of the {@link DietParameterUnitController}
     * class.
     */
    private DietParameterUnitController()
    {
        _unitConverters = new HashMap<ParameterDefinitionUnitBO, Map<ParameterDefinitionUnitBO, Float>>();

        // store a set of all units accessible by name for later
        // lazy-initialization
        ParameterDefinitionUnitDAO dao = DAOFactory.getInstance()
                .getParameterDefinitionUnitDAO();
        _unitsByName = new HashMap<String, ParameterDefinitionUnitBO>();

        for (ParameterDefinitionUnit model : dao.findAll())
        {
            _unitsByName.put(model.getName(), new ParameterDefinitionUnitBO(
                    model));
        }
    }

    /**
     * Returns a list of Units which can be converter from the given unit
     * 
     * @param unit
     *            the unit to get the from
     * @return a list of units for which are converters available.
     */
    public Set<ParameterDefinitionUnitBO> getCompatibleUnitsFor(
            ParameterDefinitionUnitBO unit)
    {
        // if we don't have initialinzed the converters for this unit yet, do it
        // now
        if (!_unitConverters.containsKey(unit))
        {
            buildConverters(unit);
        }

        return _unitConverters.get(unit).keySet();
    }

    /**
     * Returns if it is possible to convert a {@link ParameterDefinitionUnitBO}
     * to another
     * 
     * @param from
     *            The {@link ParameterDefinitionUnitBO} to convert
     * @param to
     *            The {@link ParameterDefinitionUnitBO} to convert to
     * @return True if convertion is possible
     */
    public boolean canConvert(ParameterDefinitionUnitBO from,
            ParameterDefinitionUnitBO to)
    {
        return getCompatibleUnitsFor(from).contains(to);
    }

    /**
     * Converts the given value from one unit to the other.
     * 
     * @param from
     *            the unit in which the value is currently
     * @param to
     *            the unit in which the value should result
     * @param fromValue
     *            the value to convert
     * @return the converted value
     * @throws OperationNotSupportedException
     *             thrown if no converter for the specified unis exist
     */
    public float convert(ParameterDefinitionUnitBO from,
            ParameterDefinitionUnitBO to, float fromValue)
            throws OperationNotSupportedException
    {
        // get converter
        if (!getCompatibleUnitsFor(from).contains(to))
        {
            throw new OperationNotSupportedException(
                    "No converter available for those units");
        }

        float divisor = _unitConverters.get(from).get(to);
        return fromValue / divisor;
    }

    /**
     * Initializes the converters for the specified unit
     * 
     * @param unit
     *            the unit to initialize
     */
    private void buildConverters(ParameterDefinitionUnitBO unit)
    {
        // create empty list
        Map<ParameterDefinitionUnitBO, Float> unitConverters = new HashMap<ParameterDefinitionUnitBO, Float>();
        _unitConverters.put(unit, unitConverters);

        if (_fixedConversions.containsKey(unit.getName()))
        {
            // get list of conversions of this unit by accessing the name-stored
            // converters
            for (Entry<String, Float> converter : _fixedConversions.get(
                    unit.getName()).entrySet())
            {
                unitConverters.put(_unitsByName.get(converter.getKey()),
                        converter.getValue());
            }
        }
    }
}
