package polsl.pl.pm.controller;


import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkByPersonController {


    @RequestMapping(value="/workbyperson", method = RequestMethod.GET)
    public String WorkByPersonWeb(){

        return "xd";
    }
}
