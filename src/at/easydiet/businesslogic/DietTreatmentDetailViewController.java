package at.easydiet.businesslogic;

import java.util.List;

import org.apache.pivot.collections.ArrayList;

import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.NutritionProtocolBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietTreatmentDAO;
import at.easydiet.dao.NutritionProtocolDAO;
import at.easydiet.model.NutritionProtocol;
import at.easydiet.view.DietTreatmentDetailView;

/**
 * This controller provides data and functions for the
 * {@link DietTreatmentDetailView}
 */
public class DietTreatmentDetailViewController
{
    /**
     * Logger for debugging
     */
    private static final org.apache.log4j.Logger     LOG = org.apache.log4j.Logger
                                                                 .getLogger(DietTreatmentDetailViewController.class);

    /**
     * The current opened diet treatment
     */
    private DietTreatmentBO                          _dietTreatment;
    private ArrayList<NutritionProtocolBO>           _nutritionProtocols;

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static DietTreatmentDetailViewController _singleton;

    /**
     * Returns a globally unique instance of this class.
     * 
     * @return a globally unique instance which gets initiated on the first
     *         call.
     */
    public static DietTreatmentDetailViewController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new DietTreatmentDetailViewController();
        }
        return _singleton;
    }

    /**
     * Initializes a new instance of the
     * {@link DietTreatmentDetailViewController} class.
     */
    private DietTreatmentDetailViewController()
    {
        // Singleton
    }

    /**
     * Gets the currently selected {@link DietTreatmentBO} within this
     * application.
     * 
     * @return the currently selected {@link DietTreatmentBO}
     * @see PatientDetailViewController#getPatient()
     */
    public DietTreatmentBO getDietTreatment()
    {
        return _dietTreatment;
    }

    /**
     * Sets the selected {@link DietTreatmentBO} within this application.
     * 
     * @param dietTreatment
     */
    public void setDietTreatment(DietTreatmentBO dietTreatment)
    {
        _dietTreatment = dietTreatment;
        reloadTreatmentData();
    }

    /**
     * Updates the cached lists of the dietTreatment
     */
    private void reloadTreatmentData()
    {
        if (_dietTreatment == null) return;
        _dietTreatment.updateContactJournalsCache();
        _dietTreatment.updateDietParametersCache();
        _dietTreatment.updateDietPlansCache();
        _dietTreatment.updatePatientStatesCache();
        _dietTreatment.updateSystemUsersCache();
        
        // load nutritionprotocols
        NutritionProtocolDAO dao = DAOFactory.getInstance().getNutritionProtocolDAO();
        List<NutritionProtocol> nps = dao.findByDietTreatment(_dietTreatment.getModel());
        _nutritionProtocols = new ArrayList<NutritionProtocolBO>();
        for (NutritionProtocol nutritionProtocol : nps) 
        {
            _nutritionProtocols.add(new NutritionProtocolBO(nutritionProtocol));
        }
    }
    
    public ArrayList<NutritionProtocolBO> getNutritionProtocols() 
    {
        return _nutritionProtocols;
    }

    /**
     * Refreshes the currently loaded diettreamtent and it's data.
     */
    public void refresh()
    {
        LOG.trace("Refreshing DietTreatment");
        DietTreatmentDAO dao = DAOFactory.getInstance().getDietTreatmentDAO();
        dao.refresh(_dietTreatment.getModel());
        reloadTreatmentData();
    }
}
