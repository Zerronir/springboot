CREATE TABLE vetData (
    id UUID not null primary key,
    vetName varchar (100),
    vetCIF char(9) not null unique,
    vetAddress varchar(100),
    vetCountry char(2) DEFAULT 'ES',
    vetEmail varchar(100) not null,
    vetPhone char(9)
)