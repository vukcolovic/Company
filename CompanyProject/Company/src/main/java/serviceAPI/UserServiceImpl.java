package serviceAPI;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import model.User;
import service.UserService;

@Stateless
@LocalBean
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager em;

	public UserServiceImpl() {
		super();
	}


	@Override
	public List<User> getAllUsers() {
		TypedQuery<User> query = em.createNamedQuery("User.getAll", User.class);
		return query.getResultList();
	}

	@Override
	public User getUserById(Integer id) {
		TypedQuery<User> query = em.createNamedQuery("User.getUserById", User.class)
				.setParameter("userId", id);
		return query.getSingleResult();
	}

	@Override
	public User saveUser(User newUser) {
		if(newUser.getId() != null) {
			newUser.setUpdated(new Date());
		} else {
			newUser.setCreated(new Date());
		}
		try {
		return em.merge(newUser);
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public User updateUser(User user) {
		return em.merge(user);
	}
	
	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}


	@Override
	public User getUserByUsernameAndPassword(String username, String password) {
		TypedQuery<User> query = em.createNamedQuery("User.getUserByUsernameAndPassword", User.class)
				.setParameter("username", username)
				.setParameter("password", password);
		try {
		return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}


	@Override
	public void deleteUser(Integer userId) {
		try {
			User userForDelete = getUserById(userId);
		em.remove(userForDelete);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
