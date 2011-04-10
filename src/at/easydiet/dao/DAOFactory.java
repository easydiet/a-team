package at.easydiet.dao;

import org.hibernate.Session;

public class DAOFactory
{
    private static DAOFactory _instance;

    public static DAOFactory getInstance()
    {
        if (_instance == null)
        {
            _instance = new DAOFactory();
        }
        return _instance;
    }

    public CheckOperatorDAO getCheckOperatorDAO()
    {
        return (CheckOperatorDAO) instantiateDAO(CheckOperatorDAO.class);
    }

    public ContactJournalDAO getContactJournalDAO()
    {
        return (ContactJournalDAO) instantiateDAO(ContactJournalDAO.class);
    }

    public ContactTypeDAO getContactTypeDAO()
    {
        return (ContactTypeDAO) instantiateDAO(ContactTypeDAO.class);
    }

    public DietParameterDAO getDietParameterDAO()
    {
        return (DietParameterDAO) instantiateDAO(DietParameterDAO.class);
    }

    public DietParameterSetDAO getDietParameterSetDAO()
    {
        return (DietParameterSetDAO) instantiateDAO(DietParameterSetDAO.class);
    }

    public DietParameterTemplateDAO getDietParameterTemplateDAO()
    {
        return (DietParameterTemplateDAO) instantiateDAO(DietParameterTemplateDAO.class);
    }

    public DietParameterTypeDAO getDietParameterTypeDAO()
    {
        return (DietParameterTypeDAO) instantiateDAO(DietParameterTypeDAO.class);
    }

    public DietPlanDAO getDietPlanDAO()
    {
        return (DietPlanDAO) instantiateDAO(DietPlanDAO.class);
    }

    public DietTreatmentDAO getDietTreatmentDAO()
    {
        return (DietTreatmentDAO) instantiateDAO(DietTreatmentDAO.class);
    }

    public DietTreatmentSystemUserDAO getDietTreatmentSystemUserDAO()
    {
        return (DietTreatmentSystemUserDAO) instantiateDAO(DietTreatmentSystemUserDAO.class);
    }

    public FamilyAnamnesisDAO getFamilyAnamnesisDAO()
    {
        return (FamilyAnamnesisDAO) instantiateDAO(FamilyAnamnesisDAO.class);
    }

    public GenderDAO getGenderDAO()
    {
        return (GenderDAO) instantiateDAO(GenderDAO.class);
    }

    public LaborReportDAO getLaborReportDAO()
    {
        return (LaborReportDAO) instantiateDAO(LaborReportDAO.class);
    }

    public MealDAO getMealDAO()
    {
        return (MealDAO) instantiateDAO(MealDAO.class);
    }

    public MealLineDAO getMealLineDAO()
    {
        return (MealLineDAO) instantiateDAO(MealLineDAO.class);
    }

    public NutrimentParameterDAO getNutrimentParameterDAO()
    {
        return (NutrimentParameterDAO) instantiateDAO(NutrimentParameterDAO.class);
    }

    public NutritionProtocolDAO getNutritionProtocolDAO()
    {
        return (NutritionProtocolDAO) instantiateDAO(NutritionProtocolDAO.class);
    }

    public ParameterDefinitionDAO getParameterDefinitionDAO()
    {
        return (ParameterDefinitionDAO) instantiateDAO(ParameterDefinitionDAO.class);
    }

    public ParameterDefinitionDataTypeDAO getParameterDefinitionDataTypeDAO()
    {
        return (ParameterDefinitionDataTypeDAO) instantiateDAO(ParameterDefinitionDataTypeDAO.class);
    }

    public ParameterDefinitionUnitDAO getParameterDefinitionUnitDAO()
    {
        return (ParameterDefinitionUnitDAO) instantiateDAO(ParameterDefinitionUnitDAO.class);
    }

    public PatientDAO getPatientDAO()
    {
        return (PatientDAO) instantiateDAO(PatientDAO.class);
    }

    public PatientStateDAO getPatientStateDAO()
    {
        return (PatientStateDAO) instantiateDAO(PatientStateDAO.class);
    }

    public PatientStateTypeDAO getPatientStateTypeDAO()
    {
        return (PatientStateTypeDAO) instantiateDAO(PatientStateTypeDAO.class);
    }

    public PlanTypeDAO getPlanTypeDAO()
    {
        return (PlanTypeDAO) instantiateDAO(PlanTypeDAO.class);
    }

    public RecipeDAO getRecipeDAO()
    {
        return (RecipeDAO) instantiateDAO(RecipeDAO.class);
    }

    public SystemUserDAO getSystemUserDAO()
    {
        return (SystemUserDAO) instantiateDAO(SystemUserDAO.class);
    }

    public SystemUserFunctionDAO getSystemUserFunctionDAO()
    {
        return (SystemUserFunctionDAO) instantiateDAO(SystemUserFunctionDAO.class);
    }

    public TimeSpanDAO getTimeSpanDAO()
    {
        return (TimeSpanDAO) instantiateDAO(TimeSpanDAO.class);
    }

    public TreatmentStateDAO getTreatmentStateDAO()
    {
        return (TreatmentStateDAO) instantiateDAO(TreatmentStateDAO.class);
    }

    public UserRightDAO getUserRightDAO()
    {
        return (UserRightDAO) instantiateDAO(UserRightDAO.class);
    }

    @SuppressWarnings("unused")
    private GenericHibernateDAO<?, ?> instantiateDAO(Class<?> daoClass)
    {
        try
        {
            GenericHibernateDAO<?, ?> dao = (GenericHibernateDAO<?, ?>) daoClass
                    .newInstance();
            dao.setSession(getCurrentSession());
            return dao;
        }
        catch (Exception ex)
        {
            throw new RuntimeException("Can not instantiate DAO: " + daoClass,
                    ex);
        }
    }

    // You could override this if you don't want HibernateUtil for lookup
    protected Session getCurrentSession()
    {
        return HibernateUtil.currentSession();
    }
}
