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

public class ParameterTableViewController {
	public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(MealContainerController.class);

	private List<DietParameterBO> _list;
	private IDietParameterizable _parameterProvider;

	public ParameterTableViewController() {
	}

	public List<ParameterDefinitionBO> getAllDefinitions() {
		ArrayList<ParameterDefinitionBO> definitions = new ArrayList<ParameterDefinitionBO>();

		// TODO: own controller?
		for (ParameterDefinition parameterDefinition : DAOFactory.getInstance()
				.getParameterDefinitionDAO().findAll()) {
			definitions.add(new ParameterDefinitionBO(parameterDefinition));
		}
		return definitions;
	}

	public DietParameterBO getParameterTemplate() {
		DietParameterBO newBo = new DietParameterBO();

		ParameterDefinitionBO newDefinition = new ParameterDefinitionBO(
				DAOFactory.getInstance().getParameterDefinitionDAO()
						.findById((long) 1, false));

		newBo.setCheckOperator(CheckOperatorBO.SMALLER);
		newBo.setDietParameterType(DietParameterTypeBO.TARGET_PARAMETER);

		newBo.setParameterDefinition(newDefinition);

		newBo.setValue("1");

		newBo.setParameterDefinitionUnit(newBo.getParameterDefinition()
				.getUnits().get(0));

		return newBo;
	}

	public void addTemplate() {
		_parameterProvider.addDietParameters(getParameterTemplate());
	}

	public void remove(DietParameterBO dietParameter) {
		_parameterProvider.removeDietParameters(dietParameter);
	}

	@SuppressWarnings("unchecked")
	public void setData(List<?> data) {
		_list = (List<DietParameterBO>) data;
	}

	public void setParameterProvider(IDietParameterizable provider) {
		_parameterProvider = provider;
	}

}
