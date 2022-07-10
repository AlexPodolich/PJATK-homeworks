package eu.glowacki.utp.assignment10.repositories;

import java.sql.Connection;

import eu.glowacki.utp.assignment10.Task10Exception;
import eu.glowacki.utp.assignment10.dtos.DTOBase;

public interface IRepository<TDTO extends DTOBase> {

	Connection getConnection();

	void add(TDTO dto) throws Task10Exception;

	void update(TDTO dto) throws Task10Exception;
	
	void addOrUpdate(TDTO dto) throws Task10Exception;

	void delete(TDTO dto) throws Task10Exception;

	TDTO findById(int id) throws Task10Exception;

	void beginTransaction();

	void commitTransaction();

	void rollbackTransaction();
	
	int getCount();

	boolean exists(TDTO dto) throws Task10Exception;
}