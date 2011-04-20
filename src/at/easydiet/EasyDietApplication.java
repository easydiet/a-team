package at.easydiet;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

import at.easydiet.view.DashboardView;
import at.easydiet.view.EasyDietMainWindow;

public class EasyDietApplication implements Application
{
    public static final org.apache.log4j.Logger LOG    = org.apache.log4j.Logger
                                                               .getLogger(EasyDietApplication.class);

    public static final Object APPLICATION_TITLE = "EasyDiet v0.1(dev) ~ ";

    private Window                              window = null;

    public void resume() throws Exception
    {
    }

    public boolean shutdown(boolean arg0) throws Exception
    {
        if (window != null)
        {
            window.close();
        }

        return false;
    }

    public void startup(Display display, Map<String, String> arg1)
            throws Exception
    {
        BXMLSerializer bxmlSerializer = new BXMLSerializer();
        window = (Window) bxmlSerializer.readObject(EasyDietMainWindow.class,
                "EasyDietMainWindow.xml");
        window.open(display);
    }

    public void suspend() throws Exception
    {
    }

}