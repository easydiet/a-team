package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.HibernateException;

import at.easydiet.businessobjects.DietParameterSetBO;
import at.easydiet.businessobjects.DietParameterTemplateBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietParameterSetDAO;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.util.StringUtils;
import at.easydiet.validation.ParameterTemplateValidator;

public class ParametersetEditingController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ParametersetEditingController.class);

    private DietParameterSetBO                  _parameterset;
	private List<String> 						_errors;


    /**
     * Gets the parameterset.
     * @return the parameterset
     */
    public DietParameterSetBO getParameterset()
    {
        return _parameterset;
    }

    /**
     * Sets the parameterset.
     *
     * @param parameterset the new parameterset
     */
    public void setParameterset(DietParameterSetBO parameterset)
    {
        _parameterset = parameterset;
    }

    private static ParametersetEditingController _singleton;

    /**
     * Gets the single instance of ParametersetEditingController.
     *
     * @return single instance of ParametersetEditingController
     */
    public static ParametersetEditingController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new ParametersetEditingController();
        }
        return _singleton;
    }

    /**
     * Creates a new parameterset.
     */
    public void createNew()
    {
        _parameterset = new DietParameterSetBO();
    }

    /**
     * Save parameterset.
     *
     * @return true, if successful
     */
    public boolean save()
    {
    	if(!validate()) return false;
        if (getErrors().getLength() > 0) return false;

        try
        {
            // assign parameters to set
            for (DietParameterTemplateBO template : _parameterset.getDietParameters())
            {
                template.setDietParameterSet(_parameterset);
            }
            
            HibernateUtil.currentSession().beginTransaction();
            DietParameterSetDAO dao = DAOFactory.getInstance().getDietParameterSetDAO();
            dao.makePersistent(_parameterset.getModel());
            HibernateUtil.currentSession().getTransaction().commit();
            _parameterset = null;
            return true;
        }
        catch (HibernateException e)
        {
            LOG.error("Could not save parameterset", e);
            HibernateUtil.currentSession().getTransaction().rollback();
            return false;
        }

    }


    /**
     * Validate the values.
     *
     * @return true, if successful
     */
    public boolean validate() {
    	boolean valid = true;
    	
    	getErrors().clear();
    	
    	validateDietParameterConflicts();
    	
    	if(StringUtils.isNullOrWhitespaceOnly(_parameterset.getName()))
		{
    		getErrors().add("Kein Name für das Parameterset angegeben!");
    		valid = false;
		}
    	
    	if(_parameterset.getDietParameters().getLength() == 0)
    	{
    		getErrors().add("Keine Parameter für dieses Parameterset angegeben!");
    		valid = false;
    	}
    	
		return valid;
	}

    /**
     * Validate diet parameter conflicts.
     */
    private void validateDietParameterConflicts()
    {
        List<IDietParameterizable> conflicts = ParameterTemplateValidator.getInstance().getConflictingComponents();
        for(IDietParameterizable component : conflicts)
        {
        	getErrors().add("Parameterkonflikt in: " + component.getDisplayText());
        }
    }

    
	/**
	 * Instantiates a new parameterset editing controller.
	 */
	protected ParametersetEditingController()
    {
        _errors = new ArrayList<String>();
    }

    /**
     * Gets the errors.
     *
     * @return the errors
     */
    public List<String> getErrors()
    {
        return _errors;
    }
}
