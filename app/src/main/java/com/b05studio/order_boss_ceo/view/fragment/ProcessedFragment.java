package com.b05studio.order_boss_ceo.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.b05studio.order_boss_ceo.R;
import com.b05studio.order_boss_ceo.view.activity.MainActivity;
import com.b05studio.order_boss_ceo.view.model.ReservationInfo;
import com.b05studio.order_boss_ceo.view.model.User;

import java.util.ArrayList;

/**
 * Created by mansu on 2017-06-02.
 */

public class ProcessedFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_processed, container, false);
        RecyclerView processedRecyclerView = (RecyclerView)rootView.findViewById(R.id.processedRecyclerView);
        processedRecyclerView.setHasFixedSize(true);
        ProcessedAdapter myReservationAdapter = new ProcessedAdapter(User.getCurrentUser().getAccpetedReservationInfos(), getContext(), inflater);
        processedRecyclerView.setAdapter(myReservationAdapter);
        return rootView;
    }

    private class ProcessedAdapter extends RecyclerView.Adapter<ProcessedFragment.ProcessedAdapter.ViewHolder> {
        private ArrayList<ReservationInfo> reservationInfos;
        private Context context;
        private LayoutInflater inflater;
        private ViewGroup parent;

        public ProcessedAdapter(ArrayList<ReservationInfo> reservationInfos, Context context, LayoutInflater inflater) {
            this.reservationInfos = reservationInfos;
            this.context = context;
            this.inflater = inflater;
        }

        @Override
        public ProcessedFragment.ProcessedAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            this.parent = parent;
            View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_processed, parent, false);
            ProcessedFragment.ProcessedAdapter.ViewHolder holder = new ProcessedFragment.ProcessedAdapter.ViewHolder(v);
            return holder;
        }

        @Override
        public void onBindViewHolder(ProcessedFragment.ProcessedAdapter.ViewHolder holder, final int position) {
            final ReservationInfo reservationInfo = reservationInfos.get(position);

            holder.processedNumber.setText((position+1)+"");
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
            holder.processedDate.setText(date);
            holder.processedName.setText(reservationInfo.getUserName());
            holder.processedPhoneNum.setText(reservationInfo.getUserPhoneNum());
            holder.processedTime.setText(""+reservationInfo.getRemainTime());

            int totalPrice = 0;
            for(int i=0; i<reservationInfo.getOrderInfos().size(); i++)
                totalPrice += reservationInfo.getOrderInfos().get(i).getMenuInfo().getPrice() * reservationInfo.getOrderInfos().get(i).getMenuNum();
            holder.processedTotalPrice.setText(""+totalPrice);

            if(holder.processedOrderList.getChildCount() < reservationInfo.getOrderInfos().size()) {
                for (int i = 0; i < reservationInfo.getOrderInfos().size(); i++) {
                    View v = inflater.from(parent.getContext()).inflate(R.layout.cardview_reservation_list_order, parent, false);
                    TextView reservationListOrderName = (TextView) v.findViewById(R.id.reservationListOrderName);
                    TextView reservationListOrderNumber = (TextView)v.findViewById(R.id.reservationListOrderNumber);
                    TextView reservationListOrderPrice = (TextView) v.findViewById(R.id.reservationListOrderPrice);
                    reservationListOrderName.setText(reservationInfo.getOrderInfos().get(i).getMenuInfo().getName());
                    reservationListOrderNumber.setText(reservationInfo.getOrderInfos().get(i).getMenuNum()+"개");
                    reservationListOrderPrice.setText(""+reservationInfo.getOrderInfos().get(i).getMenuInfo().getPrice());
                    holder.processedOrderList.addView(v);
                }
            }
            holder.processedRequestContent.setText(reservationInfo.getRequestContent());
        }

        @Override
        public int getItemCount() {
            return reservationInfos.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView processedNumber;
            TextView processedDate;
            TextView processedName;
            TextView processedPhoneNum;
            TextView processedTime;
            TextView processedTotalPrice;
            TextView processedRequestContent;
            LinearLayout processedOrderList;

            public ViewHolder(View view) {
                super(view);
                processedNumber = (TextView)view.findViewById(R.id.processedNumber);
                processedDate = (TextView)view.findViewById(R.id.processedDate);
                processedName = (TextView)view.findViewById(R.id.processedName);
                processedPhoneNum = (TextView)view.findViewById(R.id.processedPhoneNum);
                processedTime = (TextView)view.findViewById(R.id.processedTime);
                processedTotalPrice = (TextView)view.findViewById(R.id.processedTotalPrice);
                processedRequestContent = (TextView)view.findViewById(R.id.processedRequestContent);
                processedOrderList = (LinearLayout)view.findViewById(R.id.processedOrderList);
            }
        }
    }
}
