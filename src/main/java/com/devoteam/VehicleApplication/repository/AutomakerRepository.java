package com.devoteam.VehicleApplication.repository;

import com.devoteam.VehicleApplication.conn.ConnectionFactory;
import com.devoteam.VehicleApplication.domain.Automaker;
import lombok.extern.log4j.Log4j2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


@Log4j2
public class AutomakerRepository {
//
//    public static List<Automaker> findByName(String name) {
//        log.info("Searching for automaker '{}'", name);
//        List<Automaker> automakers = new ArrayList<>();
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementFindByName(conn, name);
//             ResultSet rs = ps.executeQuery()) {
//            automakerBuilder(automakers, rs);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("Error finding automaker by name");
//        }
//        return automakers;
//    }
//
//    private static PreparedStatement createPreparedStatementFindByName(Connection conn, String name) throws SQLException {
//        String sql = "SELECT * FROM auto_dealer.automaker WHERE automaker.name = ?;";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, String.format("%s", name));
//        return ps;
//    }
//
//    public static void saveAutomaker(String name) {
//        log.info("Saving automaker named '{}' in the database", name);
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementSaveAutomaker(conn, name)) {
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("Error saving automaker in the database");
//        }
//    }
//
//    private static PreparedStatement createPreparedStatementSaveAutomaker(Connection conn, String name) throws SQLException {
//        String sql = "INSERT INTO auto_dealer.automaker (automaker.name) VALUES (?);";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, String.format("%s", name));
//        return ps;
//    }
//
//    public static void automakerBuilder(List<Automaker> automakers, ResultSet rs) throws SQLException {
//        while (rs.next()) {
//            Automaker automaker = Automaker
//                    .builder()
//                    .id(rs.getInt("id"))
//                    .name(rs.getString("name"))
//                    .build();
//            automakers.add(automaker);
//
//        }
//    }
}
