package at.easydiet.domainlogic;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.apache.pivot.collections.ArrayList;
import org.hibernate.HibernateException;

import at.easydiet.EasyDietApplication;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.DietTreatmentBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.TimeSpanBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietPlanDAO;
import at.easydiet.dao.HibernateUtil;
import at.easydiet.dao.MealDAO;
import at.easydiet.util.CollectionUtils;

public class DietPlanEditingController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietPlanEditingController.class);

    private DietPlanBO                          _dietPlan;
    private ArrayList<String>                   _mealCodes;
    private ArrayList<String>                   _mealNames;

    /**
     * Gets the dietPlan.
     * @return the dietPlan
     */
    public DietPlanBO getDietPlan()
    {
        return _dietPlan;
    }

    public void setDietPlan(DietPlanBO dietPlan)
    {
        _dietPlan = dietPlan;
    }

    public TimeSpanBO createTimeSpan()
    {
        TimeSpanBO t = new TimeSpanBO();
        t.addToDietPlan(_dietPlan);
        return t;
    }

    private static DietPlanEditingController _singleton;

    public static DietPlanEditingController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new DietPlanEditingController();
        }
        return _singleton;
    }

    public void createNew(DietTreatmentBO dietTreatment)
    {
        _dietPlan = new DietPlanBO();
        _dietPlan.setDietTreatment(dietTreatment.getTreatment());
    }

    public MealBO createMeal(TimeSpanBO timeSpan)
    {
        MealBO meal = new MealBO();
        meal.addToTimeSpan(timeSpan);
        return meal;
    }

    public MealLineBO addRecipeToMeal(MealBO meal, RecipeBO recipe)
    {
        MealLineBO line = new MealLineBO();
        line.setMeal(meal.getMeal());
        line.setRecipe(recipe.getRecipe());
        line.setQuantity(recipe.getAmount());
        meal.getMealLines().add(line.getMealLine());
        return line;
    }

    public void removeMealLine(MealLineBO selectedRow)
    {
        if (selectedRow == null) return;
        selectedRow.removeFromMeal();
    }

    public MealLineBO addRecipeAsAlternative(MealLineBO mealLine,
            RecipeBO recipe)
    {
        MealLineBO alternative = new MealLineBO();
        alternative.setParent(mealLine.getMealLine());
        alternative.setMeal(mealLine.getMeal());
        alternative.setRecipe(recipe.getRecipe());
        alternative.setQuantity(recipe.getAmount());

        // insert alternative after mealLine
        int index = mealLine.getMeal().getMealLines()
                .indexOf(mealLine.getMealLine()) + 1;
        mealLine.getMeal().getMealLines().add(index, alternative.getMealLine());
        return alternative;
    }

    public boolean saveDietPlan()
    {
        validateDietPlan();

        SimpleDateFormat formatter = new SimpleDateFormat(
                EasyDietApplication.DATE_FORMAT);
        // generate a good name if it's a new plan
        if (_dietPlan.getDietPlanId() <= 0)
        {
            _dietPlan.setCreatedOn(new Date());
            String name = String.format("Diätplan vom %s",
                    formatter.format(_dietPlan.getCreatedOn()));
            _dietPlan.setName(name);
        }

        // update creator
        _dietPlan.setCreator(SystemUserController.getInstance()
                .getCurrentUser().getSystemUser());

        try
        {
            HibernateUtil.currentSession().beginTransaction();
            DietPlanDAO dao = DAOFactory.getInstance().getDietPlanDAO();
            dao.makePersistent(_dietPlan.getDietPlan());
            _dietPlan = null;
            HibernateUtil.currentSession().getTransaction().commit();
            return true;
        }
        catch(HibernateException e)
        {
            HibernateUtil.currentSession().getTransaction().rollback();
            return false;
        }
    }

    public void validateDietPlan()
    {

    }

    private DietPlanEditingController()
    {

    }

    public void refresh()
    {
        if (_dietPlan != null && _dietPlan.getDietPlanId() > 0)
        {
            DietPlanDAO dao = DAOFactory.getInstance().getDietPlanDAO();
            dao.refresh(_dietPlan.getDietPlan());
        }

        MealDAO mealDao = DAOFactory.getInstance().getMealDAO();
        _mealCodes = CollectionUtils.toPivotList(mealDao.findCodes());
        _mealNames = CollectionUtils.toPivotList(mealDao.findNames());
    }

    public ArrayList<String> getMealCodes()
    {
        return _mealCodes;
    }

    public ArrayList<String> getMealNames()
    {
        return _mealNames;
    }
    
    public Set<DietParameterBO> getDietPlanParameters()
    {
    	return _dietPlan.getDietParameterBOs();
    }
}
