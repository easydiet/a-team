package at.easydiet.model;
// Generated 06.04.2011 16:43:36 by Hibernate Tools 3.4.0.CR1


import java.sql.Clob;
import java.util.HashSet;
import java.util.Set;

/**
 * Recipe generated by hbm2java
 */
public class Recipe  implements java.io.Serializable {


     private long recipeId;
     private String name;
     private String blsCode;
     private int difficulty;
     private Clob description;
     private Clob benefits;
     private Clob cookInstructions;
     private Set<Recipe> recipes = new HashSet<Recipe>(0);
     private Set<NutrimentParameter> nutrimentParameters = new HashSet<NutrimentParameter>(0);

    public Recipe() {
    }

	
    public Recipe(String name, int difficulty) {
        this.name = name;
        this.difficulty = difficulty;
    }
    public Recipe(String name, String blsCode, int difficulty, Clob description, Clob benefits, Clob cookInstructions, Set<Recipe> recipes, Set<NutrimentParameter> nutrimentParameters) {
       this.name = name;
       this.blsCode = blsCode;
       this.difficulty = difficulty;
       this.description = description;
       this.benefits = benefits;
       this.cookInstructions = cookInstructions;
       this.recipes = recipes;
       this.nutrimentParameters = nutrimentParameters;
    }
   
    public long getRecipeId() {
        return this.recipeId;
    }
    
    public void setRecipeId(long recipeId) {
        this.recipeId = recipeId;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getBlsCode() {
        return this.blsCode;
    }
    
    public void setBlsCode(String blsCode) {
        this.blsCode = blsCode;
    }
    public int getDifficulty() {
        return this.difficulty;
    }
    
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
    public Clob getDescription() {
        return this.description;
    }
    
    public void setDescription(Clob description) {
        this.description = description;
    }
    public Clob getBenefits() {
        return this.benefits;
    }
    
    public void setBenefits(Clob benefits) {
        this.benefits = benefits;
    }
    public Clob getCookInstructions() {
        return this.cookInstructions;
    }
    
    public void setCookInstructions(Clob cookInstructions) {
        this.cookInstructions = cookInstructions;
    }
    public Set<Recipe> getRecipes() {
        return this.recipes;
    }
    
    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }
    public Set<NutrimentParameter> getNutrimentParameters() {
        return this.nutrimentParameters;
    }
    
    public void setNutrimentParameters(Set<NutrimentParameter> nutrimentParameters) {
        this.nutrimentParameters = nutrimentParameters;
    }




}


