package lk.ijse.wholeSalePos.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, ID> extends SuperDAO{
    ArrayList<T> getAll() throws ClassNotFoundException, SQLException;

    boolean save(T dto) throws ClassNotFoundException, SQLException;

    boolean update(T dto) throws ClassNotFoundException, SQLException;

    T search(ID id) throws ClassNotFoundException, SQLException;

    boolean exist(ID id) throws ClassNotFoundException, SQLException;

    boolean delete(ID id) throws ClassNotFoundException, SQLException;

    String generateNewId() throws ClassNotFoundException, SQLException;
}
