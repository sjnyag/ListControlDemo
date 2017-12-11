package com.sjn.demo.listcontrolldemo;

public enum DrawerMenu {
    HOME(R.id.navigation_home) {
    },;
    final int mMenuId;

    DrawerMenu(int menuId) {
        mMenuId = menuId;
    }

    public int getMenuId() {
        return mMenuId;
    }

    public static DrawerMenu first() {
        return HOME;
    }

}
