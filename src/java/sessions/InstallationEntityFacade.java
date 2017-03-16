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
    
    public List<InstallationEntity> search(String tags, String departementLib, String cityLib){
        String queryString = "SELECT i.* FROM Installation i "
                             +"NATURAL JOIN Commune c NATURAL JOIN Departement d "
                             +"WHERE c.ComInsee = '"+cityLib+"'"; 
        if(!(cityLib == null)){
            if(!("".equals(tags))){
                queryString+=" AND "+tags;
            }
        } else{
            queryString = "SELECT i.* FROM Installation i "
                                           +"NATURAL JOIN Commune c NATURAL JOIN Departement d "
                                           +"WHERE d.deplib = '" + departementLib+"'";
        
        
            if(!("".equals(tags))){
                queryString+=" AND "+tags;
            }
        }
        System.out.println(queryString);
        Query query = em.createNativeQuery(queryString,InstallationEntity.class);
        
        List<InstallationEntity> array = query.getResultList();            
        return array;                
    }
}
