package com.example.dzarflowersapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dzarflowersapp.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<Product>();
    BoxAdapter boxAdapter;

    private ActivityMainBinding binding;

    Button  btnOk;

    View header, footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        header = createHeader("Магазин цветов");
        footer = createFooter("Футер");

        fillData();

        TextView footerTextView = findViewById(R.id.tvText); // Находим текстовое поле в футере
        boxAdapter = new BoxAdapter(this, products, footerTextView); // Передаем его в адаптер

        ListView lvMain = binding.lvMain;
        lvMain.addHeaderView(header);
        //lvMain.addFooterView(footer);
        lvMain.setAdapter(boxAdapter);

        Button btnGo = findViewById(R.id.btnGo);

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Product> selectedProducts = boxAdapter.getBox();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putParcelableArrayListExtra("selectedProducts", selectedProducts);
                startActivity(intent);
            }
        });
    }

    View createHeader(String text) {
        View v = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }

    View createFooter(String text) {
        View v = getLayoutInflater().inflate(R.layout.footer, null);
        //((TextView) v.findViewById(R.id.tvText)).setText(text);
        return v;
    }


    private String[] flowerNames = {"Роза", "Лилия", "Тюльпан", "Орхидея", "Пион", "Гиацинт", "Астра", "Хризантема",
            "Нарцисс", "Гвоздика", "Маргаритка", "Ирис", "Гербера", "Петуния", "Лаванда",
            "Фиалка", "Сирень", "Мимоза", "Зверобой", "Василек"};
    private int[] prices = {340, 240, 150, 200, 340, 400, 230, 130, 540, 420, 690, 190, 350, 150, 410, 310, 230, 430, 320, 200};
    private int[] images = {R.drawable.rose, R.drawable.lily, R.drawable.tulpan, R.drawable.orhidea, R.drawable.pion, R.drawable.giacint,
            R.drawable.astra, R.drawable.hrizantema, R.drawable.narciss, R.drawable.gvozdika, R.drawable.margaritka, R.drawable.iris, R.drawable.gerbera,
            R.drawable.petunia, R.drawable.lavanda, R.drawable.fialka, R.drawable.siren, R.drawable.mimoza, R.drawable.zveroboy, R.drawable.vasilek};
    void fillData() {
        for (int i = 0; i < flowerNames.length; i++) {
            products.add(new Product(flowerNames[i], prices[i], images[i], false));
        }
    }




    public void showResult(View v) {
        String result = "Товары в корзине:";
        for (Product p : boxAdapter.getBox()) {
            if (p.box) {
                result += "\n" + p.name;
            }
        }
        Toast.makeText(this,result, Toast.LENGTH_LONG).show();
    }
}