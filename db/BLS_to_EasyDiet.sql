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
INSERT INTO fhv_easyRecipe (Name, BlsCode, Difficulty, Description, Benefits, CookInstruction, Amount, Unit)
    SELECT a.name, a.blscode, 3, "", "", "", 100, (SELECT ParameterDefinitionUnitId FROM ParameterDefinitionUnit WHERE Name='g') FROM fhv_bls.nahrungsmitteleintrag;
    
-- Import nährwerte
