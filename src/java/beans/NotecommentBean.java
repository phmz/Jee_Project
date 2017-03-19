package beans;

import entities.NotecommentEntity;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import sessions.NotecommentEntityFacade;

/**
 *
 * @author phm
 */
@Named(value = "notecommentBean")
@SessionScoped
public class NotecommentBean implements Serializable {
    
    @EJB
    NotecommentEntityFacade facade;
    
    private NotecommentEntity noteEntity;
    
    private int rating;
    
    private String comment;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
    
    

    /**
     * Creates a new instance of NotecommentBean
     */
    public NotecommentBean() {
    }

    public NotecommentEntityFacade getFacade() {
        return facade;
    }

    public void setFacade(NotecommentEntityFacade facade) {
        this.facade = facade;
    }

    public NotecommentEntity getNoteEntity() {
        return noteEntity;
    }

    public void setNoteEntity(NotecommentEntity noteEntity) {
        this.noteEntity = noteEntity;
    }
    
    public int printNote(String insNumeroInstall) {
        return facade.getAvg(insNumeroInstall);
    }
    
    public void printNotecomment() {
        System.out.println("rating "+rating+" "+comment);
    }
    
}
