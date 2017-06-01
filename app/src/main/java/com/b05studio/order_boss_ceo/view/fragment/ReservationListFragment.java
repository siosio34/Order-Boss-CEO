package com.b05studio.order_boss_ceo.view.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.b05studio.order_boss_ceo.R;
import com.b05studio.order_boss_ceo.view.activity.MainActivity;
import com.b05studio.order_boss_ceo.view.model.MenuInfo;
import com.b05studio.order_boss_ceo.view.model.OrderInfo;
import com.b05studio.order_boss_ceo.view.model.ReservationInfo;
import com.b05studio.order_boss_ceo.view.model.RestaurantInfo;
import com.b05studio.order_boss_ceo.view.model.Review;
import com.b05studio.order_boss_ceo.view.model.User;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by mansu on 2017-06-02.
 */

public class ReservationListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_reservation_list, container, false);
        RecyclerView reservationListRecyclerView = (RecyclerView)rootView.findViewById(R.id.reservationListRecyclerView);
        reservationListRecyclerView.setHasFixedSize(true);

        ArrayList<ReservationInfo> reservationInfos = new ArrayList<>();
        //TODO:2017.06.01 ReservationInfo 가져오는것 필요함
        ArrayList<MenuInfo> menuInfos = new ArrayList<>();
        menuInfos.add(new MenuInfo("", "소세지 또띠아", 15000));
        menuInfos.add(new MenuInfo("", "모둠 포", 15000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        menuInfos.add(new MenuInfo("", "생맥주 500cc", 6000));
        ArrayList<OrderInfo> orderInfos = new ArrayList<>();
        orderInfos.add(new OrderInfo("1", menuInfos.get(0), 1));
        orderInfos.add(new OrderInfo("1", menuInfos.get(1), 2));
        orderInfos.add(new OrderInfo("1", menuInfos.get(2), 3));
        orderInfos.add(new OrderInfo("1", menuInfos.get(3), 4));
        boolean[] holiday = new boolean[28];
        for(int i=0; i<28; i++)
            holiday[i] = false;
        //첫째 주 일요일
        holiday[6] = true;
        //셋째 주 일요일
        holiday[20] = true;

        ReservationInfo reservationInfo = new ReservationInfo("1", "김만수", "010-0000-0000", new RestaurantInfo("1", "멘무샤", "일식 > 라멘", "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1280, "1", 62, 12, 12, new ArrayList<Review>(), menuInfos), orderInfos, 25, Calendar.getInstance(), "배달 빨리해주세요.");
        ReservationInfo reservationInfo2 = new ReservationInfo("1", "김만수", "010-0000-0000", new RestaurantInfo("1", "멘무샤", "일식 > 라멘", "경기도 화성시 동탄중앙로 220", "010-0000-0000", 17, 0, 2, 0, holiday, "첫째 주, 셋째 주 일요일", "만원 ~ 이만원", 1280, "1", 62, 12, 12, new ArrayList<Review>(), menuInfos), orderInfos, 25, Calendar.getInstance(), "배달 빨리해주세요.");
        reservationInfos.add(reservationInfo);
        reservationInfos.add(reservationInfo2);
        //if(reservationInfos.size() == 0)
         //   rootView.findViewById(R.id.myReservationNoResult).setVisibility(View.VISIBLE);
        //else {
            MyReservationAdapter myReservationAdapter = new MyReservationAdapter(reservationInfos, getContext(), inflater);
            reservationListRecyclerView.setAdapter(myReservationAdapter);
       // }

        return rootView;
    }

    private class MyReservationAdapter extends RecyclerView.Adapter<MyReservationAdapter.ViewHolder> {
        private ArrayList<ReservationInfo> reservationInfos;
        private Context context;
        private LayoutInflater inflater;
        private ViewGroup parent;

        public MyReservationAdapter(ArrayList<ReservationInfo> reservationInfos, Context context, LayoutInflater inflater) {
            this.reservationInfos = reservationInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            this.parent = parent;
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_reservation_list, parent, false);
            ViewHolder holder = new ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, final int position) {
            final ReservationInfo reservationInfo = reservationInfos.get(position);

            holder.reservationListNumber.setText((position+1)+"");
            long diff = System.currentTimeMillis() - reservationInfo.getOrderTime().getTimeInMillis();
            String date = "";
            if(diff < 60*1000)
                date = (diff/1000)+"초 전";
            else if(diff < 60*60*1000)
                date = (diff/(60*1000))+"분 전";
            else if(diff < 24*60*60*1000)
                date = (diff/(60*60*1000))+"시간 전";
            else if(diff < 30*24*60*60*1000)
                date = (diff/(24*60*60*1000))+"일 전";
            holder.reservationListDate.setText(date);
            holder.reservationListName.setText(reservationInfo.getUserName());
            holder.reservationListPhoneNum.setText(reservationInfo.getUserPhoneNum());
            int totalPrice = 0;
            for(int i=0; i<reservationInfo.getOrderInfos().size(); i++)
                totalPrice += reservationInfo.getOrderInfos().get(i).getMenuInfo().getPrice() * reservationInfo.getOrderInfos().get(i).getMenuNum();
            holder.reservationListTotalPrice.setText(""+totalPrice);

            if(holder.reservationListOrderList.getChildCount() < reservationInfo.getOrderInfos().size()) {
                for (int i = 0; i < reservationInfo.getOrderInfos().size(); i++) {
                    View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_reservation_list_order, parent, false);
                    TextView reservationListOrderName = (TextView) v.findViewById(R.id.reservationListOrderName);
                    TextView reservationListOrderNumber = (TextView)v.findViewById(R.id.reservationListOrderNumber);
                    TextView reservationListOrderPrice = (TextView) v.findViewById(R.id.reservationListOrderPrice);
                    reservationListOrderName.setText(reservationInfo.getOrderInfos().get(i).getMenuInfo().getName());
                    reservationListOrderNumber.setText(reservationInfo.getOrderInfos().get(i).getMenuNum()+"개");
                    reservationListOrderPrice.setText(""+reservationInfo.getOrderInfos().get(i).getMenuInfo().getPrice());
                    holder.reservationListOrderList.addView(v);
                }
            }

            holder.reservationListRequestContent.setText(reservationInfo.getRequestContent());
            holder.reservationListAcceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final View popupView = inflater.inflate(R.layout.popup_reservation_list_waittime, null);
                    MainActivity.popupWindow = new PopupWindow(popupView, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.MATCH_PARENT);
                    ConstraintLayout background = (ConstraintLayout)popupView.findViewById(R.id.reservationListChooseBackground);
                    Button acceptButton = (Button)popupView.findViewById(R.id.reservationListPopupAcceptButton);

                    background.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            MainActivity.popupWindow.dismiss();
                            MainActivity.popupWindow = null;
                        }
                    });
                    acceptButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            EditText time = (EditText)popupView.findViewById(R.id.reservationListTime);
                            ReservationInfo reservationInfo = (ReservationInfo)reservationInfos.get(position).clone();
                            reservationInfo.setRemainTime(Integer.parseInt(time.getText().toString()));
                            User.getCurrentUser().getAccpetedReservationInfos().add(reservationInfo);
                            reservationInfos.remove(position);
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    notifyDataSetChanged();
                                }
                            });

                            MainActivity.popupWindow.dismiss();
                            MainActivity.popupWindow = null;
                        }
                    });
                    MainActivity.popupWindow.setFocusable(true);
                    MainActivity.popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
                }
            });
            holder.reservationListRejectionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    reservationInfos.remove(reservationInfo);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            notifyDataSetChanged();
                        }
                    });
                }
            });
        }

        @Override
        public int getItemCount() {
            return reservationInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView reservationListNumber;
            TextView reservationListDate;
            TextView reservationListName;
            TextView reservationListPhoneNum;
            TextView reservationListTotalPrice;
            TextView reservationListRequestContent;
            LinearLayout reservationListOrderList;
            Button reservationListAcceptButton;
            Button reservationListRejectionButton;

            public ViewHolder(View view) {
                super(view);
                reservationListNumber = (TextView)view.findViewById(R.id.reservationListNumber);
                reservationListDate = (TextView)view.findViewById(R.id.reservationListDate);
                reservationListName = (TextView)view.findViewById(R.id.reservationListName);
                reservationListPhoneNum = (TextView)view.findViewById(R.id.reservationListPhoneNum);
                reservationListTotalPrice = (TextView)view.findViewById(R.id.reservationListTotalPrice);
                reservationListRequestContent = (TextView)view.findViewById(R.id.reservationListRequestContent);
                reservationListOrderList = (LinearLayout)view.findViewById(R.id.reservationListOrderList);
                reservationListAcceptButton = (Button)view.findViewById(R.id.reservationListAcceptButton);
                reservationListRejectionButton = (Button)view.findViewById(R.id.reservationListRejectionButton);
            }
        }
    }
}