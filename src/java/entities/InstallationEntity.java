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
@Table(name = "installation")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "InstallationEntity.findAll", query = "SELECT i FROM InstallationEntity i")
    , @NamedQuery(name = "InstallationEntity.findByInsNumeroInstall", query = "SELECT i FROM InstallationEntity i WHERE i.insNumeroInstall = :insNumeroInstall")
    , @NamedQuery(name = "InstallationEntity.findByInsNom", query = "SELECT i FROM InstallationEntity i WHERE i.insNom = :insNom")
    , @NamedQuery(name = "InstallationEntity.findByInsNoVoie", query = "SELECT i FROM InstallationEntity i WHERE i.insNoVoie = :insNoVoie")
    , @NamedQuery(name = "InstallationEntity.findByInsLibelleVoie", query = "SELECT i FROM InstallationEntity i WHERE i.insLibelleVoie = :insLibelleVoie")
    , @NamedQuery(name = "InstallationEntity.findByInsLieuDit", query = "SELECT i FROM InstallationEntity i WHERE i.insLieuDit = :insLieuDit")
    , @NamedQuery(name = "InstallationEntity.findByInsCodePostal", query = "SELECT i FROM InstallationEntity i WHERE i.insCodePostal = :insCodePostal")
    , @NamedQuery(name = "InstallationEntity.findByInsPartLibelle", query = "SELECT i FROM InstallationEntity i WHERE i.insPartLibelle = :insPartLibelle")
    , @NamedQuery(name = "InstallationEntity.findByInsMultiCommune", query = "SELECT i FROM InstallationEntity i WHERE i.insMultiCommune = :insMultiCommune")
    , @NamedQuery(name = "InstallationEntity.findByInsNbCouvert", query = "SELECT i FROM InstallationEntity i WHERE i.insNbCouvert = :insNbCouvert")
    , @NamedQuery(name = "InstallationEntity.findByInsNbLit", query = "SELECT i FROM InstallationEntity i WHERE i.insNbLit = :insNbLit")
    , @NamedQuery(name = "InstallationEntity.findByInsInternat", query = "SELECT i FROM InstallationEntity i WHERE i.insInternat = :insInternat")
    , @NamedQuery(name = "InstallationEntity.findByInsAccessibiliteAucun", query = "SELECT i FROM InstallationEntity i WHERE i.insAccessibiliteAucun = :insAccessibiliteAucun")
    , @NamedQuery(name = "InstallationEntity.findByInsAccessibiliteHandiMoteur", query = "SELECT i FROM InstallationEntity i WHERE i.insAccessibiliteHandiMoteur = :insAccessibiliteHandiMoteur")
    , @NamedQuery(name = "InstallationEntity.findByInsAccessibiliteHandiSens", query = "SELECT i FROM InstallationEntity i WHERE i.insAccessibiliteHandiSens = :insAccessibiliteHandiSens")
    , @NamedQuery(name = "InstallationEntity.findByInsNbPlaceParking", query = "SELECT i FROM InstallationEntity i WHERE i.insNbPlaceParking = :insNbPlaceParking")
    , @NamedQuery(name = "InstallationEntity.findByInsNbPlaceParkingHandi", query = "SELECT i FROM InstallationEntity i WHERE i.insNbPlaceParkingHandi = :insNbPlaceParkingHandi")
    , @NamedQuery(name = "InstallationEntity.findByInsGardiennee", query = "SELECT i FROM InstallationEntity i WHERE i.insGardiennee = :insGardiennee")
    , @NamedQuery(name = "InstallationEntity.findByInsEmpriseFonciere", query = "SELECT i FROM InstallationEntity i WHERE i.insEmpriseFonciere = :insEmpriseFonciere")
    , @NamedQuery(name = "InstallationEntity.findByInsTransportMetro", query = "SELECT i FROM InstallationEntity i WHERE i.insTransportMetro = :insTransportMetro")
    , @NamedQuery(name = "InstallationEntity.findByInsTransportBus", query = "SELECT i FROM InstallationEntity i WHERE i.insTransportBus = :insTransportBus")
    , @NamedQuery(name = "InstallationEntity.findByInsTransportTram", query = "SELECT i FROM InstallationEntity i WHERE i.insTransportTram = :insTransportTram")
    , @NamedQuery(name = "InstallationEntity.findByInsTransportTrain", query = "SELECT i FROM InstallationEntity i WHERE i.insTransportTrain = :insTransportTrain")
    , @NamedQuery(name = "InstallationEntity.findByInsTransportBateau", query = "SELECT i FROM InstallationEntity i WHERE i.insTransportBateau = :insTransportBateau")
    , @NamedQuery(name = "InstallationEntity.findByInsTransportAutre", query = "SELECT i FROM InstallationEntity i WHERE i.insTransportAutre = :insTransportAutre")
    , @NamedQuery(name = "InstallationEntity.findByInsDateCreation", query = "SELECT i FROM InstallationEntity i WHERE i.insDateCreation = :insDateCreation")
    , @NamedQuery(name = "InstallationEntity.findByInsDateMaj", query = "SELECT i FROM InstallationEntity i WHERE i.insDateMaj = :insDateMaj")
    , @NamedQuery(name = "InstallationEntity.findByNbFicheEquipement", query = "SELECT i FROM InstallationEntity i WHERE i.nbFicheEquipement = :nbFicheEquipement")
    , @NamedQuery(name = "InstallationEntity.findByNbEquipements", query = "SELECT i FROM InstallationEntity i WHERE i.nbEquipements = :nbEquipements")})
