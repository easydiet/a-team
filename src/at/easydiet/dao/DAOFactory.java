package at.easydiet.dao;

import org.hibernate.Session;

/**
 * A Factory which provides instances for all DAOs.
 * @author Daniel
 */
public class DAOFactory
{
    private static DAOFactory _instance;

    /**
     * Returns the globally useable {@link DAOFactory}.
     * @return the instance.
     */
    public static DAOFactory getInstance()
    {
        if (_instance == null)
        {
            _instance = new DAOFactory();
        }
        return _instance;
    }

    /**
     * Returns a new DAO for managing {@link CheckOperator} Objects.
     * @return a new {@link CheckOperatorDAO} instance.
     */
    public CheckOperatorDAO getCheckOperatorDAO()
    {
        return (CheckOperatorDAO) instantiateDAO(CheckOperatorDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link ContactJournal} Objects.
     * @return a new {@link ContactJournalDAO} instance.
     */
    public ContactJournalDAO getContactJournalDAO()
    {
        return (ContactJournalDAO) instantiateDAO(ContactJournalDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link ContactType} Objects.
     * @return a new {@link ContactTypeDAO} instance.
     */
    public ContactTypeDAO getContactTypeDAO()
    {
        return (ContactTypeDAO) instantiateDAO(ContactTypeDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link DietParameter} Objects.
     * @return a new {@link DietParameterDAO} instance.
     */
    public DietParameterDAO getDietParameterDAO()
    {
        return (DietParameterDAO) instantiateDAO(DietParameterDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link DietParameterSet} Objects.
     * @return a new {@link DietParameterSetDAO} instance.
     */
    public DietParameterSetDAO getDietParameterSetDAO()
    {
        return (DietParameterSetDAO) instantiateDAO(DietParameterSetDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link DietParameterTemplate} Objects.
     * @return a new {@link DietParameterTemplateDAO} instance.
     */
    public DietParameterTemplateDAO getDietParameterTemplateDAO()
    {
        return (DietParameterTemplateDAO) instantiateDAO(DietParameterTemplateDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link DietParameterType} Objects.
     * @return a new {@link DietParameterTypeDAO} instance.
     */
    public DietParameterTypeDAO getDietParameterTypeDAO()
    {
        return (DietParameterTypeDAO) instantiateDAO(DietParameterTypeDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link DietPlan} Objects.
     * @return a new {@link DietPlanDAO} instance.
     */
    public DietPlanDAO getDietPlanDAO()
    {
        return (DietPlanDAO) instantiateDAO(DietPlanDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link DietTreatment} Objects.
     * @return a new {@link DietTreatmentDAO} instance.
     */
    public DietTreatmentDAO getDietTreatmentDAO()
    {
        return (DietTreatmentDAO) instantiateDAO(DietTreatmentDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link DietTreatmentSystemUser} Objects.
     * @return a new {@link DietTreatmentSystemUserDAO} instance.
     */
    public DietTreatmentSystemUserDAO getDietTreatmentSystemUserDAO()
    {
        return (DietTreatmentSystemUserDAO) instantiateDAO(DietTreatmentSystemUserDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link FamilyAnamnesis} Objects.
     * @return a new {@link FamilyAnamnesisDAO} instance.
     */
    public FamilyAnamnesisDAO getFamilyAnamnesisDAO()
    {
        return (FamilyAnamnesisDAO) instantiateDAO(FamilyAnamnesisDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link Gender} Objects.
     * @return a new {@link GenderDAO} instance.
     */
    public GenderDAO getGenderDAO()
    {
        return (GenderDAO) instantiateDAO(GenderDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link LaborReport} Objects.
     * @return a new {@link LaborReportDAO} instance.
     */
    public LaborReportDAO getLaborReportDAO()
    {
        return (LaborReportDAO) instantiateDAO(LaborReportDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link Meal} Objects.
     * @return a new {@link MealDAO} instance.
     */
    public MealDAO getMealDAO()
    {
        return (MealDAO) instantiateDAO(MealDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link MealLine} Objects.
     * @return a new {@link MealLineDAO} instance.
     */
    public MealLineDAO getMealLineDAO()
    {
        return (MealLineDAO) instantiateDAO(MealLineDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link NutrimentParameter} Objects.
     * @return a new {@link NutrimentParameterDAO} instance.
     */
    public NutrimentParameterDAO getNutrimentParameterDAO()
    {
        return (NutrimentParameterDAO) instantiateDAO(NutrimentParameterDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link NutritionProtocol} Objects.
     * @return a new {@link NutritionProtocolDAO} instance.
     */
    public NutritionProtocolDAO getNutritionProtocolDAO()
    {
        return (NutritionProtocolDAO) instantiateDAO(NutritionProtocolDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link ParameterDefinition} Objects.
     * @return a new {@link ParameterDefinitionDAO} instance.
     */
    public ParameterDefinitionDAO getParameterDefinitionDAO()
    {
        return (ParameterDefinitionDAO) instantiateDAO(ParameterDefinitionDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link ParameterDefinitionDataType}
     * Objects.
     * @return a new {@link ParameterDefinitionDataTypeDAO} instance.
     */
    public ParameterDefinitionDataTypeDAO getParameterDefinitionDataTypeDAO()
    {
        return (ParameterDefinitionDataTypeDAO) instantiateDAO(ParameterDefinitionDataTypeDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link ParameterDefinitionUnit} Objects.
     * @return a new {@link ParameterDefinitionUnitDAO} instance.
     */
    public ParameterDefinitionUnitDAO getParameterDefinitionUnitDAO()
    {
        return (ParameterDefinitionUnitDAO) instantiateDAO(ParameterDefinitionUnitDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link Patient} Objects.
     * @return a new {@link PatientDAO} instance.
     */
    public PatientDAO getPatientDAO()
    {
        return (PatientDAO) instantiateDAO(PatientDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link PatientLike} Objects.
     * @return a new {@link PatientLikeDAO} instance.
     */
    public PatientLikeDAO getPatientLikeDAO()
    {
        return (PatientLikeDAO) instantiateDAO(PatientLikeDAO.class);
    }
    
    /**
     * Returns a new DAO for managing {@link PatientLikeGrade} Objects.
     * @return a new {@link PatientLikeGradeDAO} instance.
     */
    public PatientLikeGradeDAO getPatientLikeGradeDAO()
    {
        return (PatientLikeGradeDAO) instantiateDAO(PatientLikeGradeDAO.class);
    }
    
    /**
     * Returns a new DAO for managing {@link PatientState} Objects.
     * @return a new {@link PatientStateDAO} instance.
     */
    public PatientStateDAO getPatientStateDAO()
    {
        return (PatientStateDAO) instantiateDAO(PatientStateDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link PatientStateType} Objects.
     * @return a new {@link PatientStateTypeDAO} instance.
     */
    public PatientStateTypeDAO getPatientStateTypeDAO()
    {
        return (PatientStateTypeDAO) instantiateDAO(PatientStateTypeDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link PlanType} Objects.
     * @return a new {@link PlanTypeDAO} instance.
     */
    public PlanTypeDAO getPlanTypeDAO()
    {
        return (PlanTypeDAO) instantiateDAO(PlanTypeDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link Recipe} Objects.
     * @return a new {@link RecipeDAO} instance.
     */
    public RecipeDAO getRecipeDAO()
    {
        return (RecipeDAO) instantiateDAO(RecipeDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link RecipeIngredient} Objects.
     * @return a new {@link RecipeIngredientDAO} instance.
     */
    public RecipeIngredientDAO getRecipeIngredientDAO()
    {
        return (RecipeIngredientDAO) instantiateDAO(RecipeIngredientDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link SystemUser} Objects.
     * @return a new {@link SystemUserDAO} instance.
     */
    public SystemUserDAO getSystemUserDAO()
    {
        return (SystemUserDAO) instantiateDAO(SystemUserDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link SystemUserFunction} Objects.
     * @return a new {@link SystemUserFunctionDAO} instance.
     */
    public SystemUserFunctionDAO getSystemUserFunctionDAO()
    {
        return (SystemUserFunctionDAO) instantiateDAO(SystemUserFunctionDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link TimeSpan} Objects.
     * @return a new {@link TimeSpanDAO} instance.
     */
    public TimeSpanDAO getTimeSpanDAO()
    {
        return (TimeSpanDAO) instantiateDAO(TimeSpanDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link TreatmentState} Objects.
     * @return a new {@link TreatmentStateDAO} instance.
     */
    public TreatmentStateDAO getTreatmentStateDAO()
    {
        return (TreatmentStateDAO) instantiateDAO(TreatmentStateDAO.class);
    }

    /**
     * Returns a new DAO for managing {@link UserRight} Objects.
     * @return a new {@link UserRightDAO} instance.
     */
    public UserRightDAO getUserRightDAO()
    {
        return (UserRightDAO) instantiateDAO(UserRightDAO.class);
    }

    /**
     * Creates a new DAO for the specified class.
     * @param daoClass the type of the dao to be created
     * @return the created an initialized dao.
     */
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

    /**
     * Returns the current session to be assigned to all DAOs
     * @return the current session.
     */
    // You could override this if you don't want HibernateUtil for lookup
    protected Session getCurrentSession()
    {
        return HibernateUtil.currentSession();
    }
}
