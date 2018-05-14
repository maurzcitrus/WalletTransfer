package com.murali.gtdollar.util;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Defines method to map from ResultSet record to domain object.
 *
 * This interface is used in MySqlQuery to expose ResultSet mapping steps to DAO
 * classes.
 *
 * @author phyo
 */
public interface ResultSetMapper<T> {

  /**
   * Creates one domain object using one row of data from ResultSet.
   *
   * @param resultSet
   * @return
   * @throws SQLException
   */
  public T map(ResultSet resultSet) throws SQLException;
}
