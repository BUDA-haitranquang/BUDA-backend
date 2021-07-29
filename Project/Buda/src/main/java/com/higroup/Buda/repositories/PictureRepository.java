package com.higroup.Buda.repositories;

import java.util.Optional;

import com.higroup.Buda.entities.Picture;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PictureRepository extends JpaRepository<Picture, Long>{
    Optional<Picture> findPictureByPictureID(Long pictureID);
}
