package com.caidapao.fgo.module.servant.service;



import com.caidapao.fgo.module.commons.base.service.AbstractBaseService;
import com.caidapao.fgo.module.servant.entity.Servant;
import com.caidapao.fgo.module.servant.repository.ServantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * (服务层实现)
 * @author by imall core generator
 * @version 1.0.0
 */
@Service
@Transactional(readOnly = true)
public class ServantService extends AbstractBaseService<Servant, Long> {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private ServantRepository servantRepository;

    @Override
    protected JpaRepository<Servant, Long> getRepository() {
        return servantRepository;
    }
}