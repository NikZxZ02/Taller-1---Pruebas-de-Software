//L1Distance
package KNN;

import com.jsilva.almamater.models.Survey;
import com.jsilva.almamater.models.University;

public class L1Distance implements Metric {

	@Override
	//L1
	public double getDistance(University s, Survey e) {
		assert s.getPoints().length == e.getPoints().length : "s and e are different types of records!";
		int numOfAttributes = s.getPoints().length;
		double sum1 = 0;
		
		for(int i = 0; i < numOfAttributes; i ++){
			sum1 += Math.abs(s.getPoints()[i] - e.getPoints()[i]);
		}
		
		return sum1;
	}

}
