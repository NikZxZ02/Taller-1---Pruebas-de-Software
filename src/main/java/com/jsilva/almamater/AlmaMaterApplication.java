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
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ClassPathResource;

@SpringBootApplication
public class AlmaMaterApplication {

    public static List<University> universities;
    public static List<Career> carreras;

    public static void main(String[] args) throws IOException{
        SpringApplication.run(AlmaMaterApplication.class, args);
        
        leerCSV();
    }
    
    static void leerCSV() throws FileNotFoundException, IOException{
        Resource resource = new ClassPathResource("data_universidades.csv");
	    InputStream universidadInput = resource.getInputStream();

        Resource resource2 = new ClassPathResource("data_carreras.csv");
	    InputStream carreraInput = resource2.getInputStream();

        universities = new ArrayList<>();
        carreras = new ArrayList<>();

        BufferedReader brData = new BufferedReader(new InputStreamReader(universidadInput));

        String line;

        int nLine = 0;
        while ((line = brData.readLine()) != null) {
            if (nLine != 0) {
                String[] values = line.split(",");
                University u = new University();
                u.setId(Integer.parseInt(values[0]));
                u.setName(values[1].replace("\u0097", "ó").replace("\u0092", "í").replace("\u008e", "é").replace("\u0087", "á"));
                u.setRegion(Integer.parseInt(values[2]));
                u.setPtsBca(Integer.parseInt(values[3]));
                u.setPtsAcr(Integer.parseInt(values[4]));
                u.setPtsRank(Integer.parseInt(values[5]));
                u.setIsPrivate((values[6]));
                if (values.length > 6) {
                    u.setLat(Double.parseDouble(values[7]));
                    u.setLng(Double.parseDouble(values[8]));
                }
                universities.add(u);
            }
            nLine++;
        }

        brData.close();

        BufferedReader brCarreras = new BufferedReader(new InputStreamReader(carreraInput));
        String lineCarreras;
        int nLineCarrera = 0;
        while ((lineCarreras = brCarreras.readLine()) != null) {
            if (nLineCarrera != 0) {
                String[] values = lineCarreras.split(",");
                Career c = new Career();
                c.setId(Integer.parseInt(values[0]));
                c.setName(values[1].replace(";", ""));
                carreras.add(c);
            }
            nLineCarrera++;
        }

        brCarreras.close();
    }

}
