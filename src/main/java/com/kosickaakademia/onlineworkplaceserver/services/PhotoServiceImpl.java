package com.kosickaakademia.onlineworkplaceserver.services;

import com.kosickaakademia.onlineworkplaceserver.entities.PhotoEntity;
import com.kosickaakademia.onlineworkplaceserver.repositories.UserRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.PhotoRepository;
import com.kosickaakademia.onlineworkplaceserver.repositories.WorkplaceRepository;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;
    private final WorkplaceRepository workplaceRepository;
    private final UserRepository userRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository, WorkplaceRepository workplaceRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.workplaceRepository = workplaceRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveUserPhoto(byte[] photo, Long userId) {
        val userEntity = userRepository.getOne(userId);
        val entity = new PhotoEntity();
        entity.setPicture(photo);
        val savedPhoto = photoRepository.save(entity);
        userEntity.setPhoto(savedPhoto.getId());
        userRepository.save(userEntity);
    }

    @Override
    public void saveWorkplacePhoto(byte[] photo, Long workplaceId) {
        val workplace = workplaceRepository.getOne(workplaceId);
        val entity = new PhotoEntity();
        entity.setPicture(photo);
        val savedPhoto = photoRepository.save(entity);
        workplace.setPhoto(savedPhoto.getId());
        workplaceRepository.save(workplace);
    }

    @Override
    public byte[] getUserPhoto(Long userId) {
        val userEntity = userRepository.getOne(userId);
        return photoRepository.getOne(userEntity.getPhoto()).getPicture();
    }

    @Override
    public byte[] getWorkplacePhoto(Long workplaceID) {
        val workplace = workplaceRepository.getWorkplaceEntityById(workplaceID);
        return photoRepository.getOne(workplace.getPhoto()).getPicture();
    }
}
