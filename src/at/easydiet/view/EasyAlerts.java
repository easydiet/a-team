package at.easydiet.view;

import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Sequence;
import org.apache.pivot.wtk.Alert;
import org.apache.pivot.wtk.DialogCloseListener;
import org.apache.pivot.wtk.MessageType;
import org.apache.pivot.wtk.Window;

public class EasyAlerts
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(EasyAlerts.class);
    
    
    public static final String YES = "Ja";
    public static final String NO = "No";
    public static final Sequence<String> YES_NO = new ArrayList<String>(YES, NO);
    
    public static void warning(String message, Sequence<?> options, Window owner, DialogCloseListener listener)
    {
        Alert alert = new Alert(MessageType.WARNING, message, options, true);
        alert.open(owner.getDisplay(), owner, listener);
        
    }
}
