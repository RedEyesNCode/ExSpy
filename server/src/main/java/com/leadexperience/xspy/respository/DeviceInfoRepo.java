package com.leadexperience.xspy.respository;

import com.leadexperience.xspy.models.tables.DeviceInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DeviceInfoRepo extends JpaRepository<DeviceInfo,Long> {


}
