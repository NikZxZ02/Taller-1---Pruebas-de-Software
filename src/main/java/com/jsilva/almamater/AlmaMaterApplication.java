package com.jsilva.almamater;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.jsilva.almamater.models.Career;
import com.jsilva.almamater.models.University;
import com.jsilva.almamater.services.CareersService;
import com.jsilva.almamater.services.UniversityService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class AlmaMaterApplication {
    public static void main(String[] args) throws IOException{
        SpringApplication.run(AlmaMaterApplication.class, args);
        
        InputStream universidadInput = leerCSV("data_universidades.csv");
        InputStream carrerasInput = leerCSV("data_carreras.csv");

        UniversityService universityService = new UniversityService();
        CareersService careersService = new CareersService();

        universityService.readUnivercities(universidadInput);
        careersService.readCareers(carrerasInput);
    }
    
    private static InputStream leerCSV(String path_csv) throws IOException{
        Resource resource = new ClassPathResource(path_csv);
	    InputStream input = resource.getInputStream();

        return input;
    }


}
