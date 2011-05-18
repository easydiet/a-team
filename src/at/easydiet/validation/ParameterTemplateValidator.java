/**
 * 
 */
package at.easydiet.validation;

import java.util.HashSet;
import java.util.Set;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.HashMap;
import org.apache.pivot.collections.List;
import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterTemplateBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionDataTypeBO;

/**
 * Validates given parameters if they conflict
 */
public class ParameterTemplateValidator {
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(ParameterTemplateValidator.class);

	private static ParameterTemplateValidator _singleton;

	private HashMap<IDietParameterizable, Set<DietParameterTemplateBO>> _cache;

	private ParameterTemplateValidator() {
		_cache = new HashMap<IDietParameterizable, Set<DietParameterTemplateBO>>();
	}

	public static ParameterTemplateValidator getInstance() {
		if (_singleton == null) {
			_singleton = new ParameterTemplateValidator();
		}
		return _singleton;
	}

	/**
	 * Get all conflicting components
	 * @return A list with conflicting components, if the list is empty, theres no conflict
	 */
	public List<IDietParameterizable> getConflictingComponents()
	{
		List<IDietParameterizable> conflicts = new ArrayList<IDietParameterizable>();
		for(IDietParameterizable comp : _cache)
		{
			// TODO[Joschi]: Check if this containsKey is correct or if we need a validation
			if(_cache.containsKey(comp) && !_cache.get(comp).isEmpty())
			{
				conflicts.add(comp);
			}
		}
		return conflicts;
	}
	
	/**
	 * Check if a dietParameter is valid
	 * 
	 * @param dietParameterBO
	 *            dietParameter to check
	 * @return true if it is
	 */
	public boolean isValid(IDietParameterizable paramterizable,
	        DietParameterTemplateBO dietParameterBO) {
		if(paramterizable == null)
		{
			return true;
		}
		else if (_cache.containsKey(paramterizable)) {
			return !_cache.get(paramterizable).contains(dietParameterBO);
		} else {
			// default value if the validator wasn't run
			return false;
		}

	}

	public boolean isValid(IDietParameterizable parameterizable) {
		return this.isValid(parameterizable, new HashSet<DietParameterTemplateBO>());
	}

