package eu.glowacki.utp.assignment10.repositories.test;

import eu.glowacki.utp.assignment10.Task10Exception;
import eu.glowacki.utp.assignment10.repositories.GroupRepository;
import org.junit.Assert;
import org.junit.Test;

import eu.glowacki.utp.assignment10.UnimplementedException;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;
import eu.glowacki.utp.assignment10.repositories.IGroupRepository;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

public class GroupRepositoryTest extends RepositoryTestBase<GroupDTO, IGroupRepository, GroupRepository> {

	public GroupRepositoryTest() throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		super(GroupRepository.class);
	}

	@Test
	public void add() throws Task10Exception {
		IGroupRepository repository = repository();
		int count = repository.getCount();
		GroupDTO group = new GroupDTO(0, "name1", "description1");
		repository.add(group);
		int expectedCount = count + 1;
		int actualCount = repository.getCount();
		Assert.assertEquals(expectedCount, actualCount);
	}

	@Test
	public void update() throws Task10Exception {
		IGroupRepository repository = repository();
		String originalName = "original name1";
		GroupDTO group = new GroupDTO(0, originalName,  "original description");
		repository.add(group);
		List<GroupDTO> groupOriginalList = repository.findByName(originalName);
		Assert.assertNotNull(groupOriginalList);
		Assert.assertEquals(1, groupOriginalList.size());
		GroupDTO originalGroup  = groupOriginalList.get(0);
		Assert.assertNotNull(originalGroup);
		Assert.assertEquals(originalName, originalGroup.getName());
		String newName = "new name1";
		originalGroup.setName(newName);
		repository.update(originalGroup);
		List<GroupDTO> groupModifiedList = repository.findByName(newName);
		Assert.assertNotNull(groupModifiedList);
		Assert.assertEquals(1, groupModifiedList.size());
		GroupDTO modifiedGroup = groupModifiedList.get(0);
		Assert.assertNotNull(modifiedGroup);
		Assert.assertEquals(newName, modifiedGroup.getName());
		Assert.assertEquals(originalGroup.getId(), modifiedGroup.getId());
	}
	
	@Test
	public void addOrUpdate()  throws Task10Exception {
		IGroupRepository repository = repository();
		int count = repository.getCount();
		GroupDTO group = new GroupDTO(0, "name1", "description1");
		//GroupDTO group = new GroupDTO(2, "name2", "description2");
		if(repository().exists(group)){
			repository.update(group);
			int expectedCount = count;
			int actualCount = repository.getCount();
			Assert.assertEquals(expectedCount, actualCount);
		}else{
			repository.add(group);
			int expectedCount = count + 1;
			int actualCount = repository.getCount();
			Assert.assertEquals(expectedCount, actualCount);
		}
	}

	@Test
	public void delete() throws Task10Exception {
//		IGroupRepository repository = repository();
//		int count = repository.getCount();
//		GroupDTO group = new GroupDTO(0, "name1", "description1");
//		repository.add(group);
//		int expectedCount = count + 1;
//		int actualCount = repository.getCount();
//		Assert.assertEquals(expectedCount, actualCount);
//		repository.delete(group);
//		int newExpectedCount = actualCount - 1;
//		int newActualCount = repository.getCount();
//		Assert.assertEquals(newExpectedCount, newActualCount);
	}

	@Test
	public void findById() throws Task10Exception {
		IGroupRepository repository = repository();
		String originalName = "random name";
		GroupDTO group = new GroupDTO(1, originalName,  "random description");
		repository.add(group);
		List<GroupDTO> groupOriginalList = repository.findByName(originalName);
		groupOriginalList.add(new GroupDTO(repository.findById(1)));
		GroupDTO roflan = repository.findById(repository.findByName(originalName).get(0).getId());
		Assert.assertNotNull(groupOriginalList);
		Assert.assertEquals("random name", group.getName());
		GroupDTO fakeGroup = repository.findById(345);
		Assert.assertNull(fakeGroup);
	}
	
	@Test
	public void findByName() throws Task10Exception  {
		IGroupRepository repository = repository();
		String originalName = "original name1";
		String fakeName = "fake name";
		GroupDTO group = new GroupDTO(0, originalName,  "original description");
		repository.add(group);
		List<GroupDTO> groupOriginalList = repository.findByName(originalName);
		Assert.assertNotNull(groupOriginalList);
		Assert.assertEquals(1, groupOriginalList.size());
		List<GroupDTO> fakeGroup = repository.findByName(fakeName);
		Assert.assertNotNull(fakeGroup);
		Assert.assertEquals(0, fakeGroup.size());
	}
}