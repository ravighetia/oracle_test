/**
 * 
 */
package com.oracle.test;

/**
 * @author Ravindra_Ghetia
 *
 */
public class MainClass {

	/**
	 * @param args Create object of class, call setter method to set inputs,call
	 *             process method to process input data and call generate method to
	 *             produce required results.
	 */
	public static void main(String[] args) {
		IResult result = new Result();
		final String data = "2343225,2345,us_east,RedTeam,ProjectApple,3445s  \r\n"
				+ "1223456,2345,us_west,BlueTeam,ProjectBanana,2211s  \r\n"
				+ "3244332,2346,eu_west,YellowTeam3,ProjectCarrot,4322s\r\n"
				+ "1233456,2345,us_west,BlueTeam,ProjectDate,2221s  \r\n"
				+ "3244132,2346,eu_west,YellowTeam3,ProjectEgg,4122s\r\n";

		result.setInut(data);
		result.processInputs();
		result.generateResult();
	}

}
