/**
 * 
 */
package at.easydiet.validation;

import java.util.HashSet;
import java.util.Set;

import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionDataTypeBO;

/**
 * @author Mathias
 *
 */
public class ParameterValidator {

	/**
	 * check if any parameters are conflicting
	 * @param checkSet
	 * @return true if no parameters are conflicting
	 */
	public boolean isValid(Set<DietParameterBO> checkSet)
	{
		return this.isValid(checkSet, new HashSet<DietParameterBO>());
	}
	 
	/**
	 * check if any parameters are conflicting
	 * @param checkSet a set with the parameters to check
	 * @param conflictingParameters an empty set to add the conflicting Parameters
	 * @return true if no parameters are conflicting
	 */
	public boolean isValid(Set<DietParameterBO> checkSet, Set<DietParameterBO> conflictingParameters)
	{	
		//fill a new set for better comparison
		Set<DietParameterBO> compareSet = new HashSet<DietParameterBO>();
		for(DietParameterBO copyParameter : checkSet)
		{
			compareSet.add(copyParameter);
		}
		
		//compare all parameters
		for(DietParameterBO checkParameter : checkSet)
		{
			ParameterDefinitionBO checkParameterDefinition = checkParameter.getParameterDefinition();
			CheckOperatorBO checkParameterOperator = checkParameter.getCheckOperator();
			
			//remove this parameter from the comparison set
			compareSet.remove(checkParameter);
			
			for(DietParameterBO compareParameter : compareSet)
			{
				ParameterDefinitionBO compareParameterDefinition = compareParameter.getParameterDefinition();
				
				//DEBUG:
				System.out.println(">>>checkParameterDefinition: " + checkParameterDefinition.getName() + ".equals(compareParameterDefinition: " + compareParameterDefinition.getName() +") = " + (checkParameterDefinition.equals(compareParameterDefinition)));
				
				//if definition is the same
				if(checkParameterDefinition.equals(compareParameterDefinition))
				{
					//if both are numbers			
					if(checkParameter.getParameterDefinitionUnit().getType() == ParameterDefinitionDataTypeBO.NUMBERS && compareParameter.getParameterDefinitionUnit().getType() == ParameterDefinitionDataTypeBO.NUMBERS)
					{
						//get number values
						double checkNumberValue;
						double compareNumberValue;

						checkNumberValue = Double.parseDouble(checkParameter.getValue());
						compareNumberValue = Double.parseDouble(compareParameter.getValue());
							
							
						//TODO: check if parameters say no to each other
						CheckOperatorBO compareParameterOperator = compareParameter.getCheckOperator();
						
						
						// > && >=
						if(checkParameterOperator == CheckOperatorBO.BIGGER || checkParameterOperator == CheckOperatorBO.EQUALORBIGGER )
						{
							if(compareParameterOperator == CheckOperatorBO.SMALLER)
							{
								//the lower limit needs to be smaller than the upper limit
								if(!(checkNumberValue < compareNumberValue))
								{
									addConflicting(conflictingParameters,
											checkParameter, compareParameter);
								}
							}
							if(compareParameterOperator == CheckOperatorBO.EQUALORSMALLER)
							{
								//the lower limit needs to be smaller or equal than the upper limit
								if(!(checkNumberValue <= compareNumberValue))
								{
									addConflicting(conflictingParameters,
											checkParameter, compareParameter);
								}
							}
							else if (compareParameterOperator == CheckOperatorBO.BIGGER)
							{
								//is not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.EQUALORBIGGER)
							{
								//is not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.EQUAL)
							{
								//is not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.NOTEQUAL)
							{
								//is not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
						}
						
						// < && <=
						else if(checkParameterOperator == CheckOperatorBO.SMALLER || checkParameterOperator == CheckOperatorBO.EQUALORSMALLER)
						{
							if(compareParameterOperator == CheckOperatorBO.SMALLER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							if(compareParameterOperator == CheckOperatorBO.EQUALORSMALLER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.BIGGER)
							{
								//the upper limit has to be bigger than the lower limit
								if(!(checkNumberValue < compareNumberValue))
								{
									addConflicting(conflictingParameters,
											checkParameter, compareParameter);
								}
							}
							else if (compareParameterOperator == CheckOperatorBO.EQUALORBIGGER)
							{
								//the upper limit has to be bigger than the lower limit
								if(!(checkNumberValue <= compareNumberValue))
								{
									addConflicting(conflictingParameters,
											checkParameter, compareParameter);
								}
							}
							else if (compareParameterOperator == CheckOperatorBO.EQUAL)
							{
								//is not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.NOTEQUAL)
							{
								//is not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
						}
						
						// ==
						else if(checkParameterOperator == CheckOperatorBO.EQUAL)
						{
							if(compareParameterOperator == CheckOperatorBO.SMALLER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							if(compareParameterOperator == CheckOperatorBO.EQUALORSMALLER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.BIGGER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.EQUALORBIGGER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.EQUAL)
							{
								//is not allowed
								if(!(checkNumberValue == compareNumberValue))
								{
									addConflicting(conflictingParameters,
											checkParameter, compareParameter);
								}
							}
							else if (compareParameterOperator == CheckOperatorBO.NOTEQUAL)
							{
								//is not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
						}
						
						// !=
						else if(checkParameterOperator == CheckOperatorBO.NOTEQUAL)
						{
							if(compareParameterOperator == CheckOperatorBO.SMALLER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							if(compareParameterOperator == CheckOperatorBO.EQUALORSMALLER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.BIGGER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.EQUALORBIGGER)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.EQUAL)
							{
								//not allowed
								addConflicting(conflictingParameters,
										checkParameter, compareParameter);
							}
							else if (compareParameterOperator == CheckOperatorBO.NOTEQUAL)
							{
								//is not allowed
								if(!(checkNumberValue == compareNumberValue))
								{
									addConflicting(conflictingParameters,
											checkParameter, compareParameter);
								}
							}
						}
					}
					else
					{
						//TODO: compare strings
					}
				}
			}
		}
			
		if(conflictingParameters.isEmpty())
		{
			return true;
		}
		
		//Default value
		return false;
	}

	/**
	 * add conflicting parameters to the conflict set if they are not already in it
	 * @param conflictingParameters
	 * @param checkParameter
	 * @param compareParameter
	 */
	private void addConflicting(Set<DietParameterBO> conflictingParameters,
			DietParameterBO checkParameter, DietParameterBO compareParameter) {
		if(!conflictingParameters.contains(checkParameter))
		{
			conflictingParameters.add(checkParameter);
		}
		if(!conflictingParameters.contains(compareParameter))
		{
			conflictingParameters.add(compareParameter);
		}
	}
}
