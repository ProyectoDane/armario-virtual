package ar.uba.fi.armariovirtual.activities.aprendoavestirme;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Rect;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import ar.uba.fi.armariovirtual.R;
import ar.uba.fi.armariovirtual.activities.cuestionario.CuestionarioBaseActivityArmarioVirtual;
import ar.uba.fi.utilidadesdane.utils.Ejecutable;

import com.github.jinatonic.confetti.ConfettiManager;
import com.github.jinatonic.confetti.ConfettiSource;
import com.github.jinatonic.confetti.ConfettoGenerator;
import com.github.jinatonic.confetti.Utils;
import com.github.jinatonic.confetti.confetto.BitmapConfetto;
import com.github.jinatonic.confetti.confetto.Confetto;

import java.util.List;
import java.util.Random;

public class AprendoAVestirmePreguntasActivity extends CuestionarioBaseActivityArmarioVirtual {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpBarraConBotonDeAtras(true, false, new Intent(AprendoAVestirmePreguntasActivity.this, AprendoAVestirmeMenuActivity.class));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(AprendoAVestirmePreguntasActivity.this, AprendoAVestirmeLeccionActivity.class);
        startActivity(intent);
    }


    @Override
    protected void MostrarFeedbackOpcionCorrecta(View opcionElegidaView, final Ejecutable onComplete)
    {
        ViewGroup container = (ViewGroup) findViewById(android.R.id.content);
        final Resources res = container.getResources();
        int defaultVelocityFast     = res.getDimensionPixelOffset(com.github.jinatonic.confetti.R.dimen.default_velocity_fast);

        final List<Bitmap> allPossibleConfetti = com.github.jinatonic.confetti.Utils.generateConfettiBitmaps(new int[] { Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE }, 40);
        final int numConfetti = allPossibleConfetti.size();
        final ConfettoGenerator generator = new ConfettoGenerator() {
            @Override
            public Confetto generateConfetto(Random random) {
                final Bitmap bitmap = allPossibleConfetti.get(random.nextInt(numConfetti));
                return new BitmapConfetto(bitmap);
            }
        };

        final Context context = container.getContext();
        final int x = container.getWidth() / 2;
        final int y = container.getHeight() / 2;
        final ConfettiSource confettiSource = new ConfettiSource(x, y);

        new ConfettiManager(context, generator, confettiSource, container)
            .setTTL(2500)
            .setBound(new Rect(
                    0, 0,
                    container.getWidth(), container.getHeight()
            ))
            .setVelocityX(0, defaultVelocityFast)
            .setVelocityY(0, defaultVelocityFast)
            .enableFadeOut(Utils.getDefaultAlphaInterpolator())
            .setInitialRotation(180, 180)
            .setRotationalAcceleration(360, 180)
            .setTargetRotationalVelocity(360)
            .setEmissionDuration(1000)
            .setEmissionRate(200)
            .animate();

        MediaPlayer.create(this,R.raw.respuesta_correcta).start();

        // Para continuar con un delay y darle tiempo a la animación
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(onComplete != null)
                {
                    onComplete.ejecutar();
                }
            }
        }, 3000);
    }

    @Override
    protected void MostrarFeedbackOpcionIncorrecta(View opcionElegidaView, final Ejecutable onComplete)
    {
        Toast toast = Toast.makeText(this, "INTENTA NUEVAMENTE", Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.show();

        // Para continuar con un delay y darle tiempo a la animación
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                if(onComplete != null)
                {
                    onComplete.ejecutar();
                }
            }
        }, 3000);
    }

    @Override
    protected void CuestionarioCompleto(View opcionElegidaView)
    {
        ViewGroup container = (ViewGroup) findViewById(android.R.id.content);
        final Resources res = container.getResources();
        int defaultVelocitySlow     = res.getDimensionPixelOffset(com.github.jinatonic.confetti.R.dimen.default_velocity_slow);
        int defaultVelocityNormal     = res.getDimensionPixelOffset(com.github.jinatonic.confetti.R.dimen.default_velocity_normal);

        final List<Bitmap> allPossibleConfetti = com.github.jinatonic.confetti.Utils.generateConfettiBitmaps(new int[] { Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE }, 40);
        final int numConfetti = allPossibleConfetti.size();
        final ConfettoGenerator generator = new ConfettoGenerator() {
            @Override
            public Confetto generateConfetto(Random random) {
                final Bitmap bitmap = allPossibleConfetti.get(random.nextInt(numConfetti));
                return new BitmapConfetto(bitmap);
            }
        };

        final Context context = container.getContext();
        final int x = container.getWidth() / 2;
        final int y = container.getHeight() / 2;
        final ConfettiSource confettiSource = new ConfettiSource(0, 0,
                container.getWidth(), 0);

        new ConfettiManager(context, generator, confettiSource, container)
                .setVelocityX(0, defaultVelocitySlow)
                .setVelocityY(defaultVelocityNormal, defaultVelocitySlow)
                .setInitialRotation(180, 180)
                .setRotationalAcceleration(360, 180)
                .setTargetRotationalVelocity(360)
                .setEmissionDuration(1500)
                .setEmissionRate(200)
                .animate();

        MediaPlayer.create(this,R.raw.cuestionario_completo).start();


//        Log.d("DANE","CUESTIONARIO COMPLETO");
//        Toast toast = Toast.makeText(this, "CUSTOM FEEDBACK CUESTIONARIO COMPLETO", Toast.LENGTH_SHORT);
//        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
//        toast.show();

        // Para continuar con un delay y darle tiempo a la animación
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(AprendoAVestirmePreguntasActivity.this, AprendoAVestirmeMenuActivity.class);
                startActivity(intent);
            }
        }, 3000);
    }

}
