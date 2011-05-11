package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.HibernateException;

import at.easydiet.businessobjects.DietParameterSetBO;
import at.easydiet.businessobjects.DietParameterTemplateBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietParameterSetDAO;
import at.easydiet.dao.HibernateUtil;

public class ParametersetEditingController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(ParametersetEditingController.class);

    private DietParameterSetBO                  _parameterset;
    private List<String>                        _parameters;
	private List<String> 						_errors;


    /**
     * Gets the dietPlan.
     * @return the dietPlan
     */
    public DietParameterSetBO getParameterset()
    {
        return _parameterset;
    }

    public void setParameterset(DietParameterSetBO parameterset)
    {
        _parameterset = parameterset;
    }

    private static ParametersetEditingController _singleton;

    public static ParametersetEditingController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new ParametersetEditingController();
        }
        return _singleton;
    }

    public void createNew()
    {
        _parameterset = new DietParameterSetBO();
    }

    public boolean saveParameterset()
    {
        System.out.println(getErrors().getLength());
        if (getErrors().getLength() > 0) return false;

        try
        {
            // assign parameters to set
            for (DietParameterTemplateBO template : _parameterset.getDietParameters())
            {
                template.setDietParameterSet(_parameterset);
            }
            
            HibernateUtil.currentSession().beginTransaction();
            _parameterset.setName("test");
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


    protected ParametersetEditingController()
    {
        _errors = new ArrayList<String>();
    }

    public List<String> getParameters()
    {
        return _parameters;
    }

    public List<String> getErrors()
    {
        return _errors;
    }
    
}
