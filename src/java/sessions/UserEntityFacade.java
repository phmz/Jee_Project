/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sessions;

import entities.UserEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Olivier
 */
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
    
}
