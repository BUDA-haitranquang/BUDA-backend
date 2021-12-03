package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.StaffNote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StaffNoteRepository extends JpaRepository<StaffNote, Long>{
    Optional<StaffNote> findStaffNoteByStaffNoteID(Long staffNoteID);
    List<StaffNote> findAllByUserID(Long userID);
    List<StaffNote> findAllByStaffID(Long staffID);
    @Query(value = "select * from staff_note where staff_id = :staffID and seen != 1", nativeQuery = true)
    List<StaffNote> findAllUnseenByStaffID(Long staffID);
}
