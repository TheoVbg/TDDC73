package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public void setCardVisibility (boolean visible, ImageView cardBack, TextView CVV)
    {
        if(visible)
        {
            cardBack.setVisibility(View.VISIBLE);
            CVV.setVisibility(View.VISIBLE);
        }
        else
        {
            cardBack.setVisibility(View.INVISIBLE);
            CVV.setVisibility(View.INVISIBLE);
        }

    }

    public void setExpiration (Spinner month, Spinner year, TextView exp)
    {
        String Month = month.getSelectedItem().toString();
        String Year = year.getSelectedItem().toString();

        if (Year.contains("Year")) {
            Year = "X";
        }
        else
        {
            Year = Year.substring(2,4);
        }

        if (Month.contains("Month"))
        {
            Month = "XX";
        }

        Month = Month.concat("/");
        String Exp = Month.concat(Year);

        exp.setText(Exp);

    }

    public void setLogo (EditText CardNumber, ImageView logo)
    {
        logo.setVisibility(View.INVISIBLE);
        String Number = CardNumber.getText().toString();
        if (Number.length()<1)
        {
            return;
        }
        String Numb1 = Number.substring(0,1);



        if (Numb1.contains("4"))
        {
            logo.setImageResource(R.drawable.visa);
            logo.setVisibility(View.VISIBLE);
        }
        if (Numb1.contains("5"))
        {
            logo.setImageResource(R.drawable.mastercard);
            logo.setVisibility(View.VISIBLE);
        }
        if (Numb1.contains("6"))
        {
            logo.setImageResource(R.drawable.discover);
            logo.setVisibility(View.VISIBLE);
        }
        if (Numb1.contains("3"))
        {
            if (Number.length()<2)
            {
                return;
            }
            String Numb2 = Number.substring(1,2);
            if (Numb2.contains("4")||Numb2.contains("7"))
            {
                logo.setImageResource(R.drawable.amex);
                logo.setVisibility(View.VISIBLE);
            }
            if (Numb2.contains("0")||Numb2.contains("6")||Numb2.contains("8"))
            {
                logo.setImageResource(R.drawable.dinersclub);
                logo.setVisibility(View.VISIBLE);
            }
        }
        return;
    }


    public void setNumbers (EditText CardNumber, TextView printCardNb)
    {
        String Number = CardNumber.getText().toString();
        if (Number.length()<1)
        {
            printCardNb.setText("#### #### #### ####");
            return;
        }
        String Numb1 = Number.substring(0,1);

        int length = 16-Number.length();
        int maxLength =  16;

        if (Numb1.contains("3"))
        {
            if (Number.length()<2)
            {

            }
            else
            {
                String Numb2 = Number.substring(1,2);
                if (Numb2.contains("4")||Numb2.contains("7"))
                {
                    length = 15-Number.length();
                    maxLength =  15;
                }
                if (Numb2.contains("0")||Numb2.contains("6")||Numb2.contains("8"))
                {
                    length = 14-Number.length();
                    maxLength =  14;
                }
            }

        }

        if (length<0)
        {
            length=0;
        }

        String fill = new String (new char[length]).replace('\0', '#');
        String textToPrint = Number.concat(fill);
        String textPrinted = new String(new char[20]);

        if(maxLength==16)
        {
            for(int part=0; part<4; part++)
            {
                textPrinted = textPrinted.concat(textToPrint.substring(part*4, part*4+4));
                textPrinted = textPrinted.concat(" ");
            }
        }

        if(maxLength==15)
        {
            for(int part=0; part<3; part++)
            {
                textPrinted = textPrinted.concat(textToPrint.substring(part*5, part*5+5));
                textPrinted = textPrinted.concat(" ");
            }
        }

        if(maxLength==14)
        {
            for(int part=0; part<3; part++)
            {
                textPrinted = textPrinted.concat(textToPrint.substring(part*4, part*4+4));
                textPrinted = textPrinted.concat(" ");
            }
            textPrinted = textPrinted.concat(textToPrint.substring(12, 14));
        }



        printCardNb.setText(textPrinted);


        return;
    }

    public void setCVV (EditText CardNumber, EditText CVVEdit, TextView printCVV)
    {
        String Number = CardNumber.getText().toString();
        String CVV = CVVEdit.getText().toString();
        int lgCVV = CVV.length();

        String textEntered = "";

        if(lgCVV==0)
        {
            return;
        }

        if(lgCVV>3)
        {
            textEntered = CVV.substring(0, 3);
        }
        else
        {
            textEntered = CVV.substring(0, lgCVV);
        }



        if (Number.length()<1)
        {
            CVVEdit.setText(textEntered);
            return;

        }
        String Numb1 = Number.substring(0,1);




        if (Numb1.contains("3"))
        {
            if (Number.length()<2||lgCVV<4)
            {

            }
            else
            {
                String Numb2 = Number.substring(1,2);
                if (Numb2.contains("4")||Numb2.contains("7"))
                {
                    textEntered = CVVEdit.getText().toString().substring(0, 4);
                    printCVV.setText(textEntered);
                    return;
                }

            }

        }


        printCVV.setText(textEntered);
        return;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinnerM = findViewById(R.id.month);
        ArrayAdapter<CharSequence> adapterM = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_spinner_item);
        adapterM.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerM.setAdapter(adapterM);
        spinnerM.setOnItemSelectedListener(this);

        Spinner spinnerY = findViewById(R.id.year);
        ArrayAdapter<CharSequence> adapterY = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_spinner_item);
        adapterY.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerY.setAdapter(adapterY);
        spinnerY.setOnItemSelectedListener(this);


        TextView printCardNb = findViewById(R.id.printCardNb);
        EditText cardNbEdit = findViewById(R.id.CardNb);

        TextView printCardName = findViewById(R.id.printCardName);
        EditText cardNameEdit = findViewById(R.id.CardName);

        TextView printExp = findViewById(R.id.printCardExp);

        Spinner monthEdit = findViewById(R.id.month);
        Spinner yearEdit = findViewById(R.id.year);

        EditText CVVEdit = findViewById(R.id.CVV);

        ImageView cardBack = findViewById(R.id.cardBack);
        TextView printCVV = findViewById(R.id.printCardCVV);
        final boolean[] visibility = {false};

        ImageView logo = findViewById(R.id.logo);



        monthEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                visibility[0] = false;
                setCardVisibility(visibility[0], cardBack, printCVV);
                setExpiration(monthEdit, yearEdit, printExp);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setExpiration(monthEdit, yearEdit, printExp);
            }
        });

        yearEdit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                visibility[0] = false;
                setCardVisibility(visibility[0], cardBack, printCVV);
                setExpiration(monthEdit, yearEdit, printExp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                setExpiration(monthEdit, yearEdit, printExp);
            }
        });






        CVVEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setCardVisibility(visibility[0], cardBack, printCVV);
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                visibility[0] = true;
                setCVV (cardNbEdit, CVVEdit, printCVV);
                setCardVisibility(visibility[0], cardBack, printCVV);

            }

            @Override
            public void afterTextChanged(Editable editable) {
                setCardVisibility(visibility[0], cardBack, printCVV);

            }
        });









        cardNameEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                visibility[0] = false;
                setCardVisibility(visibility[0], cardBack, printCVV);
                String textEntered = cardNameEdit.getText().toString();
                printCardName.setText(textEntered.toUpperCase());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });









        cardNbEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                setLogo(cardNbEdit, logo);
                visibility[0] = false;
                setCardVisibility(visibility[0], cardBack, printCVV);

                setNumbers(cardNbEdit, printCardNb);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}