package dci.j24e01.ToyStore.services;

import java.sql.Connection;

public interface DBConnectionManager {
    Connection getConnection();
}
