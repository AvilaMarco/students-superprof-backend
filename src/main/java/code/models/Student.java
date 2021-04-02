package code.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@Document(collection = "students")
public class Student implements Serializable {

    @Id
    private String id;
    private Long cellphone;
    private String email;
    private String drive;
    private String meet;

    public Student (){}

    public void setId(String id) {
        this.id = id;
    }

    public void setCellphone(Long cellphone) {
        this.cellphone = cellphone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }

    public void setMeet(String meet) {
        this.meet = meet;
    }

    public Map<String, Object> studentDTO(){
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", id);
        dto.put("cellphone", cellphone);
        dto.put("email", email);
        dto.put("drive", drive);
        dto.put("meet", meet);
        return dto;
    }

    public Map<String, Object> studentCompleteDTO(){
        Map<String, Object> dto = new HashMap<>();
        dto.put("id", id);
        dto.put("cellphone", cellphone);
        dto.put("email", email);
        dto.put("drive", drive);
        dto.put("meet", meet);
        return dto;
    }
}
