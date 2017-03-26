package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "request")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RequestEntity.findAll", query = "SELECT r FROM RequestEntity r")
    , @NamedQuery(name = "RequestEntity.findByIdrequest", query = "SELECT r FROM RequestEntity r WHERE r.idrequest = :idrequest")
    , @NamedQuery(name = "RequestEntity.findByDepLib", query = "SELECT r FROM RequestEntity r WHERE r.depLib = :depLib")
    , @NamedQuery(name = "RequestEntity.findByComInsee", query = "SELECT r FROM RequestEntity r WHERE r.comInsee = :comInsee")
    , @NamedQuery(name = "RequestEntity.findByTagsList", query = "SELECT r FROM RequestEntity r WHERE r.tagsList = :tagsList")})
public class RequestEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "idrequest")
    private Integer idrequest;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "depLib")
    private String depLib;
    @Size(max = 45)
    @Column(name = "comInsee")
    private String comInsee;
    @Size(max = 500)
    @Column(name = "tagsList")
    private String tagsList;
    @JoinColumn(name = "email", referencedColumnName = "email")
    @ManyToOne(optional = false)
    private UserEntity user;

    public RequestEntity() {
    }

    public RequestEntity(Integer idrequest) {
        this.idrequest = idrequest;
    }

    public RequestEntity(Integer idrequest, String depLib) {
        this.idrequest = idrequest;
        this.depLib = depLib;
    }

    public Integer getIdrequest() {
        return idrequest;
    }

    public void setIdrequest(Integer idrequest) {
        this.idrequest = idrequest;
    }

    public String getDepLib() {
        return depLib;
    }

    public void setDepLib(String depLib) {
        this.depLib = depLib;
    }

    public String getComInsee() {
        return comInsee;
    }

    public void setComInsee(String comInsee) {
        this.comInsee = comInsee;
    }

    public String getTagsList() {
        return tagsList;
    }

    public void setTagsList(String tagsList) {
        this.tagsList = tagsList;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
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
