package com.example.bourbon;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import com.example.bourbon.databinding.ActivityGraphDailyBindingImpl;
import com.example.bourbon.databinding.ActivityMainBindingImpl;
import com.example.bourbon.databinding.ActivityPersonLocHarishBindingImpl;
import com.example.bourbon.databinding.CardCovidStatsBindingImpl;
import com.example.bourbon.databinding.CardCustomerOrderBindingImpl;
import com.example.bourbon.databinding.CardDashboardBindingImpl;
import com.example.bourbon.databinding.CardHarishEmergencyContactNumBindingImpl;
import com.example.bourbon.databinding.CardInfectedPeopleBindingImpl;
import com.example.bourbon.databinding.CardNewsBackBindingImpl;
import com.example.bourbon.databinding.CardNewsBindingImpl;
import com.example.bourbon.databinding.CardNewsFrontBindingImpl;
import com.example.bourbon.databinding.CardOrderBindingImpl;
import com.example.bourbon.databinding.CardShopBindingImpl;
import com.example.bourbon.databinding.CovidStatusCardBindingImpl;
import com.example.bourbon.databinding.DashboardActivityBindingImpl;
import com.example.bourbon.databinding.RvActivityCovidStatusBindingImpl;
import com.example.bourbon.databinding.RvActivityOrderInfoBindingImpl;
import com.example.bourbon.databinding.RvActivityShopInfoBindingImpl;
import com.example.bourbon.databinding.RvCustomerOrderInfoBindingImpl;
import com.example.bourbon.databinding.RvHarishEmergencyContactNumBindingImpl;
import com.example.bourbon.databinding.RvInfectedPeopleInfoBindingImpl;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYGRAPHDAILY = 1;

  private static final int LAYOUT_ACTIVITYMAIN = 2;

  private static final int LAYOUT_ACTIVITYPERSONLOCHARISH = 3;

  private static final int LAYOUT_CARDCOVIDSTATS = 4;

  private static final int LAYOUT_CARDCUSTOMERORDER = 5;

  private static final int LAYOUT_CARDDASHBOARD = 6;

  private static final int LAYOUT_CARDHARISHEMERGENCYCONTACTNUM = 7;

  private static final int LAYOUT_CARDINFECTEDPEOPLE = 8;

  private static final int LAYOUT_CARDNEWS = 9;

  private static final int LAYOUT_CARDNEWSBACK = 10;

  private static final int LAYOUT_CARDNEWSFRONT = 11;

  private static final int LAYOUT_CARDORDER = 12;

  private static final int LAYOUT_CARDSHOP = 13;

  private static final int LAYOUT_COVIDSTATUSCARD = 14;

  private static final int LAYOUT_DASHBOARDACTIVITY = 15;

  private static final int LAYOUT_RVACTIVITYCOVIDSTATUS = 16;

  private static final int LAYOUT_RVACTIVITYORDERINFO = 17;

  private static final int LAYOUT_RVACTIVITYSHOPINFO = 18;

  private static final int LAYOUT_RVCUSTOMERORDERINFO = 19;

  private static final int LAYOUT_RVHARISHEMERGENCYCONTACTNUM = 20;

  private static final int LAYOUT_RVINFECTEDPEOPLEINFO = 21;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(21);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.activity_graph_daily, LAYOUT_ACTIVITYGRAPHDAILY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.activity_main, LAYOUT_ACTIVITYMAIN);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.activity_person_loc_harish, LAYOUT_ACTIVITYPERSONLOCHARISH);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_covid_stats, LAYOUT_CARDCOVIDSTATS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_customer_order, LAYOUT_CARDCUSTOMERORDER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_dashboard, LAYOUT_CARDDASHBOARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_harish_emergency_contact_num, LAYOUT_CARDHARISHEMERGENCYCONTACTNUM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_infected_people, LAYOUT_CARDINFECTEDPEOPLE);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_news, LAYOUT_CARDNEWS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_news_back, LAYOUT_CARDNEWSBACK);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_news_front, LAYOUT_CARDNEWSFRONT);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_order, LAYOUT_CARDORDER);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.card_shop, LAYOUT_CARDSHOP);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.covid_status_card, LAYOUT_COVIDSTATUSCARD);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.dashboard_activity, LAYOUT_DASHBOARDACTIVITY);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.rv_activity_covid_status, LAYOUT_RVACTIVITYCOVIDSTATUS);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.rv_activity_order_info, LAYOUT_RVACTIVITYORDERINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.rv_activity_shop_info, LAYOUT_RVACTIVITYSHOPINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.rv_customer_order_info, LAYOUT_RVCUSTOMERORDERINFO);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.rv_harish_emergency_contact_num, LAYOUT_RVHARISHEMERGENCYCONTACTNUM);
    INTERNAL_LAYOUT_ID_LOOKUP.put(com.example.bourbon.R.layout.rv_infected_people_info, LAYOUT_RVINFECTEDPEOPLEINFO);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYGRAPHDAILY: {
          if ("layout/activity_graph_daily_0".equals(tag)) {
            return new ActivityGraphDailyBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_graph_daily is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYMAIN: {
          if ("layout/activity_main_0".equals(tag)) {
            return new ActivityMainBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_main is invalid. Received: " + tag);
        }
        case  LAYOUT_ACTIVITYPERSONLOCHARISH: {
          if ("layout/activity_person_loc_harish_0".equals(tag)) {
            return new ActivityPersonLocHarishBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_person_loc_harish is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDCOVIDSTATS: {
          if ("layout/card_covid_stats_0".equals(tag)) {
            return new CardCovidStatsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_covid_stats is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDCUSTOMERORDER: {
          if ("layout/card_customer_order_0".equals(tag)) {
            return new CardCustomerOrderBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_customer_order is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDDASHBOARD: {
          if ("layout/card_dashboard_0".equals(tag)) {
            return new CardDashboardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_dashboard is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDHARISHEMERGENCYCONTACTNUM: {
          if ("layout/card_harish_emergency_contact_num_0".equals(tag)) {
            return new CardHarishEmergencyContactNumBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_harish_emergency_contact_num is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDINFECTEDPEOPLE: {
          if ("layout/card_infected_people_0".equals(tag)) {
            return new CardInfectedPeopleBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_infected_people is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDNEWS: {
          if ("layout/card_news_0".equals(tag)) {
            return new CardNewsBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_news is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDNEWSBACK: {
          if ("layout/card_news_back_0".equals(tag)) {
            return new CardNewsBackBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_news_back is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDNEWSFRONT: {
          if ("layout/card_news_front_0".equals(tag)) {
            return new CardNewsFrontBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_news_front is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDORDER: {
          if ("layout/card_order_0".equals(tag)) {
            return new CardOrderBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_order is invalid. Received: " + tag);
        }
        case  LAYOUT_CARDSHOP: {
          if ("layout/card_shop_0".equals(tag)) {
            return new CardShopBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for card_shop is invalid. Received: " + tag);
        }
        case  LAYOUT_COVIDSTATUSCARD: {
          if ("layout/covid_status_card_0".equals(tag)) {
            return new CovidStatusCardBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for covid_status_card is invalid. Received: " + tag);
        }
        case  LAYOUT_DASHBOARDACTIVITY: {
          if ("layout/dashboard_activity_0".equals(tag)) {
            return new DashboardActivityBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for dashboard_activity is invalid. Received: " + tag);
        }
        case  LAYOUT_RVACTIVITYCOVIDSTATUS: {
          if ("layout/rv_activity_covid_status_0".equals(tag)) {
            return new RvActivityCovidStatusBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_activity_covid_status is invalid. Received: " + tag);
        }
        case  LAYOUT_RVACTIVITYORDERINFO: {
          if ("layout/rv_activity_order_info_0".equals(tag)) {
            return new RvActivityOrderInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_activity_order_info is invalid. Received: " + tag);
        }
        case  LAYOUT_RVACTIVITYSHOPINFO: {
          if ("layout/rv_activity_shop_info_0".equals(tag)) {
            return new RvActivityShopInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_activity_shop_info is invalid. Received: " + tag);
        }
        case  LAYOUT_RVCUSTOMERORDERINFO: {
          if ("layout/rv_customer_order_info_0".equals(tag)) {
            return new RvCustomerOrderInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_customer_order_info is invalid. Received: " + tag);
        }
        case  LAYOUT_RVHARISHEMERGENCYCONTACTNUM: {
          if ("layout/rv_harish_emergency_contact_num_0".equals(tag)) {
            return new RvHarishEmergencyContactNumBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_harish_emergency_contact_num is invalid. Received: " + tag);
        }
        case  LAYOUT_RVINFECTEDPEOPLEINFO: {
          if ("layout/rv_infected_people_info_0".equals(tag)) {
            return new RvInfectedPeopleInfoBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for rv_infected_people_info is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(3);

    static {
      sKeys.put(0, "_all");
      sKeys.put(1, "pd");
      sKeys.put(2, "pd2");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(21);

    static {
      sKeys.put("layout/activity_graph_daily_0", com.example.bourbon.R.layout.activity_graph_daily);
      sKeys.put("layout/activity_main_0", com.example.bourbon.R.layout.activity_main);
      sKeys.put("layout/activity_person_loc_harish_0", com.example.bourbon.R.layout.activity_person_loc_harish);
      sKeys.put("layout/card_covid_stats_0", com.example.bourbon.R.layout.card_covid_stats);
      sKeys.put("layout/card_customer_order_0", com.example.bourbon.R.layout.card_customer_order);
      sKeys.put("layout/card_dashboard_0", com.example.bourbon.R.layout.card_dashboard);
      sKeys.put("layout/card_harish_emergency_contact_num_0", com.example.bourbon.R.layout.card_harish_emergency_contact_num);
      sKeys.put("layout/card_infected_people_0", com.example.bourbon.R.layout.card_infected_people);
      sKeys.put("layout/card_news_0", com.example.bourbon.R.layout.card_news);
      sKeys.put("layout/card_news_back_0", com.example.bourbon.R.layout.card_news_back);
      sKeys.put("layout/card_news_front_0", com.example.bourbon.R.layout.card_news_front);
      sKeys.put("layout/card_order_0", com.example.bourbon.R.layout.card_order);
      sKeys.put("layout/card_shop_0", com.example.bourbon.R.layout.card_shop);
      sKeys.put("layout/covid_status_card_0", com.example.bourbon.R.layout.covid_status_card);
      sKeys.put("layout/dashboard_activity_0", com.example.bourbon.R.layout.dashboard_activity);
      sKeys.put("layout/rv_activity_covid_status_0", com.example.bourbon.R.layout.rv_activity_covid_status);
      sKeys.put("layout/rv_activity_order_info_0", com.example.bourbon.R.layout.rv_activity_order_info);
      sKeys.put("layout/rv_activity_shop_info_0", com.example.bourbon.R.layout.rv_activity_shop_info);
      sKeys.put("layout/rv_customer_order_info_0", com.example.bourbon.R.layout.rv_customer_order_info);
      sKeys.put("layout/rv_harish_emergency_contact_num_0", com.example.bourbon.R.layout.rv_harish_emergency_contact_num);
      sKeys.put("layout/rv_infected_people_info_0", com.example.bourbon.R.layout.rv_infected_people_info);
    }
  }
}
