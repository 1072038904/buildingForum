package com.service.AnnexManage.imple;

import com.dao.AnnexManage.AnnexDao;
import com.model.Annex;
import com.service.AnnexManage.AnnexService;
import com.service.common.imple.BaseServiceImple;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AnnexServiceImple extends BaseServiceImple<Annex>implements AnnexService {
    private AnnexDao annexDao;

    public AnnexDao getAnnexDao() {
        return annexDao;
    }
    @Resource
    public void setAnnexDao(AnnexDao annexDao) {
        this.setBaseDao(annexDao);
        this.annexDao = annexDao;
    }
}
