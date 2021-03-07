package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.services.PhotoServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class PhotoController {
    private static final String GET_PHOTO = "photo/{photoId}";
    private static final String ADD_PHOTO_USER = "user-photo/{userId}";
    private static final String PHOTO_WORKPLACE = "workplace-photo/{workplaceId}";


    private final PhotoServiceImpl photoService;

    public PhotoController(PhotoServiceImpl photoService) {
        this.photoService = photoService;
    }

    @GetMapping(value = GET_PHOTO, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getPhoto(@PathVariable Long photoId) {
        return ResponseEntity.ok(photoService.getPhoto(photoId));
    }

    @PutMapping(ADD_PHOTO_USER)
    public void addUserPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long userId) throws IOException {
        photoService.saveUserPhoto(file.getBytes(), userId);
    }

    @PutMapping(PHOTO_WORKPLACE)
    public void addWorkplacePhoto(@RequestParam("file") MultipartFile file, @PathVariable Long workplaceId) throws IOException {
        photoService.saveWorkplacePhoto(file.getBytes(), workplaceId);
    }
}
