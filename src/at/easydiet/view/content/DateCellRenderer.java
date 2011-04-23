package at.easydiet.view.content;

import org.apache.pivot.wtk.content.TableViewDateCellRenderer;

import at.easydiet.EasyDietApplication;

public class DateCellRenderer extends TableViewDateCellRenderer
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(DateCellRenderer.class);
    
    public DateCellRenderer()
    {
        setDateFormat(EasyDietApplication.DATE_FORMAT);
    }
}
