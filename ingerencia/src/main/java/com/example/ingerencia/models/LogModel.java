package com.example.ingerencia.models;

import java.sql.Timestamp;
import java.sql.Date;
import java.sql.Time;

import jakarta.persistence.*;

@Entity
@Table(name = "Log")
public class LogModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long nIdLog;

    private String vService;
    private Timestamp dExecTime;
    private String vResponseTime;

    public LogModel(){

    }
    
    public LogModel(String vService, double dResponseTime){
        //System.out.println(dResponseTime);
        this.vService = vService;
        this.dExecTime = new Timestamp(System.currentTimeMillis());
        this.vResponseTime = dResponseTime + " ms";
    }
}
