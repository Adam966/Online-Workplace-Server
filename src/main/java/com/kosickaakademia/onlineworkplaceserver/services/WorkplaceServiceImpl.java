package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.UserEntity;
import com.kosickaakademia.onlineworkplaceserver.entities.WorkplaceEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.UserRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.WorkplaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void addWorkplace(WorkplaceEntity workplaceEntity) {
        workplaceRepository.save(workplaceEntity);
    }

    @Override
    public void deleteWorkplace() {

    }

    @Override
    public void addUserToWorkplace(Long userId, Long workplaceId) {
        UserEntity user = userRepository.findUserEntityById(userId);
        WorkplaceEntity workplace = workplaceRepository.getWorkplaceEntityById(workplaceId);
        user.getUserWorkplaces().add(workplace);
        userRepository.save(user);
    }
}
