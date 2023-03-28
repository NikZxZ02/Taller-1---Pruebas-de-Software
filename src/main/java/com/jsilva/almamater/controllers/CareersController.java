package com.jsilva.almamater.controllers;

import com.jsilva.almamater.models.Career;
import com.jsilva.almamater.AlmaMaterApplication;

import com.jsilva.almamater.services.CareersService;
import com.jsilva.almamater.services.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 *
 * @author JSilva
 */
@RestController
@RequestMapping("/careers")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET})
public class CareersController {
    private final CareersService careersService = new CareersService();
    
    @RequestMapping(method = GET, value = "")
    public @ResponseBody List<Career> all() {
        return this.careersService.getCareers();
    }
}
