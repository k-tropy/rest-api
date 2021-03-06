package ru.bolgov.bell.organization.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bolgov.bell.utils.mapper.MapperFacade;
import ru.bolgov.bell.organization.dao.OrganizationDao;
import ru.bolgov.bell.organization.dto.OrganizationByParamInDto;
import ru.bolgov.bell.organization.dto.OrganizationByParamOutDto;
import ru.bolgov.bell.organization.dto.OrganizationFullDto;
import ru.bolgov.bell.organization.dto.OrganizationWithoutIdDto;
import ru.bolgov.bell.organization.entity.Organization;

import javax.validation.Valid;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationDao dao;
    private final MapperFacade mapper;

    @Autowired
    public OrganizationServiceImpl(OrganizationDao dao, MapperFacade mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional(readOnly = true)
    public List<OrganizationFullDto> organizations() {
        List<Organization> organizations = dao.loadAllOrganizations();
        return mapper.mapAsList(organizations, OrganizationFullDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OrganizationByParamOutDto> organizationsFilter(@Valid OrganizationByParamInDto organizationInDto) {
        Organization organizationIn = mapper.map(organizationInDto, Organization.class);
        List<Organization> organizationsList = dao.loadOrganizationsByParam(organizationIn);
        return mapper.mapAsList(organizationsList, OrganizationByParamOutDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OrganizationFullDto organizationById(Integer organizationId) {
        Organization organization = dao.loadOrganizationById(organizationId);
        return mapper.map(organization, OrganizationFullDto.class);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOrganization(@Valid OrganizationFullDto organizationInDto) {
        Integer id = organizationInDto.id;
        Organization organizationIn = mapper.map(organizationInDto, Organization.class);
        dao.updateOrganization(organizationIn, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOrganization(@Valid OrganizationWithoutIdDto organizationInDto) {
        Organization organizationIn = mapper.map(organizationInDto, Organization.class);
        dao.saveOrganization(organizationIn);
    }
}
