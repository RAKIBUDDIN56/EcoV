package com.example.eco_v;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class Materials extends AppCompatActivity implements ListFrag.ItemSelected {
    TextView tvDescription;
    ArrayList<String> description=new ArrayList<>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.setTitle("Materials");

        //for back direction
        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_materials);

        tvDescription=findViewById(R.id.tvDescription);


        description.add("Aerosols aren't aerosols at all. No, really, let's be clear about this. An aerosol is really the cloud of " +
                "liquid and gas that comes out of an aerosol can, not the can itself. In fact, to be strictly correct about it, an " +
                "aerosol is a fine mist of liquid, or lots of solid particles, widely and evenly dispersed throughout a gas. So " +
                "clouds, fog, and steam from your kettle are all examples of aerosols, because they're made up of water droplets " +
                "dispersed through a much bigger volume of air. Smoke is an aerosol too, though unlike those other examples " +
                "(which are liquids dispersed in gases) it's made up of solid particles of unburned carbon (soot) mixed through a " +
                "cloud of warm, rising air. Even candles make aerosols: the smoky steam swirling above a candle flame consists of " +
                "soot and water vapor dispersed through hot air.");
        description.add("The origin of aluminum foil can be traced by to the early 1900s. Life Savers—one of today’s most popular" +
                " candies—were first packaged in foil in 1913. To this day, the treats are encased in the world-famous aluminum foil tube. " +
                " The uses of foil have grown over the past 100 years to a nearly endless count. From Christmas tree ornaments to " +
                "spacecraft insulation," +
                " TV dinners to medicine packets—aluminum foil has, in many ways, improved both our products and our lives.");
        description.add("Asbestos is a naturally occurring mineral that can be pulled into a fluffy consistency. Asbestos fibers are soft and flexible yet resistant to heat, electricity and corrosion. These qualities make the mineral useful, but they also make asbestos exposure highly toxic.\n" +
                "\n" +
                "Vintage Johns Manville asbestos advertisement\n" +
                "Vintage Johns Manville asbestos advertisement.\n" +
                "Pure asbestos is an effective insulator, and it can be used in cloth, paper, cement, plastic and other materials to make them stronger. But when someone inhales or ingests asbestos dust, the mineral fibers can become forever trapped in their body.\n" +
                "\n" +
                "Over decades, trapped asbestos fibers can cause inflammation, scarring and eventually genetic damage to the body’s cells. A rare and aggressive cancer called mesothelioma is almost exclusively caused by asbestos exposure. Asbestos also causes other forms of cancer as well as progressive lung disease.\n" +
                "\n" +
                "Microscopic asbestos fibers cannot be seen, smelled or tasted, and it is unsafe to sniff a substance suspected of being asbestos. To detect asbestos, a sample of questionable material must be sent to a lab for testing.");

        //Phon is portrait mode
        if(findViewById(R.id.layout_portrait) !=null){
            FragmentManager manger=this.getSupportFragmentManager();
            manger.beginTransaction()
                    .hide(manger.findFragmentById(R.id.detailFrag))
                    .show(manger.findFragmentById(R.id.listFrag))
                    .commit();

        }
        //[hon is in landscape mode
        if(findViewById(R.id.layout_land) !=null){
            FragmentManager manger=this.getSupportFragmentManager();
            manger.beginTransaction()
                    .show(manger.findFragmentById(R.id.detailFrag))
                    .show(manger.findFragmentById(R.id.listFrag))

                    .commit();

        }




    }

    @Override
    public void onItemSeleted(int index) {
        tvDescription.setText(description.get(index));

        //phone is in portrait mode
        if(findViewById(R.id.layout_portrait) !=null){
            FragmentManager manager=this.getSupportFragmentManager();
            manager.beginTransaction()
                    .show(manager.findFragmentById(R.id.detailFrag))
                    .hide(manager.findFragmentById(R.id.listFrag))
                    .addToBackStack(null)
                    .commit();

        }

    }
}
