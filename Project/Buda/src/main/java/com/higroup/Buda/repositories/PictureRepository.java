package com.higroup.Buda.repositories;

import java.util.Optional;

import com.higroup.Buda.entities.Picture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface PictureRepository extends JpaRepository<Picture, Long>{
    Optional<Picture> findPictureByPictureID(@Param("pictureID") Long pictureID);
}
