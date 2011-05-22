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
import at.easydiet.view.PatientLikeManagementView;

/**
 * Provides data and actions for the {@link PatientLikeManagementView}.
 */
public class PatientLikeManagementController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger   LOG = org.apache.log4j.Logger
                                                               .getLogger(PatientLikeManagementController.class);

    /**
     * Stores the current patient
     */
    private PatientBO                              _patient;

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static PatientLikeManagementController _singleton;

    /**
     * Get an instance of {@link PatientLikeManagementController}
     * 
     * @return The instance of the {@link PatientLikeManagementController}
     */
    public static PatientLikeManagementController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new PatientLikeManagementController();
        }
        return _singleton;
    }

    /**
     * Initializes a new instance of the {@link PatientLikeManagementController}
     * class.
     */
    private PatientLikeManagementController()
    {}

    /**
     * Gets the patient.
     * 
     * @return the patient
     */
    public PatientBO getPatient()
    {
        return _patient;
    }

    /**
     * Sets the patient.
     * 
     * @param patient
     *            the patient to set
     */
    public void setPatient(PatientBO patient)
    {
        _patient = patient;
    }

    /**
     * Gets a list of the currently loaded likes.
     * 
     * @return List of all {@link PatientLikeBO}
     */
    public List<PatientLikeBO> getLikes()
    {
        return _patient.getLikes();
    }

    /**
     * Get a new {@link PatientLikeBO}
     * 
     * @return A new instance of {@link PatientLikeBO}
     */
    public PatientLikeBO getNewLike()
    {
        return new PatientLikeBO();
    }

    /**
     * Add a new {@link PatientLikeBO} to the {@link PatientBO}
     * 
     * @param newLike
     *            The new {@link PatientLikeBO}
     */
    public void addLike(PatientLikeBO newLike)
    {
        _patient.addLikes(newLike);
    }

    /**
     * Get a list of all {@link RecipeBO} corresponding to the pattern
     * 
     * @param text
     *            Pattern
     * @return List of {@link RecipeBO}s
     */
    public List<RecipeBO> getRecipesForPattern(String text)
    {
        // is only wildcard?
        if (isOnlyWildcard(text))
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

    /**
     * Checks if the pattern is just a wildcard
     * 
     * @param text
     *            Pattern
     * @return True if the pattern is just a wildcard
     */
    private boolean isOnlyWildcard(String text)
    {
        if (text == null) return true;

        text = text.replace("%", "").replace("_", "").trim();

        return text.isEmpty();
    }

    /**
     * Get a list of all {@link PatientLikeGradeBO}s
     * 
     * @return List of all {@link PatientLikeGradeBO}s
     */
    public List<PatientLikeGradeBO> getGrades()
    {
        return PatientLikeGradeController.getInstance().getGrades();
    }

    /**
     * Remove a {@link PatientLikeBO} from a {@link PatientBO}
     * 
     * @param toRemove
     *            {@link PatientLikeBO} to Remove
     */
    public void removeLike(PatientLikeBO toRemove)
    {
        _patient.removeLikes(toRemove);
    }

    /**
     * Save the {@link PatientBO} with it's {@link PatientLikeBO}s
     * 
     * @return True if saving was possible
     */
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
        catch (Exception e)
        {
            HibernateUtil.currentSession().getTransaction().rollback();
            return false;
        }
    }

    /**
     * Discard all the changes and reload the {@link PatientBO} from the
     * database.
     */
    public void revertChanges()
    {
        // TODO: Find a good way to revert changes
        // reload patient
        HibernateUtil.currentSession().evict(_patient.getModel());
        PatientDAO dao = DAOFactory.getInstance().getPatientDAO();
        _patient.setModel(dao.findById(_patient.getPatientId(), false));
    }
}
