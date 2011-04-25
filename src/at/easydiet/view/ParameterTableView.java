package at.easydiet.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.List;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.ListButtonSelectionListener;
import org.apache.pivot.wtk.TableView;
import at.easydiet.businessobjects.CheckOperatorBO;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.ParameterDefinitionBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.model.ParameterDefinition;
import at.easydiet.validation.ParameterValidator;
import at.easydiet.view.EasyTableViewRowEditor.RowEditorListener;
import at.easydiet.view.content.ParameterCellRenderer;

public class ParameterTableView extends TableView {
	private EasyTableViewRowEditor _editor;
	private ListButton _definitionListButton;
	private ListButton _checkOperatorListButton;
	private ListButton _parameterDefinitionUnitListButton;
	private ParameterValidator _validator;

	public ParameterTableView() {
		_validator = new ParameterValidator();
	}

	@SuppressWarnings("unchecked")
	public void initialize() {
		
		//set the validator into all cell renderers
		_validator.isValid((ArrayList<DietParameterBO>) getTableData());
		for(Column col : getColumns())
		{
			((ParameterCellRenderer)col.getCellRenderer()).setValidator(_validator);
		}

		// get editor
		_editor = (EasyTableViewRowEditor) getRowEditor();
		
		_editor.getRowEditorListeners().add(new RowEditorListener(){

			@Override
			public Vote previewEditRow(RowEditor rowEditor,
					TableView tableView, int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				return Vote.APPROVE;
			}

			@Override
			public void editRowVetoed(RowEditor rowEditor, Vote reason) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void rowEditing(RowEditor rowEditor, TableView tableView,
					int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void changesSaved(RowEditor rowEditor, TableView tableView,
					int rowIndex, int columnIndex) {
				validateView();
				
			}

			@Override
			public void editCancelled(RowEditor rowEditor, TableView tableView,
					int rowIndex, int columnIndex) {
				// TODO Auto-generated method stub
				
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
						_definitionListButton.setListData(getAllDefinitions());

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
								.getCellEditors().get("parameterDefinitionUnit.name");
						DietParameterBO row = (DietParameterBO) getTableData()
								.get(rowIndex);

						_parameterDefinitionUnitListButton.setListData(row.getParameterDefinition().getUnits());

						for (int i = 0; i < _parameterDefinitionUnitListButton
								.getListData().getLength(); i++) {
							ParameterDefinitionUnitBO parameterDefinitionUnitBO = (ParameterDefinitionUnitBO) _parameterDefinitionUnitListButton
									.getListData().get(i);
							if (parameterDefinitionUnitBO.getName()
									.equalsIgnoreCase(
											row.getParameterDefinitionUnit().getName())) {
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

	public void refreshOperatorButton(
			ParameterDefinitionBO parameterDefinitionBO) {
		if (_checkOperatorListButton != null && parameterDefinitionBO != null) {
			List<CheckOperatorBO> list = CheckOperatorBO.getAllOperators();

			_checkOperatorListButton.setListData(list);
			_checkOperatorListButton.setSelectedIndex(0);
		}
	}

	public void refreshUnitButton(ParameterDefinitionBO parameterDefinitionBO) {
		if (_parameterDefinitionUnitListButton != null
				&& parameterDefinitionBO != null) {

			List<ParameterDefinitionUnitBO> list = parameterDefinitionBO
					.getUnits();

			_parameterDefinitionUnitListButton.setListData(list);
			_parameterDefinitionUnitListButton.setSelectedIndex(0);
		}
	}

	private ArrayList<ParameterDefinitionBO> getAllDefinitions() {
		ArrayList<ParameterDefinitionBO> definitions = new ArrayList<ParameterDefinitionBO>();

		// own controller?
		for (ParameterDefinition parameterDefinition : DAOFactory.getInstance()
				.getParameterDefinitionDAO().findAll()) {
			definitions.add(new ParameterDefinitionBO(parameterDefinition));
		}
		return definitions;
	}
	
	public void setValidator(ParameterValidator parameterValidator)
	{
		_validator = parameterValidator;
		for(Column col : getColumns())
		{
			((ParameterCellRenderer)col.getCellRenderer()).setValidator(parameterValidator);
		}
	}

	@SuppressWarnings("unchecked")
	public boolean validateView()
	{
		return _validator.isValid((List<DietParameterBO>)this.getTableData());
	}
}
