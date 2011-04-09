package at.easydiet;

import org.apache.log4j.BasicConfigurator;
import at.easydiet.dao.HibernateUtil;

public class Setup
{
    public static void main(String[] args) throws Exception
    {
        Setup s = new Setup();
        s.doIt();
    }

    private void doIt() throws Exception
    {
        BasicConfigurator.configure();
        setUp();
    } 

    protected void setUp() throws Exception
    {
        HibernateUtil.currentSession();
    }
}
