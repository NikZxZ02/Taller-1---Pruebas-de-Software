//basic metric interface
package KNN;

import com.jsilva.almamater.models.Survey;
import com.jsilva.almamater.models.University;

public interface Metric {
	double getDistance(University s, Survey e);
}
