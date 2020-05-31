package com.java.repository;

import com.java.domain.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface StudyPlanRepository extends JpaRepository<StudyPlan,UUID> {

    Optional<StudyPlan> findById(UUID uuid);

    void deleteById(UUID uuid);
	
	@Query(value = "select st.* from studyplan st, speciality sp where st.speciality_id = sp.id and sp.lectern_id=?1",nativeQuery = true)
	List<StudyPlan> findStudyplansByLecternId(UUID id);

    @Query(value = "select * from studyplan where speciality_id=?1", nativeQuery = true)
    List<StudyPlan> findStudyplansBySpecialityId(UUID id);
}