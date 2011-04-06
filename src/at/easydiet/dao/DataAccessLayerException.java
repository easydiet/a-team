package at.easydiet.dao;

/**
 * Represents Exceptions thrown by the Data Access Layer.
 */
public class DataAccessLayerException extends RuntimeException
{
    private static final long serialVersionUID = 4919907232450668909L;

    /**
     * Initializes a new instance of the {@link DataAccessLayerException} class.
     */
    public DataAccessLayerException()
    {
        super();
    }

    /**
     * Initializes a new instance of the {@link DataAccessLayerException} class.
     * 
     * @param message
     * @param cause
     */
    public DataAccessLayerException(String message, Throwable cause)
    {
        super(message, cause);
    }

    /**
     * Initializes a new instance of the {@link DataAccessLayerException} class.
     * 
     * @param message
     */
    public DataAccessLayerException(String message)
    {
        super(message);
    }

    /**
     * Initializes a new instance of the {@link DataAccessLayerException} class.
     * 
     * @param cause
     */
    public DataAccessLayerException(Throwable cause)
    {
        super(cause);
    }

}
