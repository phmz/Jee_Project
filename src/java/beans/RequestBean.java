/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.InstallationEntity;
import entities.RequestEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import sessions.CommuneEntityFacade;
import sessions.InstallationEntityFacade;
import sessions.RequestEntityFacade;

/**
 *
 * @author phm
 */
@ManagedBean
@Named(value = "requestBean")
@RequestScoped
public class RequestBean implements Serializable {

    @EJB
    RequestEntityFacade facade;

    @EJB
    CommuneEntityFacade cityfacade;
    
    @EJB
    InstallationEntityFacade installfacade;
    
    private RequestEntity request;

    private String department;

    private String city;
    
    private String possibleTags = "[{name: \"Parking\"},{name: \"Parking handicapé\"},{name: \"Accessibilité handicapé moteur\"},{name: \"Accessibilité handicapé sensoriel\"},{name: \"Bus\"},{name: \"Tram\"},{name: \"Train\"},{name: \"Metro\"}]";

    private List<InstallationEntity> installationList;

    private Map<String,String> citys;

    public List<InstallationEntity> getInstallationList() {
        return installationList;
    }

    public void setInstallationList(List<InstallationEntity> installationList) {
        this.installationList = installationList;
    }
    
    /**
     * Creates a new instance of UserBean
     */    
    public RequestBean() {
        request = new RequestEntity();
    }
    public List<String> completeText() {
        String query = "a";
        List<String> results = new ArrayList<String>();
        for(int i = 0; i < 10; i++) {
            results.add(query + i);
        }
         
        return results;
    }
    
    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public RequestEntity getRequest() {
        return request;
    }

    public void setRequest(RequestEntity request) {
        this.request = request;
    }

    public String getPossibleTags() {
        return possibleTags;
    }

    public void setPossibleTags(String possibleTags) {
        this.possibleTags = possibleTags;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Map<String, String> getCitys() {
        return citys;
    }

    public void setCitys(Map<String, String> citys) {
        this.citys = citys;
    }
    
    

    public void addTagToRequest() {
        System.err.println("Debut addTag");
        String tag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tags-form:keywords");
        if(tag.isEmpty()) {
            installationList = installfacade.search("", department,city);
            return;
        }
        String tagsArray[] = tag.split(",");
        HashMap<String, String> tagsMap = fillTagsMap();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if (tagsArray.length > 0) {
            //sb.append("WHERE ").append("i.").append(tagsMap.get(tagsArray[0])).append(" > 0");
            sb.append("i.").append(tagsMap.get(tagsArray[0])).append(" > 0");
            for (int i = 1; i < tagsArray.length; i++) {
                sb.append(" AND ").append("i.").append(tagsMap.get(tagsArray[i])).append(" > 0");
            }
        }
        
        installationList = installfacade.search(sb.toString(), department,city);
    }

    private HashMap<String, String> fillTagsMap() {
        HashMap<String, String> tagsMap = new HashMap<>();
        tagsMap.put("Parking", "InsNbPlaceParking");
        tagsMap.put("Parking handicapé", "InsNbPlaceParkingHandi");
        tagsMap.put("Accessibilité handicapé moteur", "InsAccessibiliteHandiMoteur");
        tagsMap.put("Accessibilité handicapé sensoriel", "InsAccessibiliteHandiSens");
        tagsMap.put("Bus", "InsTransportBus");
        tagsMap.put("Tram", "InsTransportTram");
        tagsMap.put("Train", "InsTransportTrain");
        tagsMap.put("Metro", "InsTransportMetro");
        return tagsMap;
    }
    
    public void stateChangeListener(){
        System.out.println("Entering stageChangeListener");
        citys = cityfacade.getNamesCommuneDept(department);        
    }
}
