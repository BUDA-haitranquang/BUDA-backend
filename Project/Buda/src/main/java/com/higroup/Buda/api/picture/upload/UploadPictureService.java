package com.higroup.Buda.api.picture.upload;

import javax.transaction.Transactional;

import com.higroup.Buda.entities.Picture;
import com.higroup.Buda.repositories.PictureRepository;
import com.higroup.Buda.services.S3StorageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
@Service
public class UploadPictureService {
    private final PictureRepository pictureRepository;

    @Autowired
    private S3StorageService s3StorageService;
    
    @Autowired
    public UploadPictureService(PictureRepository pictureRepository) {
        this.pictureRepository = pictureRepository;
    }
    @Transactional
    public Picture uploadPicture(Long userID, MultipartFile file)
    {
        String filePath = s3StorageService.uploadFile(file);
        Picture picture = new Picture(filePath, userID);
        return pictureRepository.save(picture);
    }
}
