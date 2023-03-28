package KNN;
//This class implements Metric interface and is used to calculate EuclideanDistance

import com.jsilva.almamater.models.Survey;
import com.jsilva.almamater.models.University;


public class EuclideanDistance implements Metric {

	@Override
	//L2
	public double getDistance(University s, Survey e) {
		assert s.getPoints().length == e.getPoints().length : "s and e are different types of records!";
		int numOfAttributes = s.getPoints().length;
		double sum2 = 0;
		
		for(int i = 0; i < numOfAttributes; i ++){
			sum2 += Math.pow(s.getPoints()[i] - e.getPoints()[i], 2);
		}
		
		return Math.sqrt(sum2);
	}

}
