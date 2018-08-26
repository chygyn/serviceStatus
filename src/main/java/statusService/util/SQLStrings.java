package statusService.util;

public class SQLStrings {
    public static final String getByID = "select Status, Date FROM objects WHERE ID=";
    public static final String updateStatus = "UPDATE objects SET Status = ?,Date = ? WHERE ID = ?";
    public static final String createEntity = "INSERT INTO objects(Status, Date)VALUES(?,?)";
    public static final String getAllEntity = "select * from objects";
}
