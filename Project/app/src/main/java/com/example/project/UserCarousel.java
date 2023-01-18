package com.example.project;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.w3c.dom.Text;

public class UserCarousel extends FrameLayout {
    Context c;
    LinearLayout usersList;
    int userNumber;
    LinearLayout actualUser;


    public UserCarousel(@NonNull Context context) {
        super(context);
        this.c=context;
        init();
    }

    public UserCarousel(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.c=context;
        init();
    }

    public UserCarousel(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.c=context;
        init();
    }

    public UserCarousel(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.c=context;
        init();
    }

    private void init ()
    {
        //Initialise the component
        LayoutInflater inflator = (LayoutInflater) this.c.getSystemService((Context.LAYOUT_INFLATER_SERVICE));
        View v = inflator.inflate(R.layout.layout_carousel, null);

        usersList = v.findViewById(R.id.usersList);



        this.addView(v);
    }

    public void addUser (String name, String pictureName, boolean status)
    {
        //Add an user in the userlist

        //Linear Layout that contains the views
        LinearLayout UserLayout = new LinearLayout(c);
        UserLayout.setOrientation(LinearLayout.VERTICAL);

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.setMargins(0,0,30,0);

        UserLayout.setLayoutParams(params);
        UserLayout.setGravity(Gravity.CENTER);

        actualUser = UserLayout;

        //Add the picture with the function
        addUserPicture(pictureName);
        //Add the name with the function
        addUserName(name, status);

        //Add this user to the list
        usersList.addView(UserLayout);

    }

    private void addUserPicture (String picturePath)
    {

        //Set a white border around the image
        CardView whiteBorder = new CardView(c);

        FrameLayout.LayoutParams paramsWhiteBorder = new FrameLayout.LayoutParams(205,205);
        whiteBorder.setLayoutParams(paramsWhiteBorder);
        whiteBorder.setRadius(250);
        whiteBorder.setBackgroundColor(Color.WHITE);




        //Set the circle shape to the image
        CardView circleShape = new CardView(c);


        RelativeLayout.LayoutParams paramsCircleShape = new RelativeLayout.LayoutParams(200,200);
        paramsCircleShape.addRule(RelativeLayout.CENTER_IN_PARENT);
        circleShape.setLayoutParams(paramsCircleShape);
        circleShape.setRadius(250);



        //Put the image
        ImageView image = new ImageView(c);

        int id = getResources().getIdentifier(picturePath, "drawable", c.getPackageName());

        image.setImageResource(id);

        LinearLayout.LayoutParams paramsImage = new LinearLayout.LayoutParams(200, 200);
        image.setLayoutParams(paramsImage);
        image.setScaleType(ImageView.ScaleType.CENTER_CROP);


        //Put the layers on top of each other
        circleShape.addView(image);
        whiteBorder.addView(circleShape);

        actualUser.addView(whiteBorder);





    }


    private void addUserName (String name, boolean status)
    {
        //Function that create the view for the username and print the status (online, offline)

        //Linear Layout that contains the views
        LinearLayout Text = new LinearLayout(c);


        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        params.setMargins(-20,-40,0,0);

        Text.setLayoutParams(params);
        Text.setOrientation(LinearLayout.HORIZONTAL);
        Text.setGravity(Gravity.CENTER);

        LayoutParams Textparams = new LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        //Create the red or green dot for the status

        TextView Status = new TextView(c);
        Status.setText("•");
        Status.setTextSize(40);

        if(status)
        {
            Status.setTextColor(Color.GREEN);
        }
        else
        {
            Status.setTextColor(Color.RED);
        }

        Status.setLayoutParams(Textparams);

        //Print the user name

        TextView Name = new TextView(c);
        Name.setText(name);
        Name.setTextSize(12);
        Name.setTextColor(Color.BLACK);
        Name.setLayoutParams(Textparams);


        Text.addView(Status);
        Text.addView(Name);
        actualUser.addView(Text);
    }


}
