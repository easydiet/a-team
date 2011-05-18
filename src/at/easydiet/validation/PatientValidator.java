package at.easydiet.validation;

public class PatientValidator
{
    /**
     * Logger for debugging purposes
     */
    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger
                                                               .getLogger(PatientValidator.class);
    
    /**
     * Returns whether the specified string is a valid insturance number.
     * @param insuranceNumber the string to check
     * @param template true if a template is valid, this means the insurancenumber only needs to be partially correct.
     * @return true fi the insturancenumber is valid otherwise false,
     */
    public static boolean isInsuranceNumber(String insuranceNumber, boolean template)
    {
        if(template)
        {
            return insuranceNumber.matches("^[0-9]{1,10}$");
        }
        return insuranceNumber.matches("^[0-9]{10}$");
    }
}
