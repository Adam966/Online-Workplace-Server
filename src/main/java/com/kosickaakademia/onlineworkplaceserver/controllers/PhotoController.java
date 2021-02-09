package com.kosickaakademia.onlineworkplaceserver.controllers;

import com.kosickaakademia.onlineworkplaceserver.services.PhotoServiceImpl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.IOException;

@RestController
public class PhotoController {
    private static final String PHOTO_USER = "user-photo";
    private static final String PHOTO_WORKPLACE = "workplace-photo";


    private final PhotoServiceImpl photoService;

    public PhotoController(PhotoServiceImpl photoService) {
        this.photoService = photoService;
    }

    @GetMapping(value = PHOTO_USER, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getUserPhoto(@PathParam("userId") Long userId) {
        return ResponseEntity.ok(photoService.getUserPhoto(userId));
    }

    @PutMapping(PHOTO_USER)
    public void addUserPhoto(@RequestBody MultipartFile file, @PathParam("userId") Long userId) throws IOException {
        photoService.saveUserPhoto(file.getBytes(), userId);
    }

    @GetMapping(value = PHOTO_WORKPLACE, produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getWorkplacePhoto(@PathParam("workplaceId") Long workplaceId) {
        return ResponseEntity.ok(photoService.getWorkplacePhoto(workplaceId));
    }

    @PutMapping(PHOTO_WORKPLACE)
    public void addWorkplacePhoto(MultipartFile file, @PathParam("workplaceId") Long workplaceId) throws IOException {
        photoService.saveWorkplacePhoto(file.getBytes(), workplaceId);
    }
}
