package at.easydiet;

import java.awt.Font;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.ApplicationContext;
import org.apache.pivot.wtk.DesktopApplicationContext;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Theme;
import org.apache.pivot.wtk.Window;

import at.easydiet.view.EasyDietMainWindow;
import at.easydiet.view.ViewController;

/**
 * This is the main application class for starting EasyDiet.
 */
public class EasyDietApplication implements Application
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG               = org.apache.log4j.Logger
                                                                          .getLogger(EasyDietApplication.class);

    /**
     * The application title used within the main window title bar.
     */
    public static final String                  APPLICATION_TITLE = "EasyDiet v0.1(alpha) ~ ";
    /**
     * The main format for formatting dates within EasyDiet rendering the date as
     * dd.MM.yyyy - 24.04.2011
     */
    public static final String                  DATE_FORMAT       = "dd.MM.yyyy";
    /**
     * The main format formatting dates and times within Easydiet
     * dd.MM.yyyy HH:mm - 24.04.2011 09:45
     */
    public static final String                  DATETIME_FORMAT   = "dd.MM.yyyy HH:mm";

    private Window                              _mainWindow            = null;

    /**
     * @see org.apache.pivot.wtk.Application#resume()
     */
    public void resume() throws Exception
    {}

    /**
     * @see org.apache.pivot.wtk.Application#shutdown(boolean)
     */
    public boolean shutdown(boolean arg0) throws Exception
    {
        LOG.trace("Shutdown application");
        if (_mainWindow != null)
        {
            _mainWindow.close();
        }
        return false;
    }

    /**
     * @see org.apache.pivot.wtk.Application#startup(org.apache.pivot.wtk.Display, org.apache.pivot.collections.Map)
     */
    public void startup(Display display, Map<String, String> args)
            throws Exception
    {
        // load custom property file if available
        if (args.containsKey("cfg"))
        {
            LOG.trace("Loading custom custom config file");
            loadProperties(args.get("cfg"));
        }
        else
        {
            LOG.trace("No config file found, will use default");
        }

        // Load custom styles
        LOG.trace("Setup GUI Styles");
        ApplicationContext.applyStylesheet("/at/easydiet/view/EasyStyles.json");
        Theme.getTheme().setFont(new Font("Arial", Font.PLAIN, 12));

        // Startup mainwindow
        LOG.trace("Loading UI");
        BXMLSerializer bxmlSerializer = new BXMLSerializer();

        _mainWindow = (Window) bxmlSerializer.readObject(EasyDietMainWindow.class,
                "EasyDietMainWindow" + ViewController.getInstance().PIVOT_FILE_EXTENSION);

                //"EasyDietMainWindow" + ViewController.PIVOT_FILE_EXTENSION);

        _mainWindow.open(display);
    }

    /**
     * @see org.apache.pivot.wtk.Application#suspend()
     */
    public void suspend() throws Exception
    {
        LOG.trace("Suspending EasyDiet");
    }

    /**
     * Loads a property file into the main application.
     * @param file the path of the file to load
     */
    public static void loadProperties(String file)
    {
        LOG.trace(String.format("Try to load property file '%s'", file));

        File propFile = new File(file);
        if (propFile.exists())
        {
            FileInputStream fis = null;
            try
            {
                fis = new FileInputStream(propFile);
                System.getProperties().load(fis);
            }
            catch (Exception e)
            {
                LOG.error("Error loading custom property file", e);
            }
            finally
            {
                if (fis != null)
                {
                    try
                    {
                        fis.close();
                    }
                    catch (IOException e)
                    {
                        LOG.warn("Error closing custom property file", e);
                    }
                }
            }
        }
    }

    /**
     * The applications main entry point.
     * @param args The console arguments formatted in 
     * --key=value per element.
     */
    public static void main(String[] args)
    {
        DesktopApplicationContext.main(EasyDietApplication.class, args);
    }

}