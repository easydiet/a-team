package at.easydiet.view.content;

import java.text.DecimalFormat;
import java.text.ParseException;

import org.apache.pivot.wtk.TextInput.TextBindMapping;

/**
 * Bind mapping for quantity cells
 */
public class QuantityBindMapping implements TextBindMapping
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG    = org.apache.log4j.Logger
                                                                .getLogger(QuantityBindMapping.class);
    /**
     * Stores the {@link DecimalFormat} to use
     */
    public static final DecimalFormat            FORMAT = new DecimalFormat(
                                                                "0.00");

    /**
     * @see org.apache.pivot.wtk.TextInput.TextBindMapping#toString(java.lang.Object)
     */
    public String toString(Object value)
    {
        if (value instanceof String)
        {
            value = Float.valueOf((String) value);
        }
        return FORMAT.format(value);
    }

    /**
     * @see org.apache.pivot.wtk.TextInput.TextBindMapping#valueOf(java.lang.String)
     */
    public Object valueOf(String text)
    {
        try
        {
            return FORMAT.parse(text).floatValue();
        }
        catch (ParseException e)
        {
            return 0;
        }
    }
}
