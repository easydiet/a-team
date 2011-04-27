package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;

import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.TimeSpanBO;

public class ParameterTableViewTemplate extends BoxPane implements Bindable {
	
	private ParameterTableView _table;
	private Button _addParameters;
	private Button _removeParameter;

	@Override
	public void initialize(Map<String, Object> namespace, URL location, Resources resources) {
		_table = (ParameterTableView)namespace.get("parameterTableView");
		_addParameters = (Button)namespace.get("addTableViewParameters");
		_removeParameter = (Button)namespace.get("removeTableViewParameter");
		
        _table.initialize();
        // TODO: setParameterProvider - but where?

        _addParameters.getButtonPressListeners().add(
                new ButtonPressListener()
                {

                    public void buttonPressed(Button arg0)
                    {
                        addNewParameters();
                    }
                });

        _removeParameter.getButtonPressListeners().add(
                new ButtonPressListener()
                {

                    public void buttonPressed(Button arg0)
                    {
                        removeParameter((DietParameterBO) _table
                                .getSelectedRow());
                    }
                });
        // end: parameterTableView
	}

	/**
	 * @return the table
	 */
	public ParameterTableView getTable() {
		return _table;
	}

	/**
	 * @return the addParameters
	 */
	public Button getAddParameters() {
		return _addParameters;
	}

	/**
	 * @return the removeParameter
	 */
	public Button getRemoveParameter() {
		return _removeParameter;
	}
	
	/**
     * Adds a new parameter into the view
     */
    private void addNewParameters()
    {
        _table.addParameterTemplate();
    }

    /**
     * Removes a parameter from the view
     * @param dietParameter parameter to remove
     */
    private void removeParameter(DietParameterBO dietParameter)
    {
        _table.remove(dietParameter);
    }

	public void setParameterProvider(IDietParameterizable provider) {
		_table.setParameterProvider(provider);
	}

}