package com.devoteam.VehicleApplication.repository;

import com.devoteam.VehicleApplication.conn.ConnectionFactory;
import com.devoteam.VehicleApplication.domain.Automaker;
import com.devoteam.VehicleApplication.domain.Vehicle;
import com.devoteam.VehicleApplication.domain.VehicleType;
import com.devoteam.VehicleApplication.util.Utils;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Log4j2
@Repository
@RequiredArgsConstructor
public class VehicleRepository {

    // Change to VehicleService or ApplicationService

    private static Utils utils;
    private static VehicleRep vehicleRep;

    public List<Vehicle> listAll () {return vehicleRep.findAll();}

    public static List<Vehicle> findByName(String name){return vehicleRep.findByName(name);}

    public static Vehicle findById(int id) {
        return utils.findVehicle(id, vehicleRep);
    }

    public static Vehicle save(Vehicle vehicle) {return vehicleRep.save(vehicle);}

    public static void delete(int id) {
        vehicleRep.delete(utils.findVehicle(id, vehicleRep));
    }

    public static void update(Vehicle vehicle) {
        // nog checken of het ID bestaat voor de update plaatsvindt
        vehicleRep.save(vehicle);
    }



//    public static final String SELECT_FROM_TABLE = "SELECT v.name, v.color, v.id, v.year, v.created_on, a.id, a.name, vt.id, vt.type ";
//
//    public static List<VehicleType> findByType(String type) {
//        log.info("Searching for vehicle type '{}'", type);
//        List<VehicleType> vehicleTypes = new ArrayList<>();
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementFindByType(conn, type);
//             ResultSet rs = ps.executeQuery()) {
//            vehicleTypeBuilder(vehicleTypes, rs);
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("Error finding vehicle type");
//        }
//        return vehicleTypes;
//    }
//
//    private static PreparedStatement createPreparedStatementFindByType(Connection conn, String type) throws SQLException {
//        String sql = "SELECT * FROM auto_dealer.vehicle_type WHERE vehicle_type.type = ?;";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, String.format("%s", type));
//        return ps;
//    }
//
//    public static void vehicleTypeBuilder(List<VehicleType> vehicleTypes, ResultSet rs) throws SQLException {
//        while (rs.next()) {
//            VehicleType vehicleType = VehicleType
//                    .builder()
//                    .id(rs.getInt("id"))
//                    .vehicleType(rs.getString("type"))
//                    .build();
//            vehicleTypes.add(vehicleType);
//        }
//    }
//
//    public static List<Vehicle> findByNameAutomaker(String name) {
//        log.info("Searching for all vehicles from automaker '{}'", name);
//        List<Vehicle> vehicles = new ArrayList<>();
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementFindByNameAutomaker(conn, name);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                vehicleBuilder(vehicles, rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("Error finding automaker by name");
//        }
//        return vehicles;
//    }
//
//    private static PreparedStatement createPreparedStatementFindByNameAutomaker(Connection conn, String name) throws SQLException {
//        String sql = SELECT_FROM_TABLE +
//                "FROM vehicle AS v INNER JOIN automaker AS a ON v.automaker_id = a.id INNER JOIN vehicle_type AS vt ON v.type_id = vt.id WHERE a.name = ?;";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, String.format("%s", name));
//        return ps;
//    }
//
//    public static List<Vehicle> findByNameModel(String name) {
//        log.info("Searching for all vehicle models named '{}'", name);
//        List<Vehicle> vehicles = new ArrayList<>();
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementFindByNameModel(conn, name);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                vehicleBuilder(vehicles, rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("Error finding vehicles by model name");
//        }
//        return vehicles;
//    }
//
//    private static PreparedStatement createPreparedStatementFindByNameModel(Connection conn, String name) throws SQLException {
//        String sql = SELECT_FROM_TABLE +
//                "FROM vehicle AS v INNER JOIN automaker AS a ON v.automaker_id = a.id INNER JOIN vehicle_type AS vt ON v.type_id = vt.id WHERE v.name = ?;";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, String.format("%s", name));
//        return ps;
//    }
//
//    public static List<Vehicle> findAllVehicles() {
//        List<Vehicle> vehicles = new ArrayList<>();
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementFindAll(conn);
//             ResultSet rs = ps.executeQuery()) {
//            while (rs.next()) {
//                vehicleBuilder(vehicles, rs);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("Error finding all vehicles in database");
//        }
//        return vehicles;
//    }
//
//    private static PreparedStatement createPreparedStatementFindAll(Connection conn) throws SQLException {
//        String sql = SELECT_FROM_TABLE +
//                "FROM vehicle AS v INNER JOIN automaker AS a ON v.automaker_id = a.id INNER JOIN vehicle_type AS vt ON v.type_id = vt.id;";
//        return conn.prepareStatement(sql);
//    }
//
//    public static void removeVehicleByModel(Vehicle vehicle) {
//        log.info("Removing vehicle named '{}' from database", vehicle.getModel());
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementRemoveVehicleByModel(conn, vehicle)) {
//            ps.execute();
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("Error removing vehicle(s) from database");
//        }
//    }
//
//    private static PreparedStatement createPreparedStatementRemoveVehicleByModel(Connection conn, Vehicle vehicle) throws SQLException {
//        String sql = "DELETE FROM auto_dealer.vehicle WHERE vehicle.id = ?;";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setInt(1, vehicle.getId());
//        return ps;
//    }
//
//    public static void replaceVehicle(Vehicle vehicle) {
//        log.info("Updating all vehicles named '{}' from database", vehicle.getModel());
//        try (Connection connection = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementUpdate(connection, vehicle)) {
//            ps.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static PreparedStatement createPreparedStatementUpdate(Connection conn, Vehicle vehicle) throws SQLException {
//        String sql = "UPDATE vehicle AS v INNER JOIN automaker AS a ON v.automaker_id = a.id " +
//                "SET v.name = ?, v.color = ?, v.year = ?, v.type_id = ?, v.created_on = ?, v.automaker_id = ?, v.id = v.id " +
//                "WHERE v.id = ?";
//        PreparedStatement ps = conn.prepareStatement(sql);
//        ps.setString(1, vehicle.getModel());
//        ps.setString(2, vehicle.getColor());
//        ps.setInt(3, vehicle.getYear());
//        ps.setInt(4, vehicle.getVehicleType().getId());
//        ps.setTimestamp(5, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
//        ps.setInt(6, vehicle.getAutomaker().getId());
//        ps.setInt(7, vehicle.getId());
//        return ps;
//    }
//
//    public static int saveVehicle(Vehicle vehicle) {
//        log.info("Saving vehicle model named '{}' in the database", vehicle.getModel());
//        try (Connection conn = ConnectionFactory.getConnection();
//             PreparedStatement ps = createPreparedStatementSaveVehicle(conn, vehicle)) {
//            ps.execute();
//            try (ResultSet rs = ps.getGeneratedKeys()) {
//                if (rs.next()) {
//                    return rs.getInt(1);
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            log.error("Error saving vehicle in the database");
//        }
//        return -1;
//    }
//
//    private static PreparedStatement createPreparedStatementSaveVehicle(Connection conn, Vehicle vehicle) throws SQLException {
//        String sql = "INSERT INTO auto_dealer.vehicle (vehicle.name, vehicle.color, vehicle.year, vehicle.type_id, vehicle.created_on, vehicle.automaker_id)" +
//                " VALUES (?,?,?,?,?,?);";
//        PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//        ps.setString(1, vehicle.getModel());
//        ps.setString(2, vehicle.getColor());
//        ps.setInt(3, vehicle.getYear());
//        ps.setInt(4, vehicle.getVehicleType().getId());
//        ps.setTimestamp(5, java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()));
//        ps.setInt(6, vehicle.getAutomaker().getId());
//        return ps;
//    }
//
//    private static void vehicleBuilder(List<Vehicle> vehicles, ResultSet rs) throws SQLException {
//        Automaker automaker = Automaker.builder()
//                .id(rs.getInt("a.id"))
//                .name(rs.getString("a.name"))
//                .build();
//        VehicleType vehicleType = VehicleType.builder()
//                .id(rs.getInt("vt.id"))
//                .vehicleType(rs.getString("vt.type"))
//                .build();
//        Vehicle vehicle = Vehicle
//                .builder()
//                .id(rs.getInt("id"))
//                .model(rs.getString("name"))
//                .automaker(automaker)
//                .color(rs.getString("color"))
//                .year(rs.getInt("year"))
//                .createdOn(rs.getDate("created_on"))
//                .vehicleType(vehicleType)
//                .build();
//        vehicles.add(vehicle);
//    }
}
