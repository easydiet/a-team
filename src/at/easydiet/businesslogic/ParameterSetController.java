package at.easydiet.businesslogic;

public class ParameterSetController
{
    private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger
                                                             .getLogger(ParameterSetController.class);
    private static ParameterSetController _singleton;

    public static ParameterSetController getInstance()
    {
        if (_singleton == null)
        {
            _singleton = new ParameterSetController();
        }
        return _singleton;
    }
    
    private ParameterSetController()
    {}
    
    
}
