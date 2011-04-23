package at.easydiet.view;

import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.BoxPane;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.Dialog;
import org.apache.pivot.wtk.DialogCloseListener;
import org.apache.pivot.wtk.Orientation;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

import at.easydiet.businesslogic.CreateDietPlanViewController;
import at.easydiet.businesslogic.MealContainerController;
import at.easydiet.businesslogic.RecipeSearchController;
import at.easydiet.businessobjects.MealBO;
import at.easydiet.businessobjects.MealLineBO;
import at.easydiet.businessobjects.RecipeBO;

public class MealContainer extends BoxPane
{
    public static final org.apache.log4j.Logger LOG         = org.apache.log4j.Logger
                                                                    .getLogger(MealContainer.class);

    private MealBO                              _meal;
    private RecipeSearchController              _searcher   = new RecipeSearchController();
    private MealContainerController             _controller = new MealContainerController();

    private TextInput                           _mealName;
    private TextInput                           _mealCode;

    private TableView                           _mealLineBox;

    public MealContainer()
    {
        setOrientation(Orientation.VERTICAL);
        getStyles().put("fill", true);

        BXMLSerializer serializer = new BXMLSerializer();
        try
        {
            Border content = (Border) serializer.readObject(
                    TimeSpanContainer.class, "MealContainerContent.xml");

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
            
            _mealLineBox = (TableView)serializer.getNamespace().get("mealLines");

            _mealName = (TextInput) serializer.getNamespace().get("mealName");
            _mealName.getTextInputContentListeners().add(
                    new TextInputContentListener.Adapter()
                    {
                        @Override
                        public void textChanged(TextInput textInput)
                        {
                            _meal.setName(textInput.getText());
                        }
                    });

            _mealCode = (TextInput) serializer.getNamespace().get("mealCode");
            _mealCode.getTextInputContentListeners().add(
                    new TextInputContentListener.Adapter()
                    {
                        @Override
                        public void textChanged(TextInput textInput)
                        {
                            _meal.setCode(textInput.getText());
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
                                            EasyAlerts.YES_NO, getWindow(),
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

                        public void buttonPressed(Button button)
                        {
                            addRecipes((Sequence<RecipeBO>)recipeSearchResult
                                    .getSelectedRows());
                        }
                    });
            Button removeRecipeFromMeal = (Button) serializer.getNamespace().get(
            "removeRecipeFromMeal");
            removeRecipeFromMeal.getButtonPressListeners().add(
                    new ButtonPressListener()
                    {
                        
                        public void buttonPressed(Button button)
                        {
                            removeMealLines((Sequence<MealLineBO>) _mealLineBox
                                    .getSelectedRows());
                        }
                    });
            Button addRecipeToMealLine = (Button) serializer.getNamespace().get(
            "addRecipeToMealLine");
            addRecipeToMealLine.getButtonPressListeners().add(
                    new ButtonPressListener()
                    {
                        
                        @SuppressWarnings("unchecked")
                        public void buttonPressed(Button button)
                        {
                            addRecipeToMealLines((Sequence<MealLineBO>) _mealLineBox
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
            for (int j = 0; j < recipes.getLength(); j++)
            {
                CreateDietPlanViewController.getInstance().addRecipeAsAlternative(mealLines.get(i), recipes.get(j));
            }
        }
        updateUI();
    }

    protected void removeMealLines(Sequence<MealLineBO> selectedRows)
    {
        for (int i = 0; i < selectedRows.getLength(); i++)
        {
            CreateDietPlanViewController.getInstance().removeMealLine(selectedRows.get(i));
        }
        updateUI();
    }

    private void addRecipes(Sequence<RecipeBO> recipes)
    {
        if (recipes == null) return;

        for (int i = 0; i < recipes.getLength(); i++)
        {
            CreateDietPlanViewController.getInstance().addRecipeToMeal(_meal, recipes.get(i));
        }
        updateUI();
    }

    private void deleteMeal()
    {
        getParent().remove(this);
        _meal.removeFromTimeSpan();
    }

    /**
     * Gets the meal.
     * @return the meal
     */
    public MealBO getMeal()
    {
        return _meal;
    }

    /**
     * Sets the meal.
     * @param meal the meal to set
     */
    public void setMeal(MealBO meal)
    {
        _meal = meal;
        updateUI();
    }

    private void updateUI()
    {
        _mealName.setText(_meal.getName());
        _mealCode.setText(_meal.getCode());

        _controller.setMeal(_meal);
        _mealLineBox.setTableData(_controller.getMealLines());
    }

}
