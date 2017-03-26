package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "equipement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipementEntity.findAll", query = "SELECT e FROM EquipementEntity e")
    , @NamedQuery(name = "EquipementEntity.findByEquipementId", query = "SELECT e FROM EquipementEntity e WHERE e.equipementId = :equipementId")
    , @NamedQuery(name = "EquipementEntity.findByEquNom", query = "SELECT e FROM EquipementEntity e WHERE e.equNom = :equNom")
    , @NamedQuery(name = "EquipementEntity.findByEquNomBatiment", query = "SELECT e FROM EquipementEntity e WHERE e.equNomBatiment = :equNomBatiment")
    , @NamedQuery(name = "EquipementEntity.findByEquNbEquIdentique", query = "SELECT e FROM EquipementEntity e WHERE e.equNbEquIdentique = :equNbEquIdentique")})
public class EquipementEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EquipementId")
    private Integer equipementId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 101)
    @Column(name = "EquNom")
    private String equNom;
    @Size(max = 85)
    @Column(name = "EquNomBatiment")
    private String equNomBatiment;
    @Basic(optional = false)
    @NotNull
    @Column(name = "EquNbEquIdentique")
    private int equNbEquIdentique;
    @JoinColumn(name = "EquipementTypeCode", referencedColumnName = "EquipementTypeCode")
    @ManyToOne(optional = false)
    private EquipementtypeEntity equipementTypeCode;
    @JoinColumn(name = "InsNumeroInstall", referencedColumnName = "InsNumeroInstall")
    @ManyToOne(optional = false)
    private InstallationEntity insNumeroInstall;

    public EquipementEntity() {
    }

    public EquipementEntity(Integer equipementId) {
        this.equipementId = equipementId;
    }

    public EquipementEntity(Integer equipementId, String equNom, int equNbEquIdentique) {
        this.equipementId = equipementId;
        this.equNom = equNom;
        this.equNbEquIdentique = equNbEquIdentique;
    }

    public Integer getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(Integer equipementId) {
        this.equipementId = equipementId;
    }

    public String getEquNom() {
        return equNom;
    }

    public void setEquNom(String equNom) {
        this.equNom = equNom;
    }

    public String getEquNomBatiment() {
        return equNomBatiment;
    }

    public void setEquNomBatiment(String equNomBatiment) {
        this.equNomBatiment = equNomBatiment;
    }

    public int getEquNbEquIdentique() {
        return equNbEquIdentique;
    }

    public void setEquNbEquIdentique(int equNbEquIdentique) {
        this.equNbEquIdentique = equNbEquIdentique;
    }

    public EquipementtypeEntity getEquipementTypeCode() {
        return equipementTypeCode;
    }

    public void setEquipementTypeCode(EquipementtypeEntity equipementTypeCode) {
        this.equipementTypeCode = equipementTypeCode;
    }

    public InstallationEntity getInsNumeroInstall() {
        return insNumeroInstall;
    }

    public void setInsNumeroInstall(InstallationEntity insNumeroInstall) {
        this.insNumeroInstall = insNumeroInstall;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipementId != null ? equipementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipementEntity)) {
            return false;
        }
        EquipementEntity other = (EquipementEntity) object;
        if ((this.equipementId == null && other.equipementId != null) || (this.equipementId != null && !this.equipementId.equals(other.equipementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EquipementEntity[ equipementId=" + equipementId + " ]";
    }

}
