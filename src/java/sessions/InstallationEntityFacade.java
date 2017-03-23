package sessions;

import entities.InstallationEntity;
import entities.NotecommentEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class InstallationEntityFacade extends AbstractFacade<InstallationEntity> {

    @PersistenceContext(unitName = "Jee_ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public InstallationEntityFacade() {
        super(InstallationEntity.class);
    }
    
    public List<InstallationEntity> search(String tags, String departementLib, String comInsee){
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT i.* FROM Installation i NATURAL JOIN Commune c NATURAL JOIN Departement d WHERE d.deplib = '").append(departementLib).append("'");
        if (comInsee != null && !comInsee.isEmpty()) {
            sb.append(" AND c.comInsee = '").append(comInsee).append("'");
        }
        if(tags != null && !tags.isEmpty()) {
            sb.append(" AND ").append(tags);
        }
        System.out.println("INSTALLATION QUERY: "+sb.toString());
        Query query = em.createNativeQuery(sb.toString(),InstallationEntity.class);
        List<InstallationEntity> array = query.getResultList();            
        return array;                
    }

    public List<NotecommentEntity> getRatings(String insNumeroInstall) {
        String queryString = "SELECT n.* FROM noteComment n WHERE n.InsNumeroInstall = '"+insNumeroInstall+"'";
        System.out.println(queryString);
        Query query = em.createNativeQuery(queryString, NotecommentEntity.class);
        return query.getResultList();
    }
    
        public String getName(String insNumeroInstall) {
        String queryString = "SELECT i.InsNom FROM Installation i WHERE i.insNumeroInstall = '" + insNumeroInstall + "'";
        Query query = em.createNativeQuery(queryString);
        String name = (String) query.getSingleResult();
        return name;
    }
}
