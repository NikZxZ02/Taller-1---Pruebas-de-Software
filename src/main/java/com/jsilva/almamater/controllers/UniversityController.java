package com.jsilva.almamater.controllers;

import com.jsilva.almamater.AlmaMaterApplication;
import java.util.List;
import com.jsilva.almamater.models.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.jsilva.almamater.exceptions.NotFoundException;

/**
 *
 * @author JSilva
 */
@RestController
@RequestMapping("/universities")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class UniversityController {
    

    @RequestMapping(method = GET, value = "")
    public @ResponseBody List<University> getAllUniversities() {
        return AlmaMaterApplication.universities;
    }

    @RequestMapping(value = "/{id}", method = GET)
    public @ResponseBody University getUniversityById(@PathVariable("id") int id) {
        return AlmaMaterApplication.universities.stream()
            .filter(university -> university.getId() == id)
            .findFirst()
            .orElse(null);
    }
}
