package at.easydiet.domainlogic;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.HibernateException;

import at.easydiet.EasyDietApplication;
import at.easydiet.businessobjects.DietParameterSetBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.PlanTypeBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietParameterSetDAO;
import at.easydiet.dao.DietParameterTemplateDAO;
import at.easydiet.dao.DietPlanDAO;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.MealDAO;
import at.easydiet.domainlogic.DietParameterController.ValidationResult;
import at.easydiet.util.CollectionUtils;
import at.easydiet.util.StringUtils;
//import at.easydiet.validation.ParameterValidator;

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
        //validateDietPlan();
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
        //validateDietPlan(true);

        if (getErrors().getLength() > 0) return false;

        try
        {
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
