package at.easydiet.view.content;

import java.lang.reflect.Method;

import org.apache.pivot.json.JSON;
import org.apache.pivot.wtk.content.TableViewCellRenderer;

public class NameCellRenderer extends TableViewCellRenderer
{
    public static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                            .getLogger(NameCellRenderer.class);

    @Override
    public String toString(Object row, String columnName) {
        Object cellData = JSON.get(row, columnName);

        String string = null;
        
        if (cellData != null)
        {
            // has object getName method?
            Method getNameMethod;
            try
            {
                getNameMethod = cellData.getClass().getMethod("getName");
                if(!getNameMethod.getReturnType().equals(Void.class))
                {
                    string = getNameMethod.invoke(cellData).toString();
                }
            }
            catch (Exception e)
            {
                string = "error getting name";
            }
        }

        return string;
    }   
}
