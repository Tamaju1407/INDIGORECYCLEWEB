package pe.edu.utp.regionslist.models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by GrupoUTP on 17/02/2017.
 */
public class RegionsEntity extends BaseEntity {
    private static String DEFAULT_SQL = "SELECT * FROM hr.regions";

    public List<Region> findAll() {
        return findByCriteria(DEFAULT_SQL);
    }

    public Region findById(int id) {
        List<Region> regions = findByCriteria(DEFAULT_SQL + " WHERE region_id = " + String.valueOf(id));
        return (regions != null) ? regions.get(0) : null;
    }

    public Region findByName(String name) {
        List<Region> regions = findByCriteria(DEFAULT_SQL + " WHERE region_name = '" + name + "'");
        return (regions.isEmpty()) ? null : regions.get(0);
    }

    private List<Region> findByCriteria(String sql) {
        List<Region> regions;
        if(getConnection() != null) {
            regions = new ArrayList<>();
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                while(resultSet.next()) {
                    Region region = new Region(resultSet.getInt("region_id"),
                            resultSet.getString("region_name"));
                    regions.add(region);
                }
                return regions;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private int getMaxId() {
        String sql = "SELECT MAX(region_id) as max_id FROM regions";
        if(getConnection() != null) {
            try {
                ResultSet resultSet = getConnection().createStatement().executeQuery(sql);
                return resultSet.next() ? resultSet.getInt(1) : 0;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
    public Region create(String name) {
        if(findByName(name) == null) {
            if(getConnection() != null) {
                String sql = "INSERT INTO regions(region_id, region_name) VALUES(" +
                        String.valueOf(getMaxId()+1) + ", '" +
                        name + "')";
                try {
                    int results = getConnection().createStatement().executeUpdate(sql);
                    if(results > 0) {
                        Region region = new Region(getMaxId(), name);
                        return region;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    private int updateByCriteria(String sql) {
        if(getConnection() != null) {
            try {
                return getConnection().createStatement().executeUpdate(sql);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    public boolean delete(int id) {
        return updateByCriteria("DELETE FROM regions WHERE region_id = "+
                String.valueOf(id)) > 0;
    }

    public boolean delete(String name) {
        return updateByCriteria("DELETE FROM regions WHERE region_name = '"+
                name + "'") > 0;
    }

    public boolean update(Region region) {
        return updateByCriteria("UPDATE regions SET region_name = '" +
                region.getName() + "' WHERE region_id = " + String.valueOf(region.getId())) > 0;
    }

}
