package com.jsilva.almamater.controllers;

import KNN.KNN;
import com.google.common.collect.Lists;
import com.jsilva.almamater.models.Career;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.jsilva.almamater.models.Survey;
import com.jsilva.almamater.models.University;
import com.jsilva.almamater.models.Error;
import java.util.Arrays;
import java.util.Comparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.jsilva.almamater.exceptions.NotFoundException;
import com.jsilva.almamater.AlmaMaterApplication;

/**
 *
 * @author JSilva
 */
@RestController
@RequestMapping("/survey")
@CrossOrigin(origins = "*", methods = { RequestMethod.POST })
public class SurveyController {

    Comparator<University> universityComparator = (locale1, locale2) -> (int) (locale2.getPointsSum() - locale1.getPointsSum());

    @RequestMapping(value = "", method = POST)
    public ResponseEntity getRecommendOur(@RequestBody Survey survey) {
        List<University> universityList = Lists.newArrayList(AlmaMaterApplication.universities);

        if (survey.getCareerId().length == 0) {
            return ResponseEntity.status(HttpStatus.OK).body(recommend(survey));
        }

        for (int carrerId : survey.getCareerId()) {
            for (University university : universityList) {
                List<Career> careerInUniversity = Lists.newArrayList(university.getCareers());
                for (Career career : careerInUniversity) {
                    if (career.getId() == carrerId) {
                        return ResponseEntity.status(HttpStatus.OK).body(recommend(survey));
                    }
                }
            }
        }
        Error error = new Error("No se encontro la carrera en ninguna universidad", HttpStatus.NOT_FOUND);
        return ResponseEntity.status(error.getCode()).body(error);
    }

    private int getNearPoints(double distance) {
        if (distance < 30000) 
            return 5;
        else if (distance < 90000)
            return 4;
        else if (distance < 180000) 
            return 3;
        else if (distance < 270000)
            return 2;
        else 
            return 1;
    }

    private Object recommend(Survey survey) {
        List<University> recommendsUniversities = new ArrayList<>();
        for (University university : AlmaMaterApplication.universities) {
            boolean skipUniversity = true;
            if (survey.getCareerId().length != 0) {
                for (int idCareer : survey.getCareerId()) {
                    try {
                        Career career = AlmaMaterApplication.carreras.stream()
                            .filter(carrera -> carrera.getId() == idCareer)
                            .findFirst()
                            .orElse(null);
                        if (university.getCareers().contains(career))
                            skipUniversity = false;
                    } catch (Exception e) {
                        continue;
                    }
                }
            }else{
                skipUniversity = false;
            }

            if(skipUniversity)
                continue;

            switch (survey.getPtsBca()) {
            case 5:
                if (university.getPtsBca() != 5) {
                    continue;
                }
                break;
            case 4:
                if (university.getPtsBca() < 4) {
                    continue;
                }
                break;
            case 3:
                if (university.getPtsBca() < 3) {
                    continue;
                }
                break;
            case 2:
                if (university.getPtsBca() < 2) {
                    continue;
                }
                break;
            }
            switch (survey.getPtsAcr()) {
            case 5:
                if (university.getPtsAcr() != 5) {
                    continue;
                }
                break;
            case 4:
                if (university.getPtsAcr() < 4) {
                    continue;
                }
                break;
            case 3:
                if (university.getPtsAcr() < 3) {
                    continue;
                }
                break;
            case 2:
                if (university.getPtsAcr() < 2) {
                    continue;
                }
                break;
            }
            switch (survey.getPtsRank()) {
            case 5:
                if (university.getPtsRank() != 5) {
                    continue;
                }
                break;
            case 4:
                if (university.getPtsRank() < 4) {
                    continue;
                }
                break;
            case 3:
                if (university.getPtsRank() < 3) {
                    continue;
                }
                break;
            case 2:
                if (university.getPtsRank() < 2) {
                    continue;
                }
                break;
            }
            double distance = getDistance(survey, university);
            university.setPtsNear(getNearPoints(distance));
            university.setDistanceLatLng(distance);
            switch (survey.getPtsNear()) {
            case 5:
                if (university.getPtsNear() != 5) {
                    continue;
                }
                break;
            case 4:
                if (university.getPtsNear() < 4) {
                    continue;
                }
                break;
            case 3:
                if (university.getPtsNear() < 3) {
                    continue;
                }
                break;
            case 2:
                if (university.getPtsNear() < 2) {
                    continue;
                }
                break;
            case 1:
                if (university.getPtsNear() < 1) {
                    continue;
                }
                break;
            }

            recommendsUniversities.add(university);
            
        }

        if (recommendsUniversities.isEmpty()) {
            return new University[0];

        }
        KNN k = new KNN();
        University[] returnRecommendUniversities = k.knn(recommendsUniversities, survey, recommendsUniversities.size(),
                0);
        if (returnRecommendUniversities.length > 1)
            Arrays.sort(returnRecommendUniversities, universityComparator);

        return returnRecommendUniversities;
    }

    private double rad(double x) {
        return x * Math.PI / 180;
    }

    private double getDistance(Survey survey, University university) {
        int R = 6378137;
        double dLat = rad(university.getLat() - survey.getLat());
        double dLong = rad(university.getLng() - survey.getLng());
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(rad(university.getLat()))
                * Math.cos(rad(university.getLat())) * Math.sin(dLong / 2) * Math.sin(dLong / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double d = R * c;
        return d;
    }
}
