/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.CommuneEntity;
import entities.RequestEntity;
import entities.UserEntity;
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
public class RequestEntityFacade extends AbstractFacade<RequestEntity> {

    @PersistenceContext(unitName = "Jee_ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RequestEntityFacade() {
        super(RequestEntity.class);
    }

    public boolean addRequest(RequestEntity request) {
        Query query;
        RequestEntity tmp = new RequestEntity(0, request.getDepLib());
        if(request.getComInsee() == null || request.getComInsee().isEmpty()) {
            tmp.setComInsee("");
        } else {
            String queryCityLib = "SELECT c.* FROM Commune c WHERE c.comInsee = '"+request.getComInsee()+"'";
            query = em.createNativeQuery(queryCityLib, CommuneEntity.class);
            List<CommuneEntity> communeList = query.getResultList();
            tmp.setComInsee(communeList.get(0).getComLib());
        }
        tmp.setTagsList(request.getTagsList());
        tmp.setUser(request.getUser());
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT r.* FROM Request r WHERE r.depLib='").append(tmp.getDepLib()).append("'");
        if (!tmp.getComInsee().isEmpty()) {
            sb.append(" AND r.comInsee='").append(tmp.getComInsee()).append("'");
        }
        if (!tmp.getTagsList().isEmpty()) {
            sb.append(" AND r.tagsList='").append(tmp.getTagsList()).append("'");
        }
        sb.append(" AND r.email='").append(tmp.getUser().getEmail()).append("'");
        query = em.createNativeQuery(sb.toString(), RequestEntity.class);
        boolean isRequestNonExistent = query.getResultList().isEmpty();
        if (isRequestNonExistent) {
            em.persist(tmp);
            return true;
        }
        return false;
    }

    public List<RequestEntity> searchUserHistory(UserEntity user) {
        String queryString = "SELECT r.* FROM Request r WHERE r.email = '" + user.getEmail() + "' ORDER BY r.daterequest DESC";
        Query query = em.createNativeQuery(queryString, RequestEntity.class);
        return query.getResultList();
    }

    public void deleteRequest(int id) {
        RequestEntity request = em.find(RequestEntity.class, id);
        if(request != null) {
            em.remove(request);
        }
    }

}
