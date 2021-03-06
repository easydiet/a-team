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

import at.easydiet.businesslogic.DietPlanEditingController;
import at.easydiet.businesslogic.MealContainerController;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.DietPlanBO;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.ParameterDefinitionUnitBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.domainlogic.ParameterDefinitionUnitController;
import at.easydiet.domainlogic.RecipeSearchController;

/**
 * This is the Component to store and handle {@link MealBO}s
 */
public class MealContainer extends BoxPane
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG         = org.apache.log4j.Logger
                                                                     .getLogger(MealContainer.class);

    /**
     * Stores the suggestion popup
     */
    private static final SuggestionPopup         SUGGESTIONS = new SuggestionPopup();

    /**
     * Stores the {@link RecipeSearchController}
     */
    private RecipeSearchController               _searcher   = new RecipeSearchController();

    /**
     * Stores the {@link MealContainerController}
     */
    private MealContainerController              _controller = new MealContainerController();

    /**
     * Stores the input field for the meal name from the GUI
     */
    private TextInput                            _mealName;
    /**
     * Stores the input field for the meal code from the GUI
     */
    private TextInput                            _mealCode;

    /**
     * Stores the table view for all {@link MealLineBO}s in the current
     * {@link MealBO}
     */
    private TableView                            _mealLineBox;

    /**
     * Stores the table view for {@link DietParameterBO}s in the current
     * {@link MealBO}
     */
    private ParameterTableViewTemplate           _parameterTableViewTemplate;

    static
    {

        DietPlanEditingController.getInstance().refresh(false);
        SUGGESTIONS.setSuggestionData(DietPlanEditingController.getInstance()
                .getMealCodes());
        SUGGESTIONS.setListSize(5);
    }

    /**
     * Initializes a new instance of the {@link MealContainer} class.
     */
    public MealContainer()
    {
        setOrientation(Orientation.VERTICAL);
        getStyles().put("fill", true);

        BXMLSerializer serializer = new BXMLSerializer();
        try
        {
            ViewController.getInstance();
            Border content = (Border) serializer.readObject(
                    TimeSpanContainer.class, "MealContainerContent"
                            + ViewController.PIVOT_FILE_EXTENSION);

            _parameterTableViewTemplate = (ParameterTableViewTemplate) serializer
                    .getNamespace().get("parameterTableViewTemplate");

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
                                            EasyAlerts.YES_NO, EasyAlerts.NO,
                                            getWindow(),
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

    /**
     * Adds a sequence of {@link RecipeBO}s to the sequence of
     * {@link MealLineBO}s
     * 
     * @param mealLines
     *            The sequence of {@link MealLineBO}s
     * @param recipes
     *            The sequence of {@link RecipeBO}
     */
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

    /**
     * Remove a sequence of {@link MealLineBO}s
     * 
     * @param mealLines
     */
    protected void removeMealLines(Sequence<MealLineBO> mealLines)
    {
        for (int i = 0; i < mealLines.getLength(); i++)
        {
            DietPlanEditingController.getInstance().removeMealLine(
                    mealLines.get(i));
            _controller.getMeal().updateMealLinesCache();
        }
        updateUI();
    }

    /**
     * Add a sequence of {@link RecipeBO}s to the current {@link MealBO}
     * 
     * @param recipes
     *            The sequence of {@link RecipeBO}s
     */
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

    /**
     * Delete this {@link MealBO} from the {@link DietPlanBO}
     */
    private void deleteMeal()
    {
        getParent().remove(this);
        DietPlanEditingController.getInstance().deleteMeal(getMeal());
    }

    /**
     * Gets the meal.
     * 
     * @return the meal
     */
    public MealBO getMeal()
    {
        return _controller.getMeal();
    }

    /**
     * Sets the meal.
     * 
     * @param meal
     *            the meal to set
     */
    public void setMeal(MealBO meal)
    {
        _controller.setMeal(meal);

        // parameterTableView
        _parameterTableViewTemplate.setParameterProvider(getMeal());

        updateUI();
    }

    /**
     * Update the input/output fields from the GUI
     */
    private void updateUI()
    {
        _mealName.setText(getMeal().getName());
        _mealCode.setText(getMeal().getCode());

        _mealLineBox.setTableData(_controller.getMealLines());
    }
}
