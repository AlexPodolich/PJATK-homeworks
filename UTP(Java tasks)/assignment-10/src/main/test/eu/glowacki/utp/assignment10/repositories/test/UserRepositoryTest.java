package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.Task10Exception;
import eu.glowacki.utp.assignment10.dtos.DTOBase;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.repositories.*;
import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.UserDTO;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryTest extends RepositoryTestBase<UserDTO, IUserRepository, UserRepository> {

	public UserRepositoryTest() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		super(UserRepository.class);
	}

	@Test
	public void add() throws Task10Exception {
		IUserRepository repository = repository();
		int count = repository.getCount();
		UserDTO user = new UserDTO(0, "login", "pass");
		repository.add(user);
		int expectedCount = count + 1;
		int actualCount = repository.getCount();
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void update() throws Task10Exception {
		IUserRepository repository = repository();
		String originalLogin = "original login1";
		UserDTO user = new UserDTO(0, originalLogin,  "original pass");
		repository.add(user);
		List<UserDTO> userOriginalList = repository.findByLogin(originalLogin);
		Assert.assertNotNull(userOriginalList);
		Assert.assertEquals(1, userOriginalList.size());
		UserDTO originalUser  = userOriginalList.get(0);
		Assert.assertNotNull(originalUser);
		Assert.assertEquals(originalLogin, originalUser.getLogin());
		String newLogin = "new login1";
		originalUser.setLogin(newLogin);
		repository.update(originalUser);
		List<UserDTO> userModifiedList = repository.findByLogin(newLogin);
		Assert.assertNotNull(userModifiedList);
		Assert.assertEquals(1, userModifiedList.size());
		UserDTO modifiedUser = userModifiedList.get(0);
		Assert.assertNotNull(modifiedUser);
		Assert.assertEquals(newLogin, modifiedUser.getLogin());
		Assert.assertEquals(originalUser.getId(), modifiedUser.getId());
	}
	
	@Test
	public void addOrUpdate() throws Task10Exception {
		IUserRepository repository = repository();
		int count = repository.getCount();
		UserDTO user = new UserDTO(0, "login", "pass");
		//UserDTO user = new UserDTO(22, "login22", "pass2");
		if(repository().exists(user)){
			repository.update(user);
			int expectedCount = count;
			int actualCount = repository.getCount();
			Assert.assertEquals(expectedCount, actualCount);
		}else{
			repository.add(user);
			int expectedCount = count + 1;
			int actualCount = repository.getCount();
			Assert.assertEquals(expectedCount, actualCount);
		}
	}

	@Test
	public void delete() throws Task10Exception {
//		IUserRepository repository = repository();
//		int count = repository.getCount();
//		UserDTO user = new UserDTO(0, "login1", "pass1");
//		repository.add(user);
//		int expectedCount = count + 1;
//		int actualCount = repository.getCount();
//		Assert.assertEquals(expectedCount, actualCount);
//		repository.delete(user);
//		int newExpectedCount = actualCount - 1;
//		int newActualCount = repository.getCount();
//		Assert.assertEquals(newExpectedCount, newActualCount);
	}

	@Test
	public void findById() throws Task10Exception {
		IUserRepository repository = repository();
		String originalLogin = "random login";
		UserDTO user = new UserDTO(1, originalLogin,  "random pass");
		repository.add(user);
		List<UserDTO> groupOriginalList = repository.findByLogin(originalLogin);
		groupOriginalList.add(new UserDTO(repository.findById(1)));
		UserDTO roflan = repository.findById(repository.findByLogin(originalLogin).get(0).getId());
		Assert.assertNotNull(groupOriginalList);
		Assert.assertEquals("random login", user.getLogin());
		UserDTO fakeUser = repository.findById(345);
		Assert.assertNull(fakeUser);
	}
	
	@Test
	public void findByName() throws Task10Exception {
		IUserRepository repository = repository();
		String originalLogin = "original login1";
		String fakeName = "fake name";
		UserDTO user = new UserDTO(0, originalLogin,  "original pass");
		repository.add(user);
		List<UserDTO> userOriginalList = repository.findByLogin(originalLogin);
		Assert.assertNotNull(userOriginalList);
		Assert.assertEquals(1, userOriginalList.size());
		List<UserDTO> fakeUser = repository.findByLogin(fakeName);
		Assert.assertNotNull(fakeUser);
		Assert.assertEquals(0, fakeUser.size());
	}
}