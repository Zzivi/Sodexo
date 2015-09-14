package com.zzivi.sodexo.cardsbalance.domain.mapper;

import com.zzivi.sodexo.cardsbalance.domain.model.CardBalanceResultModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import retrofit.client.Response;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;

/**
 * Created by daniel on 4/10/14.
 */
public class CardBalanceMapper {

    public List<CardBalanceResultModel> transform(Response pageContent) throws IOException {

        //transform api retrofit response to html string
        TypedInput body = pageContent.getBody();
        String bodyMime = body.mimeType();
        byte[] bodyBytes = ((TypedByteArray) pageContent.getBody()).getBytes();
        String bodyCharset = MimeUtil.parseCharset(bodyMime);
        String html = new String(bodyBytes, bodyCharset);

        //parse html to obtain cards balances
        List<CardBalanceResultModel> cardsBalance = new CopyOnWriteArrayList<CardBalanceResultModel>();
        org.jsoup.nodes.Document doc = Jsoup.parse(html);
        Elements hrefs = doc.getElementsByTag("a");
        for(Element href : hrefs){
            CardBalanceResultModel cardBalanceResult = new CardBalanceResultModel();
            Elements tarjetas = href.getElementsByClass("select-tarjetas");

            if(tarjetas.size()>0){
                String linkHref = tarjetas.text();
                String[] splited = linkHref.split("\\s+");
                cardBalanceResult.setCardName(splited[1]);
                cardBalanceResult.setCardBalance(splited[splited.length - 2]);
            }

            if(cardBalanceResult!=null && cardBalanceResult.getCardName()!=null && cardBalanceResult.getCardBalance()!=null) {
                cardsBalance.add(cardBalanceResult);

            }
        }
        return cardsBalance;
    }
}
