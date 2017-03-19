package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "departement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DepartementEntity.findAll", query = "SELECT d FROM DepartementEntity d")
    , @NamedQuery(name = "DepartementEntity.findByDepCode", query = "SELECT d FROM DepartementEntity d WHERE d.depCode = :depCode")
    , @NamedQuery(name = "DepartementEntity.findByDepLib", query = "SELECT d FROM DepartementEntity d WHERE d.depLib = :depLib")})
public class DepartementEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 5)
    @Column(name = "DepCode")
    private String depCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "DepLib")
    private String depLib;
    @OneToMany(mappedBy = "depCode")
    private List<CommuneEntity> communeEntityList;

    public DepartementEntity() {
    }

    public DepartementEntity(String depCode) {
        this.depCode = depCode;
    }

    public DepartementEntity(String depCode, String depLib) {
        this.depCode = depCode;
        this.depLib = depLib;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getDepLib() {
        return depLib;
    }

    public void setDepLib(String depLib) {
        this.depLib = depLib;
    }

    @XmlTransient
    public List<CommuneEntity> getCommuneEntityList() {
        return communeEntityList;
    }

    public void setCommuneEntityList(List<CommuneEntity> communeEntityList) {
        this.communeEntityList = communeEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (depCode != null ? depCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DepartementEntity)) {
            return false;
        }
        DepartementEntity other = (DepartementEntity) object;
        if ((this.depCode == null && other.depCode != null) || (this.depCode != null && !this.depCode.equals(other.depCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.DepartementEntity[ depCode=" + depCode + " ]";
    }
    
}
