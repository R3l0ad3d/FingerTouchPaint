package test.net.atshq.fingerpathdrawing;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.rarepebble.colorpicker.ColorPickerView;

public class MainActivity extends AppCompatActivity implements DialogToView {
    private static PaintView paintView;
    private SeekBar sbBrushSize;
    private static Button btnColor;
    private static ImageView ivPreview;

    public static Bitmap mBitmap;

    public static int COLOR = Color.RED;
    public static int RADIUS=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paintView = findViewById(R.id.paintView);
        sbBrushSize = findViewById(R.id.seekBarBrushSize);
        btnColor = findViewById(R.id.btnColor);
        ivPreview = findViewById(R.id.ivPreview);
        paintInit();
        //CircleDraw(10);
        createBitMap(10/2);
        btnColor.setBackgroundColor(COLOR);
        sbBrushSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                paintView.BRUSH_SIZE=progress;

                paintView.setStrokeWidth(progress);
                //CircleDraw(progress);

                if(progress>2){
                    RADIUS=progress/3;
                    createBitMap(RADIUS);
                }else {
                    RADIUS=1;
                    createBitMap(1);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        final ColorPickerDialog dd = new ColorPickerDialog(MainActivity.this);
        btnColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dd.show();
            }
        });
    }

    private void paintInit() {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),R.drawable.img);

        paintView.init(metrics,bmp);
        paintView.emboss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.save){
            mBitmap=paintView.getmBitmap();
            startActivity(new Intent(this,ImageShowActivity.class));
        }
        if(item.getItemId()==R.id.color){
           paintView.UndoPath();

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void ColorChange(int color) {
        btnColor.setBackgroundColor(color);
        paintView.setDefaultColor(color);
        COLOR=color;
        createBitMap(RADIUS);
    }



    private void createBitMap(int radius) {
        // Create a mutable bitmap
        Bitmap bitMap = Bitmap.createBitmap(48, 48, Bitmap.Config.ARGB_8888);

        bitMap = bitMap.copy(bitMap.getConfig(), true);
        // Construct a canvas with the specified bitmap to draw into
        Canvas canvas = new Canvas(bitMap);
        // Create a new paint with default settings.
        Paint paint = new Paint();
        // smooths out the edges of what is being drawn
        paint.setAntiAlias(true);
        // set color
        paint.setColor(COLOR);
        // set style
        paint.setStyle(Paint.Style.FILL);
        paint.setXfermode(null);
        paint.setAlpha(0xff);
        // draw circle with radius 30
        canvas.drawCircle(24, 24, radius, paint);
        // set on ImageView or any other view
        ivPreview.setImageBitmap(bitMap);

    }
}
