package sessions;

import entities.EquipementEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class EquipementEntityFacade extends AbstractFacade<EquipementEntity> {

    @PersistenceContext(unitName = "Jee_ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipementEntityFacade() {
        super(EquipementEntity.class);
    }

    public List<EquipementEntity> getEquipementsByInsNumeroInstall(String insNumeroInstall) {
        String queryString = "SELECT e.* FROM Equipement e WHERE e.insNumeroInstall = '" + insNumeroInstall + "'";
        Query query = em.createNativeQuery(queryString, EquipementEntity.class);
        List<EquipementEntity> array = query.getResultList();

        return array;
    }

}
