package com.util.AnnexManage.imple;

import com.dao.AnnexManage.AnnexDao;
import com.model.Annex;
import com.service.common.imple.BaseServiceImple;
import com.util.AnnexManage.AnnexService;
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
