package statusService.entity;


public class Entity {

    Integer id;
    String status;
    String date;

    public Entity (){
    }

    public Entity(Integer id){
        this.id = id;
    }

    public Entity (Integer id, String status, String date){
        this.id=id;
        this.status=status;
        this.date = date;
    }

    public Entity (String status, String date){
        this.status=status;
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString (){
        return "id: "+this.id+" Status: "+this.status+" date: "+this.date;
    }
}
