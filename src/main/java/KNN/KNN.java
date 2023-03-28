package KNN;

import java.util.List;
import com.jsilva.almamater.models.Survey;
import com.jsilva.almamater.models.University;

public class KNN {

    public University[] knn(List<University> trainingUniversities, Survey survey, int K, int metricType) {
        //get the current time
        final long startTime = System.currentTimeMillis();

        // make sure the input arguments are legal
        if (K <= 0) {
            System.out.println("K should be larger than 0!");
            return null;
        }

        // metricType should be within [0,2];
        if (metricType > 2 || metricType < 0) {
            System.out.println("metricType is not within the range [0,2]. Please try again later");
            return null;
        }

        //determine the type of metric according to metricType
        Metric metric;
        switch (metricType) {
            case 0:
                metric = new CosineSimilarity();
                break;
            case 1:
                metric = new L1Distance();
                break;
            case 2:
                metric = new EuclideanDistance();
                break;
            default:
                System.out.println("The entered metric_type is wrong!");
                return null;
        }
        
        University[] training = trainingUniversities.toArray(new University[trainingUniversities.size()]);

        //test those TestRecords one by one
        return findKNearestNeighbors(training, survey, K, metric);

    }

    // Find K nearest neighbors of testRecord within trainingSet 
    University[] findKNearestNeighbors(University[] trainingSet, Survey testRecord, int K, Metric metric) {
        int NumOfTrainingSet = trainingSet.length;
        assert K <= NumOfTrainingSet : "K is lager than the length of trainingSet!";

        //Update KNN: take the case when testRecord has multiple neighbors with the same distance into consideration
        //Solution: Update the size of container holding the neighbors
        University[] neighbors = new University[K];

        //initialization, put the first K trainRecords into the above arrayList
        int index;
        for (index = 0; index < K; index++) {
            trainingSet[index].setDistanceKNN(metric.getDistance(trainingSet[index], testRecord));
            neighbors[index] = trainingSet[index];
        }

        //go through the remaining records in the trainingSet to find K nearest neighbors
        for (index = K; index < NumOfTrainingSet; index++) {
            trainingSet[index].setDistanceKNN(metric.getDistance(trainingSet[index], testRecord));

            //get the index of the neighbor with the largest distance to testRecord
            int maxIndex = 0;
            for (int i = 1; i < K; i++) {
                if (neighbors[i].getDistanceKNN()> neighbors[maxIndex].getDistanceKNN()) {
                    maxIndex = i;
                }
            }

            //add the current trainingSet[index] into neighbors if applicable
            if (neighbors[maxIndex].getDistanceKNN()> trainingSet[index].getDistanceKNN()) {
                neighbors[maxIndex] = trainingSet[index];
            }
        }
        return neighbors;
    }
}
