package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.UserRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.WorkplaceRepository;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkplaceServiceImpl implements WorkplaceService {
    private final WorkplaceRepository workplaceRepository;
    private final UserRepository userRepository;

    public WorkplaceServiceImpl(WorkplaceRepository workplaceRepository, UserRepository userRepository) {
        this.workplaceRepository = workplaceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<WorkplaceEntity> getAllUserWorkplace(Long userId) {
        return userRepository.findUserEntityById(userId).getUserWorkplaces();
    }

    @Override
    public WorkplaceEntity addWorkplace(WorkplaceEntity workplaceEntity) {
        return workplaceRepository.save(workplaceEntity);
    }

    @Override
    public void addUserToWorkplace(Long userId, Long workplaceId) {
        val user = userRepository.findUserEntityById(userId);
        val workplace = workplaceRepository.getWorkplaceEntityById(workplaceId);
        user.getUserWorkplaces().add(workplace);
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> getAllUsers(Long workplaceId) {
        return workplaceRepository.getWorkplaceEntityById(workplaceId).getWorkplaceUsers()
                .stream()
                .map(u -> new UserDTO(u.getId(), u.getName(), u.getSurname(), u.getEmail()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId, Long workplaceId) {
        val user = userRepository.findUserEntityById(userId);
        user.getUserWorkplaces().removeIf(workplaceEntity -> workplaceEntity.getId().equals(workplaceId));
        userRepository.save(user);
    }
}
