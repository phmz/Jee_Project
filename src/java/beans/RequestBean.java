/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.InstallationEntity;
import entities.RequestEntity;
import entities.UserEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.ManagedBean;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
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

    private boolean showSearch = false;

    private List<RequestEntity> requestHistory;

    public boolean isShowSearch() {
        return showSearch;
    }

    public void setShowSearch(boolean showSearch) {
        this.showSearch = showSearch;
    }

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

    public List<RequestEntity> getRequestHistory() {
        return requestHistory;
    }

    public void setRequestHistory(List<RequestEntity> requestHistory) {
        this.requestHistory = requestHistory;
    }

    public void addTagToRequest(UserEntity user) {
        showSearch = true;
        String tag = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("tags-form:keywords");
        System.out.println("TAG= " + tag);
        if (tag == null || tag.isEmpty()) {
            launchRequest("", user);
            tags = "no tag";
            return;
        }
        System.out.println("HERE IM HERE");
        tag = tag.replace(", ", ",");
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
        tags = tag.replace(",", ", ");
        launchRequest(sb.toString(), user);
    }

    private void launchRequest(String tag, UserEntity user) {
        installationList = installfacade.search(tag, department, city);
        request.setDepLib(department);
        request.setComInsee(city);
        request.setUser(user);
        if (tag.isEmpty()) {
            request.setTagsList("");
        } else {
            request.setTagsList(tags);
        }
        if (facade.addRequest(request)) {
            // DO SOMETHING
        }
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
        if ((currentPage + 1) * elementPerPage > installationList.size()) {
            return;
        }
        currentInstallationList.clear();
        currentPage++;
        for (int i = 0; i < elementPerPage; i++) {
            int index = elementPerPage * currentPage + i;
            if (installationList.size() > index) {
                currentInstallationList.add(installationList.get(elementPerPage * currentPage + i));
            }
        }
        updateDataTable();
    }

    public void prevPage() {
        if (currentPage == 0) {
            return;
        }
        System.out.println("Getting previous page");
        currentInstallationList.clear();
        currentPage--;
        for (int i = 0; i < elementPerPage; i++) {
            int index = elementPerPage * currentPage + i;
            if (installationList.size() > index) {
                currentInstallationList.add(installationList.get(elementPerPage * currentPage + i));
            }
        }
        updateDataTable();
    }

    private void initCurrentList() {
        System.out.println("Initializing current list");
        currentInstallationList.clear();
        currentPage = 0;
        for (int i = 0; i < elementPerPage; i++) {
            int index = elementPerPage * currentPage + i;
            if (installationList.size() > index) {
                currentInstallationList.add(installationList.get(elementPerPage * currentPage + i));
            }
        }
        System.out.println("showResult " + showSearch);
        RequestContext.getCurrentInstance().update("tags-form:resultdiv");
        updateDataTable();
    }

    public String getInstallationAddress(InstallationEntity installation) {
        StringBuilder sb = new StringBuilder();
        if (installation.getInsNoVoie() != null && !installation.getInsNoVoie().isEmpty() && !installation.getInsNoVoie().equals("-")) {
            // Only keep the first number
            // e.g. 20-22 will be 20 
            String noVoie = installation.getInsNoVoie().split("-")[0];
            sb.append(noVoie);
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

    private void updateDataTable() {
        RequestContext.getCurrentInstance().update("tags-form:section");
    }

    public void reset() {
        installationList.clear();
        currentInstallationList.clear();
        tags = "";
        showSearch = false;
        city = "";
        department = "";
        currentPage = 0;
    }

    public void fillHistory(UserEntity user) {
        System.out.println("Filling history");
        requestHistory = facade.searchUserHistory(user);
    }
    
    public void deleteRequest(int id, UserEntity user) {
        facade.deleteRequest(id);
        fillHistory(user);
    }
    
    public void searchRequest(UserEntity user, String depLib, String cityLib, String tagsList) {
        if (cityLib.isEmpty()) {
            city = "";
        } else {
        city = cityfacade.getComInsee(cityLib);
        }
        department = depLib;
        System.out.println("TAGSLIST "+tagsList);
        tags = tagsList;
        addTagToRequest(user);
    }
}
