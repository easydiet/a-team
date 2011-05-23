package at.easydiet.view.content;

import org.apache.pivot.wtk.content.TableViewDateCellRenderer;

import at.easydiet.EasyDietApplication;

/**
 * This renderer renders the date cell to the gien format
 */
public class DateCellRenderer extends TableViewDateCellRenderer
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(DateCellRenderer.class);

    /**
     * Initializes a new instance of the {@link DateCellRenderer} class.
     */
    public DateCellRenderer()
    {
        setDateFormat(EasyDietApplication.DATE_FORMAT);
    }
}
