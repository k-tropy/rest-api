package ru.bolgov.bell.office.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bolgov.bell.mapper.MapperFacade;
import ru.bolgov.bell.office.dao.OfficeDao;
import ru.bolgov.bell.office.dto.OfficeByParamInDto;
import ru.bolgov.bell.office.dto.OfficeByParamOutDto;
import ru.bolgov.bell.office.dto.OfficeInfoDto;
import ru.bolgov.bell.office.dto.OfficeNewDto;
import ru.bolgov.bell.office.entity.Office;

import javax.validation.Valid;
import java.util.List;

/**
 * {@inheritDoc}
 */
@Service
public class OfficeServiseImpl implements OfficeService{
    private final OfficeDao dao;
    private final MapperFacade mapper;

    @Autowired
    public OfficeServiseImpl(OfficeDao dao, MapperFacade mapper) {
        this.dao = dao;
        this.mapper = mapper;
    }

    //todo мне не нравится реализация!!!Как это сделать правильно? Мапер может это делать автоматически?
    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public List<OfficeByParamOutDto> officesFilter(@Valid OfficeByParamInDto officeInDto) {
        Office officeIn = mapper.mapOfficeDtoWhithOrganization(officeInDto, Office.class);
        List<Office> officesList = dao.loadOfficesByParam(officeIn);
        return mapper.mapAsList(officesList, OfficeByParamOutDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public OfficeInfoDto officeById(Integer officeId) {
        Office office = dao.loadOfficeById(officeId);
        return mapper.map(office, OfficeInfoDto.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void updateOffice(@Valid OfficeInfoDto officeInDto) {
        Office officeIn = mapper.map(officeInDto, Office.class);
        dao.updateOffice(officeIn);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public void saveOffice(@Valid OfficeNewDto officeInDto) {
        Office officeIn = mapper.mapOfficeDtoWhithOrganization(officeInDto, Office.class);
        System.out.println(officeIn.toString());
        dao.saveOffice(officeIn);
    }
}
