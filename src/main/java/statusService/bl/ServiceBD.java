package statusService.bl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import statusService.entity.Entity;
import statusService.util.SQLStrings;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Repository
public class ServiceBD {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Entity createEntity (Entity entity){
        KeyHolder holder = new GeneratedKeyHolder();
        int i = jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection)
                    throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQLStrings.createEntity,Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,entity.getStatus());
                ps.setString(2,entity.getDate());

                return ps;
            }
        }, holder);
        Integer id = (Integer)holder.getKeys().get("id");
        entity.setId(id);
        return entity;
    }

    public int updateEntity (Entity entity){
        return jdbcTemplate.update(SQLStrings.updateStatus,entity.getStatus(),entity.getDate(),entity.getId());
    }

    public Entity getEntityByID (Integer id){
        Entity entity = new Entity(id);
        String sql = SQLStrings.getByID+id;
        jdbcTemplate.query(sql,rs->{
                entity.setStatus(rs.getString("Status"));
                entity.setDate(rs.getString("Date"));
        });
        return entity;
    }

    public ArrayList<Entity> getAllEntity (){
        ArrayList<Entity> result = new ArrayList<>();
        List<Map<String,Object>> rows = jdbcTemplate.queryForList(SQLStrings.getAllEntity);
        for (Map<String,Object> row:rows){
            Entity p = new Entity((Integer)row.get("ID"),(String)row.get("Status"),(String)row.get("Date"));
            result.add(p);
        }
        return result;
    }
}
