package at.easydiet.view;

import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.util.Vote;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.DialogCloseListener;
import org.apache.pivot.wtk.ListButton;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.SuggestionPopup;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TableView.RowEditor;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

import at.easydiet.businesslogic.MealContainerController;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.domainlogic.DietPlanEditingController;
import at.easydiet.domainlogic.ParameterDefinitionUnitController;
import at.easydiet.domainlogic.RecipeSearchController;

public class MealContainer extends BoxPane
{
    public static final org.apache.log4j.Logger LOG         = org.apache.log4j.Logger
                                                                    .getLogger(MealContainer.class);

    private static final SuggestionPopup        SUGGESTIONS = new SuggestionPopup();

    private RecipeSearchController              _searcher   = new RecipeSearchController();
    private MealContainerController             _controller = new MealContainerController();

    private TextInput                           _mealName;
    private TextInput                           _mealCode;

    private TableView                           _mealLineBox;
    
    //start: parameterTableView
    private ParameterTableView					_mealParameterTableView;
	private Button _removeMealParameterButton;
	private Button _addMealParameterButton;
	//end: parameterTableView

    static
    {
        
        DietPlanEditingController.getInstance().refresh(false);
        SUGGESTIONS.setSuggestionData(DietPlanEditingController.getInstance()
                .getMealCodes());
        SUGGESTIONS.setListSize(5);
    }

    public MealContainer()
    {
        setOrientation(Orientation.VERTICAL);
        getStyles().put("fill", true);

        BXMLSerializer serializer = new BXMLSerializer();
        try
        {
            Border content = (Border) serializer.readObject(
                    TimeSpanContainer.class, "MealContainerContent.xml");
            
         //start: parameterTableView
			_mealParameterTableView = (ParameterTableView) serializer
					.getNamespace().get("parameterTableView");
			_mealParameterTableView.initialize();

			_addMealParameterButton = (Button) serializer.getNamespace()
					.get("addTableViewParameters");
			_addMealParameterButton.getButtonPressListeners().add(
					new ButtonPressListener() {

						public void buttonPressed(Button arg0) {
							addNewParameters();
						}
					});

			_removeMealParameterButton = (Button) serializer.getNamespace()
					.get("removeTableViewParameter");
			_removeMealParameterButton.getButtonPressListeners().add(
					new ButtonPressListener() {

						public void buttonPressed(Button arg0) {
							removeParameter((DietParameterBO) _mealParameterTableView
									.getSelectedRow());
						}
					});
			//end: parameterTableView
            

            final TableView recipeSearchResult = (TableView) serializer
                    .getNamespace().get("recipeSearchResult");
            recipeSearchResult.setTableData(_searcher.getRecipes());

            TextInput recipeSearchBox = (TextInput) serializer.getNamespace()
                    .get("recipeSearchBox");
            recipeSearchBox.getTextInputContentListeners().add(
                    new TextInputContentListener.Adapter()
                    {
                        @Override
                        public void textChanged(TextInput textInput)
                        {
                            _searcher.setQueryString(textInput.getText());
                            _searcher.doSearch();
                        }
                    });

            _mealLineBox = (TableView) serializer.getNamespace().get(
                    "mealLines");
            final EasyTableViewRowEditor editor = (EasyTableViewRowEditor) _mealLineBox
                    .getRowEditor();
            editor.getRowEditorListeners().add(
                    new EasyTableViewRowEditor.RowEditorListener.Adapter()
                    {
                        @Override
                        public Vote previewEditRow(RowEditor rowEditor,
                                TableView tableView, int rowIndex,
                                int columnIndex)
                        {
                            // update list of units
                            ListButton unitList = (ListButton) editor
                                    .getCellEditors().get("unit.name");
                            MealLineBO row = (MealLineBO) _mealLineBox
                                    .getTableData().get(rowIndex);

                            unitList.setListData(ParameterDefinitionUnitController
                                    .getInstance()
                                    .getUnitsCompatibleWithRecipe(
                                            row.getRecipe()));

                            for (int i = 0; i < unitList.getListData()
                                    .getLength(); i++)
                            {
                                ParameterDefinitionUnitBO bo = (ParameterDefinitionUnitBO) unitList
                                        .getListData().get(i);
                                if (bo.getName().equalsIgnoreCase(
                                        row.getUnit().getName()))
                                {
                                    unitList.setSelectedIndex(i);
                                    layout();
                                    break;
                                }
                            }

                            return super.previewEditRow(rowEditor, tableView,
                                    rowIndex, columnIndex);
                        }
                    });
            _mealName = (TextInput) serializer.getNamespace().get("mealName");
            _mealName.getTextInputContentListeners().add(
                    new TextInputContentListener.Adapter()
                    {
                        @Override
                        public void textChanged(TextInput textInput)
                        {
                            getMeal().setName(textInput.getText());
                        }

                        @Override
                        public void textInserted(TextInput textInput,
                                int index, int count)
                        {
                            String text = textInput.getText();
                            ArrayList<String> suggestions = new ArrayList<String>();

                            for (String meal : DietPlanEditingController
                                    .getInstance().getMealNames())
                            {
                                if (meal.toUpperCase().startsWith(
                                        text.toUpperCase()))
                                {
                                    suggestions.add(meal);
                                }
                            }

                            if (suggestions.getLength() > 0)
                            {
                                SUGGESTIONS.setSuggestionData(suggestions);
                                SUGGESTIONS.open(textInput);
                            }
                        }

                        @Override
                        public void textRemoved(TextInput textInput, int index,
                                int count)
                        {
                            SUGGESTIONS.close();
                        }
                    });

            _mealCode = (TextInput) serializer.getNamespace().get("mealCode");
            _mealCode.getTextInputContentListeners().add(
                    new TextInputContentListener.Adapter()
                    {
                        @Override
                        public void textChanged(TextInput textInput)
                        {
                            getMeal().setCode(textInput.getText());
                        }

                        @Override
                        public void textInserted(TextInput textInput,
                                int index, int count)
                        {
                            SUGGESTIONS
                                    .setSuggestionData(DietPlanEditingController
                                            .getInstance().getMealCodes());
                            if (SUGGESTIONS.getSuggestionData().getLength() > 0)
                            {
                                SUGGESTIONS.open(textInput);
                            }
                        }

                        @Override
                        public void textRemoved(TextInput textInput, int index,
                                int count)
                        {
                            SUGGESTIONS.close();
                        }
                    });

            Button deleteButton = (Button) serializer.getNamespace().get(
                    "deleteMealButton");
            deleteButton.getButtonPressListeners().add(
                    new ButtonPressListener()
                    {

                        public void buttonPressed(Button button)
                        {
                            EasyAlerts
                                    .warning(
                                            "Wollen Sie diese Mahlzeit wirklich löschen?",
                                            EasyAlerts.YES_NO, EasyAlerts.NO, getWindow(),
                                            new DialogCloseListener()
                                            {

                                                public void dialogClosed(
                                                        Dialog dialog,
                                                        boolean modal)
                                                {
                                                    if (((Alert) dialog)
                                                            .getSelectedOption()
                                                            .equals(EasyAlerts.YES))
                                                    {
                                                        deleteMeal();
                                                    }
                                                }

                                            });
                        }
                    });

            Button addRecipeToMeal = (Button) serializer.getNamespace().get(
                    "addRecipeToMeal");
            addRecipeToMeal.getButtonPressListeners().add(
                    new ButtonPressListener()
                    {

                        @SuppressWarnings("unchecked")
                        public void buttonPressed(Button button)
                        {
                            addRecipes((Sequence<RecipeBO>) recipeSearchResult
                                    .getSelectedRows());
                        }
                    });
            Button removeRecipeFromMeal = (Button) serializer.getNamespace()
                    .get("removeRecipeFromMeal");
            removeRecipeFromMeal.getButtonPressListeners().add(
                    new ButtonPressListener()
                    {

                        @SuppressWarnings("unchecked")
                        public void buttonPressed(Button button)
                        {
                            removeMealLines((Sequence<MealLineBO>) _mealLineBox
                                    .getSelectedRows());
                        }
                    });
            Button addRecipeToMealLine = (Button) serializer.getNamespace()
                    .get("addRecipeToMealLine");
            addRecipeToMealLine.getButtonPressListeners().add(
                    new ButtonPressListener()
                    {

                        @SuppressWarnings("unchecked")
                        public void buttonPressed(Button button)
                        {
                            addRecipeToMealLines(
                                    (Sequence<MealLineBO>) _mealLineBox
                                            .getSelectedRows(),
                                    (Sequence<RecipeBO>) recipeSearchResult
                                            .getSelectedRows());
                        }
                    });

            add(content);
        }
        catch (IOException e)
        {
            LOG.debug(e);
        }
        catch (SerializationException e)
        {
            LOG.debug(e);
        }
    }

