package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.ContactJournalBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.NutritionProtocolBO;

public class DietTreatmentDetailViewController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietTreatmentDetailViewController.class);

    private DietTreatmentBO _dietTreatment;
    private ArrayList<DietPlanBO> _dietPlans;
    private ArrayList<ContactJournalBO> _contactJournals;
    private ArrayList<NutritionProtocolBO> _nutritionProtocols;
    
    private static DietTreatmentDetailViewController _singleton;
    public static DietTreatmentDetailViewController getInstance()
    {
        if(_singleton == null)
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
    
    public ArrayList<DietPlanBO> getDietPlans()
    {
        return _dietPlans;
    }

    public ArrayList<ContactJournalBO> getContactJournals()
    {
        return _contactJournals;
    }
    
    public void reloadTreatmentData()
    {
        if(_dietTreatment == null) return;
        _dietPlans = _dietTreatment.getDietPlanBOs();
        _contactJournals = _dietTreatment.getContactJournalBOs();
        _nutritionProtocols = _dietTreatment.getNutritionProtocolBOs();
    }

    public ArrayList<NutritionProtocolBO> getNutritionProcotols()
    {
        return _nutritionProtocols;
    }
}
