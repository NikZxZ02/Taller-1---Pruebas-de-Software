package com.jsilva.almamater.models;

import java.util.Arrays;

/**
 *
 * @author JSilva
 */
public class Survey {
    private int id;
    private int ptsBca;
    private int ptsAcr;
    private int ptsRank;
    private int region;
    private String isPrivate;
    private int ptsNear;
    private double lat;
    private double lng;
    private int[] careerId;
    double[] points = {ptsBca, ptsAcr, ptsRank, ptsNear};

    public Survey() {
    }

    public int[] getCareerId() {
        return careerId;
    }

    public void setCareerId(int[] careerId) {
        this.careerId = careerId;
    }
    
    public double[] getPoints(){
        return points;
    }
    
    public double getPointsSum(){
        return Arrays.stream(getPoints()).sum();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public int getPtsAcr() {
        return ptsAcr;
    }

    public void setPtsAcr(int ptsAcr) {
        this.ptsAcr = ptsAcr;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getIsPrivate() {
        return isPrivate;
    }

    public void setIsPrivate(String isPrivate) {
        this.isPrivate = isPrivate;
    }

    public int getPtsNear() {
        return ptsNear;
    }

    public void setPtsNear(int ptsNear) {
        this.ptsNear = ptsNear;
    }
    
    public int getPtsRank() {
        return ptsRank;
    }

    public void setPtsRank(int ptsRank) {
        this.ptsRank = ptsRank;
    }

    public int getPtsBca() {
        return ptsBca;
    }

    public void setPtsBca(int ptsBca) {
        this.ptsBca = ptsBca;
    }
    
    
}
