package com.kosickaakademia.onlineworkplaceserver.security;

import com.kosickaakademia.onlineworkplaceserver.repositories.UserRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.UserRightsRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.WorkplaceRepository;
import lombok.val;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.stream.Collectors;

@Service
public class SecurityService {
    private final UserRightsRepository userRightsRepository;
    private final UserRepository userRepository;

    public SecurityService(UserRightsRepository userRightsRepository, UserRepository userRepository) {
        this.userRightsRepository = userRightsRepository;
        this.userRepository = userRepository;
    }

    public boolean checkUserRightsToAdd(Long userId, Long workplaceId) {
        val rights = userRightsRepository.getUserRightsEntityByWorkplaceEntityIdAndUserEntityId(workplaceId, userId);
        return rights.isAddToWorkplace();
    }

    public boolean isUserInWorkplace(Long workplaceId) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        val userWorkplace = userRepository.findUserEntityById(Long.parseLong(principal.getName())).getUserWorkplaces()
                .stream()
                .filter(workplaceEntity -> workplaceEntity.getId().equals(workplaceId))
                .collect(Collectors.toList());

        return userWorkplace.size() != 0;
    }

    public boolean isUserSameAsRequested(Long requestedUserId) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return requestedUserId == Long.parseLong(principal.getName());
    }

    public boolean checkUserRightToArchive(Long workplaceId) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        val rights = userRightsRepository
                .getUserRightsEntityByWorkplaceEntityIdAndUserEntityId(workplaceId, Long.parseLong(principal.getName()));
        return rights.isArchiveElement();
    }

    public boolean checkUserRightToChangeRights(Long workplaceId) {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        val rights = userRightsRepository
                .getUserRightsEntityByWorkplaceEntityIdAndUserEntityId(workplaceId, Long.parseLong(principal.getName()));
        return rights.isChangeRights();
    }
}
