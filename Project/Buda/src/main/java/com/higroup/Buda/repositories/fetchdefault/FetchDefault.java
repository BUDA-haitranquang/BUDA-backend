package com.higroup.Buda.repositories.fetchdefault;

public class FetchDefault {
    public static final String buyOrder = "select distinct b from BuyOrder b" +
    " LEFT JOIN FETCH b.buyOrderItems bo" +
    " LEFT JOIN FETCH bo.ingredient ingr " +
    " LEFT JOIN FETCH ingr.picture " +
    " LEFT JOIN FETCH b.supplier s" +
    " LEFT JOIN FETCH b.staff st" ;
    public static final String sellOrder = "select distinct s from SellOrder s "
    + "LEFT JOIN FETCH s.sellOrderItems si "
    + "LEFT JOIN FETCH si.product pr "
    + "LEFT JOIN FETCH pr.picture "
    + "LEFT JOIN FETCH s.customer c "
    + "LEFT JOIN FETCH s.staff ss ";
}
