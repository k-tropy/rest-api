# Java REST API APPLICATION
## Учебное приложение
*Приложение управляет данными организаций, принадлежащих им офисов и сотрудников.*
## Использованные технологии
- Java 8
- Spring Boot
- Spring MVC
- Spring Data JPA
## Как запустить у себя
    ПО - git, JDK8, IntelliJ IDEA, Maven

- Клонировать репозиторий:        
```git clone https://github.com/k-tropy/rest-api.git```

__Запуск в в IntelliJ IDEA:__
- Открыть проект в IntelliJ IDEA;
- Запустить средствами IDEA.

__Для запуска через командную строку:__
- В консоли перейти в папку с проектом, выполнить
```mvn spring-boot:run```

Проект будет доступен по url: http://localhost:8888

## Описание API
### api/organization/list

```In (filter):
{
  “name”:” ”, //required
  “inn”:” ”,
  “isActive”:” ”
}
Out:
[
  {
    “id”:” ”,
    “name”:” ”,
    “isActive”:”true”
  },
  ...
]
```

### api/organization/{id}
#### method:GET
```
Out:
{
  “id”:” ”,
  “name”:” ”,
  “fullName”:” ”,
  “inn”:” ”,
  “kpp”:” ”,
  “address”:” ”,
  “phone”,” ”,
  “isActive”:”true”
}
```
### api/organization/update
```
In:
{
  “id”:” ”, //required
  “name”:” ”, //required
  “fullName”:” ”, //required
  “inn”:” ”, //required
  “kpp”:” ”,  //required
  “address”:” ”, //required
  “phone”,” ”,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}
```
### api/organization/save
```
In:
{
  “name”:” ”, //required
  “fullName”:” ”, //required
  “inn”:” ”, //required
  “kpp”:” ”, //required
  “address”:” ”, //required
  “phone”,” ”,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}
```
### api/office/list
```
In (filter):
{
  “orgId”:” ”, //required
  “name”:” ”,
  “phone”:” ”,
  “isActive” 
}

Out:
[
  {
    “id”:” ”,
    “name”:” ”,
    “isActive”:”true”
  },
  ...
]
```
### api/office/{id}
#### method:GET
```
Out:
{
  “id”:” ”,
  “name”:” ”,
  “address”:” ”,
  “phone”,” ”,
  “isActive”:”true”
}
```
### api/office/update
```
In:
{
  “id”:” ”, //required
  “name”:” ”, //required
  “address”:” ”, //required
  “phone”,” ”,
  “isActive”:”true” //пример
}

Out:
{
    “result”:”success”
}
```
### api/office/save
```
In:
{
  “orgId”:” ”, //required
  “name”:” ”,
  “address”:” ”,
  “phone”,” ”,
  “isActive”:”true”
}

Out:
{
    “result”:”success”
}
```
### api/user/list
```
In (фильтр):
{
  “officeId”:” ”, //required
  “firstName”:” ”,
  “lastName”:” ”,
  “middleName”:” ”,
  “position”,” ”,
  “docCode”:” ”,
  “citizenshipCode”:” ”
}
Out:
[
    {
      “id”:” ”,
      “firstName”:” ”,
      “lastName”:” ”,
      “middleName”:” ”,
      “position”:” ”
    },
    ...
]
```
### api/user/{id}
#### method:GET
```
Out:
{
  “id”:” ”,
  “firstName”:” ”,
  “lastName”:” ”,
  “middleName”:” ”,
  “position”:” ”
  “phone”,” ”,
  “docName”:” ”,
  “docNumber”:” ”,
  “docDate”:” ”,
  “citizenshipName”:” ”,
  “citizenshipCode”:” ”,
  “isIdentified”:”true”
}
```
### api/user/update
```
In:
{
  “id”:” ”, //required
  “officeId”:” ”,
  “firstName”:” ”, //required
  “lastName”:” ”,
  “middleName”:” ”,
  “position”:” ” //required
  “phone”,” ”,
  “docName”:” ”,
  “docNumber”:” ”,
  “docDate”:” ”,
  “citizenshipCode”:” ”,
  “isIdentified”:”true” //пример
}

Out:
{
    “result”:”success”
}
```
### api/user/save
```
In:
{
  “officeId”:” ”, //required
  “firstName”:” ”, //required
  “lastName”:” ”,
  “middleName”:” ”,
  “position”:” ” //required
  “phone”,” ”,
  “docCode”:” ”,
  “docName”:” ”,
  “docNumber”:” ”,
  “docDate”:” ”,
  “citizenshipCode”:” ”,
  “isIdentified”:”true” //пример
}
```
### api/docs
#### method:GET
`[
  {
    “name”:“Паспорт гражданина РФ”,
    “code”:”21”
  },
  ...
]`

### api/countries
`[
  {
    “name”:“Российская Федерация”,
    “code”:”643”
  },
  ...
]`
