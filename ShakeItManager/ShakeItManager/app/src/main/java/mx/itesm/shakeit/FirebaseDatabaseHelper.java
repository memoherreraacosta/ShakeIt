package mx.itesm.shakeit;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceLocation;//cambiar cupón por location
    private List<Location> locations = new ArrayList<>();//cambió cupones por locations
    private List<Cupon> cupones;

    public interface DataStatus{
        void DataIsLoaded(List<Location> locations, List<String> keys);//cambió cupones por locations
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceLocation = mDatabase.getReference("Locations");//cambió cupones por locations

    }

    //cambió cupones por locations
    public void leerLocations(final DataStatus dataStatus){
        mReferenceLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locations.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    cupones = new ArrayList<>();
                    keys.add(keyNode.getKey());
                    Location location = keyNode.getValue(Location.class);

                    DataSnapshot contentSnapshot = keyNode.child("Cupones");
                    Iterable<DataSnapshot> matchSnapShot = contentSnapshot.getChildren();
                    for (DataSnapshot match : matchSnapShot){
                        Cupon cupon = match.getValue(Cupon.class);
                        cupones.add(cupon);
                    }

                    location.setCupones(cupones);
                    locations.add(location);
                }
                dataStatus.DataIsLoaded(locations, keys);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public List<Location> getLocations(){
        return locations;
    }
}
