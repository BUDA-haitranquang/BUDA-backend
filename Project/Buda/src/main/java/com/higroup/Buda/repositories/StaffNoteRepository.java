package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.StaffNote;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StaffNoteRepository extends JpaRepository<StaffNote, Long>{
    Optional<StaffNote> findStaffNoteByStaffNoteID(Long staffNoteID);
    List<StaffNote> findAllByUserID(Long userID);
    List<StaffNote> findAllByStaffID(Long staffID);
}
