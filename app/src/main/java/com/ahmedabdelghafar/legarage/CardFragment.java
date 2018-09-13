package com.ahmedabdelghafar.legarage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by orcl on 21/02/2018.
 */

public class CardFragment extends Fragment{
    private CardView cardView;

    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager, container, false);

        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        TextView title = (TextView) view.findViewById(R.id.title_text);

        ImageView img= (ImageView) view.findViewById(R.id.imageView_to_card);


        title.setText(String.format("Card %d", getArguments().getInt("position")));
        int ddd = getArguments().getInt("position");

        switch (ddd) {
            case 0:
                title.setText("A collectable object such as a piece of furniture or work of art that has a high value because of its age and quality.");
                img.setImageResource(R.drawable.photo2);
                break;
            case 1:
                title.setText("Furniture Antique");
                img.setImageResource(R.drawable.photo3);
                break;
            case 2:
                title.setText("an object of considerable age valued for its aesthetic or historical significance. In the antiques trade, the term refers to objects more than 100 years old.");
                img.setImageResource(R.drawable.photo1);
                break;
            case 3:
                title.setText("Buyers in the know respect sellers who understand how to correctly describe their wares.");
                img.setImageResource(R.drawable.photo4);
                break;
            case 4:
                title.setText("Different and unique personal possessions");
                img.setImageResource(R.drawable.photo5);
                break;

            case 5:
                title.setText("An antique is an old-fashioned thing, like a lamp from the sixties. Anything antique is old or at least old-ish.");
                img.setImageResource(R.drawable.photo6);
                break;

            case 6:
                title.setText("Old objects of a special nature in a certain category of persons");
                img.setImageResource(R.drawable.photo7);
                break;

            case 7:
                title.setText("Women and men accessories made of the finest materials");
                img.setImageResource(R.drawable.photo8);
                break;

            default:
                title.setText("Le Garage 4");
                img.setImageResource(R.drawable.photo4);
                break;
        }
        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
