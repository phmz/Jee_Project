/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
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

/**
 *
 * @author phm
 */
@Entity
@Table(name = "equipementtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "EquipementtypeEntity.findAll", query = "SELECT e FROM EquipementtypeEntity e")
    , @NamedQuery(name = "EquipementtypeEntity.findByEquipementTypeCode", query = "SELECT e FROM EquipementtypeEntity e WHERE e.equipementTypeCode = :equipementTypeCode")
    , @NamedQuery(name = "EquipementtypeEntity.findByEquipementTypeLib", query = "SELECT e FROM EquipementtypeEntity e WHERE e.equipementTypeLib = :equipementTypeLib")})
public class EquipementtypeEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "EquipementTypeCode")
    private Integer equipementTypeCode;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 51)
    @Column(name = "EquipementTypeLib")
    private String equipementTypeLib;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "equipementTypeCode")
    private List<EquipementEntity> equipementEntityList;

    public EquipementtypeEntity() {
    }

    public EquipementtypeEntity(Integer equipementTypeCode) {
        this.equipementTypeCode = equipementTypeCode;
    }

    public EquipementtypeEntity(Integer equipementTypeCode, String equipementTypeLib) {
        this.equipementTypeCode = equipementTypeCode;
        this.equipementTypeLib = equipementTypeLib;
    }

    public Integer getEquipementTypeCode() {
        return equipementTypeCode;
    }

    public void setEquipementTypeCode(Integer equipementTypeCode) {
        this.equipementTypeCode = equipementTypeCode;
    }

    public String getEquipementTypeLib() {
        return equipementTypeLib;
    }

    public void setEquipementTypeLib(String equipementTypeLib) {
        this.equipementTypeLib = equipementTypeLib;
    }

    @XmlTransient
    public List<EquipementEntity> getEquipementEntityList() {
        return equipementEntityList;
    }

    public void setEquipementEntityList(List<EquipementEntity> equipementEntityList) {
        this.equipementEntityList = equipementEntityList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (equipementTypeCode != null ? equipementTypeCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EquipementtypeEntity)) {
            return false;
        }
        EquipementtypeEntity other = (EquipementtypeEntity) object;
        if ((this.equipementTypeCode == null && other.equipementTypeCode != null) || (this.equipementTypeCode != null && !this.equipementTypeCode.equals(other.equipementTypeCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.EquipementtypeEntity[ equipementTypeCode=" + equipementTypeCode + " ]";
    }
    
}
