package com.leadexperience.xspy.respository;


import com.leadexperience.xspy.models.tables.NameTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDataRepository extends JpaRepository<NameTable, Long> {
}
