/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.RequestEntity;
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
        String queryString = "SELECT r.* FROM Request r";
        Query query = em.createNativeQuery(queryString, RequestEntity.class);
        int size = query.getResultList().size();
        RequestEntity tmp = new RequestEntity(size, request.getDepLib());
        tmp.setComInsee(request.getComInsee());
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

}
