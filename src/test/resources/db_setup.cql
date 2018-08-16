CREATE KEYSPACE IF NOT EXISTS assetmonitoring with replication = {'class':'SimpleStrategy','replication_factor':1};

CREATE TABLE IF NOT EXISTS assetmonitoring.assets(
    id text, 
    os text, 
    type text, 
    ipaddress text, 
    version text, 
    antivirus text, 
    current double, 
    rotation int, 
    pressure int, 
    temperature int, 
    latitude double, 
    longitude double,
    PRIMARY KEY(id)
);

CREATE INDEX IF NOT EXISTS ON assetmonitoring.assets (os);
CREATE INDEX IF NOT EXISTS ON assetmonitoring.assets (type);

INSERT INTO assetmonitoring.assets (id, os, type, ipaddress, version, antivirus, current, rotation, pressure, temperature, latitude, longitude) values ('1', 'Window', 'Window', '0.0.0.0', '1.0.0', 'Kaspersky', 1, 1, 1, 1, 1, 1);
INSERT INTO assetmonitoring.assets (id, os, type, ipaddress, version, antivirus, current, rotation, pressure, temperature, latitude, longitude) values ('2', 'Window', 'Window', '0.0.0.0', '2.0.0', 'Kaspersky', 1, 1, 1, 1, 1, 1);