package com.rubenskj.security.loginlocation.util;

import com.maxmind.geoip2.DatabaseReader;
import com.rubenskj.security.loginlocation.property.DbMaxMindProperty;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class DatabaseReaderGeoIp {

    private DatabaseReader databaseReader;
    private String dbLocation;

    public DatabaseReaderGeoIp(DbMaxMindProperty dbMaxMindProperty) {
        this.dbLocation = dbMaxMindProperty.getDblocation();
    }

    public DatabaseReader getInstanceDatabaseReader() {
        File database = new File(dbLocation);

        if (this.databaseReader != null) {
            return this.databaseReader;
        } else {
            DatabaseReader databaseReader = this.createDatabaseReader(database);

            this.databaseReader = databaseReader;

            return databaseReader;
        }
    }

    private DatabaseReader createDatabaseReader(File database) {
        try {
            return new DatabaseReader.Builder(database).build();
        } catch (IOException e) {
            return null;
        }
    }
}
