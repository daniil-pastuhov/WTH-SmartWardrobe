package com.smartwardrobe.www.smartwardobe.Adapters;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.smartwardrobe.www.smartwardobe.R;
import com.smartwardrobe.www.smartwardobe.database.Apparel;
import com.smartwardrobe.www.smartwardobe.fragments.ApparelListFragment;

import java.util.List;

/**
 * Created by eugene on 18.10.14.
 */
public class WardrobeAdapter extends BaseAdapter {

    public static List<Apparel> data;
    private Context context;
    private ApparelListFragment fragment = null;

    public WardrobeAdapter(List<Apparel> data, Context context, ApparelListFragment fragment) {
        this.data = data;
        this.context = context;
        this.fragment = fragment;

    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.wardrobe_item_layout, parent, false);
            TextView name = (TextView) convertView.findViewById(R.id.wardrobe_list_item_name);
            TextView description = (TextView) convertView.findViewById(R.id.wardrobe_list_item_description);
            TextView material = (TextView) convertView.findViewById(R.id.wardrobe_list_item_material);
            TextView dateOfWashing = (TextView) convertView.findViewById(R.id.wardrobe_list_item_washing);
            ImageView photo = (ImageView) convertView.findViewById(R.id.wardrobe_list_item_photo);
            RelativeLayout layout = (RelativeLayout)  convertView.findViewById(R.id.wardrobe_list_item_layout);


            ViewHolder vh = new ViewHolder(name, photo, material, dateOfWashing, description, layout);
            convertView.setTag(vh);

            vh.name.setText(data.get(position).name);
            vh.description.setText(data.get(position).description);
            vh.material.setText(data.get(position).material);
            vh.dateOfWashing.setText(String.valueOf(data.get(position).lastWahsedDate));
            if(data.get(position).imagePath != null && !data.get(position).imagePath.isEmpty())
            vh.photo.setImageURI(Uri.parse(data.get(position).imagePath));
            vh.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    fragment.onListItemSelected(position);
                }
            });

        }


        final ViewHolder vh = (ViewHolder) convertView.getTag();

        return convertView;

    }

    public interface AdapterClickCallbacks {
        void onListItemSelected(int position);
    }

    public class ViewHolder {

        public final TextView name;
        public final TextView material;
        public final TextView description;
        public final TextView dateOfWashing;
        public final ImageView photo;
        public final RelativeLayout layout;

        public ViewHolder(TextView name, ImageView photo, TextView material, TextView dateOfWashing, TextView description, RelativeLayout layout) {

            this.photo = photo;
            this.name = name;
            this.material = material;
            this.description = description;
            this.dateOfWashing = dateOfWashing;
            this.layout = layout;
        }
    }
}

