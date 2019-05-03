package mx.itesm.shakeit;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Button;
import java.util.List;


public class RecyclerView_Config {
    private Context mContext;
    private CuponAdapter mCuponAdapter;

    public void setConfig(RecyclerView recyclerView, Context context, List<Cupon> cupones, List<String> keys, List<Location>locations){
        this.mContext = context;
        this.mCuponAdapter = new CuponAdapter(cupones, keys, locations);
        LinearLayoutManager llm = new LinearLayoutManager(context);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        recyclerView.setAdapter(mCuponAdapter);

    }

    class CuponItemView extends RecyclerView.ViewHolder {
        private TextView rest, lat, lng, nombre;
        private Button bMap;
        private String key;

        public String resName, promo, lati, longi;

        public CuponItemView(final ViewGroup parent){
            super(LayoutInflater.from(mContext).inflate(R.layout.activity_cupon_list_item2, parent, false));

            rest = itemView.findViewById(R.id.tRestACLI);
            lat = itemView.findViewById(R.id.tLatACLI);
            lng = itemView.findViewById(R.id.tLonACLI);
            nombre = itemView.findViewById(R.id.tPromACLI);
            bMap = itemView.findViewById(R.id.bMapACLI);

        }

        public void bind(Cupon cupon, String key, Location location){
            this.resName = cupon.getInformacion();
            this.promo = location.getRestaurante();
            this.lati = location.getLatitud()+"";
            this.longi = location.getLongitud()+"";

            rest.setText(this.resName);
            nombre.setText(this.promo);
            lat.setText(this.lati);
            lng.setText(this.longi);
            this.key = key;
        }
    }

    class CuponAdapter extends RecyclerView.Adapter<CuponItemView>{
        private List<Cupon> mCuponList;
        private List<String> mKeys;
        private List<Location> mLocations;

        public CuponAdapter(List<Cupon> cupones, List<String> keys, List<Location> locations){
            this.mCuponList = cupones;
            this.mKeys = keys;
            this.mLocations = locations;
        }

        @NonNull
        @Override
        public CuponItemView onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            return new CuponItemView(parent);
        }

        @Override
        public void onBindViewHolder(@NonNull CuponItemView cuponItemView, int i) {
            cuponItemView.bind(mCuponList.get(i), mKeys.get(i), mLocations.get(i));
        }

        @Override
        public int getItemCount() {
            return mCuponList.size();
        }
    }
}
