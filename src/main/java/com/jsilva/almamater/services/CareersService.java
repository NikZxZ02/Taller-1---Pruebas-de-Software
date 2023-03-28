package com.jsilva.almamater.services;

import com.jsilva.almamater.models.Career;
import com.jsilva.almamater.models.University;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CareersService {
    private List<Career> careers;

    public void readCareers(InputStream input) throws IOException {
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader brCarreras = new BufferedReader(reader);

        String lineCarreras;
        int nLineCarrera = 0;
        while ((lineCarreras = brCarreras.readLine()) != null) {
            if (nLineCarrera != 0) {
                String[] values = lineCarreras.split(",");
                Career c = new Career();
                c.setId(Integer.parseInt(values[0]));
                c.setName(values[1].replace(";", ""));
                this.careers.add(c);
            }
            nLineCarrera++;
        }

        brCarreras.close();
    }

    public List<Career> getCareers() {
        return this.careers;
    }
}
