package sessions;

import entities.EquipementtypeEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class EquipementtypeEntityFacade extends AbstractFacade<EquipementtypeEntity> {

    @PersistenceContext(unitName = "Jee_ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EquipementtypeEntityFacade() {
        super(EquipementtypeEntity.class);
    }
    
}
