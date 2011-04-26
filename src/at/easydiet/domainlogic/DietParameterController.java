package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.HashSet;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Set;

import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.TimeSpanBO;

public class DietParameterController
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DietParameterController.class);

    private static DietParameterController      _singleton;

    public static DietParameterController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new DietParameterController();
        }
        return _singleton;
    }

    private DietParameterController()
    {}

    /**
     * Validate the given dietplan if it matches the DietParameters defined
     * within the full hierarchy
     * @param plan the plan to validate
     * @return a list of violations agains the available dietparameters
     */
    public List<ValidationResult> validateDietPlanDietParameters(DietPlanBO plan)
    {
        List<ValidationResult> violations = new ArrayList<ValidationResult>();

        // at first receive a list of dietparameters we need to validate
        Set<ParameterDefinitionBO> parametersToValidate = new HashSet<ParameterDefinitionBO>();
        addDietParametersToList(parametersToValidate, plan);

        // now as we have all parameters
        // we can validate hierarchy by hierarchy and sum up the values
        validateDietParameters(parametersToValidate, violations, plan);

        return violations;
    }

    private Map<ParameterDefinitionBO, Float> buildSumMap(
            Set<ParameterDefinitionBO> parameters)
    {
        Map<ParameterDefinitionBO, Float> map = new HashMap<ParameterDefinitionBO, Float>();
        for (ParameterDefinitionBO param : parameters)
        {
            map.put(param, 0f);
        }
        return map;
    }

    private void validateDietParameters(
            Set<ParameterDefinitionBO> parametersToValidate,
            List<ValidationResult> violations, DietPlanBO plan)
    {
        // store sums of dietplan
        Map<ParameterDefinitionBO, Float> dietPlanSums = buildSumMap(parametersToValidate);

        // process deeper hierachies first for summing
        for (TimeSpanBO timeSpan : plan.getTimeSpans())
        {
            validateDietParameters(parametersToValidate, dietPlanSums,
                    violations, timeSpan);
        }

        // validate parameters of dietplan
        for (DietParameterBO parameter : plan.getDietParameters())
        {
            CheckOperatorBO violation = parameter.getCheckOperator().isValid(parameter.getFloatValue(),
                    dietPlanSums.get(parameter.getParameterDefinition()));
            
            // test if checkoperator applies for specified value and summed value
            if (violation != null)
            {
                // if not add a violation
                violations.add(new ValidationResult(plan, violation, parameter));
            }
        }

        // add sums
    }

    private void validateDietParameters(
            Set<ParameterDefinitionBO> parametersToValidate,
            Map<ParameterDefinitionBO, Float> dietPlanSums,
            List<ValidationResult> violations, TimeSpanBO timeSpan)
    {

    }

    private void validateDietParameters(Map<ParameterDefinitionBO, Float> sums,
            List<ValidationResult> violations, TimeSpanBO timeSpan)
    {
        Map<ParameterDefinitionBO, Float> timeSpanSums = new HashMap<ParameterDefinitionBO, Float>();

        // process deeper hierachies first for summing
        for (MealBO meal : timeSpan.getMeals())
        {
            validateDietParameters(timeSpanSums, violations, meal);
        }

        // validate parameters of timeSpan

        // add sums of timespan to dietplan sum
    }

    private void validateDietParameters(
            Map<ParameterDefinitionBO, Float> timeSpanSums,
            List<ValidationResult> violations, MealBO meal)
    {
        // we need to sum up

    }

    private void addDietParametersToList(Set<ParameterDefinitionBO> toFill,
            DietPlanBO plan)
    {
        addDietParametersToList(toFill, plan.getDietParameters());

        for (TimeSpanBO timeSpan : plan.getTimeSpans())
        {
            addDietParametersToList(toFill, timeSpan);
        }
    }

    private void addDietParametersToList(Set<ParameterDefinitionBO> toFill,
            TimeSpanBO timeSpan)
    {
        for (MealBO meal : timeSpan.getMeals())
        {
            addDietParametersToList(toFill, meal.getDietParameters());
        }
    }

    private void addDietParametersToList(Set<ParameterDefinitionBO> toFill,
            List<DietParameterBO> dietParameters)
    {
        for (DietParameterBO param : dietParameters)
        {
            if (!toFill.contains(param.getParameterDefinition()))
            {
                toFill.add(param.getParameterDefinition());
            }
        }
    }

    public static class ValidationResult
    {
        private Object          _affectedObject;
        private CheckOperatorBO _errorType;
        private DietParameterBO _dietParameter;
        private float           _currentValue;

        /**
         * Gets the dietParameter which got violated.
         * @return the dietParameter
         */
        public DietParameterBO getDietParameter()
        {
            return _dietParameter;
        }

        /**
         * Gets the object which contained the dietparameter which got violated.
         * @return the object
         */
        public Object getAffectedObject()
        {
            return _affectedObject;
        }

        /**
         * Gets the type of violation.
         * @return the errorType
         */
        public CheckOperatorBO getErrorType()
        {
            return _errorType;
        }

        /**
         * Gets the sum value which the DietParameter had at the time of
         * violation
         * @return
         */
        public float getCurrentValue()
        {
            return _currentValue;
        }

        /**
         * Initializes a new instance of the {@link ValidationResult} class.
         * @param affectedObject
         * @param errorType
         * @param dietParameter
         */
        private ValidationResult(Object affectedObject,
                CheckOperatorBO errorType, DietParameterBO dietParameter)
        {
            super();
            _affectedObject = affectedObject;
            _errorType = errorType;
            _dietParameter = dietParameter;
        }

    }
}
