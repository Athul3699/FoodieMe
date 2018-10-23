package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    int quantity=2;
    boolean topping;
    boolean cream;
    boolean chocolate;
    String emailId="athulputhanveetil@gmail.com";

    /**
     * This method is called when the order button is clicked.
     */


    public void composeEmail( String subject,String body) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, body);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

    }


    public void increment(View view){
        quantity=quantity+1;
       display(quantity);

    }

    public void decrement(View view){
        if(quantity>1) {
            quantity = quantity - 1;
        }
        display(quantity);

    }

   /* public void check(View view) {topping=true;}*/



    public void submitOrder(View view) {

        String priceMessage=orderSummary();
        displayMessage(priceMessage);
        composeEmail("Order ",priceMessage);

    }

    public int calcuatePrice(){
        int cash=quantity*5;
        if(cream){cash=cash+5;}
        if(chocolate){cash=cash+20;}
        return cash;
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }

    private String orderSummary() {

        EditText NameBox = (EditText) findViewById(R.id.name_field);
        String name = NameBox.getText().toString();
        String priceMessage;

        if (name.isEmpty()) {
            return "error";
        } else {

            priceMessage = name;
            priceMessage = priceMessage + "\nQuantity:" + quantity;

            CheckBox hasTopping = (CheckBox) findViewById(R.id.has_topping);
            cream = hasTopping.isChecked();
            CheckBox hasTopping2 = (CheckBox) findViewById(R.id.has_topping2);
            chocolate = hasTopping2.isChecked();
            priceMessage = priceMessage + "\nTotal:" + calcuatePrice();
            if (cream) {
                priceMessage = priceMessage + "\nTopping included";
            }
            if (chocolate) {
                priceMessage = priceMessage + "\nChocolate included";
            }
            priceMessage = priceMessage + "\nThankYou!!!";

            return priceMessage;
        }

    }






}