package test.net.atshq.fingerpathdrawing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageShowActivity extends AppCompatActivity {

    private ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_show);
        ivImage = findViewById(R.id.ivImage);

        if(MainActivity.mBitmap!=null){
            ivImage.setImageBitmap(MainActivity.mBitmap);
        }
    }
}
