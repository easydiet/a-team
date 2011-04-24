package at.easydiet.businesslogic;

import org.apache.pivot.collections.List;

import at.easydiet.businessobjects.ContactJournalBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietTreatmentDAO;

public class DietTreatmentDetailViewController
{
    public static final org.apache.log4j.Logger      LOG = org.apache.log4j.Logger
                                                                 .getLogger(DietTreatmentDetailViewController.class);

    private DietTreatmentBO                          _dietTreatment;
    private List<DietPlanBO>                    _dietPlans;
    private List<ContactJournalBO>              _contactJournals;
    private List<NutritionProtocolBO>           _nutritionProtocols;

    private static DietTreatmentDetailViewController _singleton;

    public static DietTreatmentDetailViewController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new DietTreatmentDetailViewController();
        }
        return _singleton;
    }

    private DietTreatmentDetailViewController()
    {

    }

    public DietTreatmentBO getDietTreatment()
    {
        return _dietTreatment;
    }

    public void setDietTreatment(DietTreatmentBO dietTreatment)
    {
        _dietTreatment = dietTreatment;
        reloadTreatmentData();
    }

    public List<DietPlanBO> getDietPlans()
    {
        return _dietPlans;
    }

    public List<ContactJournalBO> getContactJournals()
    {
        return _contactJournals;
    }

    public void reloadTreatmentData()
    {
        if (_dietTreatment == null) return;
        _dietPlans = _dietTreatment.getDietPlans();
        _contactJournals = _dietTreatment.getContactJournals();
        _nutritionProtocols = _dietTreatment.getNutritionProtocols();
    }

    public List<NutritionProtocolBO> getNutritionProcotols()
    {
        return _nutritionProtocols;
    }

    public void refresh()
    {
        DietTreatmentDAO dao = DAOFactory.getInstance().getDietTreatmentDAO();
        dao.refresh(_dietTreatment.getModel());
        _dietTreatment.updateContactJournalsCache();
        _dietTreatment.updateDietParametersCache();
        _dietTreatment.updateDietPlansCache();
        _dietTreatment.updateNutritionProtocolsCache();
        _dietTreatment.updatePatientStatesCache();
        _dietTreatment.updateSystemUsersCache();
        reloadTreatmentData();
    }
}
