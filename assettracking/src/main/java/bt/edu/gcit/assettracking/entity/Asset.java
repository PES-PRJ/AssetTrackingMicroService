package bt.edu.gcit.assettracking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "assets")
public class Asset {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "serial_no", unique = true, nullable = false)
    private String serialNo;

    @Column(nullable = false)
    private String name;

    private String location;
    private String category;

    // Simply stores the email/username identifier of the assigned Employee
    @Column(name = "assigned_to")
    private String assignedTo;

    // Constructors
    public Asset() {}

    public Asset(String serialNo, String name, String location, String category, String assignedTo) {
        this.serialNo = serialNo;
        this.name = name;
        this.location = location;
        this.category = category;
        this.assignedTo = assignedTo;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public String getAssignedTo() { return assignedTo; }
    public void setAssignedTo(String assignedTo) { this.assignedTo = assignedTo; }
}