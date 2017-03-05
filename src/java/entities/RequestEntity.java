/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author phm
 */
@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestEntity.findAll", query = "SELECT r FROM RequestEntity r")
    , @NamedQuery(name = "RequestEntity.findByIdrequest", query = "SELECT r FROM RequestEntity r WHERE r.idrequest = :idrequest")
    , @NamedQuery(name = "RequestEntity.findByRequestLib", query = "SELECT r FROM RequestEntity r WHERE r.requestLib = :requestLib")})
public class RequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idrequest")
    private Integer idrequest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1000)
    @Column(name = "requestLib")
    private String requestLib;

    public RequestEntity() {
    }

    public RequestEntity(Integer idrequest) {
        this.idrequest = idrequest;
    }

    public RequestEntity(Integer idrequest, String requestLib) {
        this.idrequest = idrequest;
        this.requestLib = requestLib;
    }

    public Integer getIdrequest() {
        return idrequest;
    }

    public void setIdrequest(Integer idrequest) {
        this.idrequest = idrequest;
    }

    public String getRequestLib() {
        return requestLib;
    }

    public void setRequestLib(String requestLib) {
        this.requestLib = requestLib;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idrequest != null ? idrequest.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequestEntity)) {
            return false;
        }
        RequestEntity other = (RequestEntity) object;
        if ((this.idrequest == null && other.idrequest != null) || (this.idrequest != null && !this.idrequest.equals(other.idrequest))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.RequestEntity[ idrequest=" + idrequest + " ]";
    }
    
}
