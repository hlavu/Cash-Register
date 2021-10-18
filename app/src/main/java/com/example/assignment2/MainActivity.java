package com.example.assignment2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    Button btnOne;
    Button btnTwo;
    Button btnThree;
    Button btnFour;
    Button btnFive;
    Button btnSix;
    Button btnSeven;
    Button btnEight;
    Button btnNine;
    Button btnZero;
    Button clearBtn;

    TextView displayQty;
    TextView displayProductType;
    TextView displayTotal;
    ListView productList;

    ArrayList<Product> products;
    ArrayList<History> histories = new ArrayList<History>();
    History history;

    ProductAdapter adapter;
    int productQtyLeft;
    double productPrice = 0.00;
    int currentProduct;

    String productName="";
    String qty="";
    String total;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        displayQty = (TextView) findViewById(R.id.qty);
        displayTotal = (TextView) findViewById(R.id.total);
        displayProductType = (TextView) findViewById(R.id.productType);

        products = new ArrayList<Product>(3);

        productList = (ListView)findViewById(R.id.list_view);
        builder = new AlertDialog.Builder(this);

        if (savedInstanceState != null) {
            productName = savedInstanceState.getString("name");
            qty = savedInstanceState.getString("qty");
            total = savedInstanceState.getString("total");
            productPrice = savedInstanceState.getDouble("price");
            productQtyLeft = savedInstanceState.getInt("qtyLeft");
            products = savedInstanceState.getParcelableArrayList("products");
            histories = savedInstanceState.getParcelableArrayList("histories");
        } else {
            Product pants = new Product("Pants", 20.44, 10 );
            Product shoes = new Product("Shoes", 10.44, 100);
            Product hats = new Product("Hats", 5.90, 30);
            products.add(pants);
            products.add(shoes);
            products.add(hats);
        }

        adapter = new ProductAdapter(this, products);
        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                displayProductType.setText(products.get(i).name);
                productQtyLeft = products.get(i).qty;
                productPrice = products.get(i).price;
                productName = products.get(i).name;
                currentProduct = i; // save index of the current Product to change qtyLeft
                if(!qty.equals("")){
                    total = String.valueOf(Math.round(Integer.parseInt(qty)*productPrice*100.00)/100.00);
                    displayTotal.setText(total);
                }
            }
        });

        clearBtn = (Button) findViewById(R.id.delbtn);

        btnZero = (Button) findViewById(R.id.btn0);
        btnOne = (Button) findViewById(R.id.btn1);
        btnTwo = (Button) findViewById(R.id.btn2);
        btnThree = (Button) findViewById(R.id.btn3);
        btnFour = (Button) findViewById(R.id.btn4);
        btnFive = (Button) findViewById(R.id.btn5);
        btnSix = (Button) findViewById(R.id.btn6);
        btnSeven = (Button) findViewById(R.id.btn7);
        btnEight = (Button) findViewById(R.id.btn8);
        btnNine = (Button) findViewById(R.id.btn9);

        clearBtn.setOnClickListener(this);

        btnZero.setOnClickListener(this);
        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);

        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Button btn = (Button) view;
        if(btn.getText().toString().equals("C")){
            displayQty.setText("");
            displayTotal.setText("");
            qty = "";
            total = "";
        } else {
            qty += btn.getText().toString();
            displayQty.setText(qty);
            if(!displayProductType.getText().toString().equals("")){
                total = String.valueOf(Math.round(Integer.parseInt(qty)*productPrice*100.00)/100.00);
                displayTotal.setText(total);
            }
        }
    }

    public void buyBtnClicked(View view) {
        if(displayProductType.getText().toString().equals("") || displayQty.getText().toString().equals("")){
            Toast.makeText(this,"All fields are required!!!",Toast.LENGTH_LONG).show();
        }else if(Integer.parseInt(qty) == 0.00){
            Toast.makeText(this,"Quantity should be more than 0!!!",Toast.LENGTH_LONG).show();
        }else if(Integer.parseInt(qty) > productQtyLeft){
            Toast.makeText(this,"Not enough quantity in the stock!!!",Toast.LENGTH_LONG).show();
        } else {
            builder.create();
            builder.setTitle("Thank you for your purchase");
            products.get(currentProduct).qty-= Integer.parseInt(qty);
            productQtyLeft = products.get(currentProduct).qty;
            adapter = new ProductAdapter(this, products);
            productList.setAdapter(adapter);
            builder.setMessage("Your purchase is " + qty + " " + productName + " for " + total);
            builder.show();
            @SuppressLint("SimpleDateFormat") DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            Date date = new Date();
            history = new History(productName, qty, total, date.toString());
            histories.add(history);
            }
    }

    public void managerBtnClicked(View view) {
        Intent myIntent = new Intent(this, ManagerLayout.class);
        myIntent.putParcelableArrayListExtra("history", histories);
        startActivity(myIntent);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("name", productName);
        outState.putString("qty", qty);
        outState.putString("total", total);
        outState.putDouble("price", productPrice);
        outState.putInt("qtyLeft", productQtyLeft);
        outState.putParcelableArrayList("products", products);
        outState.putParcelableArrayList("histories", histories);
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        displayProductType.setText(savedInstanceState.getString("name"));
        displayQty.setText(savedInstanceState.getString("qty"));
        displayTotal.setText(savedInstanceState.getString("total"));
        products = savedInstanceState.getParcelableArrayList("products");
        histories = savedInstanceState.getParcelableArrayList("histories");
    }

}
