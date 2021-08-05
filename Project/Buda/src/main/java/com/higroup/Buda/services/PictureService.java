package com.higroup.Buda.services;

import java.util.Optional;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.repositories.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PictureService {
    private PictureRepository pictureRepository;
    
    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture findPictureByPictureID(Long pictureID)
    {
        Optional<Picture> picture = this.pictureRepository.findPictureByPictureID(pictureID);
        if (picture.isPresent())
        {
            return picture.get();
        }
        return null;
    }

    public ResponseEntity<?> saveNewPicture(Picture picture)
    {
        if (!picture.getLink().equals(null))
        {
            this.pictureRepository.save(picture);
            return ResponseEntity.ok().body(picture.getLink());
        }
        return ResponseEntity.badRequest().body("Not found");
    }

}
