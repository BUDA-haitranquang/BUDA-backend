package com.higroup.Buda.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.repositories.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PictureService {
    private final PictureRepository pictureRepository;
    
    @Autowired
    public PictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }

    public Picture findPictureByPictureID(Long pictureID)
    {
        Optional<Picture> picture = this.pictureRepository.findPictureByPictureID(pictureID);
        return picture.orElse(null);
    }
    @Transactional
    public void deletePicture(Long pictureID)
    {
        Optional<Picture> picture = this.pictureRepository.findPictureByPictureID(pictureID);
        if (picture.isPresent())
        {
            this.pictureRepository.delete(picture.get());
        }
        else throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Picture does not exist");
    }
    @Transactional
    public Picture saveNewPicture(Picture picture)
    {
        if (picture.getPictureLink() != null)
        {
            this.pictureRepository.save(picture);
            return picture;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Link can not be null");
    }
    @Transactional
    public Picture updatePicture(Picture picture)
    {
        if ((picture.getPictureID()!=null) && (picture.getPictureLink()!=null))
        {
            this.pictureRepository.save(picture);
            return picture;
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Picture does not exist");
    }
}
