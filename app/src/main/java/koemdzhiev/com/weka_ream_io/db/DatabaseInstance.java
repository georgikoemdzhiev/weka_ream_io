package koemdzhiev.com.weka_ream_io.db;

import io.realm.RealmObject;

/**
 * Created by Georgi on 1/9/2017.
 */

public class DatabaseInstance extends RealmObject {
    // ORDER MATTER
    private double accX__mean;
    private double accY__mean;
    private double accZ__mean;
    private double accX__stdv;
    private double accY__stdv;
    private double accZ__stdv;
    private double classValue;

    public void setValues(double[] values) {
        accX__mean = values[0];
        accY__mean = values[1];
        accZ__mean = values[2];
        accX__stdv = values[3];
        accY__stdv = values[4];
        accZ__stdv = values[5];
        classValue = values[6];
    }

    public double[] getValues() {
        double[] values = new double[7];
        values[0] = accX__mean;
        values[1] = accY__mean;
        values[2] = accZ__mean;
        values[3] = accX__stdv;
        values[4] = accY__stdv;
        values[5] = accZ__stdv;
        values[6] = classValue;
        return values;
    }

    @Override
    public String toString() {
        return "DatabaseInstance{" +
                "accX__mean=" + accX__mean +
                ", accY__mean=" + accY__mean +
                ", accZ__mean=" + accZ__mean +
                ", accX__stdv=" + accX__stdv +
                ", accY__stdv=" + accY__stdv +
                ", accZ__stdv=" + accZ__stdv +
                ", classValue=" + classValue +
                "\n";
    }
}
