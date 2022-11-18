package com.higroup.Buda.repositories;

import java.util.List;
import java.util.Optional;

import com.higroup.Buda.entities.StaffNote;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StaffNoteRepository extends JpaRepository<StaffNote, Long>{
    Optional<StaffNote> findStaffNoteByStaffNoteID(@Param("staffNoteID") Long staffNoteID);
    List<StaffNote> findAllByUserID(@Param("userID") Long userID);
    List<StaffNote> findAllByStaffID(@Param("staffID") Long staffID);
    @Query(value = "select * from staff_note where staff_id = :staffID and seen != 1", nativeQuery = true)
    List<StaffNote> findAllUnseenByStaffID(@Param("staffID") Long staffID);
}
