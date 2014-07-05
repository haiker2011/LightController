package com.android.adpater;

import com.android.ui.R;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class ImageAdapter extends BaseAdapter {
	
	// 定义Context
		private Context		mContext;
		// 定义整型数组 即图片源
		private Integer[]	mImageIds	= 
		{ 
				R.drawable.firstui, 
				R.drawable.secondui, 
				R.drawable.thirdui,
				R.drawable.fourthui,
				
		};

		public ImageAdapter(Context c)
		{
			mContext = c;
		}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mImageIds.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ImageView imageView;
		if (convertView == null)
		{
			// 给ImageView设置资源
			imageView = new ImageView(mContext);
			// 设置布局 图片显示,可以改变图片大小
			imageView.setLayoutParams(new GridView.LayoutParams(360, 360));
			// 设置显示比例类型
			imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
		}
		else
		{
			imageView = (ImageView) convertView;
		}

		imageView.setImageResource(mImageIds[position]);
		return imageView;
	}
		
	

}
