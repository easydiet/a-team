package at.easydiet.view.content;

import java.lang.reflect.Method;

import org.apache.pivot.json.JSON;
import org.apache.pivot.wtk.content.TableViewCellRenderer;

import at.easydiet.util.StringUtils;

/**
 * Renders the name cell
 */
public class NameCellRenderer extends TableViewCellRenderer
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(NameCellRenderer.class);

    /**
     * Stores the name of the property
     */
    private String                               _propertyName;

    /**
     * Gets the propertyName.
     * 
     * @return the propertyName
     */
    public String getPropertyName()
    {
        return _propertyName;
    }

    /**
     * Sets the propertyName.
     * 
     * @param propertyName
     *            the propertyName to set
     */
    public void setPropertyName(String propertyName)
    {
        _propertyName = propertyName;
    }

    /**
     * Initializes a new instance of the {@link NameCellRenderer} class.
     */
    public NameCellRenderer()
    {
        _propertyName = "name";
    }

    /**
     * @see org.apache.pivot.wtk.content.TableViewCellRenderer#toString(java.lang.Object,
     *      java.lang.String)
     */
    @Override
    public String toString(Object row, String columnName)
    {
        Object cellData = JSON.get(row, columnName);

        String string = null;

        if (cellData != null)
        {
            Method getterMethod;
            try
            {
                String getter = "get" + StringUtils.capitalize(_propertyName);
                getterMethod = cellData.getClass().getMethod(getter);
                if (!getterMethod.getReturnType().equals(Void.class))
                {
                    string = getterMethod.invoke(cellData).toString();
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
