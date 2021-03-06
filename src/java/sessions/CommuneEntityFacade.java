package sessions;

import entities.CommuneEntity;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class CommuneEntityFacade extends AbstractFacade<CommuneEntity> {

    @PersistenceContext(unitName = "Jee_ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CommuneEntityFacade() {
        super(CommuneEntity.class);
    }

    public Map<String, String> getNamesCommuneDept(String depLib) {
        String queryString = "SELECT c.* FROM Commune c NATURAL JOIN Departement d"
                + " WHERE d.depLib = '" + depLib + "'";
        Query query = em.createNativeQuery(queryString, CommuneEntity.class);

        List<CommuneEntity> communes = query.getResultList();

        Map<String, String> maps = new LinkedHashMap<>();
        communes.forEach(c -> maps.put(c.getComLib(), c.getComInsee()));
        return maps;
    }

    public String getComInsee(String cityLib, String depLib) {
        String queryCityLib = "SELECT c.* FROM Commune c NATURAL JOIN Departement d WHERE d.depLib = '" + depLib + "' AND c.comLib = '" + cityLib + "'";
        Query query = em.createNativeQuery(queryCityLib, CommuneEntity.class);
        List<CommuneEntity> communeList = query.getResultList();
        return communeList.get(0).getComInsee();
    }
}
