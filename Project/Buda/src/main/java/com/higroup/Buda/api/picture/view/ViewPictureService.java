package com.higroup.Buda.api.picture.view;

import java.util.Optional;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.repositories.PictureRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ViewPictureService {
    private final PictureRepository pictureRepository;
    @Autowired
    public ViewPictureService(PictureRepository pictureRepository){
        this.pictureRepository = pictureRepository;
    }
    public Picture findPictureByPictureID(Long userID, Long pictureID)
    {
        Optional<Picture> picture = this.pictureRepository.findPictureByPictureID(pictureID);
        if ((picture.isPresent()) && ((picture.get().getUserID().equals(userID)) || (picture.get().getUserID() == null)))
        {
            return picture.get();
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Picture not found");
    }
}
