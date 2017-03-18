/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import entities.NotecommentEntity;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import sessions.NotecommentEntityFacade;

/**
 *
 * @author phm
 */
@Named(value = "notecommentBean")
@Dependent
public class NotecommentBean {
    
    @EJB
    NotecommentEntityFacade facade;
    
    private NotecommentEntity noteEntity;

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
    
}
