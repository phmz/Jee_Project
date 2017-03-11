/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import entities.DepartementEntity;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import sessions.DepartementEntityFacade;

/**
 *
 * @author phm
 */
@Named(value = "departement")
@SessionScoped
@ManagedBean
public class DepartementBean implements Serializable {
    
    @EJB
    DepartementEntityFacade facade;

    private DepartementEntity departement;
    
    private Map<String, String> departments;
    
    /**
     * Creates a new instance of Departement
     */
    public DepartementBean() {
        departement = new DepartementEntity();
    }
    
    @PostConstruct
    public void init() {
        System.out.println("INIT STARTED");
        departments = new LinkedHashMap<>();
        List<String> arrayLibDep = facade.getAllNamesDepartement();        
        arrayLibDep.forEach(s -> departments.put(s,s));               
        System.out.println(departments.size());
    }

    public DepartementEntity getDepartement() {
        return departement;
    }

    public void setDepartement(DepartementEntity departement) {
        this.departement = departement;
    }

    public Map<String, String> getDepartments() {
        return departments;
    }

    public void setDepartments(Map<String, String> departments) {
        this.departments = departments;
    }               
}
