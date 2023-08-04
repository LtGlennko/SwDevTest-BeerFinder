package com.example.ingerencia.models;

import java.sql.Date;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "Beer")
public class BeerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long nIdBeer;
    
    private String vName;
    private String vSlogan;
    private Integer nStock;

    public BeerModel(){
        
    }

    public BeerModel(Long nIdBeer, String vName, String vSlogan, Integer nStock){
        this.nIdBeer = nIdBeer;
        this.vName = vName;
        this.vSlogan = vSlogan;
        this.nStock = nStock;
    }

    public Long getnIdBeer() {
        return nIdBeer;
    }
    public void setnIdBeer(Long nIdBeer) {
        this.nIdBeer = nIdBeer;
    }
    public String getvName() {
        return vName;
    }
    public void setvName(String vName) {
        this.vName = vName;
    }
    public String getvSlogan() {
        return vSlogan;
    }
    public void setvSlogan(String vSlogan) {
        this.vSlogan = vSlogan;
    }
    public Integer getnStock() {
        return nStock;
    }
    public void setnStock(Integer nStock) {
        this.nStock = nStock;
    }
}
