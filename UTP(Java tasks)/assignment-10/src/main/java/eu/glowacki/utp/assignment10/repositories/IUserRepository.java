package eu.glowacki.utp.assignment10.repositories;

import java.util.List;

import eu.glowacki.utp.assignment10.Task10Exception;
import eu.glowacki.utp.assignment10.dtos.UserDTO;

public interface IUserRepository extends IRepository<UserDTO> {

	List<UserDTO> findByLogin(String username) throws Task10Exception;
}