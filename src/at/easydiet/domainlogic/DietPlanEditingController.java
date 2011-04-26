package at.easydiet.domainlogic;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.hibernate.HibernateException;

import at.easydiet.EasyDietApplication;
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
    private List<String>                        _mealCodes;
    private List<String>                        _mealNames;
    private List<String>                        _errors;

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
        validateDietPlan();
    }

    public TimeSpanBO createTimeSpan()
    {
        TimeSpanBO t = new TimeSpanBO();
        _dietPlan.addTimeSpans(t);
        validateDietPlan();
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
        _dietPlan.setDietTreatment(dietTreatment);
    }

    public MealBO createMeal(TimeSpanBO timeSpan)
    {
        MealBO meal = new MealBO();
        timeSpan.addMeals(meal);
        validateDietPlan();
        return meal;
    }

    public MealLineBO addRecipeToMeal(MealBO meal, RecipeBO recipe)
    {
        MealLineBO line = new MealLineBO();
        line.setMeal(meal);
        line.setRecipe(recipe);
        line.setQuantity(recipe.getAmount());
        meal.addMealLines(line);
        validateDietPlan();
        return line;
    }

    public void removeMealLine(MealLineBO selectedRow)
    {
        if (selectedRow == null) return;
        selectedRow.getMeal().removeMealLines(selectedRow);
        validateDietPlan();
    }

    public MealLineBO addRecipeAsAlternative(MealLineBO mealLine,
            RecipeBO recipe)
    {
        MealLineBO alternative = new MealLineBO();
        alternative.setParent(mealLine);
        alternative.setMeal(mealLine.getMeal());
        alternative.setRecipe(recipe);
        alternative.setQuantity(recipe.getAmount());

        // insert alternative after mealLine
        int index = mealLine.getMeal().getMealLines().indexOf(mealLine) + 1;
        mealLine.getMeal().addMealLines(index, alternative);
        validateDietPlan();
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
                .getCurrentUser());

        try
        {
            HibernateUtil.currentSession().beginTransaction();
            DietPlanDAO dao = DAOFactory.getInstance().getDietPlanDAO();
            dao.makePersistent(_dietPlan.getModel());
            _dietPlan = null;
            HibernateUtil.currentSession().getTransaction().commit();
            return true;
        }
        catch (HibernateException e)
        {
        	LOG.error("Could not save dietplan", e);
            HibernateUtil.currentSession().getTransaction().rollback();
            return false;
        }
    }

    public void validateDietPlan()
    {
        _errors.clear();
        
        // validate all timespans
        validateTimeSpans();
        
        // validate all dietparameters for conflicts
        validateDietParameterConflicts();
        
        // validate all dietparameters if they match for hierarchy
        validateDietPlanParameters();
    }

    private void validateTimeSpans()
    {
        for (TimeSpanBO timeSpan : _dietPlan.getTimeSpans())
        {
            validateTimeSpan(timeSpan);
        }
    }

    private void validateDietParameterConflicts()
    {
        // TODO: Joschi code
    }

    private void validateDietPlanParameters()
    {
//        DietParameter
//        for (TimeSpanBO timeSpan : _dietPlan.getTimeSpans())
//        {
//            validateTimeSpanParameters()
//        }
    }

    private void validateTimeSpan(TimeSpanBO t)
    {
        // check for timespan collisions
        List<Object> timeSpanCollisions = TimeSpanController.getInstance().validateCollisions(t);
        
        // generate error messages 
        for (Object object : timeSpanCollisions)
        {
            if(TimeSpanBO.class.isAssignableFrom(object.getClass()))
            {
                _errors.add(String.format("Der Zeitraum '%s' überschneidet sich mit dem Zeitraum '%s'",
                        t.getDisplayName(), ((TimeSpanBO)object).getDisplayName()));
            }
            else if(DietPlanBO.class.isAssignableFrom(object.getClass()))
            {
                _errors.add(String.format("Der Zeitraum '%s' überschneidet sich mit dem Diätplan '%s'",
                        t.getDisplayName(), ((DietPlanBO)object).getName()));
            }
            else if(DietTreatmentBO.class.isAssignableFrom(object.getClass()))
            {
                _errors.add(String.format("Der Zeitraum '%s' überschneidet sich mit der Diätbehandlung '%s'",
                        t.getDisplayName(), ((DietTreatmentBO)object).getName()));
            }
        }
    }

    protected DietPlanEditingController()
    {
        _errors = new ArrayList<String>();
    }

    public void refresh()
    {
        refresh(true);
    }
    public void refresh(boolean refreshDietPlan)
    {
        if (refreshDietPlan && _dietPlan != null && _dietPlan.getDietPlanId() > 0)
        {
            DietPlanDAO dao = DAOFactory.getInstance().getDietPlanDAO();
            dao.refresh(_dietPlan.getModel());
        }

        MealDAO mealDao = DAOFactory.getInstance().getMealDAO();
        _mealCodes = CollectionUtils.toPivotList(mealDao.findCodes());
        _mealNames = CollectionUtils.toPivotList(mealDao.findNames());
    }

    public List<String> getMealCodes()
    {
        return _mealCodes;
    }

    public List<String> getMealNames()
    {
        return _mealNames;
    }

    public List<String> getErrors()
    {
        return _errors;
    }

    public void deleteTimeSpan(TimeSpanBO timeSpan)
    {
        timeSpan.getDietPlan().removeTimeSpans(timeSpan);
        validateDietPlan();
    }

    public void deleteMeal(MealBO meal)
    {
        meal.getTimeSpan().removeMeals(meal);
        validateDietPlan();
    }
}
