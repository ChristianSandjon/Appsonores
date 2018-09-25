package com.example.christiansandjon.appsonores.Tools;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.christiansandjon.appsonores.DetailsActivity;
import com.example.christiansandjon.appsonores.Models.DataModel;
import com.example.christiansandjon.appsonores.R;

import java.util.ArrayList;

public class SonorAdapter extends ArrayAdapter<DataModel> implements View.OnClickListener {

    private ArrayList<DataModel> datamodels;
    Context context;
    public static final String KEY = "sonor";

    public SonorAdapter(@NonNull Context context, int resource, ArrayList<DataModel> datamodels) {
        super(context, resource);
        this.datamodels = datamodels;
        this.context = context;
    }
    // View lookup cache
   private static class ViewHolder {
        TextView txtVille;
        TextView txtAdresse;
        TextView txtDate;
        TextView txtDecibel;
        ImageView info;
    }

    public SonorAdapter(ArrayList<DataModel> data, Context context) {
        super(context, R.layout.custom_sonor, data);
        this.datamodels = data;
        this.context=context;

    }

    @Override
    public void onClick(View v) {

        int position=(Integer) v.getTag();
        Object object= getItem(position);
        DataModel dataModel=(DataModel)object;

        switch (v.getId())
        {
            case R.id.item_info:
                gotoDetailsActivity(dataModel);
                break;
        }
    }

    public void gotoDetailsActivity(DataModel dataModel){
        Intent intent = new Intent(context, DetailsActivity.class);
        intent.putExtra(KEY, dataModel);
        context.startActivity(intent);

    }
    //private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.custom_sonor, parent, false);
            viewHolder.txtVille = convertView.findViewById(R.id.ville);
            viewHolder.txtAdresse =  convertView.findViewById(R.id.adresse);
            viewHolder.txtDate =  convertView.findViewById(R.id.date);
            viewHolder.txtDecibel = convertView.findViewById(R.id.decibel);
            viewHolder.info = convertView.findViewById(R.id.item_info);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        /*Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.up_from_bottom : R.anim.down_from_top);
        result.startAnimation(animation);
        lastPosition = position;*/

        viewHolder.txtVille.setText(dataModel.getVille());
        viewHolder.txtAdresse.setText(dataModel.getAdresse());
        viewHolder.txtDate.setText(""+dataModel.getDate());
        viewHolder.txtDecibel.setText(""+dataModel.getDb());
        viewHolder.info.setOnClickListener(this);
        viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
