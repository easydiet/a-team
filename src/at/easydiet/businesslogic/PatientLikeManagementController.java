package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.businessobjects.PatientBO;
import at.easydiet.businessobjects.PatientLikeBO;
import at.easydiet.businessobjects.PatientLikeGradeBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.PatientDAO;
import at.easydiet.dao.RecipeDAO;
import at.easydiet.domainlogic.PatientLikeGradeController;
import at.easydiet.model.Recipe;

public class PatientLikeManagementController
{
    public static final org.apache.log4j.Logger    LOG = org.apache.log4j.Logger
                                                               .getLogger(PatientLikeManagementController.class);

    private PatientBO                              _patient;

    private static PatientLikeManagementController _singleton;

    public static PatientLikeManagementController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new PatientLikeManagementController();
        }
        return _singleton;
    }

    /**
     * Initializes a new instance of the {@link PatientLikeManagementController} class.
     */
    private PatientLikeManagementController()
    {}

    /**
     * Gets the patient.
     * @return the patient
     */
    public PatientBO getPatient()
    {
        return _patient;
    }

    /**
     * Sets the patient.
     * @param patient the patient to set
     */
    public void setPatient(PatientBO patient)
    {
        _patient = patient;
    }

    /**
     * Gets a list of the currently loaded likes.
     * @return
     */
    public List<PatientLikeBO> getLikes()
    {
        return _patient.getLikes();
    }

    public PatientLikeBO getNewLike()
    {
        return new PatientLikeBO();
    }

    public void addLike(PatientLikeBO newLike)
    {
        _patient.addLikes(newLike);
    }

    public List<RecipeBO> getRecipesForPattern(String text)
    {
        // is only wildcard?
        if(isOnlyWildcard(text))
        {
            return new ArrayList<RecipeBO>();
        }
        
        RecipeDAO dao = DAOFactory.getInstance().getRecipeDAO();
        List<RecipeBO> recipes = new ArrayList<RecipeBO>();
        
        for (Recipe recipe : dao.findByBlsCode(text))
        {
            recipes.add(new RecipeBO(recipe));
        }
        
        return recipes;        
    }

    private boolean isOnlyWildcard(String text)
    {
        if(text == null) return true;
        
        text = text.replace("%", "").replace("_", "").trim();
        
        return text.isEmpty();
    }

    public List<PatientLikeGradeBO> getGrades()
    {
        return PatientLikeGradeController.getInstance().getGrades();
    }

    public void removeLike(PatientLikeBO toRemove)
    {
        _patient.removeLikes(toRemove);
    }

    public boolean saveData()
    {
        try
        {
            HibernateUtil.currentSession().beginTransaction();
            PatientDAO dao = DAOFactory.getInstance().getPatientDAO();
            dao.makePersistent(_patient.getModel());
            HibernateUtil.currentSession().getTransaction().commit();
            return true;
        }
        catch(Exception e)
        {
            HibernateUtil.currentSession().getTransaction().rollback();
            return false;
        }
    }

    public void revertChanges()
    {
        // TODO: Find a good way to revert changes 
        // reload patient
        HibernateUtil.currentSession().evict(_patient.getModel());
        PatientDAO dao = DAOFactory.getInstance().getPatientDAO();
        _patient.setModel(dao.findById(_patient.getPatientId(), false));
    }
}
