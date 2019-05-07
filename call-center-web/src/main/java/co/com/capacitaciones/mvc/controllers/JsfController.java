package co.com.capacitaciones.mvc.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(value = "session")
@Component(value = "jsfController")
public class JsfController {

    public String loadEmployeePage() {
        checkPermission();
        return "/employee.xhtml";
    }

    private void checkPermission() {
        // Details omitted
    }

}
