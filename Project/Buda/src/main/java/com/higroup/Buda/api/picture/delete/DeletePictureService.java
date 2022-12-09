package com.higroup.Buda.api.picture.delete;

import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.repositories.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DeletePictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    public DeletePictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    @Transactional
    public void deletePicture(Long userID, Long pictureID) {
        Optional<Picture> picture = this.pictureRepository.findPictureByPictureID(pictureID);
        if ((picture.isPresent()) && (picture.get().getUserID().equals(userID))) {
            this.pictureRepository.delete(picture.get());
        } else
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Picture with ID: " + pictureID.toString() + " does not exist");
    }
}
