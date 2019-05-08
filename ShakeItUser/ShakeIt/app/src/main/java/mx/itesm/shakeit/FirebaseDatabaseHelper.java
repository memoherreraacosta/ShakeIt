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
import java.util.Random;

public class FirebaseDatabaseHelper {

    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferenceCupon;
    private DatabaseReference mRefCupon;
    private List<Cupon> cupones = new ArrayList<>();
    private List<Cupon> cuponesBien = new ArrayList<>();
    private List<Location> locations = new ArrayList<>();
    private List<Location> locationsBien = new ArrayList<>();
    private int n;

    public interface DataStatus{
        void DataIsLoaded(List<Cupon> cupones, List<String> keys, List<Location> locations);
        void DataIsInserted();
        void DataIsUpdated();
        void DataIsDeleted();
    }
    public FirebaseDatabaseHelper(){
        mDatabase = FirebaseDatabase.getInstance();
        mReferenceCupon = mDatabase.getReference("Locations");
    }

    public void leerCupones(final DataStatus dataStatus){
        mReferenceCupon.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                locations.clear();
                locationsBien.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Location location = keyNode.getValue(Location.class);
                    //Log.wtf(" restuarante", location.getRestaurante());
                    locations.add(location);
                }
                //Log.wtf("los ID" , keys.get(new Random().nextInt(keys.size())));
                n = new Random().nextInt(keys.size());
                locationsBien.add(locations.get(n));
                mRefCupon = mDatabase.getReference("Locations/" + keys.get(n) + "/Cupones");
                mRefCupon.addValueEventListener(new ValueEventListener() {
                              @Override
                              public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                  cuponesBien.clear();
                                  cupones.clear();
                                  List<String> keys = new ArrayList<>();
                                  for(DataSnapshot keyNode: dataSnapshot.getChildren()){
                                      keys.add(keyNode.getKey());
                                      Cupon cupon = keyNode.getValue(Cupon.class);
                                      cupones.add(cupon);
                                  }
                                  cuponesBien.add(cupones.get(new Random().nextInt(cupones.size())));
                                  dataStatus.DataIsLoaded(cuponesBien, keys, locationsBien);
                              }

                              @Override
                              public void onCancelled(@NonNull DatabaseError databaseError) {}
                          });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
