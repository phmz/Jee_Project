/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.InstallationEntity;
import entities.NotecommentEntity;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import org.primefaces.context.RequestContext;
import sessions.InstallationEntityFacade;

/**
 *
 * @author phm
 */
@Named(value = "installationBean")
@SessionScoped
public class InstallationBean implements Serializable {

    @EJB
    InstallationEntityFacade facade;

    List<NotecommentEntity> ratings;

    InstallationEntity installation;

    /**
     * Creates a new instance of InstallationBean
     */
    public InstallationBean() {
    }

    public InstallationEntityFacade getFacade() {
        return facade;
    }

    public void setFacade(InstallationEntityFacade facade) {
        this.facade = facade;
    }

    public List<NotecommentEntity> getRatings() {
        return ratings;
    }

    public void setRatings(List<NotecommentEntity> ratings) {
        this.ratings = ratings;
    }

    public InstallationEntity getInstallation() {
        return installation;
    }

    public void setInstallation(InstallationEntity installation) {
        this.installation = installation;
    }

    public void initRatings(String insNumeroInstall) {
        System.out.println("Initializing ratings");
        ratings = facade.getRatings(insNumeroInstall);
        for (NotecommentEntity note : ratings) {
            String email = note.getNotecommentEntityPK().getEmail().split("@")[0];
            int size = email.length();
            email = email.substring(0, size / 2 + 1);
            StringBuilder sb = new StringBuilder();
            sb.append(email);
            for (int i = 0; i < size; i++) {
                sb.append("*");
            }
            note.getNotecommentEntityPK().setEmail(sb.toString());
        }
        for (NotecommentEntity note : ratings) {
            System.out.println(note.getNotecommentEntityPK().getEmail() + " " + note.getNote() + " " + note.getComment());
        }
    }
    
    public void ratingsInfo() {
                for (NotecommentEntity note : ratings) {
            System.out.println(note.getNotecommentEntityPK().getEmail() + " " + note.getNote() + " " + note.getComment());
        }
    }
    
    public List<NotecommentEntity> updateRatings(String insNumeroInstall) {
        return facade.getRatings(insNumeroInstall);
    }
    
    public void showRatingModal(String insNumeroInstall) {
        initRatings(insNumeroInstall);
        Map<String,Object> options = new HashMap<String, Object>();
        options.put("resizable", false);
        System.out.println("YEEEE");
        RequestContext.getCurrentInstance().openDialog("viewRatings", options, null);
        RequestContext.getCurrentInstance().openDialog("viewRatings");
        
    }
}
