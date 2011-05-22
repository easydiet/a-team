package at.easydiet.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.DialogCloseListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Window;

public class EasyAlerts
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOG     = org.apache.log4j.Logger
                                                                .getLogger(EasyAlerts.class);

    public static final String                  OK      = "OK";
    public static final String                  YES     = "Ja";
    public static final String                  NO      = "No";
    public static final Sequence<String>        YES_NO  = new ArrayList<String>(
                                                                YES, NO);
    public static final Sequence<String>        OK_ONLY = new ArrayList<String>(
                                                                OK);

    public static void warning(String message, Sequence<?> options,
            Object defaultOption, Window owner, DialogCloseListener listener)
    {
        show(MessageType.WARNING, message, options, defaultOption, owner,
                listener);
    }

    private static void show(MessageType msg, String message,
            Sequence<?> options, Object defaultOption, Window owner,
            DialogCloseListener listener)
    {
        Alert alert = new Alert(msg, message, options, true);
        alert.getStyles().put("showWindowControls", false);
        alert.setSelectedOption(defaultOption);
        if (listener == null)
            alert.open(owner.getDisplay(), owner);
        else
            alert.open(owner.getDisplay(), owner, listener);
    }

    public static void error(String message, Sequence<?> options,
            Object defaultOption, Window owner, DialogCloseListener listener)
    {
        show(MessageType.ERROR, message, options, defaultOption, owner,
                listener);
    }
}
