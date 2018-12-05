
package com.caidapao.fgo.module.servant.repository;

import com.caidapao.fgo.module.servant.entity.Servant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
/**
 * (JPA持久化层)
 * @author by imall core generator
 * @version 1.0.0
 */
public interface ServantRepository extends JpaRepository<Servant, Long>, JpaSpecificationExecutor<Servant>  {


}

