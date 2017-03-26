package sessions;

import entities.DepartementEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class DepartementEntityFacade extends AbstractFacade<DepartementEntity> {

    @PersistenceContext(unitName = "Jee_ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DepartementEntityFacade() {
        super(DepartementEntity.class);
    }

    public List<String> getAllNamesDepartement() {
        Query query = em.createNamedQuery("DepartementEntity.findAll", DepartementEntity.class);
        List<DepartementEntity> list = query.getResultList();
        ArrayList<String> array = new ArrayList<>();
        list.forEach((d) -> {
            array.add(d.getDepLib());
        });
        array.sort((d1, d2) -> {
            int result = String.CASE_INSENSITIVE_ORDER.compare(d1, d2);
            if (result == 0) {
                result = d1.compareTo(d2);
            }
            return result;
        });
        return array;
    }

}
