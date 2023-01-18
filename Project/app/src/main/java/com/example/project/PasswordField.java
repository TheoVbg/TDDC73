package com.example.project;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import javax.security.auth.callback.PasswordCallback;

public class PasswordField extends FrameLayout {
    Context c;
    EditText passwordEntered;
    EditText passwordVerification;
    TextView NotMatchingPasswords;
    LinearLayout ConditionFrame;
    LinearLayout NotMatchingPasswordsFrame;
    TextView Condition1;
    TextView Condition2;
    TextView Condition3;
    TextView Condition4;
    passwordsValidsCallback passwordsCallback;

    public void setPasswordCallback (passwordsValidsCallback passwordsCallback)
    {
        this.passwordsCallback = passwordsCallback;
    }


    //Variables used
    boolean Cond1Filled;
    boolean Cond2Filled;
    boolean Cond3Filled;
    boolean Cond4Filled;
    boolean AllConditionsFilled;
    boolean ErrorInPassword;


    public PasswordField(@NonNull Context context) {
        super(context);
        this.c = context;
        init();
    }

    public PasswordField(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.c = context;
        init();
    }

    public PasswordField(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.c = context;
        init();
    }

    public PasswordField(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private void init() {
        //Initialise the component
        LayoutInflater inflator = (LayoutInflater) this.c.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View v = inflator.inflate(R.layout.layout_password, null);

        //Import all the components of the XML
        passwordEntered = v.findViewById(R.id.passwordEntered);
        NotMatchingPasswords = v.findViewById(R.id.NotMatchingPasswords);
        passwordVerification = v.findViewById(R.id.passwordVerification);
        ConditionFrame = v.findViewById(R.id.ConditionFrame);
        NotMatchingPasswordsFrame = v.findViewById(R.id.NotMatchingPasswordsFrame);
        Condition1 = v.findViewById(R.id.Condition1);
        Condition2 = v.findViewById(R.id.Condition2);
        Condition3 = v.findViewById(R.id.Condition3);
        Condition4 = v.findViewById(R.id.Condition4);


        //Initialise the local variables
        Cond1Filled = false;
        Cond2Filled = false;
        Cond3Filled = false;
        Cond4Filled = false;
        AllConditionsFilled = false;
        ErrorInPassword = false;



        //Initialise the visibility of the frames
        ConditionFrame.setVisibility(View.INVISIBLE);
        NotMatchingPasswordsFrame.setVisibility(View.INVISIBLE);
        NotMatchingPasswords.setTextColor(Color.RED);



        this.addView(v);








        passwordEntered.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Print the frame of condition to fill
                ConditionFrame.setVisibility(View.VISIBLE);
                setConditions(passwordEntered.getText().toString());
                printConditions();

                //Check the match with the otehr password
                checkMatch();

                //Do the callback on the validity of the passwords
                passwordsCallback.toDO(arePasswordsValids());


            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





        passwordVerification.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Print the frame of matching with the first password
                NotMatchingPasswordsFrame.setVisibility(View.VISIBLE);
                passwordVerification.setTextColor(Color.RED);

                //Check if the 2 passwords match
                checkMatch();

                //Do the callback
                passwordsCallback.toDO(arePasswordsValids());

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });




    }



    private void setConditions (String password)
    {
        //Permits to check each condition to fill

        //Initialisation
        Cond1Filled = false;
        Cond2Filled = false;
        Cond3Filled = false;
        Cond4Filled = false;
        AllConditionsFilled = false;
        ErrorInPassword = false;

        boolean lowerCase = false;
        boolean upperCase = false;
        boolean symbol = false;
        boolean number = false;

        int pLength = password.length();


        //Check each char of the password entered to determine the character types (lowerCase, upperCase, symbol, number ...)

        for (char character: password.toCharArray())
        {
            if (character<33)
            {
                ErrorInPassword = true;
                continue;
            }
            if (character<48)
            {
                symbol = true;
                continue;
            }
            if (character<58)
            {
                number = true;
                continue;
            }
            if (character<65)
            {
                symbol = true;
                continue;
            }
            if (character<91)
            {
                upperCase = true;
                continue;
            }
            if (character<97)
            {
                symbol = true;
                continue;
            }
            if (character<123)
            {
                lowerCase = true;
                continue;
            }
            if (character<127)
            {
                symbol = true;
                continue;
            }
            ErrorInPassword = true;
        }


        //Fill or not each condition
        if (pLength>7)
        {
            Cond1Filled = true;
        }

        Cond2Filled = upperCase && lowerCase;

        Cond3Filled = number;

        Cond4Filled = symbol;

        AllConditionsFilled = Cond1Filled&&Cond2Filled&&Cond3Filled&&Cond4Filled;

    }

    private void printConditions ()
    {
        //Permits to print the condition in red if unfilled and green if filled

        //Initialisation
        Condition1.setTextColor(Color.RED);
        Condition2.setTextColor(Color.RED);
        Condition3.setTextColor(Color.RED);
        Condition4.setTextColor(Color.RED);

        if(Cond1Filled)
        {
            Condition1.setTextColor(Color.GREEN);
        }
        if(Cond2Filled)
        {
            Condition2.setTextColor(Color.GREEN);
        }
        if(Cond3Filled)
        {
            Condition3.setTextColor(Color.GREEN);
        }
        if(Cond4Filled)
        {
            Condition4.setTextColor(Color.GREEN);
        }

        //If all the conditions are filled the frame disapear
        if (Cond1Filled&&Cond2Filled&&Cond3Filled&&Cond4Filled)
        {
            passwordEntered.setTextColor(Color.BLACK);
            ConditionFrame.setVisibility(View.INVISIBLE);
        }
        else
        {
            passwordEntered.setTextColor(Color.RED);
        }

    }

    private boolean checkMatch ()
    {
        //Check if the 2 passwords matches
        String password1 = passwordEntered.getText().toString();
        String password2 = passwordVerification.getText().toString();


        if (password1.equals(password2))
        {
            NotMatchingPasswordsFrame.setVisibility(View.INVISIBLE);
            passwordVerification.setTextColor(Color.BLACK);
            return true;
        }

        return false;
    }


    //Check if the passwords are valids and usable
    public boolean arePasswordsValids ()
    {
        return checkMatch()&&AllConditionsFilled;
    }


    public interface passwordsValidsCallback{
        void toDO(boolean arePasswordsValids);
    }

}
