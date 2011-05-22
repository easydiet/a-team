package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietParameterTemplateBO;
import at.easydiet.businessobjects.IDietParameterizable;

/**
 * This is the background class for the ParameterTableViewTemplate.bxml
 */
public class ParameterTableViewTemplate extends BoxPane implements Bindable
{
    /**
     * Stores the {@link ParameterTableView}
     */
    private ParameterTableView _table;
    /**
     * Stores the {@link Button} to add a new {@link DietParameterBO}
     */
    private Button             _addParameters;

    /**
     * Stores the {@link Button} to remove a {@link DietParameterBO}
     */
    private Button             _removeParameter;

    /**
     * Set the handled type of this {@link ParameterTableView}
     * 
     * @param newInstanceType
     *            The type to handle
     */
    public void setNewInstanceType(
            Class<? extends DietParameterTemplateBO> newInstanceType)
    {
        _table.setNewInstanceType(newInstanceType);
    }

    @Override
    public void initialize(Map<String, Object> namespace, URL location,
            Resources resources)
    {
        _table = (ParameterTableView) namespace.get("parameterTableView");
        _addParameters = (Button) namespace.get("addTableViewParameters");
        _removeParameter = (Button) namespace.get("removeTableViewParameter");

        _table.initialize();

        _addParameters.getButtonPressListeners().add(new ButtonPressListener()
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
                        removeParameter((DietParameterTemplateBO) _table
                                .getSelectedRow());
                    }
                });
        // end: parameterTableView
    }

    /**
     * @return the table
     */
    public ParameterTableView getTable()
    {
        return _table;
    }

    /**
     * @return the addParameters
     */
    public Button getAddParameters()
    {
        return _addParameters;
    }

    /**
     * @return the removeParameter
     */
    public Button getRemoveParameter()
    {
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
     * 
     * @param dietParameter
     *            parameter to remove
     */
    private void removeParameter(DietParameterTemplateBO dietParameter)
    {
        _table.remove(dietParameter);
    }

    /**
     * Sets the parameter providert
     * 
     * @param provider
     *            A instance of an {@link IDietParameterizable} object
     */
    public void setParameterProvider(IDietParameterizable provider)
    {
        _table.setParameterProvider(provider);
    }
}