public class InstallationEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 9)
    @Column(name = "InsNumeroInstall")
    private String insNumeroInstall;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 131)
    @Column(name = "InsNom")
    private String insNom;
    @Size(max = 10)
    @Column(name = "InsNoVoie")
    private String insNoVoie;
    @Size(max = 100)
    @Column(name = "InsLibelleVoie")
    private String insLibelleVoie;
    @Size(max = 99)
    @Column(name = "InsLieuDit")
    private String insLieuDit;
    @Column(name = "InsCodePostal")
    private Integer insCodePostal;
    @Size(max = 34)
    @Column(name = "InsPartLibelle")
    private String insPartLibelle;
    @Column(name = "InsMultiCommune")
    private Boolean insMultiCommune;
    @Column(name = "InsNbCouvert")
    private Integer insNbCouvert;
    @Column(name = "InsNbLit")
    private Integer insNbLit;
    @Column(name = "InsInternat")
    private Boolean insInternat;
    @Basic(optional = false)
    @NotNull
    @Column(name = "InsAccessibiliteAucun")
    private boolean insAccessibiliteAucun;
    @Basic(optional = false)
    @NotNull
    @Column(name = "InsAccessibiliteHandiMoteur")
    private boolean insAccessibiliteHandiMoteur;
    @Basic(optional = false)
    @NotNull
    @Column(name = "InsAccessibiliteHandiSens")
    private boolean insAccessibiliteHandiSens;
    @Column(name = "InsNbPlaceParking")
    private Integer insNbPlaceParking;
    @Column(name = "InsNbPlaceParkingHandi")
    private Integer insNbPlaceParkingHandi;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 24)
    @Column(name = "InsGardiennee")
    private String insGardiennee;
    @Column(name = "InsEmpriseFonciere")
    private Integer insEmpriseFonciere;
    @Basic(optional = false)
    @NotNull
    @Column(name = "InsTransportMetro")
    private boolean insTransportMetro;
    @Column(name = "InsTransportBus")
    private Boolean insTransportBus;
    @Column(name = "InsTransportTram")
    private Boolean insTransportTram;
    @Column(name = "InsTransportTrain")
    private Boolean insTransportTrain;
    @Column(name = "InsTransportBateau")
    private Boolean insTransportBateau;
    @Column(name = "InsTransportAutre")
    private Boolean insTransportAutre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 23)
    @Column(name = "InsDateCreation")
    private String insDateCreation;
    @Size(max = 23)
    @Column(name = "InsDateMaj")
    private String insDateMaj;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nb_FicheEquipement")
    private int nbFicheEquipement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Nb_Equipements")
    private int nbEquipements;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "insNumeroInstall")
    private List<EquipementEntity> equipementEntityList;
    @JoinColumn(name = "ComInsee", referencedColumnName = "ComInsee")
    @ManyToOne(optional = false)
    private CommuneEntity comInsee;

    public InstallationEntity() {
    }

    public InstallationEntity(String insNumeroInstall) {
        this.insNumeroInstall = insNumeroInstall;
    }

    public InstallationEntity(String insNumeroInstall, String insNom, boolean insAccessibiliteAucun, boolean insAccessibiliteHandiMoteur, boolean insAccessibiliteHandiSens, String insGardiennee, boolean insTransportMetro, String insDateCreation, int nbFicheEquipement, int nbEquipements) {
        this.insNumeroInstall = insNumeroInstall;
        this.insNom = insNom;
        this.insAccessibiliteAucun = insAccessibiliteAucun;
        this.insAccessibiliteHandiMoteur = insAccessibiliteHandiMoteur;
        this.insAccessibiliteHandiSens = insAccessibiliteHandiSens;
        this.insGardiennee = insGardiennee;
        this.insTransportMetro = insTransportMetro;
        this.insDateCreation = insDateCreation;
        this.nbFicheEquipement = nbFicheEquipement;
        this.nbEquipements = nbEquipements;
    }

    public String getInsNumeroInstall() {
        return insNumeroInstall;
    }

    public void setInsNumeroInstall(String insNumeroInstall) {
        this.insNumeroInstall = insNumeroInstall;
    }

    public String getInsNom() {
        return insNom;
    }

    public void setInsNom(String insNom) {
        this.insNom = insNom;
    }

    public String getInsNoVoie() {
        return insNoVoie;
    }

    public void setInsNoVoie(String insNoVoie) {
        this.insNoVoie = insNoVoie;
    }

    public String getInsLibelleVoie() {
        return insLibelleVoie;
    }

    public void setInsLibelleVoie(String insLibelleVoie) {
        this.insLibelleVoie = insLibelleVoie;
    }

    public String getInsLieuDit() {
        return insLieuDit;
    }

    public void setInsLieuDit(String insLieuDit) {
        this.insLieuDit = insLieuDit;
    }

    public Integer getInsCodePostal() {
        return insCodePostal;
    }

    public void setInsCodePostal(Integer insCodePostal) {
        this.insCodePostal = insCodePostal;
    }

    public String getInsPartLibelle() {
        return insPartLibelle;
    }

    public void setInsPartLibelle(String insPartLibelle) {
        this.insPartLibelle = insPartLibelle;
    }

    public Boolean getInsMultiCommune() {
        return insMultiCommune;
    }

    public void setInsMultiCommune(Boolean insMultiCommune) {
        this.insMultiCommune = insMultiCommune;
    }

    public Integer getInsNbCouvert() {
        return insNbCouvert;
    }

    public void setInsNbCouvert(Integer insNbCouvert) {
        this.insNbCouvert = insNbCouvert;
    }

    public Integer getInsNbLit() {
        return insNbLit;
    }

    public void setInsNbLit(Integer insNbLit) {
        this.insNbLit = insNbLit;
    }

    public Boolean getInsInternat() {
        return insInternat;
    }

    public void setInsInternat(Boolean insInternat) {
        this.insInternat = insInternat;
    }

    public boolean getInsAccessibiliteAucun() {
        return insAccessibiliteAucun;
    }

    public void setInsAccessibiliteAucun(boolean insAccessibiliteAucun) {
        this.insAccessibiliteAucun = insAccessibiliteAucun;
    }

    public boolean getInsAccessibiliteHandiMoteur() {
        return insAccessibiliteHandiMoteur;
    }

    public void setInsAccessibiliteHandiMoteur(boolean insAccessibiliteHandiMoteur) {
        this.insAccessibiliteHandiMoteur = insAccessibiliteHandiMoteur;
    }

    public boolean getInsAccessibiliteHandiSens() {
        return insAccessibiliteHandiSens;
    }

    public void setInsAccessibiliteHandiSens(boolean insAccessibiliteHandiSens) {
        this.insAccessibiliteHandiSens = insAccessibiliteHandiSens;
    }

    public Integer getInsNbPlaceParking() {
        return insNbPlaceParking;
    }

    public void setInsNbPlaceParking(Integer insNbPlaceParking) {
        this.insNbPlaceParking = insNbPlaceParking;
    }

    public Integer getInsNbPlaceParkingHandi() {
        return insNbPlaceParkingHandi;
    }

    public void setInsNbPlaceParkingHandi(Integer insNbPlaceParkingHandi) {
        this.insNbPlaceParkingHandi = insNbPlaceParkingHandi;
    }

    public String getInsGardiennee() {
        return insGardiennee;
    }

    public void setInsGardiennee(String insGardiennee) {
        this.insGardiennee = insGardiennee;
    }

    public Integer getInsEmpriseFonciere() {
        return insEmpriseFonciere;
    }

    public void setInsEmpriseFonciere(Integer insEmpriseFonciere) {
        this.insEmpriseFonciere = insEmpriseFonciere;
    }

    public boolean getInsTransportMetro() {
        return insTransportMetro;
    }

    public void setInsTransportMetro(boolean insTransportMetro) {
        this.insTransportMetro = insTransportMetro;
    }

    public Boolean getInsTransportBus() {
        return insTransportBus;
    }

    public void setInsTransportBus(Boolean insTransportBus) {
        this.insTransportBus = insTransportBus;
    }

    public Boolean getInsTransportTram() {
        return insTransportTram;
    }

    public void setInsTransportTram(Boolean insTransportTram) {
        this.insTransportTram = insTransportTram;
    }

    public Boolean getInsTransportTrain() {
        return insTransportTrain;
    }

    public void setInsTransportTrain(Boolean insTransportTrain) {
        this.insTransportTrain = insTransportTrain;
    }

    public Boolean getInsTransportBateau() {
        return insTransportBateau;
    }

    public void setInsTransportBateau(Boolean insTransportBateau) {
        this.insTransportBateau = insTransportBateau;
    }

    public Boolean getInsTransportAutre() {
        return insTransportAutre;
    }

    public void setInsTransportAutre(Boolean insTransportAutre) {
        this.insTransportAutre = insTransportAutre;
    }

    public String getInsDateCreation() {
        return insDateCreation;
    }

    public void setInsDateCreation(String insDateCreation) {
        this.insDateCreation = insDateCreation;
    }

    public String getInsDateMaj() {
        return insDateMaj;
    }

    public void setInsDateMaj(String insDateMaj) {
        this.insDateMaj = insDateMaj;
    }

    public int getNbFicheEquipement() {
        return nbFicheEquipement;
    }

    public void setNbFicheEquipement(int nbFicheEquipement) {
        this.nbFicheEquipement = nbFicheEquipement;
    }

    public int getNbEquipements() {
        return nbEquipements;
    }

    public void setNbEquipements(int nbEquipements) {
        this.nbEquipements = nbEquipements;
    }

    @XmlTransient
    public List<EquipementEntity> getEquipementEntityList() {
        return equipementEntityList;
    }

    public void setEquipementEntityList(List<EquipementEntity> equipementEntityList) {
        this.equipementEntityList = equipementEntityList;
    }

    public CommuneEntity getComInsee() {
        return comInsee;
    }

    public void setComInsee(CommuneEntity comInsee) {
        this.comInsee = comInsee;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insNumeroInstall != null ? insNumeroInstall.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InstallationEntity)) {
            return false;
        }
        InstallationEntity other = (InstallationEntity) object;
        if ((this.insNumeroInstall == null && other.insNumeroInstall != null) || (this.insNumeroInstall != null && !this.insNumeroInstall.equals(other.insNumeroInstall))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.InstallationEntity[ insNumeroInstall=" + insNumeroInstall + " ]";
    }

}
