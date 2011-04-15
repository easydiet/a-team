package at.easydiet;

import org.apache.pivot.beans.BXMLSerializer;
import org.apache.pivot.collections.Map;
import org.apache.pivot.wtk.Application;
import org.apache.pivot.wtk.Display;
import org.apache.pivot.wtk.Window;

import at.easydiet.view.DashboardView;

public class EasyDietApplication implements Application
{
    public static final org.apache.log4j.Logger LOG    = org.apache.log4j.Logger
                                                               .getLogger(EasyDietApplication.class);

    private Window                              window = null;

    public void resume() throws Exception
    {
        // TODO Auto-generated method stub

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
        window = (Window) bxmlSerializer.readObject(DashboardView.class,
                "DashboardView.xml");
        window.open(display);
    }

    public void suspend() throws Exception
    {
        // TODO Auto-generated method stub

    }

}