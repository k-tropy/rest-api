package ru.bolgov.bell.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bolgov.bell.mapper.MapperFacade;
import ru.bolgov.bell.organization.dao.OrganizationDao;
import ru.bolgov.bell.organization.dto.OrgDtoOutShort;
import ru.bolgov.bell.organization.entity.Organization;

import java.util.List;

@Service
public class OrgServiceImpl implements OrgService{
    private final OrganizationDao dao;
    private final MapperFacade mapperFacade;

    @Autowired
    public OrgServiceImpl(OrganizationDao dao, MapperFacade mapperFacade){
        this.dao = dao;
        this.mapperFacade = mapperFacade;
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrgDtoOutShort> organization() {
        List<Organization> all = dao.all();
        return mapperFacade.mapAsList(all, OrgDtoOutShort.class);
    }
}
