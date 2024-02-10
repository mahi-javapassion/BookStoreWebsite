import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.bookstore.entity.User;

public class UserTest {

	public static void main(String[] args) {
		User user1 = new User();
		user1.setEmail("mahesh@java.net");
		user1.setFullName("Mahesh Yerudkar");
		user1.setPassword("password");
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("BookStoreWebsite");
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		entityManager.getTransaction().begin();
		entityManager.persist(user1);
		entityManager.getTransaction().commit();
		
		
		entityManager.close();
		entityManagerFactory.close();
		
		System.out.println("User mahesh is persisted to backend");
	}

}
