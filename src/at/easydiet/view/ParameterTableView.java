package at.easydiet.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.SortDirection;
import org.apache.pivot.wtk.TableView;

import at.easydiet.businesslogic.ParameterTableViewController;
import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterTemplateBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.view.EasyTableViewRowEditor.RowEditorListener;
import at.easydiet.view.content.ParameterCellRenderer;

/**
 * Shows a tableView which handles parameters
 */
public class ParameterTableView extends TableView
{
    /**
     * Stores the {@link ParameterTableViewController}
     */
    private ParameterTableViewController _controller = new ParameterTableViewController();

    /**
     * Stores the {@link EasyTableViewRowEditor} used for this {@link TableView}
     */
    private EasyTableViewRowEditor       _editor;

    /**
     * Stores the {@link ListButton} to select {@link ParameterDefinitionBO}s
     */
    private ListButton                   _definitionListButton;
    /**
     * Stores the {@link ListButton} to select {@link CheckOperatorBO}s
     */
    private ListButton                   _checkOperatorListButton;
    /**
     * Stores the {@link ListButton} to select {@link ParameterDefinitionUnitBO}
     * s
     */
    private ListButton                   _parameterDefinitionUnitListButton;

    /**
     * Set the handled type of this {@link ParameterTableView}
     * 
     * @param newInstanceType
     *            The type to handle
     */
    public void setNewInstanceType(
            Class<? extends DietParameterTemplateBO> newInstanceType)
    {
        _controller.setNewInstanceType(newInstanceType);
    }

    /**
     * Initializes a new instance of the {@link ParameterTableView} class.
     * 
     * @param tableData
     *            The data which the table shows
     */
    public ParameterTableView(List<?> tableData)
    {
        super(tableData);
    }

    /**
     * Initializes a new instance of the {@link ParameterTableView} class.
     */
    public ParameterTableView()
    {
        this(new ArrayList<DietParameterTemplateBO>());
    }

    /**
     * Set a new parameter data provider
     * 
     * @param provider
     *            parameter data provider
     */
    public void setParameterProvider(IDietParameterizable provider)
    {
        setTableData(provider.getDietParameters());
        getController().setParameterProvider(provider);
        // set the validator into all cell renderers
        setValidatorToCellRenderers();
        refreshView();
    }

    /**
     * Returns the controller of this instance
     * 
     * @return The {@link ParameterTableViewController}
     */
    private ParameterTableViewController getController()
    {
        if (_controller != null)
        {
            return _controller;
        }
        return _controller = new ParameterTableViewController();
    }

