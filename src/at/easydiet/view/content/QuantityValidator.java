package at.easydiet.view.content;

import java.text.ParseException;

import org.apache.pivot.wtk.validation.Validator;

/**
 * Validator for quantity cells
 */
public class QuantityValidator implements Validator
{
    /**
     * Logger for debugging purposes
     */
    @SuppressWarnings("unused")
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(QuantityValidator.class);

    /**
     * @see org.apache.pivot.wtk.validation.Validator#isValid(java.lang.String)
     */
    public boolean isValid(String text)
    {
        boolean valid = false;

        if (text.length() > 0)
        {
            try
            {
                float value = QuantityBindMapping.FORMAT.parse(text)
                        .floatValue();

                if (value >= 0)
                {
                    valid = true;
                }
            }
            catch (ParseException e)
            {
                valid = false;
            }
        }

        return valid;
    }
}