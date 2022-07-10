package eu.glowacki.utp.assignment10.repositories.test;

import org.junit.After;
import org.junit.Before;

import eu.glowacki.utp.assignment10.dtos.DTOBase;
import eu.glowacki.utp.assignment10.repositories.IRepository;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class RepositoryTestBase<TDTO extends DTOBase, TRepository extends IRepository<TDTO>, TRepositoryImpl extends TRepository> {

	private static final String URI = "jdbc:postgresql://localhost:5432/postgres";
	private static final String USERNAME = "postgres";
	private static final String PASSWORD = "postgres";

	private static Connection connection() throws SQLException {
		return DriverManager.getConnection(URI, USERNAME, PASSWORD);
	}

	private final Constructor<TRepositoryImpl> _constructor;
	private final TRepository _repository;

	protected RepositoryTestBase(Class<TRepositoryImpl> type) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
		_constructor = type.getConstructor(Connection.class);
		_constructor.setAccessible(true);
		_repository = _constructor.newInstance(connection());
	}

	protected TRepository repository(){
		return _repository;
	}

	@Before
	public void before() {
		if (_repository != null) {
			_repository.beginTransaction();
		}
	}

	@After
	public void after() {
		if (_repository != null) {
			_repository.rollbackTransaction();
		}
	}

	private void beginTransaction(){
		TRepository repository = repository();
		repository.beginTransaction();
	}

	private void rollbackTransaction(){
		TRepository repository = repository();
		repository.rollbackTransaction();
	}

	private void commitTransaction(){
		TRepository repository = repository();
		repository.commitTransaction();
	}
}