    /**
     * Initializes the {@link ParameterTableView}
     */
    public void initialize()
    {
        // get editor
        _editor = (EasyTableViewRowEditor) getRowEditor();

        _editor.getRowEditorListeners().add(new RowEditorListener.Adapter()
        {
            public void changesSaved(RowEditor rowEditor, TableView tableView,
                    int rowIndex, int columnIndex)
            {
                refreshView();
            }
        });

        // add parameternames to the listbutton
        _editor.getRowEditorListeners().add(
                new EasyTableViewRowEditor.RowEditorListener.Adapter()
                {
                    @Override
                    public Vote previewEditRow(RowEditor rowEditor,
                            TableView tableView, int rowIndex, int columnIndex)
                    {

                        final DietParameterTemplateBO row = (DietParameterTemplateBO) getTableData()
                                .get(rowIndex);

                        // define listbutton
                        _definitionListButton = (ListButton) _editor
                                .getCellEditors().get(
                                        "parameterDefinition.name");

                        // get all possible definitions for the listbutton
                        _definitionListButton.setListData(_controller
                                .getAllDefinitions());

                        // set listeners
                        _definitionListButton.getListButtonSelectionListeners()
                                .add(new ListButtonSelectionListener.Adapter()
                                {
                                    @Override
                                    public void selectedItemChanged(
                                            ListButton listbutton, Object obj)
                                    {
                                        refreshUnitButton((ParameterDefinitionBO) listbutton
                                                .getSelectedItem());
                                        refreshOperatorButton((ParameterDefinitionBO) listbutton
                                                .getSelectedItem());
                                    }
                                });

                        for (int i = 0; i < _definitionListButton.getListData()
                                .getLength(); i++)
                        {
                            ParameterDefinitionBO parameterDefinitionBO = (ParameterDefinitionBO) _definitionListButton
                                    .getListData().get(i);
                            if (parameterDefinitionBO.getName()
                                    .equalsIgnoreCase(
                                            row.getParameterDefinition()
                                                    .getName()))
                            {
                                _definitionListButton.setSelectedIndex(i);
                                layout();
                                break;
                            }
                        }

                        return super.previewEditRow(rowEditor, tableView,
                                rowIndex, columnIndex);
                    }
                });

        // add checkOperators to the ListButton
        _editor.getRowEditorListeners().add(
                new EasyTableViewRowEditor.RowEditorListener.Adapter()
                {
                    @Override
                    public Vote previewEditRow(RowEditor rowEditor,
                            TableView tableView, int rowIndex, int columnIndex)
                    {
                        // update list of units
                        _checkOperatorListButton = (ListButton) _editor
                                .getCellEditors().get("checkOperator.name");
                        DietParameterTemplateBO row = (DietParameterTemplateBO) getTableData()
                                .get(rowIndex);

                        _checkOperatorListButton
                                .setListData((org.apache.pivot.collections.List<?>) CheckOperatorBO
                                        .getAllOperators());

                        for (int i = 0; i < _checkOperatorListButton
                                .getListData().getLength(); i++)
                        {
                            CheckOperatorBO checkOperatorBO = (CheckOperatorBO) _checkOperatorListButton
                                    .getListData().get(i);
                            if (checkOperatorBO.getName().equalsIgnoreCase(
                                    row.getCheckOperator().getName()))
                            {
                                _checkOperatorListButton.setSelectedIndex(i);
                                layout();
                                break;
                            }
                        }

                        return super.previewEditRow(rowEditor, tableView,
                                rowIndex, columnIndex);
                    }
                });

        // add units to the ListButton
        _editor.getRowEditorListeners().add(
                new EasyTableViewRowEditor.RowEditorListener.Adapter()
                {
                    @Override
                    public Vote previewEditRow(RowEditor rowEditor,
                            TableView tableView, int rowIndex, int columnIndex)
                    {
                        // update list of units
                        _parameterDefinitionUnitListButton = (ListButton) _editor
                                .getCellEditors().get(
                                        "parameterDefinitionUnit.name");
                        DietParameterTemplateBO row = (DietParameterTemplateBO) getTableData()
                                .get(rowIndex);

                        _parameterDefinitionUnitListButton.setListData(row
                                .getParameterDefinition().getUnits());

                        for (int i = 0; i < _parameterDefinitionUnitListButton
                                .getListData().getLength(); i++)
                        {
                            ParameterDefinitionUnitBO parameterDefinitionUnitBO = (ParameterDefinitionUnitBO) _parameterDefinitionUnitListButton
                                    .getListData().get(i);
                            if (parameterDefinitionUnitBO.getName()
                                    .equalsIgnoreCase(
                                            row.getParameterDefinitionUnit()
                                                    .getName()))
                            {
                                _parameterDefinitionUnitListButton
                                        .setSelectedIndex(i);
                                layout();
                                break;
                            }
                        }

                        return super.previewEditRow(rowEditor, tableView,
                                rowIndex, columnIndex);
                    }
                });

        this.setSort(this.getColumns().get(0).getName(),
                SortDirection.ASCENDING);
    }

    /**
     * refresh the listbutton to choose operators
     * 
     * @param parameterDefinitionBO
     */
    public void refreshOperatorButton(
            ParameterDefinitionBO parameterDefinitionBO)
    {
        if (_checkOperatorListButton != null && parameterDefinitionBO != null)
        {
            List<CheckOperatorBO> list = CheckOperatorBO.getAllOperators();

            _checkOperatorListButton.setListData(list);
            _checkOperatorListButton.setSelectedIndex(0);
        }
    }

    /**
     * refresh the listbutton to choose units
     * 
     * @param parameterDefinitionBO
     */
    public void refreshUnitButton(ParameterDefinitionBO parameterDefinitionBO)
    {
        if (_parameterDefinitionUnitListButton != null
                && parameterDefinitionBO != null)
        {

            List<ParameterDefinitionUnitBO> list = parameterDefinitionBO
                    .getUnits();

            _parameterDefinitionUnitListButton.setListData(list);
            _parameterDefinitionUnitListButton.setSelectedIndex(0);
        }
    }

    /**
     * Set the Validator into all cell renderers
     */
    private void setValidatorToCellRenderers()
    {
        for (Column col : getColumns())
        {
            ((ParameterCellRenderer) col.getCellRenderer())
                    .setParameterizable(_controller.getParameterProvider());
        }
    }

    /**
     * Starts a table view row editor
     */
    public void beginEdit()
    {
        layout();
        // TODO: not on it's place
        // _editor.beginEdit(this, getTableData().getLength() - 1, 0);

    }

    /**
     * Reload the view
     */
    public void refreshView()
    {
        validateView();
        this.invalidate();
    }

    /**
     * Validates this view
     * 
     * @return true if no conflicts are found
     */
    public boolean validateView()
    {
        return _controller.isValid();
    }

    /**
     * Add a new template parameter to this view
     */
    public void addParameterTemplate()
    {
        _controller.addTemplate();
        refreshView();

        beginEdit();
    }

    /**
     * Remove a parameter from the view
     * 
     * @param dietParameter
     *            parameter to remove
     */
    public void remove(DietParameterTemplateBO dietParameter)
    {
        _controller.remove(dietParameter);
        refreshView();
    }
}
