package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "commune")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommuneEntity.findAll", query = "SELECT c FROM CommuneEntity c")
    , @NamedQuery(name = "CommuneEntity.findByComInsee", query = "SELECT c FROM CommuneEntity c WHERE c.comInsee = :comInsee")
    , @NamedQuery(name = "CommuneEntity.findByComLib", query = "SELECT c FROM CommuneEntity c WHERE c.comLib = :comLib")})
public class CommuneEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "ComInsee")
    private String comInsee;
    @Size(max = 45)
    @Column(name = "ComLib")
    private String comLib;
    @JoinColumn(name = "DepCode", referencedColumnName = "DepCode")
    @ManyToOne
    private DepartementEntity depCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "comInsee")
    private List<InstallationEntity> installationEntityList;

    public CommuneEntity() {
    }

    public CommuneEntity(String comInsee) {
        this.comInsee = comInsee;
    }

    public String getComInsee() {
        return comInsee;
    }

    public void setComInsee(String comInsee) {
        this.comInsee = comInsee;
    }

    public String getComLib() {
        return comLib;
    }

    public void setComLib(String comLib) {
        this.comLib = comLib;
    }

    public DepartementEntity getDepCode() {
        return depCode;
    }

    public void setDepCode(DepartementEntity depCode) {
        this.depCode = depCode;
    }

    @XmlTransient
    public List<InstallationEntity> getInstallationEntityList() {
        return installationEntityList;
    }

    public void setInstallationEntityList(List<InstallationEntity> installationEntityList) {
        this.installationEntityList = installationEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (comInsee != null ? comInsee.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CommuneEntity)) {
            return false;
        }
        CommuneEntity other = (CommuneEntity) object;
        if ((this.comInsee == null && other.comInsee != null) || (this.comInsee != null && !this.comInsee.equals(other.comInsee))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.CommuneEntity[ comInsee=" + comInsee + " ]";
    }
    
}
