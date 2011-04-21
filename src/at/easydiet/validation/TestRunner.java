package at.easydiet.validation;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietParameterDAO;
import at.easydiet.dao.DietParameterSetDAO;
import at.easydiet.model.DietParameter;

public class TestRunner {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DietParameterDAO dao = DAOFactory.getInstance().getDietParameterDAO();
		
		List<DietParameter> list = dao.findAll();
		
		Set<DietParameterBO> set = new HashSet<DietParameterBO>();
		
		for(DietParameter dietParameter : list)
		{
			set.add(new DietParameterBO(dietParameter));
		}
		
		Set<DietParameterBO> conflicts = new HashSet<DietParameterBO>();
		
		System.out.println(new ParameterValidator().isValid(set, conflicts));
		for(DietParameterBO con : conflicts)
		{
			System.out.println(">>>" + con.toString());
		}
	}
}
