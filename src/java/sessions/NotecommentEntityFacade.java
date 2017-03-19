/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.NotecommentEntity;
import entities.NotecommentEntityPK;
import java.math.BigDecimal;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author phm
 */
@Stateless
public class NotecommentEntityFacade extends AbstractFacade<NotecommentEntity> {

    @PersistenceContext(unitName = "Jee_ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NotecommentEntityFacade() {
        super(NotecommentEntity.class);
    }

    public int getAvg(String insNumeroInstall) {
        String queryString = "SELECT AVG(n.note) FROM Notecomment n WHERE n.insNumeroInstall = '" + insNumeroInstall + "'";
        Query query = em.createNativeQuery(queryString);
        BigDecimal bd = (BigDecimal) query.getSingleResult();
        return bd == null ? 0 : bd.intValue();
    }

    public void rate(String email, String insNumeroInstall, String comment, String rating) {
        NotecommentEntity tmp = em.find(NotecommentEntity.class, new NotecommentEntityPK(email, insNumeroInstall));
        NotecommentEntity note = new NotecommentEntity(email, insNumeroInstall);
        note.setComment(comment);
        note.setNote(Integer.parseInt(rating));
        if (tmp == null) {
            em.persist(note);
        } else {
            em.merge(note);
        }
    }
}
