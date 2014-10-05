package com.zzivi.sodexo.cardsbalance.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zzivi.sodexo.R;
import com.zzivi.sodexo.cardsbalance.view.model.CardBalanceItem;

import java.util.List;

/**
 * Created by daniel on 5/10/14.
 */
public class CardBalanceListAdapter extends BaseAdapter {

    private List<CardBalanceItem> cardBalanceItems;
    private Context context;

    public CardBalanceListAdapter(Context context, List<CardBalanceItem> cardBalanceItems) {
        this.cardBalanceItems = cardBalanceItems;
        this.context = context;
    }

    @Override
    public int getCount() {
        return cardBalanceItems.size();
    }

    @Override
    public Object getItem(int i) {
        return cardBalanceItems.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {

        ViewHolder viewHolder;
        CardBalanceItem cardBalance = (CardBalanceItem) getItem(i);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_cardbalance, viewGroup, false);
            viewHolder = new ViewHolder();
            viewHolder.cardNameView = (TextView) convertView.findViewById(R.id.tvCardName);
            viewHolder.cardBalanceView = (TextView) convertView.findViewById(R.id.tvCardBalance);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.cardNameView.setText(cardBalance.getCardName());
        viewHolder.cardBalanceView.setText(cardBalance.getCardBalance());

        return convertView;

    }

    public class ViewHolder{
        TextView cardNameView;
        TextView cardBalanceView;
    }
}
