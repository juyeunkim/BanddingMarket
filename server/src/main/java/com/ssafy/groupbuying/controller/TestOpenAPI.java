package com.ssafy.groupbuying.controller;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.io.BufferedReader;
import java.io.IOException;

public class TestOpenAPI {
    public static void main(String[] args) throws IOException {
        StringBuilder urlBuilder = new StringBuilder("http://api.data.go.kr/openapi/tn_pubr_public_cctv_api"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=서비스키"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("type","UTF-8") + "=" + URLEncoder.encode("xml", "UTF-8")); /*XML/JSON 여부*/
        urlBuilder.append("&" + URLEncoder.encode("institutionNm","UTF-8") + "=" + URLEncoder.encode("구례군청", "UTF-8")); /*관리기관명*/
        urlBuilder.append("&" + URLEncoder.encode("rdnmadr","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*소재지도로명주소*/
        urlBuilder.append("&" + URLEncoder.encode("lnmadr","UTF-8") + "=" + URLEncoder.encode("전라남도 구례군 간전면 간문리 307-7", "UTF-8")); /*소재지지번주소*/
        urlBuilder.append("&" + URLEncoder.encode("installationPurpsType","UTF-8") + "=" + URLEncoder.encode("차량방범", "UTF-8")); /*설치목적구분*/
        urlBuilder.append("&" + URLEncoder.encode("cctvNumber","UTF-8") + "=" + URLEncoder.encode("3", "UTF-8")); /*카메라대수*/
        urlBuilder.append("&" + URLEncoder.encode("cctvPixel","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*카메라화소수*/
        urlBuilder.append("&" + URLEncoder.encode("potogrfInfo","UTF-8") + "=" + URLEncoder.encode("간문삼거리", "UTF-8")); /*촬영방면정보*/
        urlBuilder.append("&" + URLEncoder.encode("cstdyDay","UTF-8") + "=" + URLEncoder.encode("30", "UTF-8")); /*보관일수*/
        urlBuilder.append("&" + URLEncoder.encode("installationYymm","UTF-8") + "=" + URLEncoder.encode("2008-01", "UTF-8")); /*설치년월*/
        urlBuilder.append("&" + URLEncoder.encode("phoneNumber","UTF-8") + "=" + URLEncoder.encode("061-780-2601", "UTF-8")); /*관리기관전화번호*/
        urlBuilder.append("&" + URLEncoder.encode("latitude","UTF-8") + "=" + URLEncoder.encode("35.180100", "UTF-8")); /*위도*/
        urlBuilder.append("&" + URLEncoder.encode("hardness","UTF-8") + "=" + URLEncoder.encode("127.543400", "UTF-8")); /*경도*/
        urlBuilder.append("&" + URLEncoder.encode("referenceDate","UTF-8") + "=" + URLEncoder.encode("2019-06-26", "UTF-8")); /*데이터기준일자*/
        urlBuilder.append("&" + URLEncoder.encode("insttCode","UTF-8") + "=" + URLEncoder.encode("4870000", "UTF-8")); /*제공기관코드*/
        urlBuilder.append("&" + URLEncoder.encode("insttNm","UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*제공기관명*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
    }
}