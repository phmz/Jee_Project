package beans;

import entities.InstallationEntity;
import entities.NotecommentEntity;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import sessions.InstallationEntityFacade;

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
    }    
    
    public List<NotecommentEntity> updateRatings(String insNumeroInstall) {
        return facade.getRatings(insNumeroInstall);
    }
    
    public String getName(String insNumeroInstall) {
        return facade.getName(insNumeroInstall);
    }
}
