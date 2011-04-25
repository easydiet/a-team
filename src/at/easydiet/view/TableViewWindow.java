package at.easydiet.view;

import java.net.URL;
import java.util.List;
import org.apache.pivot.beans.BXML;
import org.apache.pivot.beans.Bindable;
import org.apache.pivot.collections.ArrayList;
import org.apache.pivot.collections.Map;
import org.apache.pivot.util.Resources;
import org.apache.pivot.wtk.Window;
import at.easydiet.businessobjects.DietParameterBO;
import at.easydiet.dao.DAOFactory;
import at.easydiet.dao.DietParameterDAO;
import at.easydiet.model.DietParameter;

public class TableViewWindow extends Window implements Bindable {

	@BXML
	private ParameterTableView parameterTableView;
	
	
	public ParameterTableView getParameterTableView() {
		return parameterTableView;
	}

	@Override
	public void initialize(Map<String, Object> arg0, URL arg1, Resources arg2) {
		
		
		DietParameterDAO dao = DAOFactory.getInstance().getDietParameterDAO();
		List<DietParameter> list = dao.findAll();

		ArrayList<DietParameterBO> set = new ArrayList<DietParameterBO>();

		for (DietParameter dietParameter : list) {
			DietParameterBO dietParameterBO = new DietParameterBO(dietParameter);
			System.out.println(dietParameterBO.toString());
			set.add(dietParameterBO);
		}

		// define values for the Editor Components (ListButtons)
		parameterTableView.setTableData(set);
		parameterTableView.initialize();

	}
}
