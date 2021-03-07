package com.kosickaakademia.onlineworkplaceserver.services;

public interface PhotoService {
    void saveUserPhoto(byte[] photo, Long userId);
    void saveWorkplacePhoto(byte[] photo, Long workplaceId);
    byte[] getPhoto(Long photoId);
}