    protected void addRecipeToMealLines(Sequence<MealLineBO> mealLines,
            Sequence<RecipeBO> recipes)
    {
        for (int i = 0; i < mealLines.getLength(); i++)
        {
            MealLineBO addTo = mealLines.get(i);
            // check if we try to add a mealline to an alternative
            if (!addTo.isAlternative())
            {
                for (int j = 0; j < recipes.getLength(); j++)
                {
                    DietPlanEditingController.getInstance()
                            .addRecipeAsAlternative(addTo, recipes.get(j));
                }
            }
        }
        updateUI();
    }

    protected void removeMealLines(Sequence<MealLineBO> selectedRows)
    {
        for (int i = 0; i < selectedRows.getLength(); i++)
        {
            DietPlanEditingController.getInstance().removeMealLine(
                    selectedRows.get(i));
        }
        updateUI();
    }

    private void addRecipes(Sequence<RecipeBO> recipes)
    {
        if (recipes == null) return;

        for (int i = 0; i < recipes.getLength(); i++)
        {
            DietPlanEditingController.getInstance().addRecipeToMeal(getMeal(),
                    recipes.get(i));
        }
        updateUI();
    }

    private void deleteMeal()
    {
        getParent().remove(this);
        DietPlanEditingController.getInstance().deleteMeal(getMeal());
    }

    /**
     * Gets the meal.
     * @return the meal
     */
    public MealBO getMeal()
    {
        return _controller.getMeal();
    }

    /**
     * Sets the meal.
     * @param meal the meal to set
     */
    public void setMeal(MealBO meal)
    {
        _controller.setMeal(meal);
        
        //parameterTableView
        _mealParameterTableView.setParameterProvider(getMeal());
		
        updateUI();
    }

    private void updateUI()
    {
        _mealName.setText(getMeal().getName());
        _mealCode.setText(getMeal().getCode());

        _mealLineBox.setTableData(_controller.getMealLines());
    }
    
    //start: parameterTableView
    /**
     * Adds a new Parameter to the parameter table view
     */
    private void addNewParameters() {		
		_mealParameterTableView.addParameterTemplate();
	}

    /**
     * Remove parameter from parameter table view
     * @param dietParameter
     */
	private void removeParameter(DietParameterBO dietParameter) {
		_mealParameterTableView.remove(dietParameter);
	}
	//end: parameterTableView

}
