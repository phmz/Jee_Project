/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.InstallationEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author phm
 */
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
}
