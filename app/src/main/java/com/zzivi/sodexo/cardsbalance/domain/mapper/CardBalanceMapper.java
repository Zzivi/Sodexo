package com.zzivi.sodexo.cardsbalance.domain.mapper;

import android.provider.DocumentsContract.Document;

import com.zzivi.sodexo.cardsbalance.domain.model.CardBalanceResultModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by daniel on 4/10/14.
 */
public class CardBalanceMapper {

    public List<CardBalanceResultModel> transform(String pageContent) throws IOException {
        List<CardBalanceResultModel> cardsBalance = new CopyOnWriteArrayList<CardBalanceResultModel>();
        org.jsoup.nodes.Document doc = Jsoup.parse(pageContent);
        Elements trs = doc.getElementsByTag("tr");
        for(Element tr : trs){
            CardBalanceResultModel cardBalanceResult = new CardBalanceResultModel();
            Elements tarjetas = tr.getElementsByClass("tarjetaTipo");
            if(tarjetas.size()>0){
                cardBalanceResult.setCardName(tarjetas.get(0).text());
            }
            Elements saldos = tr.getElementsByClass("tarjetaSaldo");
            if(saldos.size()>0){
                cardBalanceResult.setCardBalance(saldos.get(0).text());
            }
            if(cardBalanceResult!=null && cardBalanceResult.getCardName()!=null && cardBalanceResult.getCardBalance()!=null) {
                cardsBalance.add(cardBalanceResult);

            }
        }
        return cardsBalance;
    }
}
