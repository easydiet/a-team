package at.easydiet.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.TableView;

import at.easydiet.businesslogic.ParameterTableViewController;
import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.IDietParameterizable;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.validation.ParameterValidator;
import at.easydiet.view.EasyTableViewRowEditor.RowEditorListener;
import at.easydiet.view.content.ParameterCellRenderer;

/**
 * Shows a tableView which handles parameters
 */
public class ParameterTableView extends TableView {
	private ParameterTableViewController _controller = new ParameterTableViewController();

	private EasyTableViewRowEditor _editor;
	private ListButton _definitionListButton;
	private ListButton _checkOperatorListButton;
	private ListButton _parameterDefinitionUnitListButton;
	
	//used for validation of this tableview
	private ParameterValidator _validator = new ParameterValidator();
	
	public ParameterTableView(List<?> tableData) {
		super(tableData);
	}

	public ParameterTableView() {
		this(new ArrayList<DietParameterBO>());}

	/**
	 * Set a new parameter data provider
	 * @param provider parameter data provider
	 */
	public void setParameterProvider(IDietParameterizable provider)
	{
		setTableData(provider.getDietParameters());
		refreshView();
		getController().setParameterProvider(provider);
	}
	
	/**
	 * returns the controller
	 * @return
	 */
	private ParameterTableViewController getController() {
		if(_controller != null)
		{
			return _controller;
		}
		return new ParameterTableViewController();
	}

	/**
	 * initalize this view and set cellrenderers
	 */
	public void initialize() {

		// set the validator into all cell renderers
		setValidatorToCellRenderers();

		// get editor
		_editor = (EasyTableViewRowEditor) getRowEditor();

		_editor.getRowEditorListeners().add(new RowEditorListener.Adapter() {
			public void changesSaved(RowEditor rowEditor, TableView tableView,
					int rowIndex, int columnIndex) {
				refreshView();
			}
		});

		// add parameternames to the listbutton
		_editor.getRowEditorListeners().add(
				new EasyTableViewRowEditor.RowEditorListener.Adapter() {
					@Override
					public Vote previewEditRow(RowEditor rowEditor,
							TableView tableView, int rowIndex, int columnIndex) {

						final DietParameterBO row = (DietParameterBO) getTableData()
								.get(rowIndex);

						// define listbutton
						_definitionListButton = (ListButton) _editor
								.getCellEditors().get(
										"parameterDefinition.name");

						// get all possible definitions for the listbutton
						_definitionListButton
								.setListData(_controller
										.getAllDefinitions());

						// set listeners
						_definitionListButton.getListButtonSelectionListeners()
								.add(new ListButtonSelectionListener.Adapter() {
									@Override
									public void selectedItemChanged(
											ListButton listbutton, Object obj) {
										refreshUnitButton((ParameterDefinitionBO) listbutton
												.getSelectedItem());
										refreshOperatorButton((ParameterDefinitionBO) listbutton
												.getSelectedItem());
									}
								});

						for (int i = 0; i < _definitionListButton.getListData()
								.getLength(); i++) {
							ParameterDefinitionBO parameterDefinitionBO = (ParameterDefinitionBO) _definitionListButton
									.getListData().get(i);
							if (parameterDefinitionBO.getName()
									.equalsIgnoreCase(
											row.getParameterDefinition()
													.getName())) {
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
				new EasyTableViewRowEditor.RowEditorListener.Adapter() {
					@Override
					public Vote previewEditRow(RowEditor rowEditor,
							TableView tableView, int rowIndex, int columnIndex) {
						// update list of units
						_checkOperatorListButton = (ListButton) _editor
								.getCellEditors().get("checkOperator.name");
						DietParameterBO row = (DietParameterBO) getTableData()
								.get(rowIndex);

						_checkOperatorListButton
								.setListData((org.apache.pivot.collections.List<?>) CheckOperatorBO
										.getAllOperators());

						for (int i = 0; i < _checkOperatorListButton
								.getListData().getLength(); i++) {
							CheckOperatorBO checkOperatorBO = (CheckOperatorBO) _checkOperatorListButton
									.getListData().get(i);
							if (checkOperatorBO.getName().equalsIgnoreCase(
									row.getCheckOperator().getName())) {
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
				new EasyTableViewRowEditor.RowEditorListener.Adapter() {
					@Override
					public Vote previewEditRow(RowEditor rowEditor,
							TableView tableView, int rowIndex, int columnIndex) {
						// update list of units
						_parameterDefinitionUnitListButton = (ListButton) _editor
								.getCellEditors().get(
										"parameterDefinitionUnit.name");
						DietParameterBO row = (DietParameterBO) getTableData()
								.get(rowIndex);

						_parameterDefinitionUnitListButton.setListData(row
								.getParameterDefinition().getUnits());

						for (int i = 0; i < _parameterDefinitionUnitListButton
								.getListData().getLength(); i++) {
							ParameterDefinitionUnitBO parameterDefinitionUnitBO = (ParameterDefinitionUnitBO) _parameterDefinitionUnitListButton
									.getListData().get(i);
							if (parameterDefinitionUnitBO.getName()
									.equalsIgnoreCase(
											row.getParameterDefinitionUnit()
													.getName())) {
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

	}
	
	/**
	 * refresh the listbutton to choose operators
	 * @param parameterDefinitionBO
	 */
	public void refreshOperatorButton(
			ParameterDefinitionBO parameterDefinitionBO) {
		if (_checkOperatorListButton != null && parameterDefinitionBO != null) {
			List<CheckOperatorBO> list = CheckOperatorBO.getAllOperators();

			_checkOperatorListButton.setListData(list);
			_checkOperatorListButton.setSelectedIndex(0);
		}
	}

	/**
	 * refresh the listbutton to choose units
	 * @param parameterDefinitionBO
	 */
	public void refreshUnitButton(ParameterDefinitionBO parameterDefinitionBO) {
		if (_parameterDefinitionUnitListButton != null
				&& parameterDefinitionBO != null) {

			List<ParameterDefinitionUnitBO> list = parameterDefinitionBO
					.getUnits();

			_parameterDefinitionUnitListButton.setListData(list);
			_parameterDefinitionUnitListButton.setSelectedIndex(0);
		}
	}

	/**
	 * Set the Validator into all cell renderers
	 */
	private void setValidatorToCellRenderers() {
		for (Column col : getColumns()) {
			((ParameterCellRenderer) col.getCellRenderer())
					.setValidator(_validator);
		}
	}

	/**
	 * Starts a table view row editor
	 */
	public void beginEdit() {
		layout();
		//TODO: not on it's place
		//_editor.beginEdit(this, getTableData().getLength() - 1, 0);

	}

	public void refreshView()
	{
		validateView();
		this.invalidate();
	}
	
	/**
	 * Validates this view
	 * @return true if no conflicts are found
	 */
	@SuppressWarnings("unchecked")
	public boolean validateView()
	{
		return _validator.isValid((List<DietParameterBO>)getTableData());
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
	 * @param dietParameter parameter to remove
	 */
	public void remove(DietParameterBO dietParameter)
	{
		_controller.remove(dietParameter);
		refreshView();
	}
}
