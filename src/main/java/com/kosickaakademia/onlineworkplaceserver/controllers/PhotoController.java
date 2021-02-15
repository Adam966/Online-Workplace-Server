package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.services.PhotoServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
public class PhotoController {
    private static final String PHOTO_USER = "user-photo/{userId}";
    private static final String PHOTO_WORKPLACE = "workplace-photo/{workplaceId}";


    private final PhotoServiceImpl photoService;

    public PhotoController(PhotoServiceImpl photoService) {
        this.photoService = photoService;
    }

    @GetMapping(value = PHOTO_USER, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable Long userId) {
        return ResponseEntity.ok(photoService.getUserPhoto(userId));
    }

    @PutMapping(PHOTO_USER)
    public void addUserPhoto(@RequestParam("file") MultipartFile file, @PathVariable Long userId) throws IOException {
        photoService.saveUserPhoto(file.getBytes(), userId);
    }

    @GetMapping(value = PHOTO_WORKPLACE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getWorkplacePhoto(@PathVariable Long workplaceId) {
            return ResponseEntity.ok(photoService.getWorkplacePhoto(workplaceId));
    }

    @PutMapping(PHOTO_WORKPLACE)
    public void addWorkplacePhoto(@RequestParam("file") MultipartFile file, @PathVariable Long workplaceId) throws IOException {
        photoService.saveWorkplacePhoto(file.getBytes(), workplaceId);
    }
}
