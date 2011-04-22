package at.easydiet.businessobjects;

import at.easydiet.model.PlanType;

public enum PlanTypeBO
{
    DIETPLAN("Diätplan"),
    NUTRITION_RECOMMENDATION("Ernährungsempfehlung"),
    NUTRITION_PROTOCOL("Ernährungsprotokoll"),
    BILL_OF_FARE("Speiseplan"),
    WEIGHT_PROTOCOL("Wägeprotokoll");
    
    private PlanType _planType;

    /**
     * Gets the planType.
     * @return the planType
     */
    public PlanType getPlanType()
    {
        return _planType;
    }
    
    
    
    /**
     * @return
     * @see at.easydiet.model.PlanType#getName()
     */
    public String getName()
    {
        return _planType.getName();
    }



    /** 
     * Initializes a new instance of the {@link PlanTypeBO} class. 
     * @param planType
     */
    private PlanTypeBO(String planType)
    {
        this(new PlanType(planType));
    }
    
    

    /** 
     * Initializes a new instance of the {@link PlanTypeBO} class. 
     * @param planType
     */
    private PlanTypeBO(PlanType planType)
    {
        _planType = planType;
    }
    
    
}
