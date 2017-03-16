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
@SessionScoped
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

    private List<InstallationEntity> installationList = new ArrayList<>();

    private List<InstallationEntity> currentInstallationList = new ArrayList<>();

    private Map<String, String> citys;

    private int currentPage = 0;

    private int elementPerPage = 5;

    private String tags;

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<InstallationEntity> getCurrentInstallationList() {
        return currentInstallationList;
    }

    public void setCurrentInstallationList(List<InstallationEntity> currentInstallationList) {
        this.currentInstallationList = currentInstallationList;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getElementPerPage() {
        return elementPerPage;
    }

    public void setElementPerPage(int elementPerPage) {
        this.elementPerPage = elementPerPage;
    }

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
        for (int i = 0; i < 10; i++) {
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
        String tag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tags-form:keywords");
        if (tag.isEmpty()) {
            installationList = installfacade.search("", department, city);
            initCurrentList();
            tags = "no tag";
            return;
        }
        String tagsArray[] = tag.split(",");
        HashMap<String, String> tagsMap = fillTagsMap();
        StringBuilder sb = new StringBuilder();
        sb.append("");
        if (tagsArray.length > 0) {
            sb.append("i.").append(tagsMap.get(tagsArray[0])).append(" > 0");
            for (int i = 1; i < tagsArray.length; i++) {
                sb.append(" AND ").append("i.").append(tagsMap.get(tagsArray[i])).append(" > 0");
            }
        }
        tags = tag;
        installationList = installfacade.search(sb.toString(), department, city);
        initCurrentList();
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

    public void stateChangeListener() {
        System.out.println("Entering stageChangeListener");
        citys = cityfacade.getNamesCommuneDept(department);
    }

    public void nextPage() {
        System.out.println("Getting next page");
        currentInstallationList.clear();
        currentPage++;
        for (int i = 0; i < elementPerPage; i++) {
            currentInstallationList.add(installationList.get(elementPerPage * currentPage + i));
        }
    }

    public void prevPage() {
        if (currentPage == 0) {
            return;
        }
        System.out.println("Getting previous page");
        currentInstallationList.clear();
        currentPage--;
        for (int i = 0; i < elementPerPage; i++) {
            currentInstallationList.add(installationList.get(elementPerPage * currentPage + i));
        }
    }

    private void initCurrentList() {
        System.out.println("Initializing current list");
        currentInstallationList.clear();
        currentPage = 0;
        for (int i = 0; i < elementPerPage; i++) {
            currentInstallationList.add(installationList.get(elementPerPage * currentPage + i));
        }
    }

    public String getInstallationAddress(InstallationEntity installation) {
        StringBuilder sb = new StringBuilder();
        if (installation.getInsNoVoie() != null) {
            sb.append(installation.getInsNoVoie());
        }
        if (installation.getInsLibelleVoie() != null) {
            sb.append(" ").append(installation.getInsLibelleVoie());
        }
        if (installation.getComInsee() != null) {
            sb.append(" ").append(installation.getComInsee().getComLib());
        }
        if (installation.getComInsee().getDepCode() != null) {
            sb.append(" ").append(installation.getComInsee().getDepCode().getDepLib());
        }
        return sb.toString();
    }

    public String getInstallationGoogleUrl(InstallationEntity installation) {
        String address = getInstallationAddress(installation).replace(" ", "+");
        return "https://www.google.com/maps/embed/v1/place?key=AIzaSyDQp7RamUbJjbyVmtpWomeTDpIcycDU1aQ&q=" + address;
    }
}
