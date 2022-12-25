package com.leadexperience.xspy.respository;

import com.leadexperience.xspy.models.tables.ContactTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ContactRepo extends JpaRepository<ContactTable,Long> {


}
