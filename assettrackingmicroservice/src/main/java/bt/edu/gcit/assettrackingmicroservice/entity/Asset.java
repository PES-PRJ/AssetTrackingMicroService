package bt.edu.gcit.assettrackingmicroservice.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data // If you have Lombok, otherwise generate Getters/Setters
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String name;
    private String assetTag; // e.g., "LAPTOP-001"
    private String status;   // e.g., "Available", "Assigned"
}