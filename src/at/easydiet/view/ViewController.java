package at.easydiet.view;

import java.io.IOException;
import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.serialization.SerializationException;
import org.apache.pivot.wtk.Component;
import org.apache.pivot.wtk.Container;

import at.easydiet.EasyDietApplication;
import at.easydiet.view.EasyDietContentView.NavigationButton;
import at.easydiet.view.EasyDietContentView.ToolbarButton;

public class ViewController
{
    private static ViewController _singleton;

    private ViewController()
    {

    }

    public static ViewController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new ViewController();
        }
        return _singleton;
    }

    /**
     * Which content to load into the Content pane
     * @param file BXML file
     */
    public void loadContent(String file, Component component)
    {
        EasyDietMainWindow wnd = (EasyDietMainWindow) component.getWindow();
        loadContent(file, wnd);
    }
    
    /**
     * Which content to load into the Content pane
     * @param file BXML file
     */
    public void loadContent(String file, EasyDietContentView component)
    {
        EasyDietMainWindow wnd = (EasyDietMainWindow) component.getWindow();
        loadContent(file, wnd);
    }

    /**
     * Which content to load into the Content pane
     * @param file BXML file
     */
    public void loadContent(String file, EasyDietMainWindow mainWindow)
    {
        
        EasyDietContentView oldView = mainWindow.getContentPane().getLength() == 0 ? null
                : (EasyDietContentView) mainWindow.getContentPane().get(0);

        // do onClose method
        if (oldView == null || oldView.onClose())
        {
            // closing is allowed
            try
            {
                EasyDietContentView newView = (EasyDietContentView) new BXMLSerializer()
                        .readObject(ViewController.class, file + ".xml");

                // do onLoad method
                newView.onLoad();

                mainWindow.getContentPane().removeAll();
                mainWindow.getContentPane().add(newView);

                replaceButtons(newView, mainWindow);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (SerializationException e)
            {
                e.printStackTrace();
            }
        }
        else
        {

        }
    }

    /**
     * Replace all buttons in the toolbar and the navigation bar
     * @param view
     * @param toolbar
     * @param navigation
     */
    public void replaceButtons(EasyDietContentView view,
            EasyDietMainWindow mainWindow)
    {
        replaceNavigation(view, mainWindow.getNavigationPane());
        replaceToolbar(view, mainWindow.getToolbarPane());
    }

    /**
     * Replace all buttons in the toolbar
     * @param view
     * @param toolbar
     */
    private void replaceToolbar(EasyDietContentView view, Container toolbar)
    {
        toolbar.removeAll();
        for (ToolbarButton button : view.getToolbarButtons())
        {
            toolbar.add(button);
        }
    }

    /**
     * Replace all buttons in the navigation bar
     * @param view
     * @param navigation
     */
    public void replaceNavigation(EasyDietContentView view, Container navigation)
    {
        StringBuilder windowTitle = new StringBuilder();
        windowTitle.append(EasyDietApplication.APPLICATION_TITLE);
        navigation.removeAll();
        NavigationButton last = null;
        for (NavigationButton button : view.getNavigationButtons())
        {
            button.refresh();
            navigation.add(button);
            last = button;
        }
        if (last != null)
        {
            windowTitle.append(last.getText());
        }
        view.getWindow().setTitle(windowTitle.toString());
    }
}
