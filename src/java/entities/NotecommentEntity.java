/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phm
 */
@Entity
@Table(name = "notecomment")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotecommentEntity.findAll", query = "SELECT n FROM NotecommentEntity n")
    , @NamedQuery(name = "NotecommentEntity.findByEmail", query = "SELECT n FROM NotecommentEntity n WHERE n.notecommentEntityPK.email = :email")
    , @NamedQuery(name = "NotecommentEntity.findByInsNumeroInstall", query = "SELECT n FROM NotecommentEntity n WHERE n.notecommentEntityPK.insNumeroInstall = :insNumeroInstall")
    , @NamedQuery(name = "NotecommentEntity.findByNote", query = "SELECT n FROM NotecommentEntity n WHERE n.note = :note")
    , @NamedQuery(name = "NotecommentEntity.findByComment", query = "SELECT n FROM NotecommentEntity n WHERE n.comment = :comment")})
public class NotecommentEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected NotecommentEntityPK notecommentEntityPK;
    @Column(name = "note")
    private Integer note;
    @Size(max = 255)
    @Column(name = "comment")
    private String comment;

    public NotecommentEntity() {
    }

    public NotecommentEntity(NotecommentEntityPK notecommentEntityPK) {
        this.notecommentEntityPK = notecommentEntityPK;
    }

    public NotecommentEntity(String email, String insNumeroInstall) {
        this.notecommentEntityPK = new NotecommentEntityPK(email, insNumeroInstall);
    }

    public NotecommentEntityPK getNotecommentEntityPK() {
        return notecommentEntityPK;
    }

    public void setNotecommentEntityPK(NotecommentEntityPK notecommentEntityPK) {
        this.notecommentEntityPK = notecommentEntityPK;
    }

    public Integer getNote() {
        return note;
    }

    public void setNote(Integer note) {
        this.note = note;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notecommentEntityPK != null ? notecommentEntityPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotecommentEntity)) {
            return false;
        }
        NotecommentEntity other = (NotecommentEntity) object;
        if ((this.notecommentEntityPK == null && other.notecommentEntityPK != null) || (this.notecommentEntityPK != null && !this.notecommentEntityPK.equals(other.notecommentEntityPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NotecommentEntity[ notecommentEntityPK=" + notecommentEntityPK + " ]";
    }
    
}
