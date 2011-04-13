-- Map Dimensions
INSERT INTO fhv_easy.ParameterDefinitionUnit (Name, Type) 
    SELECT Bezeichnung, 'Numbers' FROM fhv_bls.dimension;

-- Add additional Dimensions for later mappings
INSERT INTO fhv_easy.ParameterDefinitionUnit (Name, Type)
    VALUES ('m?q', 'Numbers'), ('myg', 'Numbers');
    
-- Map Nährwertbezeichnungen
INSERT INTO fhv_easy.ParameterDefinition (Name) 
    SELECT name FROM fhv_bls.naehrwertbezeichnung GROUP BY name;
    
-- Map Units for Nährwertbezeichnungen
INSERT INTO fhv_easy.ParameterDefinitionUnits (parameterDefinitionId, parameterDefinitionUnitId)
    SELECT d.parameterDefinitionId, c.parameterDefinitionUnitId FROM fhv_bls.naehrwertbezeichnung a
        INNER JOIN fhv_bls.dimension b ON a.dimensions_id = b.id
        LEFT JOIN fhv_easy.ParameterDefinitionUnit c ON c.name = b.bezeichnung
        LEFT JOIN fhv_easy.ParameterDefinition d ON d.name = a.name;
        
-- Import StandardParameter as Parameterdefinitions
INSERT INTO fhv_easy.ParameterDefinition (Name) 
    SELECT bezeichnung FROM fhv_bls.whostandardparameter GROUP BY bezeichnung;

-- Assign Units for StandardParameter
INSERT INTO fhv_easy.ParameterDefinitionUnits (parameterDefinitionId, parameterDefinitionUnitId)
    SELECT d.parameterDefinitionId, c.parameterDefinitionUnitId FROM (SELECT a.bezeichnung, a.dimensions_id FROM fhv_bls.whostandardparameter a GROUP BY bezeichnung) a
        INNER JOIN fhv_bls.dimension b ON a.dimensions_id = b.id
        INNER JOIN fhv_easy.ParameterDefinitionUnit c ON b.bezeichnung = c.name
        INNER JOIN fhv_easy.ParameterDefinition d ON d.name = a.bezeichnung

-- Update ParameterDefinitionUnits Add additional Units
--    mg/100g -> mg
--    kJ/100g -> kJ
--    m?q/100g -> m?g
--    kcal/100g -> kcal
--    myg/100g -> 100g
INSERT INTO fhv_easy.ParameterDefinitionUnits (parameterDefinitionId, parameterDefinitionUnitId)
    SELECT a.ParameterDefinitionId, c.ParameterDefinitionUnitId FROM fhv_easy.ParameterDefinitionUnits a
        INNER JOIN fhv_easy.ParameterDefinitionUnit b ON a.ParameterDefinitionUnitId = b.ParameterDefinitionUnitId
        INNER JOIN fhv_easy.ParameterDefinitionUnit c ON c.Name = REPLACE(b.Name, '/100g', '') AND c.Name != b.Name
      
-- Import Nahrungsmitteleintrag 
INSERT INTO fhv_easy.Recipe (Name, BlsCode, Difficulty, Description, Benefits, CookInstructions, Amount, ParameterDefinitionUnitId)
    SELECT a.name, a.blscode, 3, "", "", "", 100, (SELECT ParameterDefinitionUnitId FROM fhv_easy.ParameterDefinitionUnit WHERE Name='g') FROM fhv_bls.nahrungsmitteleintrag a;
    
-- Import naehrwerte
/*
 *  SQL for mapping a single column/naehrwert
    
    INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
        SELECT COLUMN_NAME, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls_naehrwerte  
            -- get parameterdefinition
            INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'COLUMN_NAME' 
            INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
            -- get parameterdefinitionunit
            LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
            LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
            -- get recipe
            INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode') 

  *  Statement for loading all columns/naehrwerte
    SELECT COLUMN_NAME FROM information_schema.COLUMNS WHERE Table_name = "naehrwerte" AND COLUMN_NAME != "SBLS"
*/        

-- Statement for generating all sqls needed for mapping the bls. 
/*SELECT CONCAT(
    'INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) \n',
    '\tSELECT ', COLUMN_NAME, ', c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a \n', 
    '\t\tINNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = \'', COLUMN_NAME, '\' \n',
    '\t\tINNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name \n',
    '\t\tLEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id \n',
    '\t\tLEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung \n',
    '\t\tINNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;') 
FROM information_schema.COLUMNS WHERE Table_name = "naehrwerte" AND COLUMN_NAME != "SBLS"
*/

INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT SE, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'SE' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT GCAL, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'GCAL' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT GJ, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'GJ' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZW, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZW' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZE, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZE' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZF, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZF' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZK, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZK' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZB, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZB' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZM, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZM' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZO, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZO' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZA, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZA' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ZV, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ZV' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VA, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VA' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VAR, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VAR' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VAC, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VAC' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VD, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VD' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VE, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VE' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VEAT, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VEAT' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VK, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VK' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB1, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB1' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB2, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB2' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB3, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB3' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB3A, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB3A' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB5, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB5' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB6, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB6' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB7, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB7' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB9, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB9' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB9G, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB9G' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB9F, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB9F' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VB12, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VB12' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT VC, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'VC' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MNA, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MNA' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MK, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MK' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MCA, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MCA' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MMG, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MMG' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MP, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MP' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MCL, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MCL' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MFE, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MFE' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MZN, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MZN' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MCU, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MCU' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MMN, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MMN' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MF, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MF' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT MJ, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'MJ' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KAM, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KAM' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KAS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KAS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KAX, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KAX' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KA, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KA' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KMT, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KMT' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KMF, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KMF' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KMG, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KMG' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KM, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KM' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KDS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KDS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KDM, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KDM' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KDL, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KDL' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KD, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KD' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KPOR, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KPOR' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KPON, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KPON' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KPG, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KPG' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KPS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KPS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KP, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KP' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KBP, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KBP' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KBH, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KBH' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KBU, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KBU' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KBC, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KBC' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KBL, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KBL' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KBW, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KBW' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT KBN, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'KBN' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EILE, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EILE' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ELEU, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ELEU' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ELYS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ELYS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EMET, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EMET' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ECYS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ECYS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EPHE, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EPHE' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ETYR, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ETYR' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ETHR, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ETHR' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ETRP, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ETRP' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EVAL, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EVAL' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EARG, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EARG' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EHIS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EHIS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EEA, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EEA' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EALA, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EALA' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EASP, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EASP' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EGLU, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EGLU' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EGLY, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EGLY' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EPRO, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EPRO' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ESER, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ESER' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT ENA, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'ENA' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EPF, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EPF' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EH, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EH' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT EP, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'EP' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F40, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F40' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F60, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F60' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F80, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F80' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F100, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F100' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F120, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F120' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F140, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F140' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F150, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F150' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F160, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F160' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F170, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F170' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F180, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F180' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F200, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F200' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F220, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F220' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F240, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F240' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT FS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'FS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F141, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F141' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F151, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F151' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F161, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F161' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F171, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F171' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F181, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F181' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F201, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F201' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F221, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F221' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F241, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F241' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT FU, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'FU' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F162, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F162' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F164, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F164' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F182, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F182' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F183, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F183' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F184, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F184' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F193, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F193' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F202, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F202' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F203, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F203' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F204, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F204' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F205, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F205' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F222, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F222' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F223, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F223' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F224, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F224' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F225, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F225' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT F226, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'F226' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT FP, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'FP' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT FK, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'FK' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT FM, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'FM' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT FL, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'FL' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT FG, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'FG' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT FC, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'FC' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT GFPS, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'GFPS' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT GEBW, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'GEBW' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT GKB, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'GKB' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT GMKO, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'GMKO' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT GMSB, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'GMSB' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;
INSERT INTO fhv_easy.NutrimentParameter (Value, ParameterDefinitionId, ParameterDefinitionUnitId, RecipeId) 
	SELECT GP, c.ParameterDefinitionId, e.ParameterDefinitionUnitId, f.RecipeId FROM fhv_bls.naehrwerte a 
		INNER JOIN fhv_bls.naehrwertbezeichnung b ON b.id = 'GP' 
		INNER JOIN fhv_easy.ParameterDefinition c ON b.Name = c.name 
		LEFT JOIN fhv_bls.Dimension d ON d.id = b.dimensions_id 
		LEFT JOIN fhv_easy.ParameterDefinitionUnit e ON e.Name = d.bezeichnung 
		INNER JOIN fhv_easy.Recipe f ON a.SBLS = f.BlsCode;

