package com.linechen.palletedemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private int count = 1;

    TextView getVibrantSwatch, getDarkVibrantSwatch, getLightVibrantSwatch,
            getMutedSwatch, getDarkMutedSwatch, getLightMutedSwatch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getVibrantSwatch = findViewById(R.id.getVibrantSwatch);
        getDarkVibrantSwatch = findViewById(R.id.getDarkVibrantSwatch);
        getLightVibrantSwatch = findViewById(R.id.getLightVibrantSwatch);
        getMutedSwatch = findViewById(R.id.getMutedSwatch);
        getDarkMutedSwatch = findViewById(R.id.getDarkMutedSwatch);
        getLightMutedSwatch = findViewById(R.id.getLightMutedSwatch);

        final ImageView image = findViewById(R.id.iv_image);
        final TextView tvText = findViewById(R.id.tv_text);
/**
 * Vibrant （有活力的）
 * Vibrant dark（有活力的 暗色）
 * Vibrant light（有活力的 亮色）
 * Muted （柔和的）
 * Muted dark（柔和的 暗色）
 * Muted light（柔和的 亮色）
 */
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = getBitmap(count);
                image.setImageBitmap(bitmap);
                count++;
                Palette generate = Palette.from(bitmap).generate();

                int vibrantColor = generate.getVibrantColor(Color.BLACK);
                int darkVibrantColor = generate.getDarkVibrantColor(Color.BLACK);
                int lightVibrantColor = generate.getLightVibrantColor(Color.WHITE);
                int mutedColor = generate.getMutedColor(Color.GRAY);
                int darkMutedColor = generate.getDarkMutedColor(Color.BLACK);
                int lightMutedColor = generate.getLightMutedColor(Color.WHITE);

                try {
                    tvText.setBackgroundColor(getColorWithAlpha(0.7f, darkMutedColor));
                    tvText.setTextColor(lightVibrantColor);

                    getVibrantSwatch.setBackgroundColor(vibrantColor);
                    getDarkVibrantSwatch.setBackgroundColor(darkVibrantColor);
                    getLightVibrantSwatch.setBackgroundColor(lightVibrantColor);
                    getMutedSwatch.setBackgroundColor(mutedColor);
                    getDarkMutedSwatch.setBackgroundColor(darkMutedColor);
                    getLightMutedSwatch.setBackgroundColor(lightMutedColor);
                } catch (Throwable t) {
                    Log.e("Pallete", t.getMessage());
                }
            }
        });
    }

    private Bitmap getBitmap(int count) {
        Bitmap result = null;
        int index = count % 5;
        switch (index) {
            case 0:
                result = BitmapFactory.decodeResource(getResources(), R.mipmap.i1);
                break;
            case 1:
                result = BitmapFactory.decodeResource(getResources(), R.mipmap.i2);
                break;
            case 2:
                result = BitmapFactory.decodeResource(getResources(), R.mipmap.i3);
                break;
            case 3:
                result = BitmapFactory.decodeResource(getResources(), R.mipmap.i4);
                break;
            case 4:
                result = BitmapFactory.decodeResource(getResources(), R.mipmap.i5);
                break;
            default:
                result = BitmapFactory.decodeResource(getResources(), R.mipmap.i4);
        }
        return result;
    }

    /**
     * RGB分解
     *
     * @param rgbColor 需要分解的颜色
     * @return int 数组
     */
    public static int[] getRGB(int rgbColor) {
        int red = (0xff0000 & rgbColor) >> 16;
        int green = (0xff00 & rgbColor) >> 8;
        int blue = (0xff & rgbColor);
        return new int[]{red, green, blue};
    }

    /**
     * 对rgb色彩加入透明度
     *
     * @param alpha     透明度，取值范围 0.0f -- 1.0f.
     * @param baseColor
     * @return a color with alpha made from base color
     */
    public static int getColorWithAlpha(float alpha, int baseColor) {
        int a = Math.min(255, Math.max(0, (int) (alpha * 255))) << 24;
        int rgb = 0x00ffffff & baseColor;
        return a + rgb;
    }

}
