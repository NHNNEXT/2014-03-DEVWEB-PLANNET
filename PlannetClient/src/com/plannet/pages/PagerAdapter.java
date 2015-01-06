package com.plannet.pages;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.ActionBar.TabListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

// PagerAdapter 클래스에 대한 설명:
// TabListener -> 탭을 선택했을 때 일어나는 일
// OnPageChangeListener -> 페이지 바뀔 때 (탭 터치든 페이지 스와이프든)
// FragmentPagerAdapter -> 페이저 어댑터, fragment를 어댑트한다
// PagerAdapter는 이것들을 통합관리해줌

public class PagerAdapter extends FragmentPagerAdapter implements TabListener, OnPageChangeListener {
	private final ActionBar tabActionBar;
	private final ViewPager pager;

	public PagerAdapter(FragmentActivity activity, ViewPager pager) {
		super(activity.getSupportFragmentManager());
		this.pager = pager;
		this.tabActionBar = activity.getActionBar();

		this.pager.setAdapter(this);
		this.pager.setOnPageChangeListener(this);
	}

	public void addTab(ActionBar.Tab tab, int cid) {
		tab.setTabListener(this); // PagerAdapter를 탭 리스너로 지정해줌
		tab.setTag(cid); // 각각의 탭의 태그 정보 안에 cid를 넣어줌
		tabActionBar.addTab(tab); // 실제 액션바에 이 새로 들어온 탭을 추가
		notifyDataSetChanged();

		// ////////////////////////
		// 번호에 대한 설명
		// ////////////////////////
		// ActionBar.Tab의 postion은 0부터 시작하고
		// Page의 position 역시 0부터 시작해서 position 값이 같다
	}

	// ////////////////////////
	// 여기서부터 FragmentPagerAdapter 구현 메서드
	// ////////////////////////

	@Override
	public int getCount() {
		// 현재 PagerAdapter가 관리하고 있는 fragment 개수가 몇개인지 알려줌
		return tabActionBar.getTabCount();
	}

	@Override
	public Fragment getItem(int position) {
		// 페이저 각각의 위치에 대해 fragment를 인스턴스로 생성해줌
		int cid = (Integer) tabActionBar.getTabAt(position).getTag(); // 여기에서 cid 추출해서 ModelFragment 생성할 때 넣어준다
		return new PlanModelFragment(cid);
	}

	// ////////////////////////
	// 여기서부터 OnPageChangeListener 구현 메서드
	// ////////////////////////

	@Override
	public void onPageSelected(int position) {
		// 페이지가 선택되었을 때 해당 위치 탭을 선택되었다고 파란색으로 표현해준다
		tabActionBar.setSelectedNavigationItem(position);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
	}

	// ////////////////////////
	// 여기서부터 TabListener 구현 메서드
	// ////////////////////////

	@Override
	public void onTabSelected(Tab tab, android.app.FragmentTransaction ft) {
		// 탭이 선택되었을 때 해당 위치 페이지를 보여줌
		pager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabReselected(Tab arg0, android.app.FragmentTransaction arg1) {
	}

	@Override
	public void onTabUnselected(Tab arg0, android.app.FragmentTransaction arg1) {
	}
}