	/**
	 * check if any parameters are conflicting
	 * 
	 * @param checkSet
	 *            a set with the parameters to check
	 * @param conflictingParameters
	 *            an empty set to add the conflicting Parameters
	 * @return true if no parameters are conflicting
	 */
	public boolean isValid(IDietParameterizable parameterizable,
			Set<DietParameterTemplateBO> conflictingParameters) {
		
		if(parameterizable == null)
		{
			return false;
		}
		
		if (_cache.containsKey(parameterizable)) {
			_cache.get(parameterizable).clear();
		} else {
			_cache.put(parameterizable, new HashSet<DietParameterTemplateBO>());
		}
		

		List<DietParameterTemplateBO> checkSet = parameterizable.getDietParameters();

		
		
		// fill a new set for better comparison
		Set<DietParameterTemplateBO> compareSet = new HashSet<DietParameterTemplateBO>();
		for (DietParameterTemplateBO copyParameter : checkSet) {
			compareSet.add((DietParameterTemplateBO) copyParameter);
		}

		// compare all parameters
		for (DietParameterTemplateBO checkParameter : checkSet) {
			ParameterDefinitionBO checkParameterDefinition = checkParameter
					.getParameterDefinition();
			CheckOperatorBO checkParameterOperator = checkParameter
					.getCheckOperator();

			// remove this parameter from the comparison set
			compareSet.remove(checkParameter);

			for (DietParameterTemplateBO compareParameter : compareSet) {
				ParameterDefinitionBO compareParameterDefinition = compareParameter
						.getParameterDefinition();

				LOG.debug(">>>checkParameterDefinition: "
						+ checkParameterDefinition.getName()
						+ ".equals(compareParameterDefinition: "
						+ compareParameterDefinition.getName()
						+ ") = "
						+ (checkParameterDefinition
								.equals(compareParameterDefinition)));

				// if definition is the same
				if (checkParameterDefinition.equals(compareParameterDefinition)) {
					// DEBUG:
					LOG.debug(">>>checkParameter.getUnitBO().getTypeBO().getName(): "
							+ checkParameter.getParameterDefinitionUnit()
									.getType().getName()
							+ " == ParameterDefinitionDataTypeBO.NUMBERS.getName(): "
							+ ParameterDefinitionDataTypeBO.NUMBERS.getName()
							+ " = "
							+ (checkParameter.getParameterDefinitionUnit()
									.getType() == ParameterDefinitionDataTypeBO.NUMBERS));
					LOG.debug(">>>compareParameter.getUnitBO().getTypeBO().getName(): "
							+ compareParameter.getParameterDefinitionUnit()
									.getType().getName()
							+ " == ParameterDefinitionDataTypeBO.NUMBERS.getName(): "
							+ ParameterDefinitionDataTypeBO.NUMBERS.getName()
							+ " = "
							+ (compareParameter.getParameterDefinitionUnit()
									.getType() == ParameterDefinitionDataTypeBO.NUMBERS));

					// if both are numbers
					if ((checkParameter.getParameterDefinitionUnit()
							.equals(compareParameter
									.getParameterDefinitionUnit()))
							&& (checkParameter.getParameterDefinitionUnit()
									.getType() == ParameterDefinitionDataTypeBO.NUMBERS && compareParameter
									.getParameterDefinitionUnit().getType() == ParameterDefinitionDataTypeBO.NUMBERS)) {
						// get number values
						double checkNumberValue;
						double compareNumberValue;

						checkNumberValue = Double.parseDouble(checkParameter
								.getValue());
						compareNumberValue = Double
								.parseDouble(compareParameter.getValue());

						// TODO: check if parameters say no to each other
						CheckOperatorBO compareParameterOperator = compareParameter
								.getCheckOperator();

						// DEBUG:
						LOG.debug(">>>checkParameterOperator.getName(): "
								+ checkParameterOperator.getName());
						LOG.debug(">>>compareParameterOperator.getName(): "
								+ compareParameterOperator.getName());

						// > && >=
						if (checkParameterOperator == CheckOperatorBO.BIGGER
								|| checkParameterOperator == CheckOperatorBO.EQUALORBIGGER) {
							if (compareParameterOperator == CheckOperatorBO.SMALLER) {
								// the lower limit needs to be smaller than the
								// upper limit
								if (!(checkNumberValue < compareNumberValue)) {
									addConflicting(parameterizable, conflictingParameters,
											checkParameter, compareParameter);
								}
							} else if (compareParameterOperator == CheckOperatorBO.EQUALORSMALLER) {
								// the lower limit needs to be smaller or equal
								// than the upper limit
								if (!(checkNumberValue <= compareNumberValue)) {
									addConflicting(parameterizable, conflictingParameters,
											checkParameter, compareParameter);
								}
							} else if (compareParameterOperator == CheckOperatorBO.BIGGER) {
								// is not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUALORBIGGER) {
								// is not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUAL) {
								// is not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.NOTEQUAL) {
								// is not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							}
						}

						// < && <=
						else if (checkParameterOperator == CheckOperatorBO.SMALLER
								|| checkParameterOperator == CheckOperatorBO.EQUALORSMALLER) {
							if (compareParameterOperator == CheckOperatorBO.SMALLER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUALORSMALLER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.BIGGER) {
								// the upper limit has to be bigger than the
								// lower limit
								if (!(checkNumberValue > compareNumberValue)) {
									addConflicting(parameterizable, conflictingParameters,
											checkParameter, compareParameter);
								}
							} else if (compareParameterOperator == CheckOperatorBO.EQUALORBIGGER) {
								// the upper limit has to be bigger than the
								// lower limit
								if (!(checkNumberValue >= compareNumberValue)) {
									addConflicting(parameterizable, conflictingParameters,
											checkParameter, compareParameter);
								}
							} else if (compareParameterOperator == CheckOperatorBO.EQUAL) {
								// is not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.NOTEQUAL) {
								// is not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							}
						}

						// ==
						else if (checkParameterOperator == CheckOperatorBO.EQUAL) {
							if (compareParameterOperator == CheckOperatorBO.SMALLER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUALORSMALLER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.BIGGER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUALORBIGGER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUAL) {
								// is not allowed
								if (!(checkNumberValue == compareNumberValue)) {
									addConflicting(parameterizable, conflictingParameters,
											checkParameter, compareParameter);
								}
							} else if (compareParameterOperator == CheckOperatorBO.NOTEQUAL) {
								// is not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							}
						}

						// !=
						else if (checkParameterOperator == CheckOperatorBO.NOTEQUAL) {
							if (compareParameterOperator == CheckOperatorBO.SMALLER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUALORSMALLER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.BIGGER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUALORBIGGER) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.EQUAL) {
								// not allowed
								addConflicting(parameterizable, conflictingParameters,
										checkParameter, compareParameter);
							} else if (compareParameterOperator == CheckOperatorBO.NOTEQUAL) {
								// is not allowed
								if (!(checkNumberValue == compareNumberValue)) {
									addConflicting(parameterizable, conflictingParameters,
											checkParameter, compareParameter);
								}
							}
						}
					} else {
						// TODO: compare strings
					}
				}
			}
		}

		if (conflictingParameters.isEmpty()) {
			return true;
		}

		// Default value
		return false;
	}

	/**
	 * add conflicting parameters to the conflict set if they are not already in
	 * it
	 * 
	 * @param conflictingParameters
	 * @param checkParameter
	 * @param compareParameter
	 */
	private void addConflicting(IDietParameterizable parameterizable, Set<DietParameterTemplateBO> conflictingParameters,
			DietParameterTemplateBO checkParameter, DietParameterTemplateBO compareParameter) {

		// fill two sets, because when you change the set, that's accesible from
		// the outside, we can not guarantee that the validation for
		// isValid(DietParameterBO) is correct

		if (!conflictingParameters.contains(checkParameter)) {
			conflictingParameters.add((DietParameterTemplateBO) checkParameter);
		}
		if (!conflictingParameters.contains(compareParameter)) {
			conflictingParameters.add(compareParameter);
		}

		if (!_cache.get(parameterizable).contains(checkParameter)) {
			_cache.get(parameterizable).add((DietParameterTemplateBO) checkParameter);
		}
		if (!_cache.get(parameterizable).contains(compareParameter)) {
			_cache.get(parameterizable).add(compareParameter);
		}
	}

	public void clearCache()
	{
		_cache.clear();
	}
}
