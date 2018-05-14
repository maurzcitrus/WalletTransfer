package com.murali.gtdollar.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Defines method to set parameter values of a PreparedStatement.
 *
 * This interface is used in MySqlQuery to expose PreparedStatement parameter
 * value setting steps to DAO classes.
 *
 * @author MuraliKrishnaB
 */
public interface ParameterSetter {

  /**
   * Sets parameter values for the PreparedStatement
   *
   * @param statement
   * @throws SQLException
   */
  public void SetParameters(PreparedStatement statement) throws SQLException;

}
