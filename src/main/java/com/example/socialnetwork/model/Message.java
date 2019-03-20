package com.example.socialnetwork.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;
    @Column
    private String message;
    @Column(name = "send_date")
    private Date date;
    @ManyToOne
    @JoinColumn(name = "from_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "to_id")
    private User friend;


    }
