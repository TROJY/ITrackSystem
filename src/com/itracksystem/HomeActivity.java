package com.itracksystem;


import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class HomeActivity extends Activity {

	ListView msgLst;

	private MessageDBHandler dbHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		dbHelper = new MessageDBHandler(this);

		msgLst = (ListView) findViewById(R.id.msgList);

		dbHelper.deleteAll();
		dbHelper.addMessage(new Message(1, "First Message","26/5/2014"));
		dbHelper.addMessage(new Message(2, "Second Message","25/5/2014"));
		dbHelper.addMessage(new Message(3, "Third Message","24/5/2014"));
		dbHelper.addMessage(new Message(4, "Fourth Message","23/5/2014"));
		dbHelper.addMessage(new Message(5, "Fifth Message","22/5/2014"));

		msgLst.setAdapter(new msgListAdapter(this, dbHelper.getAllMessages()));

		msgLst.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, final View arg1, int arg2,
					long arg3) {

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						HomeActivity.this);
				alertDialogBuilder.setTitle(null);
				alertDialogBuilder
				.setMessage("Are you sure you want to delete?")
				.setCancelable(false)

				.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {

						dialog.cancel();
						System.out.println(((Message)arg1.getTag()).getDate());
						System.out.println(((Message)arg1.getTag()).getMsg());

						dbHelper.deleteMessage((Message)arg1.getTag());

						msgLst.setAdapter(new msgListAdapter(HomeActivity.this, dbHelper.getAllMessages()));
					}
				})
				.setNegativeButton("No",new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();


					}
				});
				alertDialogBuilder.create().show();

			}
		});


	}


	static class ViewHolder {
		public TextView msg;
		public TextView date;

	}

	public class msgListAdapter extends BaseAdapter  {
		private final Activity context;
		private final List<Message> obj;

		public msgListAdapter(Activity context, List<Message> obj) {
			this.context = context;
			this.obj = obj;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View rowView = convertView;
			// reuse views
			if (rowView == null) {
				LayoutInflater inflater = context.getLayoutInflater();
				rowView = inflater.inflate(R.layout.my_message_item, null);
				// configure view holder
				ViewHolder viewHolder = new ViewHolder();
				viewHolder.msg = (TextView) rowView.findViewById(R.id.msg);
				viewHolder.date = (TextView) rowView.findViewById(R.id.date);
				rowView.setTag(viewHolder);
			}

			// fill data
			ViewHolder holder = (ViewHolder) rowView.getTag();

			try {
				Message msg = obj.get(position);
				holder.msg.setText(msg.getMsg());
				holder.date.setText(msg.getDate());

				System.out.println("msg.getDate() "+msg.getDate());

				rowView.setTag(msg);
				//				

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return rowView;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return obj.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return 0;
		}

	} 





}
