package us.xvicario.scryfallapi_test;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import us.xvicario.scryfallandroidapibinding.Card;
import us.xvicario.scryfallandroidapibinding.ScryfallAPI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TestAPI job = new TestAPI();
        job.execute();
    }

    private class TestAPI extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            Card card = ScryfallAPI.getCardFromMultiverse(366255);
            //card.getCmc();
            return null;
        }
    }
}
