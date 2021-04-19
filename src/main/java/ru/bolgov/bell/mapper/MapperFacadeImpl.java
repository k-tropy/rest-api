package ru.bolgov.bell.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.bell.office.dto.OfficeByParamInDto;
import ru.bolgov.bell.office.dto.OfficeDtoWithOrgId;
import ru.bolgov.bell.office.entity.Office;
import ru.bolgov.bell.organization.entity.Organization;

import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class MapperFacadeImpl implements MapperFacade {
    private final MapperFactory mapperFactory;

    @Autowired
    public MapperFacadeImpl(MapperFactory mapperFactory) {
        this.mapperFactory = mapperFactory;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> D map(S sourceObject, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().map(sourceObject, destinationClass);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> void map(S sourceObject, D destinationObject) {
        mapperFactory.getMapperFacade().map(sourceObject, destinationObject);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <S, D> List<D> mapAsList(Iterable<S> source, Class<D> destinationClass) {
        return mapperFactory.getMapperFacade().mapAsList(source, destinationClass);
    }

    /*Возможно маппер нужно реализовать иначе. Но через кастомный маппер не получилось!*/
    /**
     * {@inheritDoc}
     */
    @Override
    public Office mapOfficeDtoWhithOrganization(OfficeDtoWithOrgId sourse, Class<Office> destinationClass) {
        Office office = mapperFactory.getMapperFacade().map(sourse, Office.class);
        //office.setOrganization(new Organization(sourse.orgId));
        office.setOrganization(new Organization(sourse.getOrgId()));
        return office;
    }
}
