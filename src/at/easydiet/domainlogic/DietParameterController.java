package at.easydiet.domainlogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.Map;

import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietParameterTemplateBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.NutrimentParameterBO;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.TimeSpanBO;

/**
 * Provides data and actions for validating Parameters.
 */
public class DietParameterController
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(DietParameterController.class);

    /**
     * This is a unique instance, it is stored as this singleton
     */
    private static DietParameterController       _singleton;

    /**
     * Get a instance of this controller
     * 
     * @return Instance of {@link DietParameterController}
     */
    public static DietParameterController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new DietParameterController();
        }
        return _singleton;
    }

    /**
     * Initializes a new instance of the {@link DietParameterController} class.
     */
    private DietParameterController()
    {}

    /**
     * Validate the given dietplan if it matches the DietParameters defined
     * within the full hierarchy
     * 
     * @param plan
     *            the plan to validate
     * @return a list of violations against the available dietparameters
     */
    public List<ValidationResult> validateDietPlanDietParameters(DietPlanBO plan)
    {
        List<ValidationResult> violations = new ArrayList<ValidationResult>();

        // at first receive a list of dietparameters we need to validate
        Map<ParameterDefinitionBO, DietParameterTemplateBO> parametersToValidate = new HashMap<ParameterDefinitionBO, DietParameterTemplateBO>();
        addDietParametersToList(parametersToValidate, plan);

        // now as we have all parameters
        // we can validate hierarchy by hierarchy and sum up the values
        validateDietParameters(parametersToValidate, violations, plan);

        return violations;
    }

    /**
     * Describes the sum of different parameters (units)
     */
    private static class ValidationSumValue
    {
        /**
         * Stores the sum
         */
        private float                     _sum;
        /**
         * Stores the unit of this sum
         */
        private ParameterDefinitionUnitBO _unit;

        /**
         * Gets the sum.
         * 
         * @return the sum
         */
        public float getSum()
        {
            return _sum;
        }

        /**
         * Sets the sum.
         * 
         * @param sum
         *            the sum to set
         */
        public void setSum(float sum)
        {
            _sum = sum;
        }

        /**
         * Gets the unit.
         * 
         * @return the unit
         */
        @SuppressWarnings("unused")
        public ParameterDefinitionUnitBO getUnit()
        {
            return _unit;
        }

        /**
         * Initializes a new instance of the {@link ValidationSumValue} class.
         * 
         * @param unit
         */
        private ValidationSumValue(ParameterDefinitionUnitBO unit)
        {
            super();
            _sum = 0;
            _unit = unit;
        }
    }

    /**
     * Sums up all the {@link ParameterDefinitionBO}s
     * {@link IncompatibleClassChangeError}
     * 
     * @param parametersToValidate
     *            List of {@link ParameterDefinitionBO}s to validate and sum up
     * @return Map of {@link ParameterDefinitionBO}s and
     *         {@link ValidationSumValue}s
     */
    private Map<ParameterDefinitionBO, ValidationSumValue> buildSumMap(
            Map<ParameterDefinitionBO, DietParameterTemplateBO> parametersToValidate)
    {
        Map<ParameterDefinitionBO, ValidationSumValue> map = new HashMap<ParameterDefinitionBO, ValidationSumValue>();
        for (ParameterDefinitionBO key : parametersToValidate)
        {
            DietParameterTemplateBO value = (DietParameterTemplateBO) parametersToValidate
                    .get(key);
            map.put(value.getParameterDefinition(), new ValidationSumValue(
                    value.getParameterDefinitionUnit()));
        }
        return map;
    }

    /**
     * Validate the {@link DietParameterBO}s for the {@link DietPlanBO}
     * 
     * @param parametersToValidate
     *            Map of parameters to validate
     * @param violations
     *            List of violations against the parameters
     * @param plan
     *            The current plan to validate
     */
    private void validateDietParameters(
            Map<ParameterDefinitionBO, DietParameterTemplateBO> parametersToValidate,
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
        for (DietParameterTemplateBO parameter : plan.getDietParameters())
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
                violations.add(new ValidationResult(plan, violation, parameter,
                        dietPlanSums.get(parameter.getParameterDefinition())
                                .getSum()));
            }
        }
    }

    /**
     * Validate the {@link DietParameterBO}s for the {@link TimeSpanBO}
     * 
     * @param parametersToValidate
     *            Map of parameters to validate
     * @param dietPlanSums
     *            Sums of the parameters in the dietplan
     * @param violations
     *            Violations against the parameters
     * @param timeSpan
     *            {@link TimeSpanBO} to validate
     */
    private void validateDietParameters(
            Map<ParameterDefinitionBO, DietParameterTemplateBO> parametersToValidate,
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
        for (DietParameterTemplateBO parameter : timeSpan.getDietParameters())
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
                        parameter, timeSpanSums.get(
                                parameter.getParameterDefinition()).getSum()));
            }
        }
    }

    /**
     * Validate the {@link DietParameterBO}s for the {@link MealBO}
     * 
     * @param parametersToValidate
     *            Map of parameters to validate
     * @param dietPlanSums
     *            Sums of the parameters in the dietplan
     * @param timeSpanSums
     *            Sums of the parameters in the time span
     * @param violations
     *            Violations against the parameters
     * @param meal
     *            {@link MealBO} to validate
     */
    private void validateDietParameters(
            Map<ParameterDefinitionBO, DietParameterTemplateBO> parametersToValidate,
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
        for (DietParameterTemplateBO parameter : meal.getDietParameters())
        {
            CheckOperatorBO violation = parameter.getCheckOperator().isValid(
                    parameter.getFloatValue(),
                    mealSums.get(parameter.getParameterDefinition()).getSum());

            // test if checkoperator applies for specified value and summed
            // value
            if (violation != null)
            {
                // if not add a violation
                violations.add(new ValidationResult(meal, violation, parameter,
                        mealSums.get(parameter.getParameterDefinition())
                                .getSum()));
            }
        }
    }

    /**
     * Add all {@link DietParameterBO}s of a {@link DietPlanBO} to one list
     * 
     * @param toFill
     *            The list to fill
     * @param plan
     *            The plan which provides the {@link DietParameterBO}s
     */
    private void addDietParametersToList(
            Map<ParameterDefinitionBO, DietParameterTemplateBO> toFill,
            DietPlanBO plan)
    {
        addDietParametersToList(toFill, plan.getDietParameters());

        for (TimeSpanBO timeSpan : plan.getTimeSpans())
        {
            addDietParametersToList(toFill, timeSpan);
        }
    }

    /**
     * Add all {@link DietParameterBO}s of a {@link TimeSpanBO} to one list
     * 
     * @param toFill
     *            The list to fill
     * @param timeSpan
     *            The timespan which provides the {@link DietParameterBO}s
     */
    private void addDietParametersToList(
            Map<ParameterDefinitionBO, DietParameterTemplateBO> toFill,
            TimeSpanBO timeSpan)
    {
        addDietParametersToList(toFill, timeSpan.getDietParameters());

        for (MealBO meal : timeSpan.getMeals())
        {
            addDietParametersToList(toFill, meal.getDietParameters());
        }
    }

    /**
     * Add all {@link DietParameterBO}s of a list to one list
     * 
     * @param toFill
     *            The list to fill
     * @param dietParameters
     *            List of {@link DietParameterBO}s
     */
    private void addDietParametersToList(
            Map<ParameterDefinitionBO, DietParameterTemplateBO> toFill,
            List<DietParameterTemplateBO> dietParameters)
    {
        for (DietParameterTemplateBO param : dietParameters)
        {
            if (!toFill.containsKey(param.getParameterDefinition()))
            {
                toFill.put(param.getParameterDefinition(), param);
            }
        }
    }

    /**
     * Defines a validation result
     */
    public static class ValidationResult
    {
        /**
         * The object whichs parameters got violated
         */
        private IDietParameterizable    _affectedObject;
        /**
         * The type of error (bigger, smaller,...)
         */
        private CheckOperatorBO         _errorType;
        /**
         * This diet parameter is violated
         */
        private DietParameterTemplateBO _dietParameter;
        /**
         * The current value of the parameterviolation
         */
        private float                   _currentValue;

        /**
         * Gets the dietParameter which got violated.
         * 
         * @return the dietParameter
         */
        public DietParameterTemplateBO getDietParameter()
        {
            return _dietParameter;
        }

        /**
         * Gets the object which contained the dietparameter which got violated.
         * 
         * @return the object
         */
        public IDietParameterizable getAffectedObject()
        {
            return _affectedObject;
        }

        /**
         * Gets the type of violation.
         * 
         * @return the errorType
         */
        public CheckOperatorBO getErrorType()
        {
            return _errorType;
        }

        /**
         * Gets the sum value which the DietParameter had at the time of
         * violation
         * 
         * @return Value the DietParameter had at the time of violation
         */
        public float getCurrentValue()
        {
            return _currentValue;
        }

        /**
         * Initializes a new instance of the {@link ValidationResult} class.
         * 
         * @param affectedObject
         * @param errorType
         * @param parameter
         * @param currentValue
         */
        private ValidationResult(IDietParameterizable affectedObject,
                CheckOperatorBO errorType, DietParameterTemplateBO parameter,
                float currentValue)
        {
            super();
            _affectedObject = affectedObject;
            _errorType = errorType;
            _dietParameter = parameter;
            _currentValue = currentValue;
        }
    }
}
