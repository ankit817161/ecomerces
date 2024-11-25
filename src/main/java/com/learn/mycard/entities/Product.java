
package com.learn.mycard.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "product") 
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pId;
    private String pName;
    private String pDesc;
    private String pPhoto;
    private int pPrices;
    private int pDiscount;
    private int pQuantity;
    @ManyToOne
    private Category category;

  
    public Product(String pName, String pDesc, String pPhoto, int pPrices, int pDiscount, int pQuantity , Category category) {
        this.pName = pName;
        this.pDesc = pDesc;
        this.pPhoto = pPhoto;
        this.pPrices = pPrices;
        this.pDiscount = pDiscount;
        this.pQuantity = pQuantity;
        this.category=category;
        
        
    }

    public Product() {
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }

    public String getpPhoto() {
        return pPhoto;
    }

    public void setpPhoto(String pPhoto) {
        this.pPhoto = pPhoto;
    }

    public int getpPrices() {
        return pPrices;
    }

    public void setpPrices(int pPrices) {
        this.pPrices = pPrices;
    }

    public int getpDiscount() {
        return pDiscount;
    }

    public void setpDiscount(int pDiscount) {
        this.pDiscount = pDiscount;
    }

    public int getpQuantity() {
        return pQuantity;
    }

    public void setpQuantity(int pQuantity) {
        this.pQuantity = pQuantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    

    @Override
    public String toString() {
        return "Product{" + "pId=" + pId + ", pName=" + pName + ", pDesc=" + pDesc + ", pPhoto=" + pPhoto + ", pPrices=" + pPrices + ", pDiscount=" + pDiscount + ", pQuantity=" + pQuantity + '}';
    }
    
    public int getPriceAfterDiscount(){
        int d=(int) ((this.getpDiscount()/100.0)*this.getpPrices());
        return this.getpPrices()-d;
    }
}
