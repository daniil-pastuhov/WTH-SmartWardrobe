package by.SmartWardrobe;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import by.idea.SmartWardrobe.R;
import main.wardrobe.entity.Apparel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Apparel> {
    // Declare Variables
    Context context;
    LayoutInflater inflater;
    List<Apparel> apparelList;
    private SparseBooleanArray mSelectedItemsIds;

    public ListViewAdapter(Context context, int resourceId,
                           List<Apparel> apparelList) {
        super(context, resourceId, apparelList);
        mSelectedItemsIds = new SparseBooleanArray();
        this.context = context;
        this.apparelList = apparelList;
        inflater = LayoutInflater.from(context);
    }

    private class ViewHolder {
        TextView name;
        TextView description;
        TextView dateOfWashing;
        ImageView photo;
    }

    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.description = (TextView) view.findViewById(R.id.matirial);
            holder.dateOfWashing = (TextView) view.findViewById(R.id.washing);
            // Locate the ImageView in listview_item.xml
            holder.photo = (ImageView) view.findViewById(R.id.photo);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Capture position and set to the TextViews
        holder.name.setText(apparelList.get(position).getName());
        holder.description.setText(apparelList.get(position).getDescription());
        holder.dateOfWashing.setText(apparelList.get(position).getLastWashedDateString());
        // Capture position and set to the ImageView
        holder.photo.setImageBitmap(apparelList.get(position).getImage());
        return view;
    }

    @Override
    public void remove(Apparel object) {
        apparelList.remove(object);
        notifyDataSetChanged();
    }

    public List<Apparel> getWorldPopulation() {
        return apparelList;
    }

    public void toggleSelection(int position) {
        selectView(position, !mSelectedItemsIds.get(position));
    }

    public void removeSelection() {
        mSelectedItemsIds = new SparseBooleanArray();
        notifyDataSetChanged();
    }

    public void selectView(int position, boolean value) {
        if (value)
            mSelectedItemsIds.put(position, value);
        else
            mSelectedItemsIds.delete(position);
        notifyDataSetChanged();
    }

    public int getSelectedCount() {
        return mSelectedItemsIds.size();
    }

    public SparseBooleanArray getSelectedIds() {
        return mSelectedItemsIds;
    }
}
