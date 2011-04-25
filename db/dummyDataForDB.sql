-- New DietParameterSet
insert into DietParameterSet (Name) values ('Energie');

-- ParameterDefinition für ja oder nein mit Pattern
insert into ParameterDefintion (Name, CheckPattern) values ('Ja oder Nein','(?i)^ja$|^nein$');
--Zur ParameterDefinition ein Typ zuweisen
insert into ParameterDefinitionUnit (Name, `Type`) values ('Entscheidung', 'Enumeration');
-- Diese verbinden
insert into ParameterDefinitionUnits (ParameterDefintionId, ParameterDefinitionUnitId) 
values
(
	select ParameterDefinitionId from ParameterDefinition where Name = 'Ja oder Nein',
	select ParameterDefinitionUnitId from ParameterDefinitionUnit where Name = 'Entscheidung'
);

-- ParameterDefinition
-- 36	Energie (Kilojoule)	NULL
-- 37	Energie (Kilokalorien)	NULL

-- ParameterDefinitionUnit
-- 6	kcal	Numbers
-- 9	kJ		Numbers

-- Energie (Kilokalorien)
insert into DietParameterTemplate 
(
	CheckOperator, 
	DietParameterType, 
	ParameterDefinitionId, 
	DietParameterSetId,
	ParameterDefinitionUnitId
) 
values 
(
	'<=',
	'Zielparameter',
	37,
	'Energie',
	6
);

-- Energie (Kilokalorien)
insert into DietParameterTemplate 
(
	CheckOperator, 
	DietParameterType, 
	ParameterDefinitionId, 
	DietParameterSetId,
	ParameterDefinitionUnitId
) 
values 
(
	'>=',
	'Zielparameter',
	37,
	'Energie',
	6
);

-- 36	Energie (Kilojoule)	NULL
insert into DietParameterTemplate 
(
	CheckOperator, 
	DietParameterType, 
	ParameterDefinitionId, 
	DietParameterSetId,
	ParameterDefinitionUnitId
) 
values 
(
	'>=',
	'Zielparameter',
	36,
	'Energie',
	9
);

-- 36	Energie (Kilojoule)	NULL
insert into DietParameterTemplate 
(
	CheckOperator, 
	DietParameterType, 
	ParameterDefinitionId, 
	DietParameterSetId,
	ParameterDefinitionUnitId
) 
values 
(
	'>=',
	'Zielparameter',
	36,
	'Energie',
	9
);


-- Siegfried Koch alter 31
insert into DietTreatment (Start, Duration, Name, TreatmentState, PatientId)
	values
	('2011-04-01 15:27:49', 80, 'Gewichtsreduktionsdiät', 'Laufend', 25);

-- Siegfried mag keine Erdbeeren RecipeId: 1414, Name: Erdbeere
insert into PatientDisfavors (PatientId, PecipeId) values (25, 1414);

-- SystemUser
insert into SystemUser (Username, Password, Name, Email) values ('fhv', 'fhv_pw', 'Gerlinde Gemüse', 'gerlinde.gemuese@fhv.at');

-- Rechte für fhv 
-- Regex: ^([a-zA-Z_]*)$
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'DIETPLAN_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'DIETPLAN_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'DIETPOOL_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'DIETPOOL_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'DIETTREATMENT_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'DIETTREATMENT_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'LABORREPORT_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'LABORREPORT_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'NUTRITIONPROTOCOL_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'NUTRITIONPROTOCOL_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'PARAMETERSET_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'PARAMETERSET_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'PATIENTSTATE_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'PATIENTSTATE_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'PATIENT_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'PATIENT_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'RECIPE_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'RECIPE_SHOW');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'SYSUSER_CREATE_EDIT_DELETE');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'SYSUSER_LOGIN');
insert into SystemUserRight (SystemUserId, UserRightId) values (select SystemUserId from SystemUser where Username = 'fhv'), 'SYSUSER_SHOW');
