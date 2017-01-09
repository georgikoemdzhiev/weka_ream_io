package koemdzhiev.com.weka_ream_io;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.File;

import io.realm.Realm;
import io.realm.RealmResults;
import koemdzhiev.com.weka_ream_io.db.DatabaseInstance;
import koemdzhiev.com.weka_ream_io.utils.FileUtils;
import weka.core.DenseInstance;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.Utils;

public class MainActivity extends AppCompatActivity {
    private Instances dataSet;
    private Instances instanceHeader;
    private Instances readDataset;
    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        realm = Realm.getDefaultInstance();
        
        instanceHeader = FileUtils.readARFFFile(this, "schema_file.arff");
        readDataset = instanceHeader;
        dataSet = FileUtils.readARFFFile(this, "data.arff");

        printArray(dataSet.get(0).toDoubleArray());

//        for (Instance i : dataSet) {
//            saveInstanceToDB(i);
//        }

        readInstancesFromDB();

        System.out.println("ReadDataset: " + readDataset.toString());
    }

    private void readInstancesFromDB() {
        RealmResults<DatabaseInstance> realmResults = realm.where(DatabaseInstance.class).findAll();
        System.out.println("RealmResults: " + realmResults.toString());
        for (int p = 0; p < realmResults.size(); p++) {
            DenseInstance denseInstance = new DenseInstance(instanceHeader.numAttributes());
            DatabaseInstance currentDBInstance = realmResults.get(p);

            for (int i = 0; i < instanceHeader.numAttributes(); i++) {
                denseInstance.setValue(i, currentDBInstance.getValues()[i]);
            }

            readDataset.add(denseInstance);
        }
    }


    public void saveInstanceToDB(Instance instance) {
        double[] instanceValues = instance.toDoubleArray();
        realm.beginTransaction();
        DatabaseInstance databaseInstance = realm.createObject(DatabaseInstance.class);
        databaseInstance.setValues(instanceValues);
        realm.commitTransaction();
    }

    public void printArray(double[] array) {
        for (double speed : array) {
            System.out.print(speed + ",");
        }
        System.out.println();
    }
}
