package at.easydiet.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.DialogCloseListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Window;

/**
 * Defines look and feel of the alerts showing up in EasyDiet
 */
public class EasyAlerts
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG     = org.apache.log4j.Logger
                                                                 .getLogger(EasyAlerts.class);

    /**
     * OK-Button
     */
    public static final String                   OK      = "OK";
    /**
     * Yes-Button
     */
    public static final String                   YES     = "Ja";

    /**
     * No-Button
     */
    public static final String                   NO      = "Nein";

    /**
     * Yes- and No-Button
     */
    public static final Sequence<String>         YES_NO  = new ArrayList<String>(
                                                                 YES, NO);
    /**
     * Only a OK-Button
     */
    public static final Sequence<String>         OK_ONLY = new ArrayList<String>(
                                                                 OK);

    /**
     * Shows a new warning message
     * 
     * @param message
     *            The message to show
     * @param options
     *            The available buttons
     * @param defaultOption
     *            The default chosen option
     * @param owner
     *            The owner window of this alert
     * @param listener
     *            The DialogCloseListener of this alert
     */
    public static void warning(String message, Sequence<?> options,
            Object defaultOption, Window owner, DialogCloseListener listener)
    {
        show(MessageType.WARNING, message, options, defaultOption, owner,
                listener);
    }

    /**
     * Shows a new alert
     * 
     * @param msg
     *            {@link MessageType} of this alert
     * @param message
     *            The message to show
     * @param options
     *            The available buttons
     * @param defaultOption
     *            The default chosen option
     * @param owner
     *            The owner window of this alert
     * @param listener
     *            The DialogCloseListener of this alert
     */
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

    /**
     * Shows a new error message
     * 
     * @param message
     *            The message to show
     * @param options
     *            The available buttons
     * @param defaultOption
     *            The default chosen option
     * @param owner
     *            The owner window of this alert
     * @param listener
     *            The DialogCloseListener of this alert
     */
    public static void error(String message, Sequence<?> options,
            Object defaultOption, Window owner, DialogCloseListener listener)
    {
        show(MessageType.ERROR, message, options, defaultOption, owner,
                listener);
    }
}
