package com.jsilva.almamater.services;

import com.jsilva.almamater.models.University;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class UniversityService {
    private List<University> universities = new ArrayList<>();

    public List<University> getUniversities() {
        return this.universities;
    }

    public void readUnivercities(InputStream input) throws IOException {
        InputStreamReader reader = new InputStreamReader(input);
        BufferedReader brData = new BufferedReader(reader);

        int nLine = 0;
        String line;

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
                this.universities.add(u);
            }
            nLine++;
        }

        brData.close();
    }
}
