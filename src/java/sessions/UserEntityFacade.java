package sessions;

import entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserEntityFacade extends AbstractFacade<UserEntity> {

    @PersistenceContext(unitName = "Jee_ProjetPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UserEntityFacade() {
        super(UserEntity.class);
    }

    public boolean registerUser(UserEntity user) {
        UserEntity tmp = em.find(UserEntity.class, user.getEmail());
        if (tmp == null) {
            em.persist(user);
            return true;
        }
        return false;
    }

    public UserEntity findUser(UserEntity user) {
        return em.find(UserEntity.class, user.getEmail());
    }

}
