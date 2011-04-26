package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;

import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.NutrimentParameterBO;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
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
        Map<ParameterDefinitionBO, DietParameterBO> parametersToValidate = new HashMap<ParameterDefinitionBO, DietParameterBO>();
        addDietParametersToList(parametersToValidate, plan);

        // now as we have all parameters
        // we can validate hierarchy by hierarchy and sum up the values
        validateDietParameters(parametersToValidate, violations, plan);

        return violations;
    }

    private static class ValidationSumValue
    {
        private float                     _sum;
        private ParameterDefinitionUnitBO _unit;

        /**
         * Gets the sum.
         * @return the sum
         */
        public float getSum()
        {
            return _sum;
        }

        /**
         * Sets the sum.
         * @param sum the sum to set
         */
        public void setSum(float sum)
        {
            _sum = sum;
        }

        /**
         * Gets the unit.
         * @return the unit
         */
        public ParameterDefinitionUnitBO getUnit()
        {
            return _unit;
        }

        /**
         * Initializes a new instance of the {@link ValidationSumValue} class.
         * @param sum
         * @param unit
         */
        private ValidationSumValue(ParameterDefinitionUnitBO unit)
        {
            super();
            _sum = 0;
            _unit = unit;
        }
    }

    private Map<ParameterDefinitionBO, ValidationSumValue> buildSumMap(
            Map<ParameterDefinitionBO, DietParameterBO> parameters)
    {
        Map<ParameterDefinitionBO, ValidationSumValue> map = new HashMap<ParameterDefinitionBO, ValidationSumValue>();
        for (ParameterDefinitionBO key : parameters)
        {
            DietParameterBO value = parameters.get(key);
            map.put(value.getParameterDefinition(), new ValidationSumValue(
                    value.getParameterDefinitionUnit()));
        }
        return map;
    }

    // for dietplans
    private void validateDietParameters(
            Map<ParameterDefinitionBO, DietParameterBO> parametersToValidate,
            List<ValidationResult> violations, DietPlanBO plan)
    {
        // store sums of dietplan
        Map<ParameterDefinitionBO, ValidationSumValue> dietPlanSums = buildSumMap(parametersToValidate);

        // process deeper hierachies first for summing
        for (TimeSpanBO timeSpan : plan.getTimeSpans())
        {
            validateDietParameters(parametersToValidate, dietPlanSums,
                    violations, timeSpan);
        }

        // validate parameters of dietplan
        for (DietParameterBO parameter : plan.getDietParameters())
        {
            CheckOperatorBO violation = parameter.getCheckOperator().isValid(
                    parameter.getFloatValue(),
                    dietPlanSums.get(parameter.getParameterDefinition())
                            .getSum());

            // test if checkoperator applies for specified value and summed
            // value
            if (violation != null)
            {
                // if not add a violation
                violations
                        .add(new ValidationResult(plan, violation, parameter, dietPlanSums.get(parameter.getParameterDefinition()).getSum()));
            }
        }
    }

    // for timespans
    private void validateDietParameters(
            Map<ParameterDefinitionBO, DietParameterBO> parametersToValidate,
            Map<ParameterDefinitionBO, ValidationSumValue> dietPlanSums,
            List<ValidationResult> violations, TimeSpanBO timeSpan)
    {
        // store sums of timespan
        Map<ParameterDefinitionBO, ValidationSumValue> timeSpanSums = buildSumMap(parametersToValidate);

        // process deeper hierachies first for summing
        for (MealBO meal : timeSpan.getMeals())
        {
            validateDietParameters(parametersToValidate, dietPlanSums,
                    timeSpanSums, violations, meal);
        }

        // validate parameters of timespan
        for (DietParameterBO parameter : timeSpan.getDietParameters())
        {
            CheckOperatorBO violation = parameter.getCheckOperator().isValid(
                    parameter.getFloatValue(),
                    timeSpanSums.get(parameter.getParameterDefinition())
                            .getSum());

            // test if checkoperator applies for specified value and summed
            // value
            if (violation != null)
            {
                // if not add a violation
                violations.add(new ValidationResult(timeSpan, violation,
                        parameter, timeSpanSums.get(parameter.getParameterDefinition()).getSum()));
            }
        }
    }

    // for meals
    private void validateDietParameters(
            Map<ParameterDefinitionBO, DietParameterBO> parametersToValidate,
            Map<ParameterDefinitionBO, ValidationSumValue> dietPlanSums,
            Map<ParameterDefinitionBO, ValidationSumValue> timeSpanSums,
            List<ValidationResult> violations, MealBO meal)
    {
        // store sums of meal
        Map<ParameterDefinitionBO, ValidationSumValue> mealSums = buildSumMap(parametersToValidate);

        // process all lines in recipe
        for (MealLineBO line : meal.getMealLines())
        {
            // iterate over all nutrimentparameters to sum up
            for (NutrimentParameterBO nutrimentParameter : line.getRecipe()
                    .getNutrimentParameters())
            {
                // do we need this parameter for validation?
                if (parametersToValidate.containsKey(nutrimentParameter
                        .getParameterDefinition()))
                {
                    // can we convert the unit of the current nutrimentParameter
                    // to the required
                    // unit in sum?
                    ParameterDefinitionUnitBO summingUnit = parametersToValidate
                            .get(nutrimentParameter.getParameterDefinition())
                            .getParameterDefinitionUnit();

                    if (DietParameterUnitController.getInstance().canConvert(
                            nutrimentParameter.getUnit(), summingUnit))
                    {
                        try
                        {
                            float nutrimentAmount = Float
                                    .valueOf(nutrimentParameter.getValue());

                            // step 1
                            // convert the recipeAmount from the recipeUnit to
                            // the mealLineUnit
                            float recipeAmountInMealLineUnit = DietParameterUnitController
                                    .getInstance().convert(
                                            line.getRecipe().getUnit(),
                                            line.getUnit(),
                                            line.getRecipe().getAmount());

                            // step 2
                            // convert the nutritionParameterValue form the
                            // nutritionParameterUnit to the
                            // summingUnit
                            float nutrimenAmountInSummingUnit = DietParameterUnitController
                                    .getInstance().convert(
                                            nutrimentParameter.getUnit(),
                                            summingUnit, nutrimentAmount);

                            // step 3
                            // interpolate from the definition to the data set
                            // in the mealLine
                            float realAmountOfNutriment = (line.getQuantity() * nutrimenAmountInSummingUnit)
                                    / recipeAmountInMealLineUnit;

                            // add value to all sum collections
                            ValidationSumValue sum = mealSums
                                    .get(nutrimentParameter
                                            .getParameterDefinition());
                            sum.setSum(sum.getSum() + realAmountOfNutriment);
                            sum = timeSpanSums.get(nutrimentParameter
                                    .getParameterDefinition());
                            sum.setSum(sum.getSum() + realAmountOfNutriment);
                            sum = dietPlanSums.get(nutrimentParameter
                                    .getParameterDefinition());
                            sum.setSum(sum.getSum() + realAmountOfNutriment);
                        }
                        catch (Exception e)
                        {
                            LOG.warn("Could not validate parameter", e);
                        }
                    }
                }
            }
        }

        // validate parameters of meal
        for (DietParameterBO parameter : meal.getDietParameters())
        {
            CheckOperatorBO violation = parameter.getCheckOperator().isValid(
                    parameter.getFloatValue(),
                    mealSums.get(parameter.getParameterDefinition()).getSum());

            // test if checkoperator applies for specified value and summed
            // value
            if (violation != null)
            {
                // if not add a violation
                violations
                        .add(new ValidationResult(meal, violation, parameter, mealSums.get(parameter.getParameterDefinition()).getSum()));
            }
        }
    }

    private void addDietParametersToList(
            Map<ParameterDefinitionBO, DietParameterBO> toFill, DietPlanBO plan)
    {
        addDietParametersToList(toFill, plan.getDietParameters());

        for (TimeSpanBO timeSpan : plan.getTimeSpans())
        {
            addDietParametersToList(toFill, timeSpan);
        }
    }

    private void addDietParametersToList(
            Map<ParameterDefinitionBO, DietParameterBO> toFill,
            TimeSpanBO timeSpan)
    {
        addDietParametersToList(toFill, timeSpan.getDietParameters());

        for (MealBO meal : timeSpan.getMeals())
        {
            addDietParametersToList(toFill, meal.getDietParameters());
        }
    }

    private void addDietParametersToList(
            Map<ParameterDefinitionBO, DietParameterBO> toFill,
            List<DietParameterBO> dietParameters)
    {
        for (DietParameterBO param : dietParameters)
        {
            if (!toFill.containsKey(param.getParameterDefinition()))
            {
                toFill.put(param.getParameterDefinition(), param);
            }
        }
    }

    public static class ValidationResult
    {
        private IDietParameterizable _affectedObject;
        private CheckOperatorBO      _errorType;
        private DietParameterBO      _dietParameter;
        private float                _currentValue;

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
        public IDietParameterizable getAffectedObject()
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
         * @param currentValue
         */
        private ValidationResult(IDietParameterizable affectedObject,
                CheckOperatorBO errorType, DietParameterBO dietParameter,
                float currentValue)
        {
            super();
            _affectedObject = affectedObject;
            _errorType = errorType;
            _dietParameter = dietParameter;
            _currentValue = currentValue;
        }
    }
}
