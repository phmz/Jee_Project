package sessions;

import entities.NotecommentEntity;
import entities.NotecommentEntityPK;
import entities.UserEntity;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public boolean rate(String email, String insNumeroInstall, String comment, String rating) {
        NotecommentEntity tmp = em.find(NotecommentEntity.class, new NotecommentEntityPK(email, insNumeroInstall));
        NotecommentEntity note = new NotecommentEntity(email, insNumeroInstall);
        note.setComment(comment);
        note.setNote(Integer.parseInt(rating));
        if (tmp == null) {
            em.persist(note);
        } else {
            em.merge(note);
        }
        return true;
    }

    public List<NotecommentEntity> searchUserRatings(UserEntity user) {
        String queryString = "SELECT n.* FROM Notecomment n WHERE n.email = '" + user.getEmail() + "' ORDER BY n.datecomment DESC";
        Query query = em.createNativeQuery(queryString, NotecommentEntity.class);
        return query.getResultList();
    }
}
