package com.plannet.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.plannet.listener.DrawerListItemOnClickListener;
import com.plannet.pages.PageFragment1;
import com.plannet.pages.PagerAdapter;

public class MyPlanActivity extends FragmentActivity implements OnClickListener{
	private String[] navItems;
	private ListView drawerNavList;
	private DrawerLayout drawerLayout;

	private ActionBar tabActionBar;
	private ActionBarDrawerToggle drawerToggle;

	private LinearLayout pagerContainer;
	private PagerAdapter pagerAdapter;
	private ViewPager pager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_plan);

		// ////////////////////////
		// 여기서부터 드로어
		// ////////////////////////

		// 초기화
		navItems = getResources().getStringArray(R.array.drawer_nav_list_items);
		drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		drawerNavList = (ListView) findViewById(R.id.drawer_nav_list);
		// 드로어 네비게이션 리스트 내용 설정
		drawerNavList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navItems));
		// 드로어 아이템 클릭 리스너 설정
		drawerNavList.setOnItemClickListener(new DrawerListItemOnClickListener(drawerLayout));
		// 액션바 초기화
		tabActionBar = getActionBar();
		// 액션바와 드로어를 연결 시켜주는 토글
		drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.drawable.ic_drawer,
				R.string.open_drawer_message, R.string.close_drawer_message);
		drawerLayout.setDrawerListener(drawerToggle); // 드로어와 액션바 토글을 연결
		tabActionBar.setDisplayHomeAsUpEnabled(true);
		// tabActionBar.setTitle("my plan");

		// ////////////////////////
		// 여기서부터 페이저
		// ////////////////////////

		// 초기화
		pagerContainer = (LinearLayout) findViewById(R.id.pager_container);
		pager = new ViewPager(this);
		pager.setId(1); // view의 아이디 꼭 필요함
		pagerContainer.addView(pager);// LinearLayout의 자식으로 ViewPager를 넣어줌

		pagerAdapter = new PagerAdapter(this, pager); // pagerAdapter 클래스에 있는 설명 참고
		tabActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		pagerAdapter.addTab(tabActionBar.newTab().setText("Now"), PageFragment1.class);
		pagerAdapter.addTab(tabActionBar.newTab().setText("Later"), PageFragment1.class);
		pagerAdapter.addTab(tabActionBar.newTab().setText("Done"), PageFragment1.class);
		
		findViewById(R.id.add_plan_button).setOnClickListener(this) ;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// 뷰가 생성되는 시점에 pager height 크기 설정해주기 = wrapper height - button height
		LinearLayout wrapper = (LinearLayout) findViewById(R.id.wrapper);
		LinearLayout buttonContainer = (LinearLayout) findViewById(R.id.button_container);

		Log.e("wrapper pixel height", "" + wrapper.getHeight());
		Log.e("button layout pixel height", "" + buttonContainer.getHeight());

		pagerContainer.getLayoutParams().height = wrapper.getHeight() - buttonContainer.getHeight();
		pagerContainer.requestLayout();
	}

	// ////////////////////////
	// 여기서부터 액션바와 드로어 상태 동기화 작업
	// ////////////////////////
	
	@Override
	public void onClick(View v) {
		int viewId = v.getId();
		switch (viewId) {
		case R.id.add_plan_button:
			Intent intent = new Intent(this, AddActivity.class);
			startActivityForResult(intent, ReqCode.ADD_PLAN);
			break;

		default:
			break;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case ReqCode.ADD_PLAN:
			String title = data.getStringExtra("title");
			Toast.makeText(this, title, Toast.LENGTH_LONG).show();
			break;

		default:
			break;
		}
		
	}
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		drawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		drawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}