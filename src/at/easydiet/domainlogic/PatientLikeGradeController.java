package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;

import at.easydiet.businessobjects.PatientLikeGradeBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.PatientLikeGradeDAO;
import at.easydiet.model.PatientLikeGrade;

public class PatientLikeGradeController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(PatientLikeGradeController.class);

    private static PatientLikeGradeController   _singleton;

    public static PatientLikeGradeController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new PatientLikeGradeController();
        }
        return _singleton;
    }

    private PatientLikeGradeController()
    {}

    
    
    private List<PatientLikeGradeBO> _grades;
    public List<PatientLikeGradeBO> getGrades()
    {
        if(_grades == null)
        {
            refreshGrades();
        }
        return _grades;
    }

    private void refreshGrades()
    {
        _grades = new ArrayList<PatientLikeGradeBO>();
        
        PatientLikeGradeDAO dao = DAOFactory.getInstance().getPatientLikeGradeDAO();
        for (PatientLikeGrade grade : dao.findAll())
        {
            _grades.add(new PatientLikeGradeBO(grade));
        }
    }
}
