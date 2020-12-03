package com.example.myapplication2;

import android.net.Uri;

import java.util.ArrayList;

public class sqlReturn{
    public static int model;
    public static String GetUserID;
    public static int GetCurrentCount;
    // 此為login資料
    public static String LoginTextViewContext = null;
    public static int LoginCount;
    public static String[] LoginContent;
    public static String[] LoginMood;
    public static String[] LoginTagName;
    public static String[] LoginDate;
    public static String[] LoginOption;
    public static String[] LoginDiaryID;
    public static boolean googleLogin = false;
    // 此為歷史抓心情
    public static String textViewContext1 = null;
    public static int SearchCountMood;
    public static String[] content1;
    public static String[] mood1;
    public static String[] tagName1;
    public static String[] date1;
    public static String[] Option1;
    public static String[] DiaryID1;
    // 此為歷史抓主題
    public static String textViewContext2 = null;
    public static int SearchCountTag;
    public static String[] content2;
    public static String[] mood2;
    public static String[] tagName2;
    public static String[] date2;
    public static String[] Option2;
    public static String[] DiaryID2;
    // 此為歷史抓手寫日記
    public static String textViewContext3 = null;
    public static int SearchCountHandWrite;
    public static String[] content3;
    public static String[] mood3;
    public static String[] tagName3;
    public static String[] date3;
    public static String[] Option3;
    public static String[] DiaryID3;
    // 此為歷史抓拍照日記
    public static String textViewContext4 = null;
    public static int SearchCountTakePhoto;
    public static String[] content4;
    public static String[] mood4;
    public static String[] tagName4;
    public static String[] date4;
    public static String[] Option4;
    public static String[] DiaryID4;
    // 此為社群好友貼文全抓
    public static String textViewContextFriend = null;
    public static int SearchCountFriend;
    public static String[] contentFriend;
    public static String[] moodFriend;
    public static String[] tagNameFriend;
    public static String[] dateFriend;
    public static String[] friendName;
    public static String[] friendBFF;
    public static String[] friendImage;
    public static String[] friendPersonImage;
    // 此為抓好友
    public static String textViewContextFriendList = null;
    public static int SearchCountFriendList;
    public static String[] friendListName;
    public static String[] friendListNum;
    public static String[] friendListBFF;
    public static String[] friendListPersonImage;
    // 此為抓摯友
    public static String textViewContextBestFriendList = null;
    public static int SearchCountBestFriendList;
    public static String[] BestFriendListName;
    public static String[] BestFriendListNum;
    public static String[] BestFriendListPersonImage;
    // 此為抓特定好友日記
    public static String textViewContextfriendSearch = null;
    public static int friendSearchCount;
    public static String[] friendSearchNum;
    public static String[] friendSearchContent;
    public static String[] friendSearchMood;
    public static String[] friendSearchTagName;
    public static String[] friendSearchDate;
    public static String[] friendSearchName;
    public static String[] friendSearchImage;
    public static String[] friendSearchPersonImage;
    // 此為抓特定摯友日記
    public static String textViewContextbestfriendSearch = null;
    public static int bestfriendSearchCount;
    public static String[] bestfriendSearchNum;
    public static String[] bestfriendSearchContent;
    public static String[] bestfriendSearchMood;
    public static String[] bestfriendSearchTagName;
    public static String[] bestfriendSearchDate;
    public static String[] bestfriendSearchName;
    public static String[] bestfriendSearchImage;
    public static String[] bestfriendSearchPersonImage;
    // 註冊成功
    public static Boolean RegisterFirstLogin = true;
    public static String RegisterEmail = "nx01daniel@gmail.com";
    public static String RegisterPassword = "";
    public static String LoginPassword = "1234";
//    public static String RegisterEmail = "";
//    public static String RegisterPassword = "";
//    public static String LoginPassword = "";
    // 個人資料
    public static String PersonalName;
    public static String PersonalPicture;
    public static String PersonalHobby;
    public static String PersonalJob = "";
    public static boolean firstUse = false;
    // 此為搜尋好友
    public static String textViewSearchFriend = null;
    public static int SearchFriend;
    public static String Message;
    public static String[] SearchFriendPersonImage;
    public static String[] SearchFriendUserId;
    public static String[] SearchFriendName;
    // 加入好友
    public static String textAdd_friend;
    public static int add_friendCount;
    public static String[] add_friendNum;
    public static String[] add_friendName;
    public static String[] add_friendBFF;
    public static boolean check_friend = false;
    //統計
    public static int $moodTotal;
    public static int moodResult01;
    public static int moodResult02;
    public static int moodResult03;
    public static int moodResult04;
    public static int moodResult05;
    public static int tagResult01;
    public static int tagResult02;
    public static int tagResult03;
    public static int tagResult04;
    public static int tagResult05;
    //經緯度
    public static double lat;
    public static double lng;
    public static boolean locationPermissionGranted=false;


}
