package test.net.atshq.fingerpathdrawing;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.rarepebble.colorpicker.ColorPickerView;

public class ColorPickerDialog {
    private Context context;
    private Dialog dialog;

    public static int COLOR=0;

    DialogToView dialogToView;

    public ColorPickerDialog(Context context) {
        this.context = context;
        dialog=new Dialog(context);
        dialogToView = new MainActivity();
        createDialog();
    }

    protected void createDialog(){
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.color_picker_dialog,null,false);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);
        dialog.setCancelable(true);

        Button btnSelect = dialog.findViewById(R.id.btnSelectColor);

        final ColorPickerView cp = dialog.findViewById(R.id.colorPicker);
        cp.setColor(Color.RED);
        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                COLOR = cp.getColor();
                dialogToView.ColorChange(COLOR);
                dialog.dismiss();
            }
        });

    }

    public void show(){
        dialog.show();
    }
}
