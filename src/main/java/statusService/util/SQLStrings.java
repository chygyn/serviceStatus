package statusService.util;

public class SQLStrings {
    public static final String getByID = "select Status, Date FROM `service`.`objects` WHERE ID=";
    public static final String updateStatus = "UPDATE `service`.`objects`SET`Status` = ?,`Date` = ? WHERE `ID` = ?";
    public static final String createEntity = "INSERT INTO `service`.`objects`(`Status`, `Date`)VALUES(?,?)";
    public static final String getAllEntity = "select * from `service`.`objects`";
}
