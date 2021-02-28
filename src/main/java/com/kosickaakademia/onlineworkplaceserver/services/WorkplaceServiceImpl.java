package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.*;
import com.kosickaakademia.onlineworkplaceserver.dto.UserDTO;
import com.kosickaakademia.onlineworkplaceserver.exceptions.DuplicateUserException;
import com.kosickaakademia.onlineworkplaceserver.repositories.*;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WorkplaceServiceImpl implements WorkplaceService {
    private final WorkplaceRepository workplaceRepository;
    private final UserRepository userRepository;
    private final LabelRepository labelRepository;
    private final UserRightsRepository userRightsRepository;
    private final NotificationsRightsRepository notificationsRightsRepository;

    public WorkplaceServiceImpl(WorkplaceRepository workplaceRepository, UserRepository userRepository, LabelRepository labelRepository, UserRightsRepository userRightsRepository, NotificationsRightsRepository notificationsRightsRepository) {
        this.workplaceRepository = workplaceRepository;
        this.userRepository = userRepository;
        this.labelRepository = labelRepository;
        this.userRightsRepository = userRightsRepository;
        this.notificationsRightsRepository = notificationsRightsRepository;
    }

    @Override
    public List<WorkplaceEntity> getAllUserWorkplace(Long userId) {
        return userRepository.findUserEntityById(userId).getUserWorkplaces();
    }

    @Override
    public WorkplaceEntity addWorkplace(WorkplaceEntity workplaceEntity) {
        val workplace = workplaceRepository.save(workplaceEntity);
        addUserToWorkplace(workplaceEntity.getAdminId(), workplace.getId());
        return workplace;
    }

    @Override
    public void addUserToWorkplace(Long userId, Long workplaceId) throws DuplicateUserException {
        val user = userRepository.findUserEntityById(userId);
        val workplace = workplaceRepository.getWorkplaceEntityById(workplaceId);

        val userRights = createDefaultUserRights(userId, workplaceId);
        val notificationRights = createDefaultNotificationEntity(userId, workplaceId);

        if (workplace.getAdminId().equals(userId)) {
            userRights.setChangeRights(true);
        }

        notificationsRightsRepository.save(notificationRights);
        userRightsRepository.save(userRights);

        if (!user.getUserWorkplaces().contains(workplace)) {
            user.getUserWorkplaces().add(workplace);
            userRepository.save(user);
        } else {
            throw new DuplicateUserException();
        }
    }

    @Override
    public List<UserDTO> getAllUsers(Long workplaceId) {
        return workplaceRepository.getWorkplaceEntityById(workplaceId).getWorkplaceUsers()
                .stream()
                .map(u -> new UserDTO(u.getId(), u.getUserName(), u.getUserSurname(), u.getEmail(), u.getPhoto()))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long userId, Long workplaceId) {
        val user = userRepository.findUserEntityById(userId);
        user.getUserWorkplaces().removeIf(workplaceEntity -> workplaceEntity.getId().equals(workplaceId));
        userRepository.save(user);
    }

    @Override
    public List<LabelEntity> getAllLabels(Long workplaceId) {
        return workplaceRepository.getWorkplaceEntityById(workplaceId).getWorkplaceLabels();
    }

    @Override
    public LabelEntity addLabel(LabelEntity labelEntity, Long workplaceId) {
        val workplace = workplaceRepository.getWorkplaceEntityById(workplaceId);
        workplace.getWorkplaceLabels().add(labelEntity);
        labelEntity.setWorkplaceEntity(workplace);
        return labelRepository.save(labelEntity);
    }

    @Override
    public void deleteLabel(Long workplaceId, Long labelId) {
        val label = labelRepository.findLabelEntityById(labelId);
        label.setWorkplaceEntity(null);
        labelRepository.delete(label);
    }

    private UserRightsEntity createDefaultUserRights(Long userId, Long workplaceId) {
        val rights = new UserRightsEntity();

        rights.setUserEntity(setUser(userId));
        rights.setWorkplaceEntity(setWorkplace(workplaceId));
        rights.setAddToWorkplace(false);
        rights.setArchiveElement(true);
        rights.setChangeRights(false);
        rights.setRemoveFromWorkplace(false);

        return rights;
    }

    private NotificationRightsEntity createDefaultNotificationEntity(Long userId, Long workplaceId) {
        val rights = new NotificationRightsEntity();

        rights.setDueDate(true);
        rights.setAddedToElement(true);
        rights.setSentMessage(true);
        rights.setRemovedFromElement(true);
        rights.setUserEntity(setUser(userId));
        rights.setWorkplaceEntity(setWorkplace(workplaceId));

        return rights;
    }

    private UserEntity setUser(Long userId) {
        val user = new UserEntity();
        user.setId(userId);
        return user;
    }

    private WorkplaceEntity setWorkplace(Long workplaceId) {
        val workplace = new WorkplaceEntity();
        workplace.setId(workplaceId);
        return workplace;
    }
}
