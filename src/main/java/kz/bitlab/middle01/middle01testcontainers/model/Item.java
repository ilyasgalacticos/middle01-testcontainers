package kz.bitlab.middle01.middle01testcontainers.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "t_items")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int amount;
    private double price;

}
