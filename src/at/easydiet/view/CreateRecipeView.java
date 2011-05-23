package at.easydiet.view;

import java.net.URL;

import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ListListener;
import org.apache.pivot.collections.Map;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Border;
import org.apache.pivot.wtk.Button;
import org.apache.pivot.wtk.ButtonPressListener;
import org.apache.pivot.wtk.ListView;
import org.apache.pivot.wtk.TableView;
import org.apache.pivot.wtk.TextInput;
import org.apache.pivot.wtk.TextInputContentListener;

import at.easydiet.businesslogic.CreateRecipeController;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.businessobjects.RecipeBO;
import at.easydiet.businessobjects.RecipeIngredientBO;
import at.easydiet.domainlogic.RecipeSearchController;

/**
 * This is the background class for the CreateRecipeView.bxml
 */
public class CreateRecipeView extends EasyDietContentView implements Bindable
{
    /**
     * Logger for debugging purposes
     */
    public static final org.apache.log4j.Logger LOG       = org.apache.log4j.Logger
                                                                  .getLogger(CreateRecipeView.class);

    /**
     * Stores the {@link RecipeSearchController}
     */
    private RecipeSearchController              _searcher = new RecipeSearchController();

    /**
     * Stores the {@link TableView} that shows the parameters of the recipe
     */
    private ParameterTableViewTemplate          _parameterTableView;

    /**
     * Stores the {@link TableView} that shows the recipe search results
     */
    private TableView                           _recipeSearchResult;
    /**
     * Stores the {@link TableView} that shows the chosen recipes
     */
    private TableView                           _chosenRecipeTableView;

    /**
     * @see at.easydiet.view.EasyDietContentView#onLoad()
     */
    @Override
    public void onLoad()
    {
        CreateRecipeController.getInstance().createNew();
        setRecipe(CreateRecipeController.getInstance().getRecipe());
    }

    /**
     * Sets the {@link RecipeBO} of this instance
     * 
     * @param recipe
     *            The new {@link RecipeBO} to set
     */
    private void setRecipe(RecipeBO recipe)
    {
        // fill gui with data
        load(recipe);
        _parameterTableView.setParameterProvider(recipe);
        _chosenRecipeTableView.setTableData(recipe.getIngredients());
    }

    /**
     * @see org.apache.pivot.beans.Bindable#initialize(org.apache.pivot.collections.Map,
     *      java.net.URL, org.apache.pivot.util.Resources)
     */
    @Override
    public void initialize(Map<String, Object> map, URL location,
            Resources resources)
    {
        // Initialize error box to show/hide upon changes on errors
        final Border errorBorder = (Border) map.get("errorBorder");
        final ListView errorBox = (ListView) map.get("errorBox");
        errorBox.setListData(CreateRecipeController.getInstance().getErrors());
        CreateRecipeController.getInstance().getErrors().getListListeners()
                .add(new ListListener.Adapter<String>()
                {

                    @Override
                    public void itemInserted(List<String> list, int arg1)
                    {
                        errorBorder.setVisible(list.getLength() > 0);
                    }

                    @Override
                    public void itemsRemoved(List<String> list, int arg1,
                            Sequence<String> arg2)
                    {
                        errorBorder.setVisible(list.getLength() > 0);
                    }

                    @Override
                    public void listCleared(List<String> list)
                    {
                        errorBorder.setVisible(list.getLength() > 0);
                    }
                });
        errorBorder.setVisible(false);

        // initialize tableview for managing dietparameters
        _parameterTableView = (ParameterTableViewTemplate) map
                .get("parameterTableView");
        _parameterTableView.setNewInstanceType(DietParameterBO.class);

        // initialize recipe chooser box to display result of search
        _recipeSearchResult = (TableView) map.get("recipeSearchResult");
        _recipeSearchResult.setTableData(_searcher.getRecipes());

        // get the box containing the currrent ingredients
        _chosenRecipeTableView = (TableView) map.get("chosenRecipeTableView");

        // initialize the searchbox to start the search for recipes
        TextInput recipeSearchBox = (TextInput) map.get("recipeSearchBox");
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

        // run validation on validate button
        Button validate = (Button) map.get("validate");
        validate.getButtonPressListeners().add(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button button)
            {
                CreateRecipeView.this.store(CreateRecipeController
                        .getInstance().getRecipe());
                CreateRecipeController.getInstance().checkRecipe();
            }
        });

        // start save
        Button save = (Button) map.get("save");
        save.getButtonPressListeners().add(new ButtonPressListener()
        {
            @Override
            public void buttonPressed(Button button)
            {
                save();
            }
        });

        addRecipeButtonListeners(map);
    }

    /**
     * Save the new {@link RecipeBO}
     */
    private void save()
    {
        store(CreateRecipeController.getInstance().getRecipe());
        boolean saved = CreateRecipeController.getInstance().saveRecipe();
        if (saved)
        {
            ViewController.getInstance().loadContent("DashboardView", this);
        }
        else if (CreateRecipeController.getInstance().getErrors().getLength() == 0)
        {
            EasyAlerts
                    .error("Es ist ein Fehler beim Speichern des Rezeptes aufgetreten, bitte versuchen Sie es erneut!",
                            EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
                            null);
        }
        else
        {
            EasyAlerts
                    .error("Es sind noch Fehler im Rezept vorhanden! Bitte korrigieren Sie diese!",
                            EasyAlerts.OK_ONLY, EasyAlerts.OK, getWindow(),
                            null);
        }
    }

    /**
     * Add all button listeners
     * 
     * @param map
     *            The pivot namespace
     */
    private void addRecipeButtonListeners(Map<String, Object> map)
    {
        // add selected ingredients to recipe
        Button addRecipeButton = (Button) map.get("addRecipeButton");
        addRecipeButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void buttonPressed(Button arg0)
            {
                addIngredientsToRecipe((Sequence<RecipeBO>) _recipeSearchResult
                        .getSelectedRows());
            }
        });
        // removes selected ingredients
        addRecipeButton.getButtonPressListeners().add(new ButtonPressListener()
        {
            @SuppressWarnings("unchecked")
            @Override
            public void buttonPressed(Button arg0)
            {
                removeIngredientsFromRecipe((Sequence<RecipeIngredientBO>) _chosenRecipeTableView
                        .getSelectedRows());
            }
        });
    }

    /**
     * Add a sequence of {@link RecipeBO}s to the open {@link RecipeBO}
     * 
     * @param ingredients
     *            The sequence of {@link RecipeBO}s to add
     */
    protected void addIngredientsToRecipe(Sequence<RecipeBO> ingredients)
    {
        for (int i = 0; i < ingredients.getLength(); i++)
        {
            CreateRecipeController.getInstance().addRecipeIngredient(
                    ingredients.get(i));
        }
        CreateRecipeController.getInstance().checkRecipe();
    }

    /**
     * Remove a sequence of {@link RecipeBO}s from the open {@link RecipeBO}
     * 
     * @param ingredients
     *            The sequence of {@link RecipeBO}s to remove
     */
    protected void removeIngredientsFromRecipe(
            Sequence<RecipeIngredientBO> ingredients)
    {
        for (int i = 0; i < ingredients.getLength(); i++)
        {
            CreateRecipeController.getInstance().removeRecipeIngredient(
                    ingredients.get(i));
        }
        CreateRecipeController.getInstance().checkRecipe();
    }
}
