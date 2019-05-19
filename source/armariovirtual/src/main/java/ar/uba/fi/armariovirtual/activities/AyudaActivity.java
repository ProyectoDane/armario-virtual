package ar.uba.fi.armariovirtual.activities;

import android.os.Bundle;

import ar.uba.fi.armariovirtual.R;

public class AyudaActivity extends BarraBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ayuda);
        setUpBarraConBotonDeAtras(true, false, null);
        findViewById(R.id.btn_ayuda).setOnClickListener(null);
    }
}
