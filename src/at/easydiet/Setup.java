package at.easydiet;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Setup
{
    public static void main(String[] args) throws Exception
    {
        Setup s = new Setup();
        s.doIt();
    }

    private SessionFactory _sessionFactory;

    private void doIt() throws Exception
    {
        setUp();
    } 

    protected void setUp() throws Exception
    {
        // A SessionFactory is set up once for an application
        _sessionFactory = new Configuration().configure(Setup.class.getResource("hibernate.cfg.xml")).buildSessionFactory();
    }
}
