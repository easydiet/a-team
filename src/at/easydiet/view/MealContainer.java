package at.easydiet.view;

import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
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

import at.easydiet.businesslogic.RecipeSearchController;

public class MealContainer extends BoxPane
{
    public static final org.apache.log4j.Logger LOG       = org.apache.log4j.Logger
                                                                  .getLogger(MealContainer.class);

    private MealBO                              _meal;
    private RecipeSearchController              _searcher = new RecipeSearchController();

    private TextInput                           _mealName;
    private TextInput                           _mealCode;

    public MealContainer()
    {
        setOrientation(Orientation.VERTICAL);
        getStyles().put("fill", true);

        BXMLSerializer serializer = new BXMLSerializer();
        try
        {
            Border content = (Border) serializer.readObject(
                    TimeSpanContainer.class, "MealContainerContent.xml");

            TableView recipeSearchResult = (TableView) serializer
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

            _mealName = (TextInput) serializer.getNamespace().get(
                    "mealName");
            _mealName.getTextInputContentListeners().add(
                    new TextInputContentListener.Adapter()
                    {
                        @Override
                        public void textChanged(TextInput textInput)
                        {
                            _meal.setName(textInput.getText());
                        }
                    });

            _mealCode = (TextInput) serializer.getNamespace().get(
                    "mealCode");
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
    }

}
