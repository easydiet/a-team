package at.easydiet.businesslogic;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietParameterTypeBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.model.ParameterDefinition;

/**
 * Controlls the Parameter Table View
 */
public class ParameterTableViewController {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(MealContainerController.class);

	private IDietParameterizable _parameterProvider;
	private ArrayList<ParameterDefinitionBO> _definitions;
	
	public ParameterTableViewController() {
	}

	/**
	 * Gets all possible parameterdefinitions out of the database.
	 * Loads once per instance.
	 * @return returns list of parameter definitions
	 */
	public List<ParameterDefinitionBO> getAllDefinitions() {
		if(_definitions == null || _definitions.isEmpty())
		{
			_definitions = new ArrayList<ParameterDefinitionBO>();
	
			// TODO: own controller?
			for (ParameterDefinition parameterDefinition : DAOFactory.getInstance()
					.getParameterDefinitionDAO().findAll()) {
				_definitions.add(new ParameterDefinitionBO(parameterDefinition));
			}
		}
		return _definitions;
	}

	/**
	 * Creates a new dietParameter for use as a Template
	 * @return returns a new dietParameter
	 */
	public DietParameterBO getParameterTemplate() {
		DietParameterBO newBo = new DietParameterBO();

		ParameterDefinitionBO newDefinition = getAllDefinitions().get(0);
		newBo.setCheckOperator(CheckOperatorBO.SMALLER);
		newBo.setDietParameterType(DietParameterTypeBO.TARGET_PARAMETER);

		newBo.setParameterDefinition(newDefinition);

		newBo.setValue("1");

		newBo.setParameterDefinitionUnit(newBo.getParameterDefinition()
				.getUnits().get(0));

		return newBo;
	}

	/**
	 * Add a dietParameter to the _parameterProvider and the tableView
	 */
	public void addTemplate() {
		_parameterProvider.addDietParameters(getParameterTemplate());
	}

	/**
	 * Removes a dietParameter from the _parametProvider and the tableView
	 * @param dietParameter dietParameter to remove
	 */
	public void remove(DietParameterBO dietParameter) {
		_parameterProvider.removeDietParameters(dietParameter);
	}

	/**
	 * Set the parameter data provider for this view
	 * @param provider
	 */
	public void setParameterProvider(IDietParameterizable provider) {
		_parameterProvider = provider;
	}

}
