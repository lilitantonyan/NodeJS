package com.mpc.product;


import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CandyAdapter extends BaseAdapter{

	ArrayList<Candy> candysArray = new ArrayList<Candy>();
	private LayoutInflater mInflater;
	
	public CandyAdapter( Context context) {
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
	public void setCandysArray(ArrayList<Candy> array){
		this.candysArray = array;
	}
	
	@Override
	public int getCount() {
		return candysArray.size();
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View converView, ViewGroup parent) {
		View row = converView;
		
		if(row == null){
			row = mInflater.inflate(R.layout.item_candy_list, parent,false);
		}
		
		TextView name = (TextView) row.findViewById(R.id.name);
		TextView type  = (TextView) row.findViewById(R.id.type);
		TextView brand = (TextView)row.findViewById(R.id.brand);
		
		Candy candy = candysArray.get(position);
		name.setText(candy.getCandyName());
		type.setText(candy.getCandyType());
		brand.setText(candy.getCandyBrand());
		
		return row;
	}

}
