package eu.glowacki.utp.assignment10.repositories;

import java.util.List;

import eu.glowacki.utp.assignment10.Task10Exception;
import eu.glowacki.utp.assignment10.dtos.GroupDTO;

public interface IGroupRepository extends IRepository<GroupDTO> {

	List<GroupDTO> findByName(String name) throws Task10Exception;
}