/**
 * 
 */
package at.easydiet.businessobjects;

import org.apache.pivot.collections.List;
import org.apache.pivot.collections.ArrayList;

import at.easydiet.model.CheckOperator;

/**
 * @author Mathias
 * 
 */
public enum CheckOperatorBO {
	BIGGER(">"), SMALLER("<"), EQUALORBIGGER(">="), EQUALORSMALLER("<="), EQUAL(
			"="), NOTEQUAL("!=");

	private CheckOperator _checkOperator;

	private CheckOperatorBO(String name) {
		_checkOperator = new CheckOperator(name);
	}

	public String getName() {
		return _checkOperator.getName();
	}

	public void setName(String name) {
		_checkOperator.setName(name);
	}

	public static CheckOperatorBO getForOperator(CheckOperator operator) {
		for (CheckOperatorBO current : values()) {
			if (current.getName().equals(operator.getName())) {
				return current;
			}
		}
		return EQUAL;
	}

	public static List<CheckOperatorBO> getAllOperators()
	{
		ArrayList<CheckOperatorBO> list = new ArrayList<CheckOperatorBO>();
		for(CheckOperatorBO current : values())
		{
			list.add(current);
		}
		return list;
	}
	
	public String toString()
	{
		return _checkOperator.getName();
	}

	CheckOperator getCheckOperator() {
		return _checkOperator;
	}
}
