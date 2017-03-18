/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author phm
 */
@Embeddable
public class NotecommentEntityPK implements Serializable {

    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "InsNumeroInstall")
    private String insNumeroInstall;

    public NotecommentEntityPK() {
    }

    public NotecommentEntityPK(String email, String insNumeroInstall) {
        this.email = email;
        this.insNumeroInstall = insNumeroInstall;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsNumeroInstall() {
        return insNumeroInstall;
    }

    public void setInsNumeroInstall(String insNumeroInstall) {
        this.insNumeroInstall = insNumeroInstall;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (email != null ? email.hashCode() : 0);
        hash += (insNumeroInstall != null ? insNumeroInstall.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NotecommentEntityPK)) {
            return false;
        }
        NotecommentEntityPK other = (NotecommentEntityPK) object;
        if ((this.email == null && other.email != null) || (this.email != null && !this.email.equals(other.email))) {
            return false;
        }
        if ((this.insNumeroInstall == null && other.insNumeroInstall != null) || (this.insNumeroInstall != null && !this.insNumeroInstall.equals(other.insNumeroInstall))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.NotecommentEntityPK[ email=" + email + ", insNumeroInstall=" + insNumeroInstall + " ]";
    }
    
}
