package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.businessobjects.PatientLikeGradeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientLikeGradeDAO;
import at.easydiet.model.PatientLikeGrade;

/**
 * Provides data and methods for
 */
public class PatientLikeGradeController
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(PatientLikeGradeController.class);

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static PatientLikeGradeController    _singleton;

    /**
     * Get a Instance of this {@link PatientLikeGradeController}
     * 
     * @return The instance of this {@link PatientLikeGradeController}
     */
    public static PatientLikeGradeController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new PatientLikeGradeController();
        }
        return _singleton;
    }

    /**
     * Initializes a new instance of the {@link PatientLikeGradeController}
     * class.
     */
    private PatientLikeGradeController()
    {}

    /**
     * Stores the grades
     */
    private List<PatientLikeGradeBO> _grades;

    /**
     * Gets all grades
     * 
     * @return List of all {@link PatientLikeGradeBO}
     */
    public List<PatientLikeGradeBO> getGrades()
    {
        if (_grades == null)
        {
            refreshGrades();
        }
        return _grades;
    }

    /**
     * Reload grades
     */
    private void refreshGrades()
    {
        _grades = new ArrayList<PatientLikeGradeBO>();

        PatientLikeGradeDAO dao = DAOFactory.getInstance()
                .getPatientLikeGradeDAO();
        for (PatientLikeGrade grade : dao.findAll())
        {
            _grades.add(new PatientLikeGradeBO(grade));
        }
    }
}
