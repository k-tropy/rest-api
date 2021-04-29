package ru.bolgov.bell.utils.mapper;

import ma.glasnost.orika.MapperFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.bolgov.bell.doc.entity.Doc;
import ru.bolgov.bell.guide.entity.Citizenship;
import ru.bolgov.bell.guide.entity.DocType;
import ru.bolgov.bell.office.dto.OfficeDtoWithOrgId;
import ru.bolgov.bell.office.entity.Office;
import ru.bolgov.bell.organization.entity.Organization;
import ru.bolgov.bell.user.dto.UserByIdOutDto;
import ru.bolgov.bell.user.dto.UserInDto;
import ru.bolgov.bell.user.entity.User;

import java.util.List;
import java.util.Optional;

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

    /**
     * {@inheritDoc}
     */
    @Override
    public Office mapOfficeDtoWhithOrganization(OfficeDtoWithOrgId sourse, Class<Office> destinationClass) {
        Office office = mapperFactory.getMapperFacade().map(sourse, Office.class);
        office.setOrganization(new Organization(sourse.getOrgId()));
        return office;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public User mapUserInDtoToUser(UserInDto sourse, Class<User> destinationClass) {
        User user = mapperFactory.getMapperFacade().map(sourse, User.class);
        addOtherFieldsToUser(sourse, user);
        return user;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UserByIdOutDto mapUserToOutDto(User sourse, Class<UserByIdOutDto> destinationClass) {
        mapperFactory.classMap(User.class, UserByIdOutDto.class)
                .field("doc.docType.name", "docName")
                .field("doc.number", "docNumber")
                .field("doc.date", "docDate")
                .field("citizenship.name", "citizenshipName")
                .field("citizenship.code", "citizenshipCode")
                .byDefault()
                .register();
        return mapperFactory.getMapperFacade().map(sourse, UserByIdOutDto.class);
    }


    private User addOtherFieldsToUser(UserInDto sourse, User user) {
        if (sourse.getDocCode() != null || sourse.getDocName() != null
                || sourse.getDocNumber() != null || sourse.getDocDate() != null) {
            Doc doc = new Doc();
            doc.setDocType(new DocType());
            doc.getDocType().setCode(sourse.getDocCode());
            doc.getDocType().setName(sourse.getDocName());
            doc.setNumber(sourse.getDocNumber());
            doc.getDocType().setCode(sourse.getDocCode());
            user.setDoc(doc);
        }

        if (sourse.getCitizenshipCode() != null) {
            Citizenship citizenship = new Citizenship();
            citizenship.setCode(sourse.getCitizenshipCode());
            user.setCitizenship(citizenship);
        }
        return user;
    }
}
